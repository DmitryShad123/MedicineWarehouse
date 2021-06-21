package com.example.luchkin.MedicineWarehouse.model;


import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class DeliveryPreparation {

    private Long delivery_preparation_id;
    private Date date_delivery;
    private Long supplier_id;
}
