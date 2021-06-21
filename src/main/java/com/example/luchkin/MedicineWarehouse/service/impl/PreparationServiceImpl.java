package com.example.luchkin.MedicineWarehouse.service.impl;

import com.example.luchkin.MedicineWarehouse.dto.AddressDto;
import com.example.luchkin.MedicineWarehouse.dto.ManufacturerDto;
import com.example.luchkin.MedicineWarehouse.dto.PreparationDto;
import com.example.luchkin.MedicineWarehouse.mapper.PreparationMapper;
import com.example.luchkin.MedicineWarehouse.model.Address;
import com.example.luchkin.MedicineWarehouse.model.Manufacturer;
import com.example.luchkin.MedicineWarehouse.model.Preparation;
import com.example.luchkin.MedicineWarehouse.service.api.PreparationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Реализация сервиса по работе с препаратом
 */

@Service
public class PreparationServiceImpl implements PreparationService {

    @Autowired
    private PreparationMapper preparationMapper;

    @Override
    public List<Preparation> getPreparation(String name) {
        List<Preparation> preparation = preparationMapper.getPreparation(name);
        return preparation;
    }

    @Override
    public Preparation getPreparationByNameByDosage(String name, Double dosage) {
        Preparation preparation = preparationMapper.getPreparationByNameByDosage(name, dosage);
        return preparation;
    }

    @Override
    public List<Preparation> getAllPreparation() {
        List<Preparation> preparationList = preparationMapper.getAllPreparation();
        return preparationList;
    }

    @Override
    public void insertPreparation(Preparation preparation) {
        preparationMapper.insertPreparation(preparation);
    }

    @Override
    public void deletePreparationById(Long id) {
        preparationMapper.deletePreparationById(id);
    }

    @Override
    public void updatePreparation(Preparation preparation) {
        preparationMapper.updatePreparation(preparation);
    }

    @Override
    public List<Preparation> getPreparationsByCategory(Long id) {
        List<Preparation> preparationList = preparationMapper.getPreparationsByCategory(id);
        return preparationList;
    }

    @Override
    public Preparation getPreparationById(Long id) {
        Preparation preparation = preparationMapper.getPreparationById(id);
        return preparation;
    }

    public PreparationDto toPreparationDto(Preparation preparation){
        return PreparationDto.builder().preparationsId(preparation.getPreparations_id())
                .name(preparation.getName())
                .price(preparation.getPrice())
                .country(preparation.getCountry())
                .stockBalance(preparation.getStock_balance())
                .dosage(preparation.getDosage())
                .expirationDate(preparation.getExpiration_date())
                .categoryId(preparation.getCategory_id())
                .manufacturerId(preparation.getManufacturer_id())
                .deliveryPreparationId(preparation.getDelivery_preparation_id())
                .build();
    }

    public Preparation toPreparation(PreparationDto preparationDto){
        return Preparation.builder().preparations_id(preparationDto.getPreparationsId())
                .name(preparationDto.getName())
                .price(preparationDto.getPrice())
                .country(preparationDto.getCountry())
                .stock_balance(preparationDto.getStockBalance())
                .dosage(preparationDto.getDosage())
                .expiration_date(preparationDto.getExpirationDate())
                .category_id(preparationDto.getCategoryId())
                .manufacturer_id(preparationDto.getManufacturerId())
                .delivery_preparation_id(preparationDto.getDeliveryPreparationId())
                .build();
    }

    public boolean checkingIdForRepeatability(PreparationDto preparationDto){

        List<Preparation> preparationList = getAllPreparation();
        for(int i = 0; i < preparationList.size(); i++){
            if(preparationList.get(i).getPreparations_id() == preparationDto.getPreparationsId()){
                return false;
            }
        }
        return true;
    }

    public void checkingIdIfNull(PreparationDto preparationDto){
        List<Preparation> preparationList = getAllPreparation();

        long maxValue = 0;
        for (int i = 0; i < preparationList.size(); i++){
            if(preparationList.get(i).getPreparations_id() > maxValue){
                maxValue = preparationList.get(i).getPreparations_id();
            }
        }

        preparationDto.setPreparationsId(maxValue+1);
    }

    public boolean checkingIdForRepeatability(Long id){

        List<Preparation> preparationList = getAllPreparation();
        for(int i = 0; i < preparationList.size(); i++){
            if(preparationList.get(i).getPreparations_id() == id){
                return true;
            }
        }
        return false;
    }

    public boolean checkingIdForRepeatabilityToUpdate(PreparationDto preparationDto){

        List<Preparation> preparationList = getAllPreparation();
        for(int i = 0; i < preparationList.size(); i++){
            if(preparationList.get(i).getPreparations_id() == preparationDto.getPreparationsId()){
                return true;
            }
        }
        return false;
    }
}
