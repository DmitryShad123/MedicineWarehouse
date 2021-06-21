package com.example.luchkin.MedicineWarehouse.controller;


import com.example.luchkin.MedicineWarehouse.dto.PreparationDto;
import com.example.luchkin.MedicineWarehouse.dto.QuantitySurrenderDto;
import com.example.luchkin.MedicineWarehouse.model.Preparation;
import com.example.luchkin.MedicineWarehouse.model.QuantitySurrender;
import com.example.luchkin.MedicineWarehouse.service.impl.PreparationServiceImpl;
import com.example.luchkin.MedicineWarehouse.service.impl.QuantitySurrenderServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/quantitysurrender")
@Tag(name = "quantity", description = "АПИ для колличества отгрузки")
public class QuantitySurrenderController {


    @Autowired
    private QuantitySurrenderServiceImpl quantitySurrenderService;

    @Autowired
    private PreparationServiceImpl preparationService;

    @GetMapping("/all")
    @Operation(summary = "Получениить каждую упаковку")
    public ResponseEntity<List<QuantitySurrenderDto>> getAllQuantitySurrender(){
        List<QuantitySurrender> quantitySurrenderList = quantitySurrenderService.getAllQuantitySurrender();
        List<QuantitySurrenderDto> quantityDtoList = new LinkedList<>();

        for(int i = 0; i < quantitySurrenderList.size(); i++){
            quantityDtoList.add(quantitySurrenderService.toQuantitySurrenderDto(quantitySurrenderList.get(i)));
        }
        return new ResponseEntity<>(quantityDtoList, HttpStatus.OK);
    }

    @PostMapping("/post")
    @Operation(summary = "Добавление упаковки")
    public ResponseEntity<QuantitySurrenderDto> insertQuantitySurrender(@RequestBody QuantitySurrenderDto quantitySurrenderDto){

        Preparation preparation = preparationService.getPreparationById(quantitySurrenderDto.getPreparationsId());
        PreparationDto preparationDto = preparationService.toPreparationDto(preparation);


        if(preparation.getStock_balance() < quantitySurrenderDto.getQuantityPackaging()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(quantitySurrenderService.checkingIdForRepeatability(quantitySurrenderDto) == false){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(quantitySurrenderDto.getQuantitySurrenderId() == null){
            quantitySurrenderService.checkingIdIfNull(quantitySurrenderDto);
        }

        preparationDto.setStockBalance(preparationDto.getStockBalance() - quantitySurrenderDto.getQuantityPackaging());
        preparation = preparationService.toPreparation(preparationDto);

        preparationService.updatePreparation(preparation);

        QuantitySurrender quantity = quantitySurrenderService.toQuantitySurrender(quantitySurrenderDto);
        quantitySurrenderService.saveQuantitySurrender(quantity);

        return new ResponseEntity<>(quantitySurrenderDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Удаление определенной упаковки")
    public ResponseEntity<QuantitySurrenderDto> deletQuantitySurrender(@PathVariable Long id){

        if(id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(quantitySurrenderService.checkingIdForRepeatability(id) == false){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        QuantitySurrender quantitySurrender = quantitySurrenderService.getQuantitySurrenderById(id);
        QuantitySurrenderDto quantitySurrenderDto = quantitySurrenderService.toQuantitySurrenderDto(quantitySurrender);


        Preparation preparation = preparationService.getPreparationById(quantitySurrenderDto.getPreparationsId());
        PreparationDto preparationDto = preparationService.toPreparationDto(preparation);

        preparationDto.setStockBalance(preparationDto.getStockBalance() + quantitySurrenderDto.getQuantityPackaging());

        preparation = preparationService.toPreparation(preparationDto);
        preparationService.updatePreparation(preparation);

        quantitySurrenderService.deleteQuantitySurrenderById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    @Operation(summary = "Обновление упаковки")
    public ResponseEntity<QuantitySurrenderDto> updateQuantitySurrender(@RequestBody QuantitySurrenderDto quantitySurrenderDto){

        QuantitySurrender quantitySurrender = quantitySurrenderService.getQuantitySurrenderById(quantitySurrenderDto.getQuantitySurrenderId());
        QuantitySurrenderDto quantitySurrenderDtoOriginal  = quantitySurrenderService.toQuantitySurrenderDto(quantitySurrender);


        Preparation preparation = preparationService.getPreparationById(quantitySurrenderDtoOriginal.getPreparationsId());
        PreparationDto preparationDto = preparationService.toPreparationDto(preparation);

        if(quantitySurrenderService.checkingIdForRepeatabilityToUpdate(quantitySurrenderDto) == false){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        if(quantitySurrenderDtoOriginal.getPreparationsId() != quantitySurrenderDto.getPreparationsId()){

            preparationDto.setStockBalance((preparationDto.getStockBalance()+quantitySurrenderDtoOriginal.getQuantityPackaging()));
            preparation = preparationService.toPreparation(preparationDto);
            preparationService.updatePreparation(preparation);


            preparation = preparationService.getPreparationById(quantitySurrenderDto.getPreparationsId());
            PreparationDto preparationDtoAfterUpdate = preparationService.toPreparationDto(preparation);

            if(preparationDtoAfterUpdate.getStockBalance() < quantitySurrenderDto.getQuantityPackaging()){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if(quantitySurrenderDto.getQuantitySurrenderId() == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            preparationDtoAfterUpdate.setStockBalance(preparationDtoAfterUpdate.getStockBalance() - quantitySurrenderDto.getQuantityPackaging());

            preparation = preparationService.toPreparation(preparationDtoAfterUpdate);

            preparationService.updatePreparation(preparation);

            QuantitySurrender quantity = quantitySurrenderService.toQuantitySurrender(quantitySurrenderDto);
            quantitySurrenderService.updateQuantitySurrender(quantity);
            return new ResponseEntity<>(quantitySurrenderDto, HttpStatus.OK);
        }

        if(preparationDto.getStockBalance() + quantitySurrenderDtoOriginal.getQuantityPackaging() < quantitySurrenderDto.getQuantityPackaging()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(quantitySurrenderDto.getQuantitySurrenderId() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        preparationDto.setStockBalance(preparationDto.getStockBalance() + quantitySurrenderDtoOriginal.getQuantityPackaging() - quantitySurrenderDto.getQuantityPackaging());
        preparation = preparationService.toPreparation(preparationDto);

        preparationService.updatePreparation(preparation);

        QuantitySurrender quantity = quantitySurrenderService.toQuantitySurrender(quantitySurrenderDto);
        quantitySurrenderService.updateQuantitySurrender(quantity);

        return new ResponseEntity<>(quantitySurrenderDto, HttpStatus.OK);
    }
}
