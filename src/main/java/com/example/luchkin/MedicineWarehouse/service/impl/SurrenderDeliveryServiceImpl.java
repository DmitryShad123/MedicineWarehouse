package com.example.luchkin.MedicineWarehouse.service.impl;

import com.example.luchkin.MedicineWarehouse.dto.AddressDto;
import com.example.luchkin.MedicineWarehouse.dto.DeliveryPreparationDto;
import com.example.luchkin.MedicineWarehouse.dto.SurrenderDeliveryDto;
import com.example.luchkin.MedicineWarehouse.mapper.SurrenderDeliveryMapper;
import com.example.luchkin.MedicineWarehouse.model.Address;
import com.example.luchkin.MedicineWarehouse.model.DeliveryPreparation;
import com.example.luchkin.MedicineWarehouse.model.SurrenderDelivery;
import com.example.luchkin.MedicineWarehouse.service.api.SupplierService;
import com.example.luchkin.MedicineWarehouse.service.api.SurrenderDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Реализация сервиса по работе с отгрузкой
 */

@Service
public class SurrenderDeliveryServiceImpl implements SurrenderDeliveryService {

    @Autowired
    private SurrenderDeliveryMapper surrenderDeliveryMapper;

    @Override
    public List<SurrenderDelivery> getAllSurrenderDelivery() {
        List<SurrenderDelivery> surrenderDeliveryList = surrenderDeliveryMapper.getAllSurrenderDelivery();
        return surrenderDeliveryList;
    }

    @Override
    public SurrenderDelivery getSurrenderDelivery(Long id) {
        SurrenderDelivery surrenderDelivery = surrenderDeliveryMapper.getSurrenderDelivery(id);
        return surrenderDelivery;
    }

    @Override
    public void saveSurrenderDelivery(SurrenderDelivery surrenderDelivery) {
        surrenderDeliveryMapper.saveSurrenderDelivery(surrenderDelivery);
    }

    @Override
    public void deleteSurrenderDeliveryById(Long id) {
        surrenderDeliveryMapper.deleteSurrenderDeliveryById(id);
    }

    @Override
    public void updateSurrenderDelivery(SurrenderDelivery surrenderDelivery) {
        surrenderDeliveryMapper.updateSurrenderDelivery(surrenderDelivery);
    }

    public SurrenderDelivery toSurrenderDelivery(SurrenderDeliveryDto surrenderDeliveryDto){
        return SurrenderDelivery.builder().surrender_delivery_id(surrenderDeliveryDto.getSurrenderDeliveryId())
                .date_surrender_delivery(surrenderDeliveryDto.getDateSurrenderDelivery())
                .client_id(surrenderDeliveryDto.getClientId())
                .build();
    }

    public SurrenderDeliveryDto toSurrenderDeliveryDto (SurrenderDelivery surrenderDelivery){
        return SurrenderDeliveryDto.builder().surrenderDeliveryId(surrenderDelivery.getSurrender_delivery_id())
                .dateSurrenderDelivery(surrenderDelivery.getDate_surrender_delivery())
                .clientId(surrenderDelivery.getClient_id())
                .build();
    }


    public void checkingIdIfNull(SurrenderDeliveryDto surrenderDeliveryDto){
        List<SurrenderDelivery> surrenderDeliveryList = getAllSurrenderDelivery();

        long maxValue = 0;
        for (int i = 0; i < surrenderDeliveryList.size(); i++){
            if(surrenderDeliveryList.get(i).getSurrender_delivery_id() > maxValue){
                maxValue = surrenderDeliveryList.get(i).getSurrender_delivery_id();
            }
        }

        surrenderDeliveryDto.setSurrenderDeliveryId(maxValue+1);
    }

    public boolean checkingIdForRepeatability(SurrenderDeliveryDto surrenderDeliveryDto){

        List<SurrenderDelivery> surrenderDeliveryDtos = getAllSurrenderDelivery();
        for(int i = 0; i < surrenderDeliveryDtos.size(); i++){
            if(surrenderDeliveryDtos.get(i).getSurrender_delivery_id() == surrenderDeliveryDto.getSurrenderDeliveryId()){
                return false;
            }
        }
        return true;
    }

    public boolean checkingIdForRepeatability(Long id){

        List<SurrenderDelivery> surrenderDeliveryDtos = getAllSurrenderDelivery();
        for(int i = 0; i < surrenderDeliveryDtos.size(); i++){
            if(surrenderDeliveryDtos.get(i).getSurrender_delivery_id() == id){
                return true;
            }
        }
        return false;
    }

    public boolean checkingIdForRepeatabilityToUpdate(SurrenderDeliveryDto surrenderDeliveryDto){

        List<SurrenderDelivery> surrenderDeliveryList = getAllSurrenderDelivery();
        for(int i = 0; i < surrenderDeliveryList.size(); i++){
            if(surrenderDeliveryList.get(i).getSurrender_delivery_id() == surrenderDeliveryDto.getSurrenderDeliveryId()){
                return true;
            }
        }
        return false;
    }
}
