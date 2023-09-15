package com.example.newwarehouseapp.data.db.dto.relations

import androidx.room.ColumnInfo
import androidx.room.Relation
import com.example.newwarehouseapp.data.db.dto.ProductDto

data class InputNoteWithProductDto (
    var id : Int,

    @ColumnInfo(name = "id_of_supplier") val idOfSupplier : Int,
    @ColumnInfo(name = "input_note_id_of_product") val inputNoteIdOfProduct : Int,
    val count : Int,

    @Relation(parentColumn = "input_note_id_of_product", entityColumn = "id")
    var product: ProductDto
)