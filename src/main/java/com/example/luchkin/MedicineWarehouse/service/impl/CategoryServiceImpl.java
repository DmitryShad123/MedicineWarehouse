package com.example.luchkin.MedicineWarehouse.service.impl;

import com.example.luchkin.MedicineWarehouse.dto.AddressDto;
import com.example.luchkin.MedicineWarehouse.dto.CategoryDto;
import com.example.luchkin.MedicineWarehouse.mapper.CategoryMapper;
import com.example.luchkin.MedicineWarehouse.model.Address;
import com.example.luchkin.MedicineWarehouse.model.Category;
import com.example.luchkin.MedicineWarehouse.model.Client;
import com.example.luchkin.MedicineWarehouse.model.Preparation;
import com.example.luchkin.MedicineWarehouse.service.api.CategoryService;
import liquibase.pro.packaged.S;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Реализация сервиса по работе с категорией
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> getAllCategory() {
        List<Category> categoryList = categoryMapper.getAllCategory();
        return categoryList;
    }

    @Override
    public void insertCategory(Category category) {
        categoryMapper.insertCategory(category);
    }

    @Override
    public void deleteClientById(Long id) {
        categoryMapper.deleteClientById(id);
    }

    @Override
    public void updateClient(Category category) {
        categoryMapper.updateClient(category);
    }

    @Override
    public Category getCategoryByName(String name) {
        Category category = categoryMapper.getCategoryByName(name);
        return category;
    }


    public Category toCategory(CategoryDto categoryDto){
        return Category.builder().category_id(categoryDto.getCategoryId())
                .name(categoryDto.getName())
                .build();
    }

    public CategoryDto toCategoryDto(Category category){
        return CategoryDto.builder().categoryId(category.getCategory_id())
                .name(category.getName())
                .build();
    }

    public boolean checkingIdForRepeatability(CategoryDto categoryDto){

        List<Category> categoryList = getAllCategory();
        for(int i = 0; i < categoryList.size(); i++){
            if(categoryList.get(i).getCategory_id() == categoryDto.getCategoryId()){
                return false;
            }
        }
        return true;
    }

    public void checkingIdIfNull(CategoryDto categoryDto){
        List<Category> categoryList = getAllCategory();

        long maxValue = 0;
        for (int i = 0; i < categoryList.size(); i++){
            if(categoryList.get(i).getCategory_id() > maxValue){
                maxValue = categoryList.get(i).getCategory_id();
            }
        }

        categoryDto.setCategoryId(maxValue+1);
    }

    public boolean checkingIdForRepeatability(Long id){

        List<Category> categoryList = getAllCategory();
        for(int i = 0; i < categoryList.size(); i++){
            if(categoryList.get(i).getCategory_id() == id){
                return true;
            }
        }
        return false;
    }

    public boolean checkingIdForRepeatabilityToUpdate(CategoryDto categoryDto){

        List<Category> categoryList = getAllCategory();
        for(int i = 0; i < categoryList.size(); i++){
            if(categoryList.get(i).getCategory_id() == categoryDto.getCategoryId()){
                return true;
            }
        }
        return false;
    }
}
