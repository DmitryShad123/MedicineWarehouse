package com.example.luchkin.MedicineWarehouse.mapper;

import com.example.luchkin.MedicineWarehouse.model.Address;
import com.example.luchkin.MedicineWarehouse.model.Manufacturer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для производителя
 */

@Mapper
@Repository
public interface ManufacturerMapper {

    /**
     * Получение всех производителей из базы
     */
    @Select("Select * from manufacturer")
    List<Manufacturer> getAllManufacturer();

    /**
     * Получение производителя по id
     */
    @Select("Select * from manufacturer where manufacturer_id = #{id}")
    Manufacturer getManufacturer(Long id);

    /**
     * Добавление нового производителя
     */
    @Insert("INSERT into manufacturer(manufacturer_id, name, inn, bic, phone, address_legal_id, address_actual_id) VALUES " +
            "(#{manufacturer_id}, #{name}, #{inn}, #{bic}, #{phone}, #{address_legal_id}, #{address_actual_id})")
    void insertManufacturer(Manufacturer manufacturer);



    /**
     * Удаление производителя по идентификатору
     */
    @Delete("Delete from manufacturer where manufacturer_id = #{id}")
    void deleteManufacturerById(Long id);

    /**
     * Обновление производителя
     */
    @Update("Update manufacturer set manufacturer_id = #{manufacturer_id}, name = #{name}, inn = #{inn}, bic = #{bic}, phone = #{phone}, address_legal_id = #{address_legal_id}, address_actual_id = #{address_actual_id}" +
            " where manufacturer_id=#{manufacturer_id}")
    void updateManufacturer(Manufacturer manufacturer);
}
