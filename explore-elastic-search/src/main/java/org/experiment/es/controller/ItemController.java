package org.experiment.es.controller;

import org.experiment.es.model.Item;
import org.experiment.es.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/search")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping("searchByKeyword")
    public Page<Item> search(@RequestParam("kw") String kw) {
        return itemService.search(kw);
    }
}