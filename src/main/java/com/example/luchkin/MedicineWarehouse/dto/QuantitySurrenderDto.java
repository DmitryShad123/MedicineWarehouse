package com.example.luchkin.MedicineWarehouse.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * DTO Колличество для отгрузки
 */

@Data
@Builder
@Schema(title = "Модель данных для колличество для отгрузки")
public class QuantitySurrenderDto {


    @Schema(title = "Идентификатор колличество для отгрузки")
    private Long quantitySurrenderId;

    @Schema(title = "Колличество упаковок")
    private Integer quantityPackaging;

    @Schema(title = "Идентификатор препарата")
    private Long preparationsId;

    @Schema(title = "Идентификатор отгрузки")
    private Long surrenderDeliveryId;
}
