package com.example.newwarehouseapp.domain.models

import androidx.room.ColumnInfo
import androidx.room.Relation
import com.example.newwarehouseapp.data.db.dto.ProductDto

data class InputNoteWithProduct (
    var id : Int,
    val idOfSupplier : Int,
    val inputNoteIdOfProduct : Int,
    val count : Int,
    var product: Product
)