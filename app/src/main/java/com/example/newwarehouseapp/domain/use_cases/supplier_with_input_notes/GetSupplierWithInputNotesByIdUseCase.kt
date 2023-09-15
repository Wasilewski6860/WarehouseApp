package com.example.newwarehouseapp.domain.use_cases.supplier_with_input_notes

import com.example.newwarehouseapp.domain.models.Supplier
import com.example.newwarehouseapp.domain.repositories.WarehouseRepository

class GetSupplierWithInputNotesByIdUseCase(private val warehouseRepository: WarehouseRepository) {

    suspend fun execute(id : Int) = warehouseRepository.getSupplierWithInputNotesById(id)
}