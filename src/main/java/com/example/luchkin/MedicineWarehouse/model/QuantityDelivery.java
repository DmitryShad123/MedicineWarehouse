package com.example.luchkin.MedicineWarehouse.model;


import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class QuantityDelivery {

    private Long quantity_delivery_id;
    private String name;
    private Integer quantity_packaging;
    private Long delivery_preparation_id;

}
