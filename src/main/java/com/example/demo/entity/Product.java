package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Table("product")
public class Product {

    @PrimaryKeyColumn(name = "sku", type = PrimaryKeyType.PARTITIONED)
    private String sku;
    @Column
    private String name;
    @Column
    private String brand;
    @Column
    private String size;
    @Column
    private BigDecimal price;
    @Column
    private String principalImageUrl;
    @Column
    private Set<String> imageUrls;
}
