package com.example.newwarehouseapp.data.db.dto.relations

import androidx.room.Relation
import com.example.newwarehouseapp.data.db.dto.ProductOnWarehouseDto


data class ProductWithProductOnWarehouseDto (

    var id : Int,
    val name: String,
    val description: String,
    val price: Int,

    @Relation(parentColumn = "id", entityColumn = "warehouse_id_of_product")
    var productOnWarehouseDto: ProductOnWarehouseDto
)