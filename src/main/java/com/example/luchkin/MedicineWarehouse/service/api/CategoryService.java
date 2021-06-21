package com.example.luchkin.MedicineWarehouse.service.api;

import com.example.luchkin.MedicineWarehouse.model.Category;
import com.example.luchkin.MedicineWarehouse.model.Client;
import com.example.luchkin.MedicineWarehouse.model.Preparation;
import liquibase.pro.packaged.S;

import java.util.List;

/**
 * Сервис для работы с категорией
 */
public interface CategoryService {

    List<Category> getAllCategory();

    void insertCategory(Category category);

    void deleteClientById(Long id);

    void updateClient(Category category);

    Category getCategoryByName(String name);

}
