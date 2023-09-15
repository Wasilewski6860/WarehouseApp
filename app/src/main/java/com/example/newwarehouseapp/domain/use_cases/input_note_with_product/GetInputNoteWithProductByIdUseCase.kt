package com.example.newwarehouseapp.domain.use_cases.input_note_with_product

import com.example.newwarehouseapp.domain.repositories.WarehouseRepository

class GetInputNoteWithProductByIdUseCase(private val warehouseRepository: WarehouseRepository) {

    suspend fun execute(id : Int) = warehouseRepository.getInputNoteWithProductById(id)
}