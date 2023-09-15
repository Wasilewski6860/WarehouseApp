package com.example.newwarehouseapp.presentation.add_output_note

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newwarehouseapp.domain.models.OutputNote
import com.example.newwarehouseapp.domain.models.Product
import com.example.newwarehouseapp.domain.models.ProductOnWarehouse
import com.example.newwarehouseapp.domain.models.ProductWithProductOnWarehouse
import com.example.newwarehouseapp.domain.use_cases.output_note.AddOutputNoteUseCase
import com.example.newwarehouseapp.domain.use_cases.product.AddProductUseCase
import com.example.newwarehouseapp.domain.use_cases.product_with_product_on_warehouse.EditProductWithProductOnWarehouseUseCase
import com.example.newwarehouseapp.domain.use_cases.product_with_product_on_warehouse.GetProductsWithProductOnWarehouseUseCase
import com.example.newwarehouseapp.presentation.screen_state.ScreenState
import kotlinx.coroutines.launch

class AddOutputNoteViewModel(
    private val addOutputNoteUseCase: AddOutputNoteUseCase,
    private val getProductsWithProductOnWarehouseUseCase: GetProductsWithProductOnWarehouseUseCase,
    private val editProductWithProductOnWarehouseUseCase : EditProductWithProductOnWarehouseUseCase
) : ViewModel() {

    var product: ProductWithProductOnWarehouse = ProductWithProductOnWarehouse(
        id = 0,
        name = "",
        description = "",
        price = 0,
        productOnWarehouse = ProductOnWarehouse(id = 0, warehouseIdOfProduct = 0, count = 0)
    )

    private val _screenState = MutableLiveData<ScreenState>()
    val screenState: LiveData<ScreenState> = _screenState

    private val _products = MutableLiveData<List<ProductWithProductOnWarehouse>>()
    val productsList: LiveData<List<ProductWithProductOnWarehouse>> = _products

    fun fetchProducts() {
        _screenState.value = ScreenState.Loading
        viewModelScope.launch {
            try {
                _products.value = getProductsWithProductOnWarehouseUseCase.execute()
                _screenState.value =
                    if (productsList.value?.isEmpty() == true) ScreenState.Empty else ScreenState.Content

            } catch (e: Exception) {
                _screenState.value = ScreenState.Error
            }
        }
    }

    fun isInputCountValid(count : String) : Boolean{
        if (count.isEmpty()) return false
        var countInt = count.toInt()
        if (countInt<0) return false
//        !!! <=
        if (countInt <= product.productOnWarehouse.count) return true else return false
    }


    fun addOutputNote(
        idOfReceiver : Int,
        count : Int
    ){
        var productClone = product
        viewModelScope.launch {
            addOutputNoteUseCase.execute(
                OutputNote(
                    id = 0,
                    idOfReceiver = idOfReceiver,
                    outputNoteIdOfProduct = product.id,
                    count = count
                )
            )
            Log.d("LOOK HERE",product.productOnWarehouse.count.toString() )
            product.productOnWarehouse.count -= count
            productClone = product
            Log.d("LOOK HERE",product.productOnWarehouse.count.toString() )
            editProductWithProductOnWarehouseUseCase.execute(
                product
            )
        }
    }
}