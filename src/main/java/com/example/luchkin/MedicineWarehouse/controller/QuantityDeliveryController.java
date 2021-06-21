package com.example.luchkin.MedicineWarehouse.controller;


import com.example.luchkin.MedicineWarehouse.dto.QuantityDeliveryDto;
import com.example.luchkin.MedicineWarehouse.model.QuantityDelivery;
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
@RequestMapping("/api/quantitydelivery")
@Tag(name = "quantity", description = "АПИ для колличества привоза")
public class QuantityDeliveryController {

    @Autowired
    private QuantityDeliveryServiceImpl quantityService;

    @GetMapping("/all")
    @Operation(summary = "Получить каждую упаковку")
    public ResponseEntity<List<QuantityDeliveryDto>> getAllQuantityDelivery(){
        List<QuantityDelivery> quantityList = quantityService.getAllQuantityDelivery();
        List<QuantityDeliveryDto> quantityDtoList = new LinkedList<>();

        for(int i = 0; i < quantityList.size(); i++){
            quantityDtoList.add(quantityService.toQuantityDto(quantityList.get(i)));
        }
        return new ResponseEntity<>(quantityDtoList, HttpStatus.OK);
    }
    /**
     * В данном случае нет реализации добавления сразу в бд колличества, как в случае с отгрузкой т.к по идеи для того, чтобы добавить на склад
     * нужно сначала проверить правильное ли колличество привезли
     */
    @PostMapping("/post")
    @Operation(summary = "Добавление упаковки")
    public ResponseEntity<QuantityDeliveryDto> insertQuantityDelivery(@RequestBody QuantityDeliveryDto quantityDto){

        if(quantityService.checkingIdForRepeatability(quantityDto) == false){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(quantityDto.getQuantityDeliveryId() == null){
            quantityService.checkingIdIfNull(quantityDto);
        }

        QuantityDelivery quantity = quantityService.toQuantity(quantityDto);
        quantityService.saveQuantityDelivery(quantity);

        return new ResponseEntity<>(quantityDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Удаление определенной упаковки")
    public ResponseEntity<QuantityDeliveryDto> deletQuantityDelivery(@PathVariable Long id){

        if(id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(quantityService.checkingIdForRepeatability(id) == false){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        quantityService.deleteQuantityDeliveryById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    @Operation(summary = "Обновление упаковки")
    public ResponseEntity<QuantityDeliveryDto> updateQuantityDelivery(@RequestBody QuantityDeliveryDto quantityDto){

        if(quantityDto.getQuantityDeliveryId() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(quantityService.checkingIdForRepeatabilityToUpdate(quantityDto) == false){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        QuantityDelivery quantity = quantityService.toQuantity(quantityDto);
        quantityService.updateQuantityDelivery(quantity);

        return new ResponseEntity<>(quantityDto, HttpStatus.OK);
    }
}
