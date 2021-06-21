package com.example.luchkin.MedicineWarehouse.service.api;

import com.example.luchkin.MedicineWarehouse.model.Manufacturer;
import com.example.luchkin.MedicineWarehouse.model.Supplier;
import liquibase.pro.packaged.S;

import java.util.List;

/**
 * Сервис для работы с поставщиков для поставки
 */

public interface SupplierService {

    List<Supplier> getAllSupplier();

    void insertSupplier(Supplier supplier);

    void updateSupplier(Supplier supplier);

    void deleteSupplierById(Long id);

    Supplier getSupplier(Long id);
}
