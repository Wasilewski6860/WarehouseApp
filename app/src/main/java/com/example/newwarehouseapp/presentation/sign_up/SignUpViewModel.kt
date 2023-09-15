package com.example.newwarehouseapp.presentation.sign_up

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newwarehouseapp.domain.models.Receiver
import com.example.newwarehouseapp.domain.models.Supplier
import com.example.newwarehouseapp.domain.use_cases.receiver.AddReceiverUseCase
import com.example.newwarehouseapp.domain.use_cases.supplier.AddSupplierUseCase
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val addReceiverUseCase : AddReceiverUseCase,
    private val addSupplierUseCase : AddSupplierUseCase
) : ViewModel() {

    private val _receiver = MutableLiveData<Receiver>()
    val receiver: LiveData<Receiver> = _receiver

    private val _supplier = MutableLiveData<Supplier>()
    val supplier: LiveData<Supplier> = _supplier

    private fun insertNewReceiver(receiver : Receiver) {
        viewModelScope.launch {
            addReceiverUseCase.execute(receiver)
        }
    }

    private fun insertNewSupplier(supplier: Supplier) {
        viewModelScope.launch {
            addSupplierUseCase.execute(supplier)
        }
    }

    fun addNewReceiver(
        name: String,
        password : String,
        phone: String,
        email : String

        ) {
        val newReceiver = Receiver(
            id = 0,
            name = name,
            password = password,
            phone  = phone,
            email = email

        )
        insertNewReceiver(newReceiver)
    }

    fun addNewSupplier(
        name: String,
        password : String,
        phone: String,
        email : String

    ) {
        val newSupplier = Supplier(
            id = 0,
            name = name,
            password = password,
            phone  = phone,
            email = email

        )
        insertNewSupplier(newSupplier)
    }

    fun isNameValid(name : String) : Boolean = !TextUtils.isEmpty(name)
    fun isEmailValid(email : String) : Boolean{
        if (TextUtils.isEmpty(email)) {
            return false
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }
    }

    fun isPhoneValid(phone : String) : Boolean{
        if (TextUtils.isEmpty(phone)) {
            return false
        } else {
            return android.util.Patterns.PHONE.matcher(phone).matches()
        }
    }

    fun isInputIsValid(
        name: String
    ) = (name.isNotBlank())

}