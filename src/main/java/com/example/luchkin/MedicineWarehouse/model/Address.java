package com.example.luchkin.MedicineWarehouse.model;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Address {
    private Long address_id;
    private String city;
    private String street;
    private Integer house;
}