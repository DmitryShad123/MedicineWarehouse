package com.example.luchkin.MedicineWarehouse.service.impl;

import com.example.luchkin.MedicineWarehouse.dto.QuantityDeliveryDto;
import com.example.luchkin.MedicineWarehouse.mapper.QuantityDeliveryMapper;
import com.example.luchkin.MedicineWarehouse.model.QuantityDelivery;
import com.example.luchkin.MedicineWarehouse.service.api.QuantityDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Реализация сервиса по работе с колличеством призова
 */

@Service
public class QuantityDeliveryServiceImpl implements QuantityDeliveryService {

    @Autowired
    private QuantityDeliveryMapper quantityMapper;

    @Override
    public List<QuantityDelivery> getAllQuantityDelivery() {
        List<QuantityDelivery> quantityList = quantityMapper.getAllQuantityDelivery();
        return quantityList;
    }

    @Override
    public void saveQuantityDelivery(QuantityDelivery quantityDelivery) {
        quantityMapper.saveQuantityDelivery(quantityDelivery);
    }

    @Override
    public void deleteQuantityDeliveryById(Long id) {
        quantityMapper.deleteQuantityDeliveryById(id);
    }

    @Override
    public void updateQuantityDelivery(QuantityDelivery quantityDelivery) {
        quantityMapper.updateQuantityDelivery(quantityDelivery);
    }

    @Override
    public List<QuantityDelivery> getAllQuantityDeliveryById(Long id) {
        List<QuantityDelivery> quantityDeliveryList = quantityMapper.getAllQuantityDeliveryById(id);
        return quantityDeliveryList;
    }

    public QuantityDelivery toQuantity(QuantityDeliveryDto quantityDeliveryDto){
        return QuantityDelivery.builder().quantity_delivery_id(quantityDeliveryDto.getQuantityDeliveryId())
                .name(quantityDeliveryDto.getName())
                .quantity_packaging(quantityDeliveryDto.getQuantityPackaging())
                .delivery_preparation_id(quantityDeliveryDto.getDeliveryPreparationId())
                .build();
    }

    public QuantityDeliveryDto toQuantityDto(QuantityDelivery quantityDelivery){
        return QuantityDeliveryDto.builder().quantityDeliveryId(quantityDelivery.getQuantity_delivery_id())
                .name(quantityDelivery.getName())
                .quantityPackaging(quantityDelivery.getQuantity_packaging())
                .deliveryPreparationId(quantityDelivery.getDelivery_preparation_id())
                .build();
    }

    public boolean checkingIdForRepeatability(QuantityDeliveryDto quantityDeliveryDto){

        List<QuantityDelivery> quantityDeliveryList = getAllQuantityDelivery();
        for(int i = 0; i < quantityDeliveryList.size(); i++){
            if(quantityDeliveryList.get(i).getQuantity_delivery_id() == quantityDeliveryDto.getQuantityDeliveryId()){
                return false;
            }
        }
        return true;
    }

    public void checkingIdIfNull(QuantityDeliveryDto quantityDeliveryDto){
        List<QuantityDelivery> quantityDeliveryList = getAllQuantityDelivery();

        long maxValue = 0;
        for (int i = 0; i < quantityDeliveryList.size(); i++){
            if(quantityDeliveryList.get(i).getQuantity_delivery_id() > maxValue){
                maxValue = quantityDeliveryList.get(i).getQuantity_delivery_id();
            }
        }

        quantityDeliveryDto.setQuantityDeliveryId(maxValue+1);
    }

    public boolean checkingIdForRepeatability(Long id){

        List<QuantityDelivery> quantityDeliveryList = getAllQuantityDelivery();
        for(int i = 0; i < quantityDeliveryList.size(); i++){
            if(quantityDeliveryList.get(i).getQuantity_delivery_id() == id){
                return true;
            }
        }
        return false;
    }

    public boolean checkingIdForRepeatabilityToUpdate(QuantityDeliveryDto quantityDeliveryDto){

        List<QuantityDelivery> quantityDeliveryList = getAllQuantityDelivery();
        for(int i = 0; i < quantityDeliveryList.size(); i++){
            if(quantityDeliveryList.get(i).getQuantity_delivery_id() == quantityDeliveryDto.getQuantityDeliveryId()){
                return true;
            }
        }
        return false;
    }
}
