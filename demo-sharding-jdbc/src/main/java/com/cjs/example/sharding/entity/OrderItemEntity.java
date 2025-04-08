package com.cjs.example.sharding.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "t_order_item")
public class OrderItemEntity implements Serializable {

    @Id
    @Column(name = "order_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    private Long orderId;

    private Long productId;

    private Long resourceId;

    private Long quantity;

    private BigDecimal salePrice;

    private BigDecimal basePrice;
}