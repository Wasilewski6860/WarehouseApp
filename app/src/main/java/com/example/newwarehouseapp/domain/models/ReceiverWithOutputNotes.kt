package com.example.newwarehouseapp.domain.models

import androidx.room.Relation
import com.example.newwarehouseapp.data.db.dto.output.OutputNoteDto

data class ReceiverWithOutputNotes (
    var id : Int,
    var name: String,
    var password : String,
    val phone : String,
    val email: String,

    var outputNotes: List<OutputNote>
)