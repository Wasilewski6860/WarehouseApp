package com.example.newwarehouseapp.data.db.relations

import androidx.room.Relation
import com.example.newwarehouseapp.data.db.dto.output.OutputNoteDto

data class ReceiverWithOutputNotesDto (
    var id : Int,
    var name: String,
    var password : String,
    val phone : String,
    val email: String,
    @Relation(parentColumn = "id", entityColumn = "id_of_receiver")
    var outputNotes: List<OutputNoteDto>
)