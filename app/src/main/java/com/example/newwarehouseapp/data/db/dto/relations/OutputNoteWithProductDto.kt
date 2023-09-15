package com.example.newwarehouseapp.data.db.relations

import androidx.room.ColumnInfo
import androidx.room.Relation
import com.example.newwarehouseapp.data.db.dto.ProductDto

data class OutputNoteWithProductDto (
    var id : Int,

    @ColumnInfo(name = "id_of_receiver") val idOfReceiver : Int,
    @ColumnInfo(name = "output_note_id_of_product") val outputNoteIdOfProduct : Int,
    val count : Int,

    @Relation(parentColumn = "output_note_id_of_product", entityColumn = "id")
    var product: ProductDto
)