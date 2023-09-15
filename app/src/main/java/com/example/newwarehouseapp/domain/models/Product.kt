package com.example.newwarehouseapp.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


data class Product(
    var id: Int = 0,
    var name: String,
    var description: String,
    var price: Int
)