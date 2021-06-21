package com.example.luchkin.MedicineWarehouse.model;

import liquibase.pro.packaged.S;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Preparation {

    private Long preparations_id;
    private String name;
    private Integer price;
    private String country;
    private Integer stock_balance;
    private Double dosage;
    private Date expiration_date;
    private Long category_id;
    private Long manufacturer_id;
    private Long delivery_preparation_id;
}
