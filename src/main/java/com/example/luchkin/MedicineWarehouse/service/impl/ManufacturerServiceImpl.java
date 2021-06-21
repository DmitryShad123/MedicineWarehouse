package com.example.luchkin.MedicineWarehouse.service.impl;

import com.example.luchkin.MedicineWarehouse.dto.AddressDto;
import com.example.luchkin.MedicineWarehouse.dto.ManufacturerDto;
import com.example.luchkin.MedicineWarehouse.mapper.ManufacturerMapper;
import com.example.luchkin.MedicineWarehouse.model.Address;
import com.example.luchkin.MedicineWarehouse.model.Manufacturer;
import com.example.luchkin.MedicineWarehouse.service.api.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Реализация сервиса по работе с производителем
 */

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    @Autowired
    private ManufacturerMapper manufacturerMapper;

    @Override
    public List<Manufacturer> getAllManufacturer() {
        List<Manufacturer> manufacturerList = manufacturerMapper.getAllManufacturer();
        return manufacturerList;
    }

    @Override
    public void insertManufacturer(Manufacturer manufacturer) {
        manufacturerMapper.insertManufacturer(manufacturer);
    }

    @Override
    public void deleteManufacturerById(Long id) {
        manufacturerMapper.deleteManufacturerById(id);
    }

    @Override
    public void updateManufacturer(Manufacturer manufacturer) {
        manufacturerMapper.updateManufacturer(manufacturer);
    }

    @Override
    public Manufacturer getManufacturer(Long id) {
        Manufacturer manufacturer = manufacturerMapper.getManufacturer(id);
        return manufacturer;
    }

    public Manufacturer toManufacturer(ManufacturerDto manufacturerDto){
        return Manufacturer.builder().manufacturer_id(manufacturerDto.getManufacturerId())
                .name(manufacturerDto.getName())
                .inn(manufacturerDto.getInn())
                .bic(manufacturerDto.getBic())
                .phone(manufacturerDto.getPhone())
                .address_legal_id(manufacturerDto.getAddressLegalId())
                .address_actual_id(manufacturerDto.getAddressActualId())
                .build();
    }

    public ManufacturerDto toManufacturerDto(Manufacturer manufacturer){
        return ManufacturerDto.builder().manufacturerId(manufacturer.getManufacturer_id())
                .name(manufacturer.getName())
                .inn(manufacturer.getInn())
                .bic(manufacturer.getBic())
                .phone(manufacturer.getPhone())
                .addressLegalId(manufacturer.getAddress_legal_id())
                .addressActualId(manufacturer.getAddress_actual_id())
                .build();
    }

    public Manufacturer toManufacturerIfLegalAndActualNull(ManufacturerDto manufacturerDto, Address addressLegal, Address addressActual){
        return Manufacturer.builder().manufacturer_id(manufacturerDto.getManufacturerId())
                .name(manufacturerDto.getName())
                .inn(manufacturerDto.getInn())
                .bic(manufacturerDto.getBic())
                .phone(manufacturerDto.getPhone())
                .address_legal_id(addressLegal.getAddress_id())
                .address_actual_id(addressActual.getAddress_id())
                .build();
    }

    public void checkingIdIfNull(ManufacturerDto manufacturerDto){
        List<Manufacturer> manufacturerList = getAllManufacturer();

        long maxValue = 0;
        for (int i = 0; i < manufacturerList.size(); i++){
            if(manufacturerList.get(i).getManufacturer_id() > maxValue){
                maxValue = manufacturerList.get(i).getManufacturer_id();
            }
        }

        manufacturerDto.setManufacturerId(maxValue+1);
    }

    public boolean checkingIdForRepeatability(ManufacturerDto manufacturerDto){

        List<Manufacturer> manufacturerList = getAllManufacturer();
        for(int i = 0; i < manufacturerList.size(); i++){
            if(manufacturerList.get(i).getManufacturer_id() == manufacturerDto.getManufacturerId()){
                return false;
            }
        }
        return true;
    }

    public boolean checkingIdForRepeatability(Long id){

        List<Manufacturer> manufacturerList = getAllManufacturer();
        for(int i = 0; i < manufacturerList.size(); i++){
            if(manufacturerList.get(i).getManufacturer_id() == id){
                return true;
            }
        }
        return false;
    }

    public boolean checkingIdForRepeatabilityToUpdate(ManufacturerDto manufacturerDto){

        List<Manufacturer> manufacturerList = getAllManufacturer();
        for(int i = 0; i < manufacturerList.size(); i++){
            if(manufacturerList.get(i).getManufacturer_id() == manufacturerDto.getManufacturerId()){
                return true;
            }
        }
        return false;
    }
}
