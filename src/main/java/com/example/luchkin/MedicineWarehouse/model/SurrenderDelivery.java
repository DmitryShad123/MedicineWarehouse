package com.example.luchkin.MedicineWarehouse.model;


import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class SurrenderDelivery {

    private Long surrender_delivery_id;
    private Date date_surrender_delivery;
    private Long client_id;

}
