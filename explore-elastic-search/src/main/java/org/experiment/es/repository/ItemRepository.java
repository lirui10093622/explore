package org.experiment.es.repository;

import org.experiment.es.model.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author neil
 * @time 2021-07-27 12:23:10
 */
public interface ItemRepository extends ElasticsearchRepository<Item, Long> {

}
