package com.example.newwarehouseapp.presentation.login

import androidx.lifecycle.ViewModel
import com.example.newwarehouseapp.domain.use_cases.output_note_with_product.GetOutputNoteWithProductByIdUseCase
import com.example.newwarehouseapp.domain.use_cases.receiver.AddReceiverUseCase
import com.example.newwarehouseapp.domain.use_cases.receiver.GetReceiverByNameUseCase
import com.example.newwarehouseapp.domain.use_cases.supplier.AddSupplierUseCase
import com.example.newwarehouseapp.domain.use_cases.supplier.GetSupplierByNameUseCase

class LoginViewModel(
    private val addReceiverUseCase : AddReceiverUseCase,
    private val addSupplierUseCase : AddSupplierUseCase,
    private val getReceiverByNameUseCase : GetReceiverByNameUseCase,
    private val getSupplierByNameUseCase : GetSupplierByNameUseCase
) : ViewModel() {



}