package com.example.luchkin.MedicineWarehouse.service.api;

import com.example.luchkin.MedicineWarehouse.model.DeliveryPreparation;

import java.util.List;

/**
 * Сервис для работы с поставкой
 */
public interface DeliveryPreparationService {

    List<DeliveryPreparation> getAllDeliveryPreparation();

    void saveDeliveryPreparation(DeliveryPreparation deliveryPreparation);

    void deleteDeliveryPreparationById(Long id);

    void updateDeliveryPreparation(DeliveryPreparation deliveryPreparation);

    DeliveryPreparation getDeliveryPreparation(Long id);
}
