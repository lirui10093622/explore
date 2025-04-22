package org.experiment.es.service.impl;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.experiment.es.model.Item;
import org.experiment.es.repository.ItemRepository;
import org.experiment.es.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Page<Item> search(String keyword) {
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //添加基本查询条件
        queryBuilder.withQuery(QueryBuilders.matchQuery("category", "手机"));
        // 排序
        queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));
        // 搜索，获取结果
        Page<Item> items = this.itemRepository.search(queryBuilder.build());
        return items;
    }
}
