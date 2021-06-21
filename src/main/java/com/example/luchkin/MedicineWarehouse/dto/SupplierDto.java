package com.example.luchkin.MedicineWarehouse.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;


/**
 * DTO Поставщик
 */

@Data
@Builder
@Schema(title = "Модель данных поставщик")
public class SupplierDto {

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

    @Schema(title = "Идентификатор юридического адреса")
    private Long addressLegalId;

    @Schema(title = "Идентификатор фактического адреса")
    private Long addressActualId;

    @Schema(title = "Город юридический адрес")
    private String cityLegal;

    @Schema(title = "Улица юридический адрес")
    private String streetLegal;

    @Schema(title = "Дом юридический адрес")
    private Integer houseLegal;

    @Schema(title = "Город фактический адрес")
    private String cityActual;

    @Schema(title = "Улица фактический адрес")
    private String streetActual;

    @Schema(title = "Дом фактический адрес")
    private Integer houseActual;
}
