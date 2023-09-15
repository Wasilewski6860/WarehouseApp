package com.example.newwarehouseapp.domain.use_cases.supplier

import com.example.newwarehouseapp.domain.models.Supplier
import com.example.newwarehouseapp.domain.repositories.WarehouseRepository

class GetSuppliersUseCase(private val warehouseRepository: WarehouseRepository) {

    suspend fun execute() = warehouseRepository.getSuppliers()
}