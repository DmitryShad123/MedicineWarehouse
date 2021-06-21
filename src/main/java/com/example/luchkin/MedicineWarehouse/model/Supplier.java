package com.example.luchkin.MedicineWarehouse.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Supplier {

    private Long supplier_id;
    private String name;
    private Long inn;
    private Long bic;
    private Long phone;
    private Long address_legal_id;
    private Long address_actual_id;
}
