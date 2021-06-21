package com.example.luchkin.MedicineWarehouse.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Category {
    private Long category_id;
    private String name;
}
