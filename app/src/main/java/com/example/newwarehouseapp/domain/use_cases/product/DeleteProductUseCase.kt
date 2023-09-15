package com.example.newwarehouseapp.domain.use_cases.product

import com.example.newwarehouseapp.domain.models.Product
import com.example.newwarehouseapp.domain.repositories.WarehouseRepository

class DeleteProductUseCase(private val warehouseRepository: WarehouseRepository) {

    suspend fun execute(product : Product) = warehouseRepository.deleteProduct(product)
}