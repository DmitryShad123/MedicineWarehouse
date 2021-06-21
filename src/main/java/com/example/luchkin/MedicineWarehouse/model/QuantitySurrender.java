package com.example.luchkin.MedicineWarehouse.model;


import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class QuantitySurrender {

    private Long quantity_surrender_id;
    private Integer quantity_packaging;
    private Long preparations_id;
    private Long surrender_delivery_id;
}
