package com.example.luchkin.MedicineWarehouse.mapper;


import com.example.luchkin.MedicineWarehouse.model.DeliveryPreparation;
import com.example.luchkin.MedicineWarehouse.model.SurrenderDelivery;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для отгрузки
 */

@Mapper
@Repository
public interface SurrenderDeliveryMapper {

    /**
     * Получение всех отгрузок
     */

    @Select("SELECT * FROM surrender_delivery")
    List<SurrenderDelivery> getAllSurrenderDelivery();

    /**
     * Получение отгрузки по id
     */
    @Select("Select * from surrender_delivery where surrender_delivery_id = #{id}")
    SurrenderDelivery getSurrenderDelivery(Long id);

    /**
     * Добавление новой отгрузки
     */
    @Insert("INSERT into surrender_delivery (surrender_delivery_id, date_surrender_delivery, client_id) VALUES " +
            "(#{surrender_delivery_id}, #{date_surrender_delivery}, #{client_id})")
    void saveSurrenderDelivery(SurrenderDelivery surrenderDelivery);


    /**
     * Удаление отгрузки по идентификатору
     */
    @Delete("Delete from surrender_delivery where surrender_delivery_id = #{id}")
    void deleteSurrenderDeliveryById(Long id);

    /**
     * Обновление отгрузки
     */
    @Update("Update surrender_delivery set surrender_delivery_id = #{surrender_delivery_id}, date_surrender_delivery = #{date_surrender_delivery}, client_id = #{client_id}" +
            " where surrender_delivery_id=#{surrender_delivery_id}")
    void updateSurrenderDelivery(SurrenderDelivery surrenderDelivery);
}
