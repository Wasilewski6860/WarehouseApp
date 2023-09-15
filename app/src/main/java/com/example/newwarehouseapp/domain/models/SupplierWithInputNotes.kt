package com.example.newwarehouseapp.domain.models

import androidx.room.Relation
import com.example.newwarehouseapp.data.db.dto.input.InputNoteDto

data class SupplierWithInputNotes (
    var id : Int,
    var name: String,
    var password : String,
    val phone : String,
    val email: String,
    var inputNotes: List<InputNote>
)