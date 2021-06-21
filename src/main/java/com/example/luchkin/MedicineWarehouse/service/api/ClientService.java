package com.example.luchkin.MedicineWarehouse.service.api;


import com.example.luchkin.MedicineWarehouse.model.Client;

import java.util.List;

/**
 * Сервис для работы с клиентом
 */
public interface ClientService {

    List<Client> getAllClient();

    void insertClient(Client client);

    void deleteClientById(Long id);

    void updateClient(Client client);
}
