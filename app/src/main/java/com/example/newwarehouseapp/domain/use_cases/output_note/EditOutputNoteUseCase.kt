package com.example.newwarehouseapp.domain.use_cases.output_note

import com.example.newwarehouseapp.domain.models.OutputNote
import com.example.newwarehouseapp.domain.repositories.WarehouseRepository

class EditOutputNoteUseCase(private val warehouseRepository: WarehouseRepository) {

    suspend fun execute(outputNote: OutputNote) = warehouseRepository.editOutputNote(outputNote)
}