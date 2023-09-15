package com.example.newwarehouseapp.data.db.dto.output

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "output_notes")
data class OutputNoteDto(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "id_of_receiver") val idOfReceiver : Int,
    @ColumnInfo(name = "output_note_id_of_product") val outputNoteIdOfProduct : Int,
    @ColumnInfo(name = "count") val count : Int
)