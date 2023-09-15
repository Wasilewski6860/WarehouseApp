package com.example.newwarehouseapp.domain.use_cases.receiver

import com.example.newwarehouseapp.domain.models.Receiver
import com.example.newwarehouseapp.domain.repositories.WarehouseRepository

class GetReceiverByIdUseCase(private val warehouseRepository: WarehouseRepository) {

    suspend fun execute(id : Int) = warehouseRepository.getReceiverById(id)
}