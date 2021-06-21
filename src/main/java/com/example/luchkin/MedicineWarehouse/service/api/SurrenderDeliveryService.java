package com.example.luchkin.MedicineWarehouse.service.api;


import com.example.luchkin.MedicineWarehouse.model.QuantitySurrender;
import com.example.luchkin.MedicineWarehouse.model.SurrenderDelivery;

import java.util.List;

/**
 * Сервис для работы с отгрузкой
 */

public interface SurrenderDeliveryService {

    List<SurrenderDelivery> getAllSurrenderDelivery();

    SurrenderDelivery getSurrenderDelivery(Long id);

    void saveSurrenderDelivery(SurrenderDelivery surrenderDelivery);

    void deleteSurrenderDeliveryById(Long id);

    void updateSurrenderDelivery(SurrenderDelivery surrenderDelivery);

}
