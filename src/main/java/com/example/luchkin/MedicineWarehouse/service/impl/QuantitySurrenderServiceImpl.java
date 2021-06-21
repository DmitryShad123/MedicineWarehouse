package com.example.luchkin.MedicineWarehouse.service.impl;

import com.example.luchkin.MedicineWarehouse.dto.QuantityDeliveryDto;
import com.example.luchkin.MedicineWarehouse.dto.QuantitySurrenderDto;
import com.example.luchkin.MedicineWarehouse.mapper.QuantitySurrenderMapper;
import com.example.luchkin.MedicineWarehouse.model.QuantityDelivery;
import com.example.luchkin.MedicineWarehouse.model.QuantitySurrender;
import com.example.luchkin.MedicineWarehouse.service.api.QuantitySurrenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Реализация сервиса по работе с колличеством отгрузки
 */

@Service
public class QuantitySurrenderServiceImpl implements QuantitySurrenderService {

    @Autowired
    private QuantitySurrenderMapper quantitySurrenderMapper;

    @Override
    public List<QuantitySurrender> getAllQuantitySurrender() {
        List<QuantitySurrender> quantitySurrenderList = quantitySurrenderMapper.getAllQuantitySurrender();
        return quantitySurrenderList;
    }

    @Override
    public QuantitySurrender getQuantitySurrenderById(Long id) {
        QuantitySurrender quantitySurrendersList = quantitySurrenderMapper.getQuantitySurrenderById(id);
        return quantitySurrendersList;
    }

    @Override
    public void saveQuantitySurrender(QuantitySurrender quantitySurrender) {
        quantitySurrenderMapper.saveQuantitySurrender(quantitySurrender);
    }

    @Override
    public void deleteQuantitySurrenderById(Long id) {
        quantitySurrenderMapper.deleteQuantitySurrenderById(id);
    }

    @Override
    public void updateQuantitySurrender(QuantitySurrender quantitySurrender) {
        quantitySurrenderMapper.updateQuantitySurrender(quantitySurrender);
    }

    @Override
    public List<QuantitySurrender> getQuantitySurrenderBySurrenderDeliveryId(Long id) {
        List<QuantitySurrender> quantitySurrenderList = quantitySurrenderMapper.getQuantitySurrenderBySurrenderDeliveryId(id);
        return quantitySurrenderList;
    }


    public QuantitySurrender toQuantitySurrender(QuantitySurrenderDto quantitySurrenderDto){
        return QuantitySurrender.builder().quantity_surrender_id(quantitySurrenderDto.getQuantitySurrenderId())
                .quantity_packaging(quantitySurrenderDto.getQuantityPackaging())
                .preparations_id(quantitySurrenderDto.getPreparationsId())
                .surrender_delivery_id(quantitySurrenderDto.getSurrenderDeliveryId())
                .build();
    }

    public QuantitySurrenderDto toQuantitySurrenderDto (QuantitySurrender quantitySurrender){
        return QuantitySurrenderDto.builder().quantitySurrenderId(quantitySurrender.getQuantity_surrender_id())
                .quantityPackaging(quantitySurrender.getQuantity_packaging())
                .preparationsId(quantitySurrender.getPreparations_id())
                .surrenderDeliveryId(quantitySurrender.getSurrender_delivery_id())
                .build();
    }

    public boolean checkingIdForRepeatability(QuantitySurrenderDto quantitySurrenderDto){

        List<QuantitySurrender> quantitySurrenders = getAllQuantitySurrender();
        for(int i = 0; i < quantitySurrenders.size(); i++){
            if(quantitySurrenders.get(i).getQuantity_surrender_id() == quantitySurrenderDto.getQuantitySurrenderId()){
                return false;
            }
        }
        return true;
    }

    public void checkingIdIfNull(QuantitySurrenderDto quantitySurrenderDto){
        List<QuantitySurrender> quantitySurrenderList = getAllQuantitySurrender();

        long maxValue = 0;
        for (int i = 0; i < quantitySurrenderList.size(); i++){
            if(quantitySurrenderList.get(i).getQuantity_surrender_id() > maxValue){
                maxValue = quantitySurrenderList.get(i).getQuantity_surrender_id();
            }
        }

        quantitySurrenderDto.setQuantitySurrenderId(maxValue+1);
    }

    public boolean checkingIdForRepeatability(Long id){

        List<QuantitySurrender> quantitySurrenderList = getAllQuantitySurrender();
        for(int i = 0; i < quantitySurrenderList.size(); i++){
            if(quantitySurrenderList.get(i).getQuantity_surrender_id() == id){
                return true;
            }
        }
        return false;
    }

    public boolean checkingIdForRepeatabilityToUpdate(QuantitySurrenderDto quantitySurrenderDto){

        List<QuantitySurrender> quantitySurrenderList = getAllQuantitySurrender();
        for(int i = 0; i < quantitySurrenderList.size(); i++){
            if(quantitySurrenderList.get(i).getQuantity_surrender_id() == quantitySurrenderDto.getQuantitySurrenderId()){
                return true;
            }
        }
        return false;
    }
}
