package com.example.newwarehouseapp.domain.models

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class OutputNote(
    var id: Int = 0,
    var idOfReceiver : Int,
    var outputNoteIdOfProduct : Int,
    var count : Int
)