package com.example.luchkin.MedicineWarehouse.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * DTO Колличество для привоза
 */

@Data
@Builder
@Schema(title = "Модель данных для колличество для привоза")
public class QuantityDeliveryDto {

    @Schema(title = "Идентификатор колличество для привоза")
    private Long quantityDeliveryId;

    @Schema(title = "Имя препарата")
    private String name;

    @Schema(title = "Колличество упаковок")
    private Integer quantityPackaging;

    @Schema(title = "Идентификатор поставки")
    private Long deliveryPreparationId;
}
