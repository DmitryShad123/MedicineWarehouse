package com.example.luchkin.MedicineWarehouse.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * DTO Отгрузки
 */

@Data
@Builder
@Schema(title = "Модель данных отгрузки товара")
public class SurrenderDeliveryDto {

    @Schema(title = "Идентификатор отгрузки")
    private Long surrenderDeliveryId;

    @Schema(title = "Дата отгрузки")
    private Date dateSurrenderDelivery;

    @Schema(title = "Идентификатор клиента")
    private Long clientId;
}
