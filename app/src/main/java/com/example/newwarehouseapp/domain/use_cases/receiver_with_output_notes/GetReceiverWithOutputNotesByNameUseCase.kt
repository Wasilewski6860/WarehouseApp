package com.example.newwarehouseapp.domain.use_cases.receiver_with_output_notes

import com.example.newwarehouseapp.domain.repositories.WarehouseRepository

class GetReceiverWithOutputNotesByNameUseCase(private val warehouseRepository: WarehouseRepository) {

    suspend fun execute(name : String) = warehouseRepository.getReceiverWithOutputNotesByName(name)
}