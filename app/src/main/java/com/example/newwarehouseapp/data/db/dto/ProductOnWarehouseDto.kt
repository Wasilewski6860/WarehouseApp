package com.example.newwarehouseapp.data.db.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_on_warehouse")
data class ProductOnWarehouseDto(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "warehouse_id_of_product") val warehouseIdOfProduct : Int,
    @ColumnInfo(name = "count") val count: Int
)