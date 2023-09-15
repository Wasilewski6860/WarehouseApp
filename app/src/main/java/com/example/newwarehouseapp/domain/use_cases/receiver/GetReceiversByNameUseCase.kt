package com.example.newwarehouseapp.domain.use_cases.receiver

import com.example.newwarehouseapp.domain.repositories.WarehouseRepository

class GetReceiversByNameUseCase(private val warehouseRepository: WarehouseRepository) {

    suspend fun execute(name : String) = warehouseRepository.getReceiversByName(name)
}