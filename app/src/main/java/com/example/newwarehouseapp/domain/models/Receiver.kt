package com.example.newwarehouseapp.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Receiver(
    var id: Int = 0,
    var name: String,
    var password : String,
    var phone : String,
    var email: String
)