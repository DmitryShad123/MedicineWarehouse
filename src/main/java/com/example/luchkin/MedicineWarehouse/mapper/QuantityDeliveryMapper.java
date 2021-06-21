package com.example.luchkin.MedicineWarehouse.mapper;


import com.example.luchkin.MedicineWarehouse.model.QuantityDelivery;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для колличества привоза
 */

@Mapper
@Repository
public interface QuantityDeliveryMapper {


    /**
     * Получение всех упаковок для поставок
     */
    @Select("SELECT * FROM quantity_delivery")
    List<QuantityDelivery> getAllQuantityDelivery();


    /**
     * Получение всех упаковок в поставке
     */
    @Select("SELECT * FROM quantity_delivery where delivery_preparation_id = #{id}")
    List<QuantityDelivery> getAllQuantityDeliveryById(Long id);

    /**
     * Добавление нового упаковки в базу
     */
    @Insert("INSERT into quantity_delivery (quantity_delivery_id, name, quantity_packaging, delivery_preparation_id) VALUES " +
            "(#{quantity_delivery_id}, #{name}, #{quantity_packaging}, #{delivery_preparation_id})")
    void saveQuantityDelivery(QuantityDelivery quantityDelivery);

    /**
     * Удаление упаковки по идентификатору
     */
    @Delete("Delete from quantity_delivery where quantity_delivery_id = #{id}")
    void deleteQuantityDeliveryById(Long id);

    /**
     * Обновление упаковки
     */
    @Update("Update quantity_delivery set quantity_delivery_id = #{quantity_delivery_id}, name = #{name}, quantity_packaging = #{quantity_packaging}, delivery_preparation_id = #{delivery_preparation_id} where quantity_delivery_id=#{quantity_delivery_id}")
    void updateQuantityDelivery(QuantityDelivery quantityDelivery);
}
