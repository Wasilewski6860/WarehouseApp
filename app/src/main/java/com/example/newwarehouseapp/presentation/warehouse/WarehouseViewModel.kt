package com.example.newwarehouseapp.presentation.warehouse

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newwarehouseapp.domain.constants.SortType
import com.example.newwarehouseapp.domain.models.Product
import com.example.newwarehouseapp.domain.models.ProductWithProductOnWarehouse
import com.example.newwarehouseapp.domain.use_cases.product.GetProductsUseCase
import com.example.newwarehouseapp.domain.use_cases.product_with_product_on_warehouse.GetProductsWithProductOnWarehouseSortedByNameUseCase
import com.example.newwarehouseapp.domain.use_cases.product_with_product_on_warehouse.GetProductsWithProductOnWarehouseSortedByPriceUseCase
import com.example.newwarehouseapp.domain.use_cases.product_with_product_on_warehouse.GetProductsWithProductOnWarehouseUseCase
import com.example.newwarehouseapp.presentation.screen_state.ScreenState
import kotlinx.coroutines.launch

class WarehouseViewModel(
    private val getProductsWithProductOnWarehouseUseCase : GetProductsWithProductOnWarehouseUseCase,
    private val getProductsUseCase: GetProductsUseCase,
    private val getAllProductsSortedByName : GetProductsWithProductOnWarehouseSortedByNameUseCase,
    private val getAllProductsSortedByPrice : GetProductsWithProductOnWarehouseSortedByPriceUseCase
) : ViewModel() {

    private val _screenState = MutableLiveData<ScreenState>()
    val screenState: LiveData<ScreenState> = _screenState

    var sortType = SortType.NAME

    private val _products = MutableLiveData<List<ProductWithProductOnWarehouse>>()
    val productsList: LiveData<List<ProductWithProductOnWarehouse>> = _products
    var allProducts = emptyList<Product>()

    fun fetchProducts() {
        _screenState.value = ScreenState.Loading
        viewModelScope.launch {
            try {
                this@WarehouseViewModel.allProducts = getProductsUseCase.execute()
                Log.d(this@WarehouseViewModel.allProducts.size.toString(),"LOOK HERE")
                _products.value = getProductsWithProductOnWarehouseUseCase.execute()
                _screenState.value =
                    if (productsList.value?.isEmpty() == true) ScreenState.Empty else ScreenState.Content

            } catch (e: Exception) {
                _screenState.value = ScreenState.Error
            }
        }
    }



    fun sortRuns(sortType: SortType){
        _screenState.value = ScreenState.Loading
        when(sortType) {

            SortType.NAME ->{
                viewModelScope.launch {
                    try {
                        _products.value = getAllProductsSortedByName.execute()
                    } catch (e: Exception) {
                    }
                    _screenState.value =
                        if (productsList.value?.isEmpty() == true) ScreenState.Empty else ScreenState.Content
                }
            }
            SortType.PRICE ->{
                viewModelScope.launch {
                    try {
                        _products.value = getAllProductsSortedByPrice.execute()
                    } catch (e: Exception) {
                    }
                }
                _screenState.value =
                    if (productsList.value?.isEmpty() == true) ScreenState.Empty else ScreenState.Content
            }

            else -> {
                viewModelScope.launch {
                    try {
                        _products.value = getProductsWithProductOnWarehouseUseCase.execute()
                    } catch (e: Exception) {
                    }
                }
                _screenState.value =
                    if (productsList.value?.isEmpty() == true) ScreenState.Empty else ScreenState.Content
            }
        }.also {
            this.sortType = sortType
        }
    }

}