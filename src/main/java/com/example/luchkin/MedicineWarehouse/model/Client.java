package com.example.luchkin.MedicineWarehouse.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Client {

    private Long client_id;
    private String first_name;
    private String last_name;
    private String middle_name;
    private Long inn;
    private Long phone;
}