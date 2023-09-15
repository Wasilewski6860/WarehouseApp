package com.example.newwarehouseapp.data.db.dto.output

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "receivers")
data class ReceiverDto(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "phone") val phone : String,
    @ColumnInfo(name = "email") val email: String
)