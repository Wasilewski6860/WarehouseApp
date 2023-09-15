package com.example.newwarehouseapp.data.db.dto.relations

import androidx.room.Relation
import com.example.newwarehouseapp.data.db.dto.input.InputNoteDto

data class SupplierWithInputNotesDto (
    var id : Int,
    var name: String,
    var password : String,
    val phone : String,
    val email: String,
    @Relation(parentColumn = "id", entityColumn = "id_of_supplier")
    var inputNotes: List<InputNoteDto>
)