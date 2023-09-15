package com.example.newwarehouseapp.domain.use_cases.supplier

import com.example.newwarehouseapp.domain.models.Supplier
import com.example.newwarehouseapp.domain.repositories.WarehouseRepository

class EditSupplierUseCase(private val warehouseRepository: WarehouseRepository) {

    suspend fun execute(supplier: Supplier) = warehouseRepository.editSupplier(supplier)
}