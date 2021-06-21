package com.example.luchkin.MedicineWarehouse.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * DTO Препарат
 */

@Data
@Builder
@Schema(title = "Модель данных для препарата")
public class PreparationDto {

    @Schema(title = "Идентификатор препарата")
    private Long preparationsId;

    @Schema(title = "Название препарата")
    private String name;

    @Schema(title = "Цена препарата")
    private Integer price;

    @Schema(title = "Страна производства препарата")
    private String country;

    @Schema(title = "Остаток на складе")
    private Integer stockBalance;

    @Schema(title = "Дозировка")
    private Double dosage;

    @Schema(title = "Срок годности")
    private Date expirationDate;

    @Schema(title = "Идентификатор категории")
    private Long categoryId;

    @Schema(title = "Идентификатор производителя")
    private Long manufacturerId;

    @Schema(title = "Идентификатор поставки")
    private Long deliveryPreparationId;
}
