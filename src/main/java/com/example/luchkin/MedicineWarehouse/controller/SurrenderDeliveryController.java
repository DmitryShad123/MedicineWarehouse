package com.example.luchkin.MedicineWarehouse.controller;


import com.example.luchkin.MedicineWarehouse.dto.AddressDto;
import com.example.luchkin.MedicineWarehouse.dto.PreparationDto;
import com.example.luchkin.MedicineWarehouse.dto.QuantitySurrenderDto;
import com.example.luchkin.MedicineWarehouse.dto.SurrenderDeliveryDto;
import com.example.luchkin.MedicineWarehouse.model.Address;
import com.example.luchkin.MedicineWarehouse.model.Preparation;
import com.example.luchkin.MedicineWarehouse.model.QuantitySurrender;
import com.example.luchkin.MedicineWarehouse.model.SurrenderDelivery;
import com.example.luchkin.MedicineWarehouse.service.impl.AddressServiceImpl;
import com.example.luchkin.MedicineWarehouse.service.impl.PreparationServiceImpl;
import com.example.luchkin.MedicineWarehouse.service.impl.QuantitySurrenderServiceImpl;
import com.example.luchkin.MedicineWarehouse.service.impl.SurrenderDeliveryServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/surrenderdelivery")
@Tag(name = "surrenderdelivery", description = "АПИ для отгрузки")
public class SurrenderDeliveryController {

    @Autowired
    private SurrenderDeliveryServiceImpl surrenderDeliveryService;

    @Autowired
    private QuantitySurrenderServiceImpl quantitySurrenderService;

    @Autowired
    private PreparationServiceImpl preparationService;

    @GetMapping("/all")
    @Operation(summary = "Получение всех отгрузок в базе")
    public ResponseEntity<List<SurrenderDeliveryDto>> getAllSurrenderDelivery(){
        List<SurrenderDelivery> surrenderDeliveryList = surrenderDeliveryService.getAllSurrenderDelivery();
        List<SurrenderDeliveryDto> allsurrenderDeliveryDto = new LinkedList<>();

        for(int i = 0; i < surrenderDeliveryList.size(); i++){
            allsurrenderDeliveryDto.add(surrenderDeliveryService.toSurrenderDeliveryDto(surrenderDeliveryList.get(i)));
        }
        return new ResponseEntity<>(allsurrenderDeliveryDto, HttpStatus.OK);
    }

    @PostMapping("/post")
    @Operation(summary = "Добавление отгрузки")
    public ResponseEntity<SurrenderDeliveryDto> insertSurrenderDelivery(@RequestBody SurrenderDeliveryDto surrenderDeliveryDto){

        if(surrenderDeliveryService.checkingIdForRepeatability(surrenderDeliveryDto) == false){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(surrenderDeliveryDto.getSurrenderDeliveryId() == null){
            surrenderDeliveryService.checkingIdIfNull(surrenderDeliveryDto);
        }

        SurrenderDelivery surrenderDelivery = surrenderDeliveryService.toSurrenderDelivery(surrenderDeliveryDto);
        surrenderDeliveryService.saveSurrenderDelivery(surrenderDelivery);

        return new ResponseEntity<>(surrenderDeliveryDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Удаление определенной отгрузки(удаляются так же данные из колличества отгрузки, и обновляется остаток на складе в таблице препарат)")
    public ResponseEntity<AddressDto> deleteSurrenderDelivery(@PathVariable Long id){

        if(id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(surrenderDeliveryService.checkingIdForRepeatability(id) == false){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<QuantitySurrender> quantitySurrenderList = quantitySurrenderService.getQuantitySurrenderBySurrenderDeliveryId(id);
        List<QuantitySurrenderDto> quantitySurrenderDtos = new LinkedList<>();

        for(int i = 0; i < quantitySurrenderList.size(); i++){
            quantitySurrenderDtos.add(quantitySurrenderService.toQuantitySurrenderDto(quantitySurrenderList.get(i)));
        }


        for(int i = 0; i < quantitySurrenderList.size(); i++){
            Preparation preparation = preparationService.getPreparationById(quantitySurrenderDtos.get(i).getPreparationsId());
            PreparationDto preparationDto = preparationService.toPreparationDto(preparation);

            preparationDto.setStockBalance(preparationDto.getStockBalance() + quantitySurrenderDtos.get(i).getQuantityPackaging());
            preparation = preparationService.toPreparation(preparationDto);
            preparationService.updatePreparation(preparation);

            quantitySurrenderService.deleteQuantitySurrenderById(quantitySurrenderDtos.get(i).getQuantitySurrenderId());
        }

        surrenderDeliveryService.deleteSurrenderDeliveryById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    @Operation(summary = "Обновление определенной отгрузки")
    public ResponseEntity<SurrenderDeliveryDto> updateSurrenderDelivery(@RequestBody SurrenderDeliveryDto surrenderDeliveryDto){

        if(surrenderDeliveryDto.getSurrenderDeliveryId() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(surrenderDeliveryService.checkingIdForRepeatabilityToUpdate(surrenderDeliveryDto) == false){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        SurrenderDelivery surrenderDelivery = surrenderDeliveryService.toSurrenderDelivery(surrenderDeliveryDto);
        surrenderDeliveryService.updateSurrenderDelivery(surrenderDelivery);

        return new ResponseEntity<>(surrenderDeliveryDto, HttpStatus.OK);
    }
}
