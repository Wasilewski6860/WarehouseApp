package com.example.newwarehouseapp.presentation.add_product

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newwarehouseapp.domain.models.Product
import com.example.newwarehouseapp.domain.models.ProductOnWarehouse
import com.example.newwarehouseapp.domain.models.ProductWithProductOnWarehouse
import com.example.newwarehouseapp.domain.use_cases.product.AddProductUseCase
import com.example.newwarehouseapp.domain.use_cases.product.DeleteProductUseCase
import com.example.newwarehouseapp.domain.use_cases.product.EditProductUseCase
import com.example.newwarehouseapp.domain.use_cases.product.GetProductByIdUseCase
import com.example.newwarehouseapp.domain.use_cases.product_on_warehouse.AddProductOnWarehouseUseCase
import com.example.newwarehouseapp.domain.use_cases.product_with_product_on_warehouse.AddProductWithProductOnWarehouseUseCase
import com.example.newwarehouseapp.domain.use_cases.product_with_product_on_warehouse.EditProductWithProductOnWarehouseUseCase
import com.example.newwarehouseapp.domain.use_cases.product_with_product_on_warehouse.GetProductWithProductOnWarehouseByIdUseCase
import com.example.newwarehouseapp.domain.use_cases.product_with_product_on_warehouse.GetProductsWithProductOnWarehouseUseCase
import kotlinx.coroutines.launch

class AddProductViewModel(
    private val addProductOnWarehouseUseCase: AddProductOnWarehouseUseCase,
    private val addProductUseCase: AddProductUseCase,
    private val editProductUseCase: EditProductUseCase,
    private val getProductByIdUseCase: GetProductByIdUseCase
) : ViewModel() {

    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product> = _product
    var id : Long = 0

    private fun insertNewProduct(product: Product) {
        viewModelScope.launch {
            id = addProductUseCase.execute(product)
        }
    }

    fun addNewProduct(
        name: String,
        description: String,
        price : Int
    ) {
        val newProduct = Product(
            id = 0,
            name = name,
            description = description,
            price = price
        )
        insertNewProduct(newProduct)
    }

    fun addEmptyProductOnWarehouse(count : Int){
        var productOnWarehouse : ProductOnWarehouse = ProductOnWarehouse(
            id = 0,
            warehouseIdOfProduct = id.toInt(),
            count = count
        )
        viewModelScope.launch {
            addProductOnWarehouseUseCase.execute(productOnWarehouse)
        }
    }


    fun isInputIsValid(
        name: String
    ) = (name.isNotBlank())

    fun isPriceValid(
        price: String
    ) = (price.isNotBlank() && price.toInt()>0)

    fun getProduct(id: Int) {
        viewModelScope.launch {
            _product.value = getProductByIdUseCase.execute(id)
        }
    }

    private fun updateProduct(product: Product) {
        viewModelScope.launch {
            editProductUseCase.execute(product)
        }
    }

    fun editProduct(
        id : Int,
        name: String,
        description: String,
        price : Int
    ) {
        val editedProduct = Product(
            id = id,
            name = name,
            description = description,
            price = price
        )
        updateProduct(editedProduct)
    }


}