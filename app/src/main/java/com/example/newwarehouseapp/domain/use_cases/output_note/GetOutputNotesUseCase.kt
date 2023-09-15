package com.example.newwarehouseapp.domain.use_cases.output_note

import com.example.newwarehouseapp.domain.models.OutputNote
import com.example.newwarehouseapp.domain.repositories.WarehouseRepository

class GetOutputNotesUseCase(private val warehouseRepository: WarehouseRepository) {

    suspend fun execute( ) = warehouseRepository.getOutputNotes()
}