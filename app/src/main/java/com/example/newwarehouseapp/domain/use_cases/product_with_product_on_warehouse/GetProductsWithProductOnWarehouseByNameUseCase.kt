package com.example.newwarehouseapp.domain.use_cases.product_with_product_on_warehouse

import com.example.newwarehouseapp.domain.repositories.WarehouseRepository

class GetProductsWithProductOnWarehouseByNameUseCase(private val warehouseRepository: WarehouseRepository) {

    suspend fun execute(name : String) = warehouseRepository.getProductsWithProductOnWarehouseByName(name)
}