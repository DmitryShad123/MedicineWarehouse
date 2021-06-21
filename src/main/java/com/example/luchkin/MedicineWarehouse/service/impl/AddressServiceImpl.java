package com.example.luchkin.MedicineWarehouse.service.impl;

import com.example.luchkin.MedicineWarehouse.dto.AddressDto;
import com.example.luchkin.MedicineWarehouse.dto.DeliveryPreparationDto;
import com.example.luchkin.MedicineWarehouse.dto.ManufacturerDto;
import com.example.luchkin.MedicineWarehouse.dto.SupplierDto;
import com.example.luchkin.MedicineWarehouse.mapper.AddressMapper;
import com.example.luchkin.MedicineWarehouse.model.Address;
import com.example.luchkin.MedicineWarehouse.model.DeliveryPreparation;
import com.example.luchkin.MedicineWarehouse.service.api.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Реализация сервиса по работе с адресом
 */

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;


    @Override
    public Address getAddressParam(String city, String street, Integer house) {
        Address address = addressMapper.getAddressParam(city, street, house);
        return address;
    }

    @Override
    public List<Address> getAllAddress() {
        List<Address> addressList = addressMapper.getAllAddress();
        return addressList;
    }

    @Override
    public void saveAddress(Address address) {
        addressMapper.saveAddress(address);
    }

    @Override
    public void deleteAddressById(Long id) {
        addressMapper.deleteAddressById(id);
    }

    @Override
    public void updateAddress(Address address) {
        addressMapper.updateAddress(address);
    }

    public Address toAddress(AddressDto addressDto){
        return Address.builder().address_id(addressDto.getAddressId())
                .city(addressDto.getCity())
                .street(addressDto.getStreet())
                .house(addressDto.getHouse())
                .build();
    }

    public AddressDto toAddressDto(Address address){
        return AddressDto.builder().addressId(address.getAddress_id())
                .city(address.getCity())
                .street(address.getStreet())
                .house(address.getHouse())
                .build();
    }

    public Address toAddressLegalFromManufacturer(ManufacturerDto manufacturerDto){
        return Address.builder().city(manufacturerDto.getCityLegal())
                .street(manufacturerDto.getStreetLegal())
                .house(manufacturerDto.getHouseLegal())
                .build();
    }
    public Address toAddressActualFromManufacturer(ManufacturerDto manufacturerDto){
        return Address.builder().city(manufacturerDto.getCityActual())
                .street(manufacturerDto.getStreetActual())
                .house(manufacturerDto.getHouseActual())
                .build();
    }

    public Address toAddressLegalFromSupplier(SupplierDto supplierDto){
        return Address.builder().city(supplierDto.getCityLegal())
                .street(supplierDto.getStreetLegal())
                .house(supplierDto.getHouseLegal())
                .build();
    }
    public Address toAddressActualFromSupplier(SupplierDto supplierDto){
        return Address.builder().city(supplierDto.getCityActual())
                .street(supplierDto.getStreetActual())
                .house(supplierDto.getHouseActual())
                .build();
    }

    public void checkingAddress(Address addressLegal, Address addressActual){
        List<Address> addressList = getAllAddress();

        long maxValue = 0;
        for (int i = 0; i < addressList.size(); i++){
            if(addressList.get(i).getAddress_id() > maxValue){
                maxValue = addressList.get(i).getAddress_id();
            }
        }

        addressLegal.setAddress_id(maxValue+1);
        addressActual.setAddress_id(maxValue+2);

        saveAddress(addressLegal);
        saveAddress(addressActual);
    }

    public boolean checkingIdForRepeatability(AddressDto addressDto){

        List<Address> addressList = getAllAddress();
        for(int i = 0; i < addressList.size(); i++){
            if(addressList.get(i).getAddress_id() == addressDto.getAddressId()){
                return false;
            }
        }
        return true;
    }

    public boolean checkingIdForRepeatabilityToUpdate(AddressDto addressDto){

        List<Address> addressList = getAllAddress();
        for(int i = 0; i < addressList.size(); i++){
            if(addressList.get(i).getAddress_id() == addressDto.getAddressId()){
                return true;
            }
        }
        return false;
    }

    public void checkingIdIfNull(AddressDto addressDto){
        List<Address> addressList = getAllAddress();

        long maxValue = 0;
        for (int i = 0; i < addressList.size(); i++){
            if(addressList.get(i).getAddress_id() > maxValue){
                maxValue = addressList.get(i).getAddress_id();
            }
        }

        addressDto.setAddressId(maxValue+1);
    }

    public boolean checkingIdForRepeatability(Long id){

        List<Address> addressList = getAllAddress();
        for(int i = 0; i < addressList.size(); i++){
            if(addressList.get(i).getAddress_id() == id){
                return true;
            }
        }
        return false;
    }
}
