package com.example.newwarehouseapp.domain.use_cases.product_on_warehouse

import com.example.newwarehouseapp.domain.models.Product
import com.example.newwarehouseapp.domain.models.ProductOnWarehouse
import com.example.newwarehouseapp.domain.repositories.WarehouseRepository

class AddProductOnWarehouseUseCase(private val warehouseRepository: WarehouseRepository) {

    suspend fun execute(productOnWarehouse: ProductOnWarehouse)  = warehouseRepository.addProductOnWarehouse(productOnWarehouse)
}