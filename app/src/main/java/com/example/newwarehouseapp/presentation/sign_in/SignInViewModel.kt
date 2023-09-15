package com.example.newwarehouseapp.presentation.sign_in

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newwarehouseapp.domain.models.Receiver
import com.example.newwarehouseapp.domain.models.Supplier
import com.example.newwarehouseapp.domain.use_cases.receiver.GetReceiverByNameUseCase
import com.example.newwarehouseapp.domain.use_cases.receiver.GetReceiversByNameAndPasswordUseCase
import com.example.newwarehouseapp.domain.use_cases.receiver.GetReceiversByNameUseCase
import com.example.newwarehouseapp.domain.use_cases.supplier.GetSupplierByNameUseCase
import com.example.newwarehouseapp.domain.use_cases.supplier.GetSuppliersByNameAndPasswordUseCase
import com.example.newwarehouseapp.domain.use_cases.supplier.GetSuppliersByNameUseCase
import kotlinx.coroutines.launch
import kotlin.reflect.KFunction1

class SignInViewModel(
    private val getReceiverByNameUseCase : GetReceiverByNameUseCase,
    private val getReceiversByNameUseCase : GetReceiversByNameUseCase,
    private val getSupplierByNameUseCase : GetSupplierByNameUseCase,
    private val getSuppliersByNameUseCase : GetSuppliersByNameUseCase,
    private val getReceiversByNameAndPasswordUseCase : GetReceiversByNameAndPasswordUseCase,
    private val getSuppliersByNameAndPasswordUseCase : GetSuppliersByNameAndPasswordUseCase
) : ViewModel() {

    private val _receiver = MutableLiveData<Receiver>()
    val receiver: LiveData<Receiver> = _receiver

    private val _supplier = MutableLiveData<Supplier>()
    val supplier: LiveData<Supplier> = _supplier

     fun signInReceiver(name: String, password: String, showNoReceiver: () -> Unit, showWrongPassword: () -> Unit){
         var isPasswordIncorrect : Boolean = false
        viewModelScope.launch {

            if ( !getReceiversByNameAndPasswordUseCase.execute(name,password).isEmpty() )
                _receiver.value = getReceiverByNameUseCase.execute(name)
            else if(!getReceiversByNameUseCase.execute(name).isEmpty() && getReceiversByNameAndPasswordUseCase.execute(name,password).isEmpty()) showNoReceiver()
            else showWrongPassword()
        }
    }

    fun signInSupplier(name : String,password : String, showNoSupplier: () -> Unit, showWrongPassword: () -> Unit){
        viewModelScope.launch {

            if ( !getSuppliersByNameAndPasswordUseCase.execute(name,password).isEmpty() )
                _supplier.value = getSupplierByNameUseCase.execute(name)
            else if(!getSuppliersByNameUseCase.execute(name).isEmpty() && getSuppliersByNameAndPasswordUseCase.execute(name,password).isEmpty()) showNoSupplier()
            else showWrongPassword()
        }
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