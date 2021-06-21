package com.example.luchkin.MedicineWarehouse.controller;


import com.example.luchkin.MedicineWarehouse.dto.*;
import com.example.luchkin.MedicineWarehouse.model.*;
import com.example.luchkin.MedicineWarehouse.service.impl.DeliveryPreparationServiceImpl;
import com.example.luchkin.MedicineWarehouse.service.impl.QuantityDeliveryServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("api/deliverypreparation")
@Tag(name = "deliveryPreparation", description = "АПИ для поставки")
public class DeliveryPreparationController {

    @Autowired
    private DeliveryPreparationServiceImpl deliveryPreparationService;

    @Autowired
    private QuantityDeliveryServiceImpl quantityDeliveryService;


    @GetMapping("/allpreparationindelivery/{id}")
    @Operation(summary = "Получение всех препаратов в поставке")
    public ResponseEntity<List<QuantityDeliveryDto>> allPreparationInDelivery(@PathVariable Long id){
        System.out.println("222");
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        DeliveryPreparation deliveryPreparation = deliveryPreparationService.getDeliveryPreparation(id);
        DeliveryPreparationDto deliveryPreparationDto = deliveryPreparationService.toDeliveryPreparationDto(deliveryPreparation);

        List<QuantityDelivery> quantityDeliveryList = quantityDeliveryService.getAllQuantityDeliveryById(deliveryPreparationDto.getDeliveryPreparationId());

        List<QuantityDeliveryDto> allQuantityDeliveryDto = new LinkedList<>();

        for(int i = 0; i < quantityDeliveryList.size(); i++){
            allQuantityDeliveryDto.add(quantityDeliveryService.toQuantityDto(quantityDeliveryList.get(i)));
        }
        return new ResponseEntity<>(allQuantityDeliveryDto, HttpStatus.OK);
    }

    @PostMapping("/post")
    @Operation(summary = "Добавление поставки")
    public ResponseEntity<DeliveryPreparationDto> insertPreparationInDelivery(@RequestBody DeliveryPreparationDto deliveryPreparationDto){

        if(deliveryPreparationDto.getSupplierId() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(deliveryPreparationService.checkingIdForRepeatability(deliveryPreparationDto) == false){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(deliveryPreparationDto.getDeliveryPreparationId() == null){
            deliveryPreparationService.checkingIdIfNull(deliveryPreparationDto);
        }

        DeliveryPreparation DeliveryPreparation = deliveryPreparationService.toDeliveryPreparation(deliveryPreparationDto);
        deliveryPreparationService.saveDeliveryPreparation(DeliveryPreparation);

        return new ResponseEntity<>(deliveryPreparationDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Удаление определенной поставки")
    public ResponseEntity<DeliveryPreparationDto> deleteDeliveryPreparation(@PathVariable Long id){

        if(id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(deliveryPreparationService.checkingIdForRepeatability(id) == false){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<QuantityDelivery> quantityDeliveryList = quantityDeliveryService.getAllQuantityDeliveryById(id);
        List<QuantityDeliveryDto> quantityDeliveryDtos = new LinkedList<>();

        for(int i = 0; i < quantityDeliveryList.size(); i++){
            quantityDeliveryDtos.add(quantityDeliveryService.toQuantityDto(quantityDeliveryList.get(i)));
        }

        for(int i = 0; i < quantityDeliveryDtos.size(); i++){
            quantityDeliveryService.deleteQuantityDeliveryById(quantityDeliveryDtos.get(i).getQuantityDeliveryId());
        }

        deliveryPreparationService.deleteDeliveryPreparationById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    @Operation(summary = "Обновление определенной поставки")
    public ResponseEntity<DeliveryPreparationDto> updateAddress(@RequestBody DeliveryPreparationDto deliveryPreparationDto){

        if(deliveryPreparationDto.getSupplierId() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(deliveryPreparationDto.getDeliveryPreparationId() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(deliveryPreparationService.checkingIdForRepeatabilityToUpdate(deliveryPreparationDto) == false){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        DeliveryPreparation deliveryPreparation = deliveryPreparationService.toDeliveryPreparation(deliveryPreparationDto);
        deliveryPreparationService.updateDeliveryPreparation(deliveryPreparation);

        return new ResponseEntity<>(deliveryPreparationDto, HttpStatus.OK);
    }
}
