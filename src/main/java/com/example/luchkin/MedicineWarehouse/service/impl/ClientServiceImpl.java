package com.example.luchkin.MedicineWarehouse.service.impl;

import com.example.luchkin.MedicineWarehouse.dto.AddressDto;
import com.example.luchkin.MedicineWarehouse.dto.ClientDto;
import com.example.luchkin.MedicineWarehouse.dto.ManufacturerDto;
import com.example.luchkin.MedicineWarehouse.mapper.ClientMapper;
import com.example.luchkin.MedicineWarehouse.model.Address;
import com.example.luchkin.MedicineWarehouse.model.Client;
import com.example.luchkin.MedicineWarehouse.model.Manufacturer;
import com.example.luchkin.MedicineWarehouse.service.api.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Реализация сервиса по работе с клиентом
 */

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public List<Client> getAllClient() {
        List<Client> clientList = clientMapper.getAllClient();
        return clientList;
    }

    @Override
    public void insertClient(Client client) {
        clientMapper.insertClient(client);
    }

    @Override
    public void deleteClientById(Long id) {
        clientMapper.deleteClientById(id);
    }

    @Override
    public void updateClient(Client client) {
        clientMapper.updateClient(client);
    }

    public Client toClient (ClientDto clientDto){
        return Client.builder().client_id(clientDto.getClientId())
                .first_name(clientDto.getFirstName())
                .last_name(clientDto.getLastName())
                .middle_name(clientDto.getMiddleName())
                .inn(clientDto.getInn())
                .phone(clientDto.getPhone())
                .build();
    }

    public ClientDto toClientDto(Client client){
        return ClientDto.builder().clientId(client.getClient_id())
                .firstName(client.getFirst_name())
                .lastName(client.getLast_name())
                .middleName(client.getMiddle_name())
                .inn(client.getInn())
                .phone(client.getPhone())
                .build();
    }

    public boolean checkingIdForRepeatability(ClientDto clientDto){

        List<Client> clientList = getAllClient();
        for(int i = 0; i < clientList.size(); i++){
            if(clientList.get(i).getClient_id() == clientDto.getClientId()){
                return false;
            }
        }
        return true;
    }

    public void checkingIdIfNull(ClientDto clientDto){
        List<Client> clientList = getAllClient();

        long maxValue = 0;
        for (int i = 0; i < clientList.size(); i++){
            if(clientList.get(i).getClient_id() > maxValue){
                maxValue = clientList.get(i).getClient_id();
            }
        }

        clientDto.setClientId(maxValue+1);
    }

    public boolean checkingIdForRepeatability(Long id){

        List<Client> clientList = getAllClient();
        for(int i = 0; i < clientList.size(); i++){
            if(clientList.get(i).getClient_id() == id){
                return true;
            }
        }
        return false;
    }

    public boolean checkingIdForRepeatabilityToUpdate(ClientDto clientDto){

        List<Client> clientList = getAllClient();
        for(int i = 0; i < clientList.size(); i++){
            if(clientList.get(i).getClient_id() == clientDto.getClientId()){
                return true;
            }
        }
        return false;
    }
}
