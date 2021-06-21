package com.example.luchkin.MedicineWarehouse.service.api;


import com.example.luchkin.MedicineWarehouse.model.QuantityDelivery;

import java.util.List;

/**
 * Сервис для работы с количеством
 */
public interface QuantityDeliveryService {

    List<QuantityDelivery> getAllQuantityDelivery();

    void saveQuantityDelivery(QuantityDelivery quantityDelivery);

    void deleteQuantityDeliveryById(Long id);

    void updateQuantityDelivery(QuantityDelivery quantityDelivery);

    List<QuantityDelivery> getAllQuantityDeliveryById(Long id);
}
