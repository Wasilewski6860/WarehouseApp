package com.example.newwarehouseapp.domain.use_cases.product_with_product_on_warehouse

import com.example.newwarehouseapp.domain.repositories.WarehouseRepository

class GetProductsWithProductOnWarehouseSortedByNameUseCase(private val warehouseRepository: WarehouseRepository) {

    suspend fun execute() = warehouseRepository.getProductsWithProductOnWarehouseSortedByName()
}