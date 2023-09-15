package com.example.newwarehouseapp.domain.models

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class ProductOnWarehouse(
    var id: Int = 0,
    var warehouseIdOfProduct : Int,
    var count: Int
)