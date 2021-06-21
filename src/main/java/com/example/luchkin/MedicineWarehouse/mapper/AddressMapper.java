package com.example.luchkin.MedicineWarehouse.mapper;

import com.example.luchkin.MedicineWarehouse.model.Address;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий адреса
 */

@Mapper
@Repository
public interface AddressMapper {

    /**
     * Получение всех адресов в базе
     */
    @Select("SELECT * FROM address")
    List<Address> getAllAddress();

    /**
     * Поиск адресов по параметрам
     */
    @Select("Select * from address a where a.city = #{city} and a.street = #{street} and a.house = #{house};")
    Address getAddressParam(String city, String street, Integer house);

    /**
     * Добавление нового адреса в базу
     */
    @Insert("INSERT into address (address_id, city, street, house) VALUES " +
            "(#{address_id}, #{city}, #{street}, #{house})")
    void saveAddress(Address address);


    /**
     * Удаление адреса по идентификатору
     */
    @Delete("Delete from address where address_id = #{id}")
    void deleteAddressById(Long id);

    /**
     * Обновление адреса
     */
    @Update("Update address set address_id = #{address_id}, city = #{city}, street = #{street}, house = #{house} where address_id=#{address_id}")
    void updateAddress(Address address);

}
