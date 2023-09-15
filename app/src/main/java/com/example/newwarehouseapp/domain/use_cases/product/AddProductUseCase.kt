package com.example.newwarehouseapp.domain.use_cases.product

import com.example.newwarehouseapp.domain.models.Product
import com.example.newwarehouseapp.domain.repositories.WarehouseRepository

class AddProductUseCase(private val warehouseRepository: WarehouseRepository) {

    suspend fun execute(product : Product) : Long = warehouseRepository.addProduct(product)
}