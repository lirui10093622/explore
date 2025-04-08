package com.cjs.example.sharding.repository;

import com.cjs.example.sharding.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ChengJianSheng
 * @date 2020-06-18
 */
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}