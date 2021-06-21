package com.example.luchkin.MedicineWarehouse.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * DTO Адрес
 */
@Data
@Builder
@Schema(title = "Модель данных адрес")
public class AddressDto {

    @Schema(title = "Идентификатор адреса")
    private Long addressId;

    @Schema(title = "Город")
    private String city;

    @Schema(title = "Улица")
    private String street;

    @Schema(title = "Дом")
    private Integer house;
}
