package com.example.luchkin.MedicineWarehouse.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * DTO Категория
 */
@Data
@Builder
@Schema(title = "Модель данных категория")
public class CategoryDto {

    @Schema(title = "Идентификатор категории")
    private Long categoryId;

    @Schema(title = "Наименование категории")
    private String name;
}
