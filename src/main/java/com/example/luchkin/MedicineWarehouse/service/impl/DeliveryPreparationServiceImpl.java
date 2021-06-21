package com.example.luchkin.MedicineWarehouse.service.impl;


import com.example.luchkin.MedicineWarehouse.dto.DeliveryPreparationDto;
import com.example.luchkin.MedicineWarehouse.dto.ManufacturerDto;
import com.example.luchkin.MedicineWarehouse.mapper.DeliveryPreparationMapper;
import com.example.luchkin.MedicineWarehouse.model.DeliveryPreparation;
import com.example.luchkin.MedicineWarehouse.model.Manufacturer;
import com.example.luchkin.MedicineWarehouse.service.api.DeliveryPreparationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Реализация сервиса по работе с поставкой
 */

@Service
public class DeliveryPreparationServiceImpl implements DeliveryPreparationService {

    @Autowired
    private DeliveryPreparationMapper deliveryPreparationMapper;

    @Override
    public List<DeliveryPreparation> getAllDeliveryPreparation() {
        List<DeliveryPreparation> deliveryPreparations = deliveryPreparationMapper.getAllDeliveryPreparation();
        return deliveryPreparations;
    }

    @Override
    public void saveDeliveryPreparation(DeliveryPreparation deliveryPreparation) {
        deliveryPreparationMapper.saveDeliveryPreparation(deliveryPreparation);
    }

    @Override
    public void deleteDeliveryPreparationById(Long id) {
        deliveryPreparationMapper.deleteDeliveryPreparationById(id);
    }

    @Override
    public void updateDeliveryPreparation(DeliveryPreparation deliveryPreparation) {
        deliveryPreparationMapper.updateDeliveryPreparation(deliveryPreparation);
    }

    @Override
    public DeliveryPreparation getDeliveryPreparation(Long id) {
        DeliveryPreparation deliveryPreparation = deliveryPreparationMapper.getDeliveryPreparation(id);
        return deliveryPreparation;
    }

    public DeliveryPreparation toDeliveryPreparation(DeliveryPreparationDto deliveryPreparationDto){
        return DeliveryPreparation.builder().delivery_preparation_id(deliveryPreparationDto.getDeliveryPreparationId())
                .date_delivery(deliveryPreparationDto.getDateDelivery())
                .supplier_id(deliveryPreparationDto.getSupplierId())
                .build();
    }

    public DeliveryPreparationDto toDeliveryPreparationDto(DeliveryPreparation deliveryPreparation){
        return DeliveryPreparationDto.builder().deliveryPreparationId(deliveryPreparation.getDelivery_preparation_id())
                .dateDelivery(deliveryPreparation.getDate_delivery())
                .supplierId(deliveryPreparation.getSupplier_id())
                .build();
    }

    public void checkingIdIfNull(DeliveryPreparationDto deliveryPreparationDto){
        List<DeliveryPreparation> deliveryPreparationList = getAllDeliveryPreparation();

        long maxValue = 0;
        for (int i = 0; i < deliveryPreparationList.size(); i++){
            if(deliveryPreparationList.get(i).getDelivery_preparation_id() > maxValue){
                maxValue = deliveryPreparationList.get(i).getDelivery_preparation_id();
            }
        }

        deliveryPreparationDto.setDeliveryPreparationId(maxValue+1);
    }

    public boolean checkingIdForRepeatability(DeliveryPreparationDto deliveryPreparationDto){

        List<DeliveryPreparation> deliveryPreparationList = getAllDeliveryPreparation();
        for(int i = 0; i < deliveryPreparationList.size(); i++){
            if(deliveryPreparationList.get(i).getDelivery_preparation_id() == deliveryPreparationDto.getDeliveryPreparationId()){
                return false;
            }
        }
        return true;
    }

    public boolean checkingIdForRepeatability(Long id){

        List<DeliveryPreparation> deliveryPreparationList = getAllDeliveryPreparation();
        for(int i = 0; i < deliveryPreparationList.size(); i++){
            if(deliveryPreparationList.get(i).getDelivery_preparation_id() == id){
                return true;
            }
        }
        return false;
    }

    public boolean checkingIdForRepeatabilityToUpdate(DeliveryPreparationDto deliveryPreparationDto){

        List<DeliveryPreparation> deliveryPreparationList = getAllDeliveryPreparation();
        for(int i = 0; i < deliveryPreparationList.size(); i++){
            if(deliveryPreparationList.get(i).getDelivery_preparation_id() == deliveryPreparationDto.getDeliveryPreparationId()){
                return true;
            }
        }
        return false;
    }
}
