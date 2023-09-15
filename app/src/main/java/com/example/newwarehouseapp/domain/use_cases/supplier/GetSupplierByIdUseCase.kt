package com.example.newwarehouseapp.domain.use_cases.supplier

import com.example.newwarehouseapp.domain.models.Supplier
import com.example.newwarehouseapp.domain.repositories.WarehouseRepository

class GetSupplierByIdUseCase(private val warehouseRepository: WarehouseRepository) {

    suspend fun execute(id : Int) = warehouseRepository.getSupplierById(id)
}