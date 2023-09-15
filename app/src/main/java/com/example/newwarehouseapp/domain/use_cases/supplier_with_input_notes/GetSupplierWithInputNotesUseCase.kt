package com.example.newwarehouseapp.domain.use_cases.supplier_with_input_notes

import com.example.newwarehouseapp.domain.repositories.WarehouseRepository

class GetSupplierWithInputNotesUseCase(private val warehouseRepository: WarehouseRepository) {

    suspend fun execute() = warehouseRepository.getSupplierWithInputNotes()
}