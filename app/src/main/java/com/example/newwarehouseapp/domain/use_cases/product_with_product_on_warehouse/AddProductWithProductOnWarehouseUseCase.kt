package com.example.newwarehouseapp.domain.use_cases.product_with_product_on_warehouse

import com.example.newwarehouseapp.domain.models.Product
import com.example.newwarehouseapp.domain.models.ProductWithProductOnWarehouse
import com.example.newwarehouseapp.domain.repositories.WarehouseRepository

class AddProductWithProductOnWarehouseUseCase(private val warehouseRepository: WarehouseRepository) {

    suspend fun execute(productWithProductOnWarehouse: ProductWithProductOnWarehouse)
        = warehouseRepository.addProductWithProductOnWarehouse(productWithProductOnWarehouse)
}