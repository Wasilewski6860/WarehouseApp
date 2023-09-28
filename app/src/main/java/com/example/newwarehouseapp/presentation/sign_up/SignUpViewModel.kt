package com.example.newwarehouseapp.presentation.sign_up

import android.text.TextUtils
import android.util.Log
import android.util.Patterns
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
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            return false
        }
        return true
    }

    fun isPhoneValid(phone : String) : Boolean{
        var re = Regex("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}\$")
        var result = phone.matches(re)
        return result
    }

    fun isInputIsValid(
        name: String
    ) = (name.isNotBlank())

    fun standartizingPhone(phone : String): String{
        var re1 = Regex("^(8+[0-9]{10})\\$/gm") //89261234567
        var re2 = Regex("^(7+[0-9]{10})\\$/gm") //79261234567
        var re3 = Regex("^(\\+7+[0-9]{10})\$") //+79261234567
        var re4 = Regex("^(\\+7+\\s+[0-9]{3}+\\s+[0-9]{3}+\\s+[0-9]{2}+\\s+[0-9]{2})$") //+7 926 123 45 67
        var re5 = Regex("^(8+\\(+[0-9]{3}+\\)+[0-9]{3}+\\-+[0-9]{2}+\\-+[0-9]{2})\$") //8(926)123-45-67
        var re6 = Regex("^([0-9]{3}+\\-+[0-9]{2}+\\-+[0-9]{2})\$") //123-45-67
        var re7 = Regex("^([0-9]{10})\$") //9261234567
        var re8 = Regex("^(\\([0-9]{3}\\)[0-9]{7})\$") //(495)1234567
        var re9 = Regex("^(\\([0-9]{3}\\)\\s[0-9]{3}\\s[0-9]{2}\\s[0-9]{2})\$") //(495) 123 45 67
        var re10 = Regex("^(8\\-[0-9]{3}\\-[0-9]{3}\\-[0-9]{2}\\-[0-9]{2})\$") //8-926-123-45-67
        var re11 = Regex("^(8\\s[0-9]{3}\\s[0-9]{4}\\s[0-9]{3})\$") //8 927 1234 234
        var re12 = Regex("^(8\\s[0-9]{3}\\s[0-9]{2}\\s[0-9]{2}\\s[0-9]{3})\$") //8 927 12 12 888
        var re13 = Regex("^(8\\s[0-9]{3}\\s[0-9]{2}\\s[0-9]{3}\\s[0-9]{2})\$") //8 927 12 555 12
        var re14 = Regex("^(8\\s[0-9]{3}\\s[0-9]{3}\\s[0-9]{1}\\s[0-9]{3})\$") //8 927 123 8 123
                                                                                      //

        if (phone.matches(re1) || phone.matches(re2)) //to 123-45-67
            return phone[4].toString()+phone[5].toString()+phone[6].toString()+"-"+phone[7].toString()+phone[8].toString()+"-"+phone[9].toString()+phone[10].toString()
        if (phone.matches(re3)) //to 123-45-67
            return phone[5].toString()+phone[6].toString()+phone[7].toString()+"-"+phone[8].toString()+phone[9].toString()+"-"+phone[10].toString()+phone[11].toString()
        if (phone.matches(re4)) //to 123-45-67
            return phone[7].toString()+phone[8].toString()+phone[9].toString()+"-"+phone[11].toString()+phone[12].toString()+"-"+phone[14].toString()+phone[15].toString()
        if (phone.matches(re5)) //to 123-45-67
            return phone[6].toString()+phone[7].toString()+phone[8].toString()+"-"+phone[10].toString()+phone[11].toString()+"-"+phone[13].toString()+phone[14].toString()
        if (phone.matches(re6)) //to 123-45-67
            return phone
        if (phone.matches(re7)) //to 123-45-67
            return phone[3].toString()+phone[4].toString()+phone[5].toString()+"-"+phone[6].toString()+phone[7].toString()+"-"+phone[8].toString()+phone[9].toString()
        if (phone.matches(re8)) //to 123-45-67
            return phone[5].toString()+phone[6].toString()+phone[7].toString()+"-"+phone[8].toString()+phone[9].toString()+"-"+phone[10].toString()+phone[11].toString()
        if (phone.matches(re9)) //to 123-45-67
            return phone[6].toString()+phone[7].toString()+phone[8].toString()+"-"+phone[10].toString()+phone[11].toString()+"-"+phone[13].toString()+phone[14].toString()
        if (phone.matches(re10)) //to 123-45-67
            return phone[6].toString()+phone[7].toString()+phone[8].toString()+"-"+phone[10].toString()+phone[11].toString()+"-"+phone[13].toString()+phone[14].toString()
        if (phone.matches(re11)) //to 123-45-67
            return phone[6].toString()+phone[7].toString()+phone[8].toString()+"-"+phone[9].toString()+phone[11].toString()+"-"+phone[12].toString()+phone[13].toString()
        if (phone.matches(re12)) //to 123-45-67
            return phone[6].toString()+phone[7].toString()+phone[9].toString()+"-"+phone[10].toString()+phone[12].toString()+"-"+phone[13].toString()+phone[14].toString()
        if (phone.matches(re13)) //to 123-45-67
            return phone[6].toString()+phone[7].toString()+phone[9].toString()+"-"+phone[10].toString()+phone[11].toString()+"-"+phone[13].toString()+phone[14].toString()
        if (phone.matches(re14)) //to 123-45-67
            return phone[6].toString()+phone[7].toString()+phone[8].toString()+"-"+phone[10].toString()+phone[12].toString()+"-"+phone[13].toString()+phone[14].toString()

        return phone
    }
    fun isPasswordInvalid(passwordText: String): String {

        if(passwordText.length < 8)
        {
            return "Minimum 8 Character Password"
        }
        if(!passwordText.matches(".*[A-Z].*".toRegex()))
        {
            return "Must Contain 1 Upper-case Character"
        }
        if(!passwordText.matches(".*[a-z].*".toRegex()))
        {
            return "Must Contain 1 Lower-case Character"
        }
        if(!passwordText.matches(".*[@#\$%^&+=].*".toRegex()))
        {
            return "Must Contain 1 Special Character (@#\$%^&+=)"
        }

        return "Good"

    }

    fun validPhone(phoneText: String): String {
        if(!phoneText.matches(".*[0-9].*".toRegex()))
        {
            return "Must be all Digits"
        }
        if(phoneText.length != 10)
        {
            return "Must be 10 Digits"
        }
        return ""
    }

}