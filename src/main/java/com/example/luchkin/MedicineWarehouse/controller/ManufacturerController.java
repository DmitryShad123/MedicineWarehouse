package com.example.luchkin.MedicineWarehouse.controller;


import com.example.luchkin.MedicineWarehouse.dto.AddressDto;
import com.example.luchkin.MedicineWarehouse.dto.ManufacturerDto;
import com.example.luchkin.MedicineWarehouse.model.Address;
import com.example.luchkin.MedicineWarehouse.model.Manufacturer;
import com.example.luchkin.MedicineWarehouse.service.impl.AddressServiceImpl;
import com.example.luchkin.MedicineWarehouse.service.impl.ManufacturerServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manufacturer")
@Tag(name = "manufacturer", description = "АПИ для производителя")
public class ManufacturerController {

    @Autowired
    private ManufacturerServiceImpl manufacturerService;

    @Autowired
    private AddressServiceImpl addressService;

    @GetMapping("/all")
    @Operation(summary = "Получение всех производителей")
    public ResponseEntity<List<Manufacturer>> getAllManufacturer(){
        List<Manufacturer> manufacturerList = manufacturerService.getAllManufacturer();
        return new ResponseEntity<>(manufacturerList, HttpStatus.OK);
    }

    @PostMapping("/post")
    @Operation(summary = "Добавление производителя. Есть возможность добавлять производителя с адресом из БД, либо добавлять" +
            "сразу с производителем новый адрес.")
    public ResponseEntity<ManufacturerDto> insertManufacturer(@RequestBody ManufacturerDto manufacturerDto){

        if(manufacturerService.checkingIdForRepeatability(manufacturerDto) == false){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(manufacturerDto.getManufacturerId() == null){
            manufacturerService.checkingIdIfNull(manufacturerDto);
        }

        if(manufacturerDto.getAddressLegalId() != null && manufacturerDto.getAddressActualId() != null){
            Manufacturer manufacturer = manufacturerService.toManufacturer(manufacturerDto);
            manufacturerService.insertManufacturer(manufacturer);
            return new ResponseEntity<>(manufacturerDto, HttpStatus.OK);
        }

        Address addressLegal = addressService.toAddressLegalFromManufacturer(manufacturerDto);
        Address addressActual = addressService.toAddressActualFromManufacturer(manufacturerDto);

        addressService.checkingAddress(addressLegal, addressActual);

        Manufacturer manufacturer = manufacturerService.toManufacturerIfLegalAndActualNull(manufacturerDto, addressLegal, addressActual);

        manufacturerService.insertManufacturer(manufacturer);
        return new ResponseEntity<>(manufacturerDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Удаление определенного производителя")
    public ResponseEntity<ManufacturerDto> deliteManufacturer(@PathVariable Long id){

        if(id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(manufacturerService.checkingIdForRepeatability(id) == false){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        manufacturerService.deleteManufacturerById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    @Operation(summary = "Обновление определенного производителя")
    public ResponseEntity<ManufacturerDto> updateAddress(@RequestBody ManufacturerDto manufacturerDto){

        if(manufacturerDto.getManufacturerId() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(manufacturerService.checkingIdForRepeatabilityToUpdate(manufacturerDto) == false){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(manufacturerDto.getAddressLegalId() != null && manufacturerDto.getAddressActualId() != null){
            Manufacturer manufacturer = manufacturerService.toManufacturer(manufacturerDto);
            manufacturerService.updateManufacturer(manufacturer);
            return new ResponseEntity<>(manufacturerDto, HttpStatus.OK);
        }

        Address addressLegal = addressService.toAddressLegalFromManufacturer(manufacturerDto);
        Address addressActual = addressService.toAddressActualFromManufacturer(manufacturerDto);

        addressService.checkingAddress(addressLegal, addressActual);

        Manufacturer manufacturer = manufacturerService.toManufacturerIfLegalAndActualNull(manufacturerDto, addressLegal, addressActual);

        manufacturerService.updateManufacturer(manufacturer);

        return new ResponseEntity<>(manufacturerDto, HttpStatus.OK);
    }
}
