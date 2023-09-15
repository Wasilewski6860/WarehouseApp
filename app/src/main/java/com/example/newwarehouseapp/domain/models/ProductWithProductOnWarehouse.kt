package com.example.newwarehouseapp.domain.models

import androidx.room.Relation
import com.example.newwarehouseapp.data.db.dto.ProductOnWarehouseDto

data class ProductWithProductOnWarehouse (

    var id : Int,
    val name: String,
    val description: String,
    val price: Int,

    var productOnWarehouse: ProductOnWarehouse
)