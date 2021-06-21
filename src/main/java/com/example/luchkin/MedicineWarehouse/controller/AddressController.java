package com.example.luchkin.MedicineWarehouse.controller;

import com.example.luchkin.MedicineWarehouse.dto.AddressDto;
import com.example.luchkin.MedicineWarehouse.model.Address;
import com.example.luchkin.MedicineWarehouse.service.impl.AddressServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;


@RestController
@RequestMapping("/api/address")
@Tag(name = "address", description = "АПИ для адреса")
public class AddressController {

    @Autowired
    private AddressServiceImpl addressService;

    @GetMapping("/all")
    @Operation(summary = "Получение всех адресов в базе")
    public ResponseEntity<List<AddressDto>> getAllAddress(){
        List<Address> addressList = addressService.getAllAddress();
        List<AddressDto> allAddressDto = new LinkedList<>();

        for(int i = 0; i < addressList.size(); i++){
            allAddressDto.add(addressService.toAddressDto(addressList.get(i)));
        }
        return new ResponseEntity<>(allAddressDto, HttpStatus.OK);
    }

    @PostMapping("/post")
    @Operation(summary = "Добавление адреса")
    public ResponseEntity<AddressDto> insertAddress(@RequestBody AddressDto addressDto){

        if(addressService.checkingIdForRepeatability(addressDto) == false){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(addressDto.getAddressId() == null){
            addressService.checkingIdIfNull(addressDto);
        }
        Address address = addressService.toAddress(addressDto);
        addressService.saveAddress(address);

        return new ResponseEntity<>(addressDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Удаление определенного адреса")
    public ResponseEntity<AddressDto> deleteAddress(@PathVariable Long id){

        if(id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(addressService.checkingIdForRepeatability(id) == false){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        addressService.deleteAddressById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    @Operation(summary = "Обновление определенного адреса")
    public ResponseEntity<AddressDto> updateAddress(@RequestBody AddressDto addressDto){

        if(addressDto.getAddressId() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(addressService.checkingIdForRepeatabilityToUpdate(addressDto) == false){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Address address = addressService.toAddress(addressDto);
        addressService.updateAddress(address);

        return new ResponseEntity<>(addressDto, HttpStatus.OK);
    }
}
