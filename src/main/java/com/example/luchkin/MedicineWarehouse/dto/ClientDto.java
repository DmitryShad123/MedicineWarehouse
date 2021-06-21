package com.example.luchkin.MedicineWarehouse.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * DTO Клиент
 */

@Data
@Builder
@Schema(title = "Модель данных клиент")
public class ClientDto {


    @Schema(title = "Идентификатор клиента")
    private Long clientId;

    @Schema(title = "Имя клиента")
    private String firstName;

    @Schema(title = "Фамилия клиента")
    private String lastName;

    @Schema(title = "Отчество клиента")
    private String middleName;

    @Schema(title = "ИНН клиента")
    private Long inn;

    @Schema(title = "Телефон клиента")
    private Long phone;
}
