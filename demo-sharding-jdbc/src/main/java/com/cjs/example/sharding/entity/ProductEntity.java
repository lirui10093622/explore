package com.cjs.example.sharding.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "t_product")
public class ProductEntity implements Serializable {

    @Id
    @Column(name = "voucher_id")
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long productId;

    private String productName;

    private Integer status;

    private String supplier;
}