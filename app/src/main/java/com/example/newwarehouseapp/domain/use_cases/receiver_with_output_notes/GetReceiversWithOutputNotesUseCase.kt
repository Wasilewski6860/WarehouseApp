package com.example.newwarehouseapp.domain.use_cases.receiver_with_output_notes

import com.example.newwarehouseapp.domain.models.Receiver
import com.example.newwarehouseapp.domain.repositories.WarehouseRepository

class GetReceiversWithOutputNotesUseCase(private val warehouseRepository: WarehouseRepository) {

    suspend fun execute() = warehouseRepository.getReceiversWithOutputNotes()
}