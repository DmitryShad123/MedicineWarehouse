package com.example.luchkin.MedicineWarehouse.service.api;


import com.example.luchkin.MedicineWarehouse.model.Preparation;
import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы с препаратом
 */
public interface PreparationService {

    List<Preparation> getPreparation(String name);

    Preparation getPreparationByNameByDosage(String name, Double dosage);

    List<Preparation> getAllPreparation();

    void insertPreparation(Preparation preparation);

    void deletePreparationById(Long id);

    void updatePreparation(Preparation preparation);

    List<Preparation> getPreparationsByCategory(Long id);

    Preparation getPreparationById(Long id);
}
