package com.example.luchkin.MedicineWarehouse.service.api;

import com.example.luchkin.MedicineWarehouse.model.Address;

import java.util.List;

/**
 * Сервис для работы с адресом
 */

public interface AddressService {

    Address getAddressParam(String city, String street, Integer house);

    List<Address> getAllAddress();

    void saveAddress(Address address);

    void deleteAddressById(Long id);

    void updateAddress(Address address);
}
