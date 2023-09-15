package com.example.newwarehouseapp.domain.use_cases.input_note

import com.example.newwarehouseapp.domain.models.InputNote
import com.example.newwarehouseapp.domain.repositories.WarehouseRepository

class GetInputNotesUseCase(private val warehouseRepository: WarehouseRepository) {

    suspend fun execute() = warehouseRepository.getInputNotes()
}