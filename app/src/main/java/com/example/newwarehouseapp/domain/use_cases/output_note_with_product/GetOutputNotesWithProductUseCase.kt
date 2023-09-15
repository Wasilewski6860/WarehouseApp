package com.example.newwarehouseapp.domain.use_cases.output_note_with_product

import com.example.newwarehouseapp.domain.models.OutputNote
import com.example.newwarehouseapp.domain.repositories.WarehouseRepository

class GetOutputNotesWithProductUseCase(private val warehouseRepository: WarehouseRepository) {

    suspend fun execute() = warehouseRepository.getOutputNotesWithProduct()
}