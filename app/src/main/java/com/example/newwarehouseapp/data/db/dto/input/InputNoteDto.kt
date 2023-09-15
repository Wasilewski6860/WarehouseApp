package com.example.newwarehouseapp.data.db.dto.input

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "input_notes")
data class InputNoteDto(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "id_of_supplier") val idOfSupplier : Int,
    @ColumnInfo(name = "input_note_id_of_product") val inputNoteIdOfProduct : Int,
    @ColumnInfo(name = "count") val count : Int
)