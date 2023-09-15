package com.example.newwarehouseapp.domain.use_cases.output_note_with_product

import com.example.newwarehouseapp.domain.repositories.WarehouseRepository

class GetOutputNotesWithProductByIdUseCase(private val warehouseRepository: WarehouseRepository) {

    suspend fun execute(id : Int) = warehouseRepository.getOutputNotesWithProductById(id)
}