package com.example.luchkin.MedicineWarehouse.mapper;

import com.example.luchkin.MedicineWarehouse.model.Manufacturer;
import com.example.luchkin.MedicineWarehouse.model.Preparation;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для препарата
 */

@Mapper
@Repository
public interface PreparationMapper {


    /**
     * Получение препарата из базы по имени
     */
    @Select("Select * from preparations where name = #{name}")
    List<Preparation> getPreparation(String name);

    /**
     * Получение препарата из базы по id
     */
    @Select("Select * from preparations where preparations_id = #{id}")
    Preparation getPreparationById(Long id);

    /**
     * Получение препарата из базы по имени и дозировке
     */
    @Select("Select * from preparations where name = #{name} and dosage = #{dosage}")
    Preparation getPreparationByNameByDosage(String name, Double dosage);

    /**
     * Получение препарата из базы по категории
     */
    @Select("Select * from preparations where category_id = #{id}")
    List<Preparation> getPreparationsByCategory(Long id);

    /**
     * Получение всех препаратов из базы
     */
    @Select("Select * from preparations")
    List<Preparation> getAllPreparation();


    /**
     * Добавление нового препарата
     */
    @Insert("INSERT into preparations(preparations_id, name, price, country, stock_balance, dosage, expiration_date, category_id, manufacturer_id, delivery_preparation_id) VALUES " +
            "(#{preparations_id}, #{name}, #{price}, #{country}, #{stock_balance}, #{dosage}, #{expiration_date}, #{category_id}, #{manufacturer_id}, #{delivery_preparation_id})")
    void insertPreparation(Preparation preparation);


    /**
     * Удаление препарата по идентификатору
     */
    @Delete("Delete from preparations where preparations_id = #{id}")
    void deletePreparationById(Long id);

    /**
     * Обновление препарата
     */
    @Update("Update preparations set preparations_id = #{preparations_id}, name = #{name}, price = #{price}, country = #{country}, stock_balance = #{stock_balance}, dosage = #{dosage}, expiration_date = #{expiration_date}, category_id = #{category_id} , manufacturer_id = #{manufacturer_id}, delivery_preparation_id = #{delivery_preparation_id}" +
            " where preparations_id=#{preparations_id}")
    void updatePreparation(Preparation preparation);
}
