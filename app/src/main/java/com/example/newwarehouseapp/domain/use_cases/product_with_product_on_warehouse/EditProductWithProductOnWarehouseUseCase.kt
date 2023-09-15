package com.example.newwarehouseapp.domain.use_cases.product_with_product_on_warehouse

import com.example.newwarehouseapp.domain.models.ProductWithProductOnWarehouse
import com.example.newwarehouseapp.domain.repositories.WarehouseRepository

class EditProductWithProductOnWarehouseUseCase(private val warehouseRepository: WarehouseRepository) {

    suspend fun execute(productWithProductOnWarehouse: ProductWithProductOnWarehouse)
            = warehouseRepository.editProductWithProductOnWarehouse(productWithProductOnWarehouse)
}