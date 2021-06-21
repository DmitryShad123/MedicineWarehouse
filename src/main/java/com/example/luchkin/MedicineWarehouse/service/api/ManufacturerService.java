package com.example.luchkin.MedicineWarehouse.service.api;

import com.example.luchkin.MedicineWarehouse.model.Manufacturer;

import java.util.List;

/**
 * Сервис для работы с производителем
 */

public interface ManufacturerService {

    List<Manufacturer> getAllManufacturer();

    void insertManufacturer(Manufacturer manufacturer);

    void deleteManufacturerById(Long id);

    void updateManufacturer(Manufacturer manufacturer);

    Manufacturer getManufacturer(Long id);
}
