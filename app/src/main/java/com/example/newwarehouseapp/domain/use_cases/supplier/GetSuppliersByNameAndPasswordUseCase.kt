package com.example.newwarehouseapp.domain.use_cases.supplier

import com.example.newwarehouseapp.domain.repositories.WarehouseRepository

class GetSuppliersByNameAndPasswordUseCase(private val warehouseRepository: WarehouseRepository) {

    suspend fun execute(name : String, password : String) = warehouseRepository.getSuppliersByNameAndPassword(name, password)
}