package com.example.newwarehouseapp.domain.use_cases.supplier

import com.example.newwarehouseapp.domain.repositories.WarehouseRepository

class GetSuppliersByNameUseCase(private val warehouseRepository: WarehouseRepository) {

    suspend fun execute(name : String) = warehouseRepository.getSuppliersByName(name)
}