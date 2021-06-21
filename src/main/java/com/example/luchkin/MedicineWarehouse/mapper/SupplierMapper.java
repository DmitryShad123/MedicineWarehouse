package com.example.luchkin.MedicineWarehouse.mapper;

import com.example.luchkin.MedicineWarehouse.model.Manufacturer;
import com.example.luchkin.MedicineWarehouse.model.Supplier;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для поставщика
 */

@Mapper
@Repository
public interface SupplierMapper {

    /**
     * Получение всех поставщиков из базы
     */
    @Select("Select * from supplier")
    List<Supplier> getAllSupplier();

    /**
     * Получение поставщиков по id
     */
    @Select("Select * from supplier where supplier_id = #{id}")
    Supplier getSupplier(Long id);

    /**
     * Добавление нового поставщика
     */
    @Insert("INSERT into supplier(supplier_id, name, inn, bic, phone, address_legal_id, address_actual_id) VALUES " +
            "(#{supplier_id}, #{name}, #{inn}, #{bic}, #{phone}, #{address_legal_id}, #{address_actual_id})")
    void insertSupplier(Supplier supplier);

    /**
     * Удаление поставщика по идентификатору
     */
    @Delete("Delete from supplier where supplier_id = #{id}")
    void deleteSupplierById(Long id);

    /**
     * Обновление поставщика
     */
    @Update("Update supplier set supplier_id = #{supplier_id}, name = #{name}, inn = #{inn}, bic = #{bic}, phone = #{phone}, address_legal_id = #{address_legal_id}, address_actual_id = #{address_actual_id}" +
            " where supplier_id=#{supplier_id}")
    void updateSupplier(Supplier supplier);
}