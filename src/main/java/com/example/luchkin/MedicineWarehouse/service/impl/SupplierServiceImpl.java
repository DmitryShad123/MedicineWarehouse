package com.example.luchkin.MedicineWarehouse.service.impl;

import com.example.luchkin.MedicineWarehouse.dto.ManufacturerDto;
import com.example.luchkin.MedicineWarehouse.dto.SupplierDto;
import com.example.luchkin.MedicineWarehouse.mapper.SupplierMapper;
import com.example.luchkin.MedicineWarehouse.model.Address;
import com.example.luchkin.MedicineWarehouse.model.Manufacturer;
import com.example.luchkin.MedicineWarehouse.model.Supplier;
import com.example.luchkin.MedicineWarehouse.service.api.SupplierService;
import liquibase.pro.packaged.S;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Реализация сервиса по работе с поставщиком
 */

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierMapper supplierMapper;

    @Override
    public List<Supplier> getAllSupplier() {
        List<Supplier> supplierList = supplierMapper.getAllSupplier();
        return supplierList;
    }

    @Override
    public void insertSupplier(Supplier supplier) {
        supplierMapper.insertSupplier(supplier);
    }

    @Override
    public void updateSupplier(Supplier supplier) {
        supplierMapper.updateSupplier(supplier);
    }

    @Override
    public void deleteSupplierById(Long id) {
        supplierMapper.deleteSupplierById(id);
    }

    @Override
    public Supplier getSupplier(Long id) {
        Supplier supplier = supplierMapper.getSupplier(id);
        return supplier;
    }

    public Supplier toSupplier(SupplierDto supplierDto){
        return Supplier.builder().supplier_id(supplierDto.getSupplierId())
                .name(supplierDto.getName())
                .inn(supplierDto.getInn())
                .bic(supplierDto.getBic())
                .phone(supplierDto.getPhone())
                .address_legal_id(supplierDto.getAddressLegalId())
                .address_actual_id(supplierDto.getAddressActualId())
                .build();
    }

    public SupplierDto toSupplierDto(Supplier supplier){
        return SupplierDto.builder().supplierId(supplier.getSupplier_id())
                .name(supplier.getName())
                .inn(supplier.getInn())
                .bic(supplier.getBic())
                .phone(supplier.getPhone())
                .addressActualId(supplier.getAddress_actual_id())
                .addressLegalId(supplier.getAddress_legal_id())
                .build();
    }

    public Supplier toSupplierIfLegalAndActualNull(SupplierDto supplierDto, Address addressLegal, Address addressActual){
        return Supplier.builder().supplier_id(supplierDto.getSupplierId())
                .name(supplierDto.getName())
                .inn(supplierDto.getInn())
                .bic(supplierDto.getBic())
                .phone(supplierDto.getPhone())
                .address_legal_id(addressLegal.getAddress_id())
                .address_actual_id(addressActual.getAddress_id())
                .build();
    }

    public void checkingIdIfNull(SupplierDto supplierDto){
        List<Supplier> supplierList = getAllSupplier();

        long maxValue = 0;
        for (int i = 0; i < supplierList.size(); i++){
            if(supplierList.get(i).getSupplier_id() > maxValue){
                maxValue = supplierList.get(i).getSupplier_id();
            }
        }

        supplierDto.setSupplierId(maxValue+1);
    }

    public boolean checkingIdForRepeatability(SupplierDto supplierDto){

        List<Supplier> supplierList = getAllSupplier();
        for(int i = 0; i < supplierList.size(); i++){
            if(supplierList.get(i).getSupplier_id() == supplierDto.getSupplierId()){
                return false;
            }
        }
        return true;
    }

    public boolean checkingIdForRepeatability(Long id){

        List<Supplier> supplierList = getAllSupplier();
        for(int i = 0; i < supplierList.size(); i++){
            if(supplierList.get(i).getSupplier_id() == id){
                return true;
            }
        }
        return false;
    }

    public boolean checkingIdForRepeatabilityToUpdate(SupplierDto supplierDto){

        List<Supplier> supplierList = getAllSupplier();
        for(int i = 0; i < supplierList.size(); i++){
            if(supplierList.get(i).getSupplier_id() == supplierDto.getSupplierId()){
                return true;
            }
        }
        return false;
    }
}
