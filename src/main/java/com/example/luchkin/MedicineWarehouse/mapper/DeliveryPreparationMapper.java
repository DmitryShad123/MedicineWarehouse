package com.example.luchkin.MedicineWarehouse.mapper;

import com.example.luchkin.MedicineWarehouse.model.DeliveryPreparation;
import com.example.luchkin.MedicineWarehouse.model.Manufacturer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Репозиторий поставки
 */

@Mapper
@Repository
public interface DeliveryPreparationMapper {

    /**
     * Получение всех поставок
     */

    @Select("SELECT * FROM delivery_preparation")
    List<DeliveryPreparation> getAllDeliveryPreparation();

    /**
     * Получение поставки по id
     */
    @Select("Select * from delivery_preparation where delivery_preparation_id = #{id}")
    DeliveryPreparation getDeliveryPreparation(Long id);

    /**
     * Добавление новой поставки
     */
    @Insert("INSERT into delivery_preparation (delivery_preparation_id, date_delivery, supplier_id) VALUES " +
            "(#{delivery_preparation_id}, #{date_delivery}, #{supplier_id})")
    void saveDeliveryPreparation(DeliveryPreparation deliveryPreparation);


    /**
     * Удаление поставки по идентификатору
     */
    @Delete("Delete from delivery_preparation where delivery_preparation_id = #{id}")
    void deleteDeliveryPreparationById(Long id);

    /**
     * Обновление поставки
     */
    @Update("Update delivery_preparation set delivery_preparation_id = #{delivery_preparation_id}, date_delivery = #{date_delivery}, supplier_id = #{supplier_id}" +
            " where delivery_preparation_id=#{delivery_preparation_id}")
    void updateDeliveryPreparation(DeliveryPreparation deliveryPreparation);
}