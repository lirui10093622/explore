package org.experiment.es.service;

import org.experiment.es.model.Item;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ItemService {

    public Page<Item> search(String keyword);
}