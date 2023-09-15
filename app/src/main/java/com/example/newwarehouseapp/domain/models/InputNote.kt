package com.example.newwarehouseapp.domain.models

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class InputNote(
    var id: Int = 0,
    var idOfSupplier : Int,
    var inputNoteIdOfProduct : Int,
    var count : Int
)