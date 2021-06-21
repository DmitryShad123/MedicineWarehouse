package com.example.luchkin.MedicineWarehouse.service.api;


import com.example.luchkin.MedicineWarehouse.model.QuantitySurrender;

import java.util.List;

/**
 * Сервис для работы с колличество для отгрузки
 */
public interface QuantitySurrenderService {

    List<QuantitySurrender> getAllQuantitySurrender();

    QuantitySurrender getQuantitySurrenderById(Long id);

    void saveQuantitySurrender(QuantitySurrender quantitySurrender);

    void deleteQuantitySurrenderById(Long id);

    void updateQuantitySurrender(QuantitySurrender quantitySurrender);

    List<QuantitySurrender> getQuantitySurrenderBySurrenderDeliveryId(Long id);
}
