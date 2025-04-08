package org.experiment.es;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.metrics.avg.InternalAvg;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author neil
 * @time 2021-07-27 12:03:24
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticSearchApplication.class)
public class ElasticSearchApplicationTest extends TestCase {

  @Autowired
  private ElasticsearchTemplate elasticsearchTemplate;

  @Autowired
  private ItemRepository itemRepository;

  @Test
  public void testCreateIndex() {
    boolean succeed = elasticsearchTemplate.createIndex(Item.class);
    log.info("索引创建结果: {}", succeed);
  }

  @Test
  public void testDeleteIndex() {
    boolean succeed = elasticsearchTemplate.deleteIndex(Item.class);
    log.info("索引删除结果: {}", succeed);
  }

  @Test
  public void testSave() {
    Item item = new Item(1L, "小米9", " 手机", "小米", 2999.00, "http://image.baidu.com/13123.jpg");
    Item result = itemRepository.save(item);
    log.info("数据保存结果: {}", result);
  }

  @Test
  public void testSaveList() {
    List<Item> list = new ArrayList<>();
    list.add(new Item(2L, "华为Mate20", " 手机", "华为", 3699.00, "http://image.baidu.com/13123.jpg"));
    list.add(new Item(3L, "苹果X", " 手机", "苹果", 8999.00, "http://image.baidu.com/13123.jpg"));
    list.add(new Item(4L, "苹果4", " 手机", "苹果", 5999.00, "http://image.baidu.com/13123.jpg"));
    Iterable<Item> result = itemRepository.saveAll(list);
    log.info("数据批量保存结果: {}", result);
  }

  @Test
  public void update() {
    Item item = new Item(1L, "小米笔记本", " 电脑", "小米", 5499.00, "http://image.baidu.com/13123.jpg");
    Item result = itemRepository.save(item);
    log.info("数据更新结果: {}", result);
  }

  @Test
  public void delete() {
    Item item = new Item(4L, "苹果4", " 手机", "苹果", 5999.00, "http://image.baidu.com/13123.jpg");
    itemRepository.delete(item);
    log.info("数据删除完成");
  }

  @Test
  public void query() {
    Iterable<Item> list = this.itemRepository.findAll(Sort.by("price").ascending());
    for (Item item : list) {
      log.info("基本查询结果: {}", item);
    }
  }

  @Test
  public void testQuery() {
    NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
    queryBuilder.withQuery(QueryBuilders.matchQuery("title", "小米"));
    Page<Item> page = itemRepository.search(queryBuilder.build());
    long totalElements = page.getTotalElements();
    System.out.println("获取的总条数：" + totalElements);
    for (Item item : page) {
      log.info("自定义查询结果: {}", item);
    }
  }

  @Test
  public void testFuzzyQuery() {
    NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
    builder.withQuery(QueryBuilders.fuzzyQuery("title", "Mate"));
    Page<Item> page = this.itemRepository.search(builder.build());
    for (Item item : page) {
      log.info("模糊查询结果: {}", item);
    }
  }

  @Test
  public void queryByPage() {
    // 构建查询条件
    NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
    // 添加基本分词查询
    queryBuilder.withQuery(QueryBuilders.matchQuery("category", "手机"));
    // 分页：
    int pageNum = 0;
    int pageSize = 5;
    queryBuilder.withPageable(PageRequest.of(pageNum, pageSize));
    // 搜索，获取结果
    Page<Item> page = itemRepository.search(queryBuilder.build());
    // 总条数
    long total = page.getTotalElements();
    System.out.println("总条数 = " + total);
    // 总页数
    System.out.println("总页数 = " + page.getTotalPages());
    // 当前页
    System.out.println("当前页：" + page.getNumber());
    // 每页大小
    System.out.println("每页大小：" + page.getSize());

    for (Item item : page) {
      System.out.println(item);
    }
  }

  @Test
  public void queryWithSort() {
    // 构建查询条件
    NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
    //添加基本查询条件
    queryBuilder.withQuery(QueryBuilders.matchQuery("category", "手机"));
    // 排序
    queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));
    // 搜索，获取结果
    Page<Item> items = this.itemRepository.search(queryBuilder.build());
    // 总条数
    long total = items.getTotalElements();
    System.out.println("总条数 = " + total);
    for (Item item : items) {
      System.out.println(item);
    }
  }

  @Test
  public void testAgg() {
    // 构建查询条件
    NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
    // 无查询关键词
    queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{""}, null));
    // 1、添加一个新的聚合，聚合类型为terms，聚合名称为brands，聚合字段为brand
    queryBuilder.addAggregation(AggregationBuilders.terms("brands").field("brand"));
    // 2、查询,需要把结果强转为AggregatedPage类型
    AggregatedPage<Item> aggPage = (AggregatedPage<Item>) this.itemRepository.search(queryBuilder.build());
    // 3、解析
    // 3.1、从结果中取出名为brands的那个聚合，
    // 因为是利用String类型字段来进行的term聚合，所以结果要强转为StringTerm类型
    StringTerms agg = (StringTerms) aggPage.getAggregation("brands");
    // 3.2、获取桶
    List<StringTerms.Bucket> buckets = agg.getBuckets();
    // 3.3、遍历
    for (StringTerms.Bucket bucket : buckets) {
      // 3.4、获取桶中的key（品牌名称）
      System.out.println(bucket.getKeyAsString());
      // 3.5、获取桶中的文档数量
      System.out.println(bucket.getDocCount());
    }
  }

  @Test
  public void testSubAgg() {
    // 构建查询条件
    NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
    // 无查询关键词
    queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{""}, null));
    // 1    添加一个新的聚合，聚合类型为terms，聚合名称为brands，聚合字段为brand
    // 在品牌聚合桶内进行嵌套聚合，求平均值
    queryBuilder.addAggregation(AggregationBuilders.terms("brands").field("brand")
        .subAggregation(AggregationBuilders.avg("priceAvg").field("price")));
    // 2    查询,需要把结果强转为AggregatedPage类型
    AggregatedPage<Item> aggPage = (AggregatedPage<Item>) this.itemRepository.search(queryBuilder.build());
    // 3    解析
    // 3.1  从结果中取出名为brands的那个聚合，
    // 因为是利用String类型字段来进行的term聚合，所以结果要强转为StringTerm类型
    StringTerms agg = (StringTerms) aggPage.getAggregation("brands");
    // 3.2  获取桶
    List<StringTerms.Bucket> buckets = agg.getBuckets();
    // 3.3  遍历
    for (StringTerms.Bucket bucket : buckets) {
      // 3.4  获取桶中的key，即品牌名称 和桶中的文档数量
      System.out.println(bucket.getKeyAsString() + "，共" + bucket.getDocCount() + "台");
      // 3.5  获取子聚合结果：
      InternalAvg avg = (InternalAvg) bucket.getAggregations().asMap().get("priceAvg");
      System.out.println("平均售价：" + avg.getValue());
    }
  }
}