package com.example.newwarehouseapp.domain.use_cases.input_note_with_product

import com.example.newwarehouseapp.domain.repositories.WarehouseRepository

class GetInputNotesWithProductByIdUseCase(private val warehouseRepository: WarehouseRepository) {

    suspend fun execute(id : Int) = warehouseRepository.getInputNotesWithProductById(id)
}