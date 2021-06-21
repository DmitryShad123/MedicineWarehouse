package com.example.luchkin.MedicineWarehouse.controller;


import com.example.luchkin.MedicineWarehouse.dto.AddressDto;
import com.example.luchkin.MedicineWarehouse.dto.CategoryDto;
import com.example.luchkin.MedicineWarehouse.model.Address;
import com.example.luchkin.MedicineWarehouse.model.Category;
import com.example.luchkin.MedicineWarehouse.service.impl.AddressServiceImpl;
import com.example.luchkin.MedicineWarehouse.service.impl.CategoryServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import liquibase.pro.packaged.S;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/category")
@Tag(name = "category", description = "АПИ для категории")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping("/all")
    @Operation(summary = "Получение каждую категорию")
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        List<Category> categoryList = categoryService.getAllCategory();
        List<CategoryDto> categoryDtoList = new LinkedList<>();

        for(int i = 0; i < categoryList.size(); i++){
            categoryDtoList.add(categoryService.toCategoryDto(categoryList.get(i)));
        }
        return new ResponseEntity<>(categoryDtoList, HttpStatus.OK);
    }

    @PostMapping("/post")
    @Operation(summary = "Добавление категории")
    public ResponseEntity<CategoryDto> insertCategory(@RequestBody CategoryDto categoryDto){

        if(categoryService.checkingIdForRepeatability(categoryDto) == false){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(categoryDto.getCategoryId() == null){
            categoryService.checkingIdIfNull(categoryDto);
        }
        Category category = categoryService.toCategory(categoryDto);
        categoryService.insertCategory(category);

        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Удаление определенной категории")
    public ResponseEntity<CategoryDto> deletCategory(@PathVariable Long id){

        if(id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(categoryService.checkingIdForRepeatability(id) == false){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        categoryService.deleteClientById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    @Operation(summary = "Обновление определенного адреса")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto){

        if(categoryDto.getCategoryId() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(categoryService.checkingIdForRepeatabilityToUpdate(categoryDto) == false){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Category category = categoryService.toCategory(categoryDto);
        categoryService.updateClient(category);

        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }
}
