package com.example.luchkin.MedicineWarehouse.controller;


import com.example.luchkin.MedicineWarehouse.dto.AddressDto;
import com.example.luchkin.MedicineWarehouse.dto.ClientDto;
import com.example.luchkin.MedicineWarehouse.model.Address;
import com.example.luchkin.MedicineWarehouse.model.Client;
import com.example.luchkin.MedicineWarehouse.service.impl.ClientServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/client")
@Tag(name = "client", description = "АПИ для клиента")
public class ClientController {

    @Autowired
    private ClientServiceImpl clientService;

    @GetMapping("/all")
    @Operation(summary = "Получение всех клиентов в базе")
    public ResponseEntity<List<ClientDto>> getAllClient(){
        List<Client> clientList = clientService.getAllClient();
        List<ClientDto> allClientDto = new LinkedList<>();

        for(int i = 0; i < clientList.size(); i++){
            allClientDto.add(clientService.toClientDto(clientList.get(i)));
        }
        return new ResponseEntity<>(allClientDto, HttpStatus.OK);
    }

    @PostMapping("/post")
    @Operation(summary = "Добавление клиента")
    public ResponseEntity<ClientDto> insertClient(@RequestBody ClientDto clientDto){

        if(clientService.checkingIdForRepeatability(clientDto) == false){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(clientDto.getClientId() == null){
            clientService.checkingIdIfNull(clientDto);
        }
        Client client = clientService.toClient(clientDto);
        clientService.insertClient(client);

        return new ResponseEntity<>(clientDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Удаление определенного клиента")
    public ResponseEntity<ClientDto> deleteAddress(@PathVariable Long id){

        if(id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(clientService.checkingIdForRepeatability(id) == false){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        clientService.deleteClientById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    @Operation(summary = "Обновление определенного клиента")
    public ResponseEntity<ClientDto> updateAddress(@RequestBody ClientDto clientDto){

        if(clientDto.getClientId() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(clientService.checkingIdForRepeatabilityToUpdate(clientDto) == false){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Client client = clientService.toClient(clientDto);
        clientService.updateClient(client);

        return new ResponseEntity<>(clientDto, HttpStatus.OK);
    }
}
