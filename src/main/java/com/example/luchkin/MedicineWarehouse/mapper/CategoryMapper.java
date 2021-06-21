package com.example.luchkin.MedicineWarehouse.mapper;


import com.example.luchkin.MedicineWarehouse.model.Category;
import com.example.luchkin.MedicineWarehouse.model.Client;
import liquibase.pro.packaged.S;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий клиента
 */

@Mapper
@Repository
public interface CategoryMapper {


    /**
     * Получение каждой категории из базы
     */
    @Select("Select * from category")
    List<Category> getAllCategory();

    /**
     * Получение категории по имени
     */
    @Select("Select * from category where name = #{name}")
    Category getCategoryByName(String name);

    /**
     * Добавление новой категории
     */
    @Insert("INSERT into category(category_id, name) VALUES " +
            "(#{category_id}, #{name})")
    void insertCategory(Category category);

    /**
     * Удаление категории по идентификатору
     */
    @Delete("Delete from category where category_id = #{id}")
    void deleteClientById(Long id);

    /**
     * Обновление категории
     */
    @Update("Update category set category_id = #{category_id}, name = #{name}" +
            " where category_id=#{category_id}")
    void updateClient(Category category);


}
