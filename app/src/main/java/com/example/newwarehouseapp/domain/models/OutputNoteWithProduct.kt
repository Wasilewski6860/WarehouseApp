package com.example.newwarehouseapp.domain.models

import androidx.room.ColumnInfo
import androidx.room.Relation
import com.example.newwarehouseapp.data.db.dto.ProductDto

data class OutputNoteWithProduct (
    var id : Int,
    val idOfReceiver : Int,
    val outputNoteIdOfProduct : Int,
    val count : Int,
    var product: Product
)