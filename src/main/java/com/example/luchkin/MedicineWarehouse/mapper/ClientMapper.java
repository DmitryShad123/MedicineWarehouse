package com.example.luchkin.MedicineWarehouse.mapper;


import com.example.luchkin.MedicineWarehouse.model.Client;
import com.example.luchkin.MedicineWarehouse.model.Manufacturer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий клиента
 */

@Mapper
@Repository
public interface ClientMapper {


    /**
     * Получение всех клиентов из базы
     */
    @Select("Select * from client")
    List<Client> getAllClient();


    /**
     * Добавление нового клиента
     */
    @Insert("INSERT into client(client_id, first_name, last_name, middle_name, inn, phone) VALUES " +
            "(#{client_id}, #{first_name}, #{last_name}, #{middle_name}, #{inn}, #{phone})")
    void insertClient(Client client);

    /**
     * Удаление клиента по идентификатору
     */
    @Delete("Delete from client where client_id = #{id}")
    void deleteClientById(Long id);

    /**
     * Обновление клиента
     */
    @Update("Update client set client_id = #{client_id}, first_name = #{first_name}, last_name = #{last_name}, middle_name = #{middle_name}, inn = #{inn}, phone = #{phone}" +
            " where client_id=#{client_id}")
    void updateClient(Client client);
}
