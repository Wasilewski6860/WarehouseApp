package com.example.newwarehouseapp.domain.use_cases.input_note_with_product

import com.example.newwarehouseapp.domain.models.InputNote
import com.example.newwarehouseapp.domain.repositories.WarehouseRepository

class GetInputNotesWithProductUseCase(private val warehouseRepository: WarehouseRepository) {

    suspend fun execute( ) = warehouseRepository.getInputNotesWithProduct()
}