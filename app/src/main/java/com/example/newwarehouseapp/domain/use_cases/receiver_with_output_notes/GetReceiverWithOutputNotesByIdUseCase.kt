package com.example.newwarehouseapp.domain.use_cases.receiver_with_output_notes

import com.example.newwarehouseapp.domain.repositories.WarehouseRepository

class GetReceiverWithOutputNotesByIdUseCase(private val warehouseRepository: WarehouseRepository) {

    suspend fun execute(id : Int) = warehouseRepository.getReceiverWithOutputNotesById(id)
}