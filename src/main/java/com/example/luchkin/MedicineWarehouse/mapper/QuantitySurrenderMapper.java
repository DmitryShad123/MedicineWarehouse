package com.example.luchkin.MedicineWarehouse.mapper;

import com.example.luchkin.MedicineWarehouse.model.QuantityDelivery;
import com.example.luchkin.MedicineWarehouse.model.QuantitySurrender;
import com.example.luchkin.MedicineWarehouse.model.SurrenderDelivery;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для колличества отгрузки
 */

@Mapper
@Repository
public interface QuantitySurrenderMapper {


    /**
     * Получение всех упаковок для отгрузки
     */
    @Select("SELECT * FROM quantity_surrender")
    List<QuantitySurrender> getAllQuantitySurrender();


    /**
     * Получение упоковки по id
     */
    @Select("SELECT * FROM quantity_surrender where quantity_surrender_id = #{id}")
    QuantitySurrender getQuantitySurrenderById(Long id);

    /**
     * Получение упоковки по id отгрузки
     */
    @Select("SELECT * FROM quantity_surrender where surrender_delivery_id = #{id}")
    List<QuantitySurrender> getQuantitySurrenderBySurrenderDeliveryId(Long id);

    /**
     * Добавление нового упаковки в базу
     */
    @Insert("INSERT into quantity_surrender (quantity_surrender_id, quantity_packaging, preparations_id, surrender_delivery_id) VALUES " +
            "(#{quantity_surrender_id}, #{quantity_packaging}, #{preparations_id}, #{surrender_delivery_id})")
    void saveQuantitySurrender(QuantitySurrender quantitySurrender);

    /**
     * Удаление упаковки по идентификатору
     */
    @Delete("Delete from quantity_surrender where quantity_surrender_id = #{id}")
    void deleteQuantitySurrenderById(Long id);

    /**
     * Обновление упаковки
     */
    @Update("Update quantity_surrender set quantity_surrender_id = #{quantity_surrender_id}, quantity_packaging = #{quantity_packaging}, preparations_id = #{preparations_id}, surrender_delivery_id = #{surrender_delivery_id}" +
            " where quantity_surrender_id=#{quantity_surrender_id}")
    void updateQuantitySurrender(QuantitySurrender quantitySurrender);
}