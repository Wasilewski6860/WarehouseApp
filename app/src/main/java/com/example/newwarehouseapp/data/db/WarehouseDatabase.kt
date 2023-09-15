package com.example.newwarehouseapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newwarehouseapp.data.db.dto.ProductDto
import com.example.newwarehouseapp.data.db.dto.ProductOnWarehouseDto
import com.example.newwarehouseapp.data.db.dto.input.InputNoteDto
import com.example.newwarehouseapp.data.db.dto.input.SupplierDto
import com.example.newwarehouseapp.data.db.dto.output.OutputNoteDto
import com.example.newwarehouseapp.data.db.dto.output.ReceiverDto

@Database(
    entities = [

        OutputNoteDto::class,
        InputNoteDto::class,
        ReceiverDto::class,
        SupplierDto::class,
        ProductDto::class,
        ProductOnWarehouseDto::class
    ],
    version = 4
)
abstract class WarehouseDatabase : RoomDatabase() {

    abstract val dao: WarehouseDao

    companion object {
        @Volatile
        private var INSTANCE: WarehouseDatabase? = null

        fun getInstance(context: Context): WarehouseDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    WarehouseDatabase::class.java,
                    "warehouse_db"
                ) .fallbackToDestructiveMigration().build().also {
                    INSTANCE = it
                }
            }
        }
    }
}