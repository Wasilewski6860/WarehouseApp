package com.example.newwarehouseapp.domain.use_cases.product

import com.example.newwarehouseapp.domain.models.Product
import com.example.newwarehouseapp.domain.repositories.WarehouseRepository

class GetProductByNameUseCase(private val warehouseRepository: WarehouseRepository) {

    suspend fun execute(name : String) = warehouseRepository.getProductByName(name)
}