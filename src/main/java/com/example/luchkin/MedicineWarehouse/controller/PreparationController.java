package com.example.luchkin.MedicineWarehouse.controller;


import com.example.luchkin.MedicineWarehouse.dto.*;
import com.example.luchkin.MedicineWarehouse.model.*;
import com.example.luchkin.MedicineWarehouse.service.impl.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import liquibase.pro.packaged.S;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/preparation")
@Tag(name = "preparation", description = "АПИ для препарата")
public class PreparationController {

    @Autowired
    private PreparationServiceImpl preparationService;

    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private ManufacturerServiceImpl manufacturerService;

    @Autowired
    private DeliveryPreparationServiceImpl deliveryPreparationService;

    @Autowired
    private SupplierServiceImpl supplierService;

    @GetMapping("/all")
    @Operation(summary = "Получение всех препаратов в базе")
    public ResponseEntity<List<PreparationDto>> getAllPreparation(){
        List<Preparation> preparationList = preparationService.getAllPreparation();
        List<PreparationDto> preparationDtos = new LinkedList<>();

        for(int i = 0; i < preparationList.size(); i++){
            preparationDtos.add(preparationService.toPreparationDto(preparationList.get(i)));
        }
        return new ResponseEntity<>(preparationDtos, HttpStatus.OK);
    }

    @GetMapping("/get/{name}")
    @Operation(summary = "Получение определенного препарата по названию")
    public ResponseEntity<List<PreparationDto>> getPreparation(@PathVariable String name){

        if(name == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Preparation> preparations = preparationService.getPreparation(name);
        List<PreparationDto> preparationDtos = new LinkedList<>();

        for(int i = 0; i < preparations.size(); i++){
            preparationDtos.add(preparationService.toPreparationDto(preparations.get(i)));
        }


        return new ResponseEntity<>(preparationDtos, HttpStatus.OK);
    }

    @GetMapping("/getNameAndDos/name/{name}/dosage/{dosage}")
    @Operation(summary = "Получение определенного препарата по названию и дозировке")
    public ResponseEntity<PreparationDto> getPreparation(@PathVariable String name, @PathVariable Double dosage){

        if(name == null || dosage == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Preparation preparation = preparationService.getPreparationByNameByDosage(name, dosage);
        PreparationDto preparationDto = preparationService.toPreparationDto(preparation);

        return new ResponseEntity<>(preparationDto, HttpStatus.OK);
    }

    @GetMapping("/getPreparationByCategory/{name}")
    @Operation(summary = "Получение определенной группы препаратов по категории")
    public ResponseEntity<List<PreparationDto>> getPreparationByCategory(@PathVariable String name){

        if(name == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Category category = categoryService.getCategoryByName(name);
        CategoryDto categoryDto = categoryService.toCategoryDto(category);

        List<Preparation> preparationList = preparationService.getPreparationsByCategory(categoryDto.getCategoryId());
        List<PreparationDto> preparationDtos = new LinkedList<>();

        for(int i = 0; i < preparationList.size(); i++){
            preparationDtos.add(preparationService.toPreparationDto(preparationList.get(i)));
        }

        return new ResponseEntity<>(preparationDtos, HttpStatus.OK);
    }

    @GetMapping("/getManufacturerByPreparation/name/{name}/dosage/{dosage}")
    @Operation(summary = "Получение производителя по названию препарату и дозировке(у каких производителей производится этот препарат)")
    public ResponseEntity<ManufacturerDto> getManufacturerByPreparation(@PathVariable String name, @PathVariable Double dosage){

        if(name == null || dosage == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Preparation preparation = preparationService.getPreparationByNameByDosage(name, dosage);
        PreparationDto preparationDto = preparationService.toPreparationDto(preparation);

        Manufacturer manufacturer = manufacturerService.getManufacturer(preparationDto.getManufacturerId());
        ManufacturerDto manufacturerDto = manufacturerService.toManufacturerDto(manufacturer);

        return new ResponseEntity<>(manufacturerDto, HttpStatus.OK);
    }

    @GetMapping("/getDeliveryPreparationByPreparation/name/{name}/dosage/{dosage}")
    @Operation(summary = "Получение даты привоза и данные поставщика по препарату")
    public ResponseEntity<DeliveryPreparationDto> getDeliveryPreparationByPreparation(@PathVariable String name, @PathVariable Double dosage){

        Preparation preparation = preparationService.getPreparationByNameByDosage(name, dosage);
        PreparationDto preparationDto = preparationService.toPreparationDto(preparation);

        DeliveryPreparation deliveryPreparation = deliveryPreparationService.getDeliveryPreparation(preparationDto.getDeliveryPreparationId());
        DeliveryPreparationDto deliveryPreparationDto = deliveryPreparationService.toDeliveryPreparationDto(deliveryPreparation);

        Supplier supplier = supplierService.getSupplier(deliveryPreparationDto.getSupplierId());
        SupplierDto supplierDto = supplierService.toSupplierDto(supplier);

        deliveryPreparationDto.setName(supplierDto.getName());
        deliveryPreparationDto.setInn(supplierDto.getInn());
        deliveryPreparationDto.setBic(supplierDto.getBic());
        deliveryPreparationDto.setPhone(supplierDto.getPhone());

        return new ResponseEntity<>(deliveryPreparationDto, HttpStatus.OK);
    }

    @PostMapping("/post")
    @Operation(summary = "Добавление препарата")
    public ResponseEntity<PreparationDto> insertPreparation(@RequestBody PreparationDto preparationDto){

        if(preparationService.checkingIdForRepeatability(preparationDto) == false){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(preparationDto.getPreparationsId() == null){
            preparationService.checkingIdIfNull(preparationDto);
        }
        Preparation preparation = preparationService.toPreparation(preparationDto);
        preparationService.insertPreparation(preparation);

        return new ResponseEntity<>(preparationDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Удаление определенного препарата")
    public ResponseEntity<PreparationDto> deletePreparation(@PathVariable Long id){

        if(id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(preparationService.checkingIdForRepeatability(id) == false){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        preparationService.deletePreparationById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    @Operation(summary = "Обновление определенного препарата")
    public ResponseEntity<PreparationDto> updatePreparation(@RequestBody PreparationDto preparationDto){

        if(preparationDto.getPreparationsId() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(preparationService.checkingIdForRepeatabilityToUpdate(preparationDto) == false){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Preparation preparation = preparationService.toPreparation(preparationDto);
        preparationService.updatePreparation(preparation);

        return new ResponseEntity<>(preparationDto, HttpStatus.OK);
    }
}
