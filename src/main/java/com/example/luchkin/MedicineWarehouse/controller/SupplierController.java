package com.example.luchkin.MedicineWarehouse.controller;

import com.example.luchkin.MedicineWarehouse.dto.SupplierDto;
import com.example.luchkin.MedicineWarehouse.model.Address;
import com.example.luchkin.MedicineWarehouse.model.Supplier;
import com.example.luchkin.MedicineWarehouse.service.impl.AddressServiceImpl;
import com.example.luchkin.MedicineWarehouse.service.impl.SupplierServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/supplier")
@Tag(name = "supplier", description = "АПИ для поставщика")
public class SupplierController {

    @Autowired
    private SupplierServiceImpl supplierService;

    @Autowired
    private AddressServiceImpl addressService;

    @PostMapping("/post")
    @Operation(summary = "Добавление поставщика. Есть возможность добавлять поставщика с адресом из БД, либо добавлять" +
            "сразу с производителем новый адрес.")
    public ResponseEntity<SupplierDto> insertSupplier (@RequestBody SupplierDto supplierDto){

        if(supplierService.checkingIdForRepeatability(supplierDto) == false){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(supplierDto.getSupplierId() == null){
            supplierService.checkingIdIfNull(supplierDto);
        }

        if(supplierDto.getAddressLegalId() != null && supplierDto.getAddressActualId() != null){
            Supplier supplier = supplierService.toSupplier(supplierDto);
            supplierService.insertSupplier(supplier);
            return new ResponseEntity<>(supplierDto, HttpStatus.OK);
        }
        Address addressLegal = addressService.toAddressLegalFromSupplier(supplierDto);
        Address addressActual = addressService.toAddressActualFromSupplier(supplierDto);

        addressService.checkingAddress(addressLegal, addressActual);


        Supplier supplier = supplierService.toSupplierIfLegalAndActualNull(supplierDto, addressLegal, addressActual);

        supplierService.insertSupplier(supplier);
        return new ResponseEntity<>(supplierDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Удаление определенного поставщика")
    public ResponseEntity<SupplierDto> deleteSupplier(@PathVariable Long id){

        if(id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(supplierService.checkingIdForRepeatability(id) == false){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        supplierService.deleteSupplierById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    @Operation(summary = "Обновление определенного поставщика")
    public ResponseEntity<SupplierDto> updateSupplier(@RequestBody SupplierDto supplierDto){

        if(supplierDto.getSupplierId() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(supplierService.checkingIdForRepeatabilityToUpdate(supplierDto) == false){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(supplierDto.getAddressLegalId() != null && supplierDto.getAddressActualId() != null){
            Supplier supplier = supplierService.toSupplier(supplierDto);
            supplierService.updateSupplier(supplier);
            return new ResponseEntity<>(supplierDto, HttpStatus.OK);
        }
        Address addressLegal = addressService.toAddressLegalFromSupplier(supplierDto);
        Address addressActual = addressService.toAddressActualFromSupplier(supplierDto);

        addressService.checkingAddress(addressLegal, addressActual);

        Supplier supplier = supplierService.toSupplierIfLegalAndActualNull(supplierDto, addressLegal, addressActual);
        supplierService.updateSupplier(supplier);

        return new ResponseEntity<>(supplierDto, HttpStatus.OK);
    }
}
