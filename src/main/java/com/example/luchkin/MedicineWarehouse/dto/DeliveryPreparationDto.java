package com.example.luchkin.MedicineWarehouse.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * DTO Поставка
 */

@Data
@Builder
@Schema(title = "Модель данных поставки")
public class DeliveryPreparationDto {

    @Schema(title = "Идентификатор поставки")
    private Long deliveryPreparationId;

    @Schema(title = "Дата поставки")
    private Date dateDelivery;

    @Schema(title = "Идентификатор поставщика")
    private Long supplierId;

    @Schema(title = "Название поставщика")
    private String name;

    @Schema(title = "ИНН поставщика")
    private Long inn;

    @Schema(title = "БИК поставщика")
    private Long bic;

    @Schema(title = "Телефон поставщика")
    private Long phone;
}
