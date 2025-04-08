package com.cjs.example.sharding.service;

import com.cjs.example.sharding.entity.OrderEntity;
import com.cjs.example.sharding.repository.OrderRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ChengJianSheng
 * @date 2020-06-18
 */
@Service
public class OrderService {

    @Resource
    private OrderRepository orderRepository;

    public void save(OrderEntity entity) {
        orderRepository.save(entity);
    }

    public List<OrderEntity> find(Long userId) {
        OrderEntity entity = new OrderEntity();
        entity.setUserId(userId);
        Example<OrderEntity> example = Example.of(entity);
        return orderRepository.findAll(example);
    }

    public List<OrderEntity> findAll() {
        return orderRepository.findAll();
    }
}
