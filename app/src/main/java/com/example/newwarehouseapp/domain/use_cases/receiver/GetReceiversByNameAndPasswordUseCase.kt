package com.example.newwarehouseapp.domain.use_cases.receiver

import com.example.newwarehouseapp.domain.repositories.WarehouseRepository

class GetReceiversByNameAndPasswordUseCase(private val warehouseRepository: WarehouseRepository) {

    suspend fun execute(name: String,password : String) = warehouseRepository.getReceiversByNameAndPassword(name,password)
}
