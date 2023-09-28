package com.example.newwarehouseapp.domain.repositories

import com.example.newwarehouseapp.data.db.dto.relations.InputNoteWithProductDto
import com.example.newwarehouseapp.data.db.dto.relations.ProductWithProductOnWarehouseDto
import com.example.newwarehouseapp.data.db.dto.relations.SupplierWithInputNotesDto
import com.example.newwarehouseapp.data.db.relations.OutputNoteWithProductDto
import com.example.newwarehouseapp.data.db.relations.ReceiverWithOutputNotesDto
import com.example.newwarehouseapp.domain.models.InputNote
import com.example.newwarehouseapp.domain.models.InputNoteWithProduct
import com.example.newwarehouseapp.domain.models.OutputNote
import com.example.newwarehouseapp.domain.models.OutputNoteWithProduct
import com.example.newwarehouseapp.domain.models.Product
import com.example.newwarehouseapp.domain.models.ProductOnWarehouse
import com.example.newwarehouseapp.domain.models.ProductWithProductOnWarehouse
import com.example.newwarehouseapp.domain.models.Receiver
import com.example.newwarehouseapp.domain.models.ReceiverWithOutputNotes
import com.example.newwarehouseapp.domain.models.Supplier
import com.example.newwarehouseapp.domain.models.SupplierWithInputNotes

interface WarehouseRepository {

    suspend fun addReceiver(receiver : Receiver)
    suspend fun editReceiver(receiver : Receiver)
    suspend fun deleteReceiver(receiver: Receiver)
    suspend fun getReceiverById(id : Int) : Receiver
    suspend fun getReceiverByName(name : String): Receiver
    suspend fun getReceiversByName(name : String): List<Receiver>
    suspend fun getReceiversByNameAndPassword(name : String, password : String): List<Receiver>
    suspend fun getReceivers() : List<Receiver>

    suspend fun addSupplier(supplier: Supplier)
    suspend fun editSupplier(supplier: Supplier)
    suspend fun deleteSupplier(supplier: Supplier)
    suspend fun getSupplierById(id : Int) : Supplier
    suspend fun getSupplierByName(name : String) : Supplier
    suspend fun getSuppliersByName(name : String): List<Supplier>
    suspend fun getSuppliersByNameAndPassword(name : String,password: String): List<Supplier>
    suspend fun getSuppliers() : List<Supplier>

    suspend fun addProduct(product: Product) : Long
    suspend fun editProduct(product: Product)
    suspend fun deleteProduct(product: Product)
    suspend fun getProductByName(name : String): Product
    suspend fun getProductById(id : Int): Product
    suspend fun getProducts(): List<Product>

    suspend fun addOutputNote(outputNote : OutputNote)
    suspend fun editOutputNote(outputNote : OutputNote)
    suspend fun deleteOutputNote(outputNote : OutputNote)
    suspend fun getOutputNoteById(id : Int): OutputNote
    suspend fun getOutputNotes(): List<OutputNote>

    suspend fun addInputNote(inputNote: InputNote)
    suspend fun editInputNote(inputNote: InputNote)
    suspend fun deleteInputNote(inputNote: InputNote)
    suspend fun getInputNoteById(id : Int): InputNote
    suspend fun getInputNotes(): List<InputNote>

    suspend fun addProductWithProductOnWarehouse(productWithProductOnWarehouse: ProductWithProductOnWarehouse)
    suspend fun addProductOnWarehouse(productOnWarehouse: ProductOnWarehouse)
    suspend fun editProductWithProductOnWarehouse(productWithProductOnWarehouse: ProductWithProductOnWarehouse)
    suspend fun getProductWithProductOnWarehouseById(id : Int) : ProductWithProductOnWarehouse
    suspend fun getProductsWithProductOnWarehouse() : List<ProductWithProductOnWarehouse>
    suspend fun getProductsWithProductOnWarehouseByName(name : String) : List<ProductWithProductOnWarehouse>

    suspend fun getProductsWithProductOnWarehouseSortedByName() : List<ProductWithProductOnWarehouse>
    suspend fun getProductsWithProductOnWarehouseSortedByPrice() : List<ProductWithProductOnWarehouse>

    suspend fun getReceiverWithOutputNotesById(id : Int) : ReceiverWithOutputNotes
    suspend fun getReceiverWithOutputNotesByName(name : String) : ReceiverWithOutputNotes
    suspend fun getReceiversWithOutputNotes() : List<ReceiverWithOutputNotes>

    suspend fun getSupplierWithInputNotesById(id : Int) : SupplierWithInputNotes
    suspend fun getSupplierWithInputNotesByName(name : String) : SupplierWithInputNotes
    suspend fun getSupplierWithInputNotes() : List<SupplierWithInputNotes>

    suspend fun getOutputNoteWithProductById(id : Int) : OutputNoteWithProduct
    suspend fun getOutputNotesWithProductById(id : Int) : List<OutputNoteWithProduct>
    suspend fun getOutputNotesWithProduct() : List<OutputNoteWithProduct>

    suspend fun getInputNoteWithProductById(id : Int) : InputNoteWithProduct
    suspend fun getInputNotesWithProductById(id : Int) : List<InputNoteWithProduct>
    suspend fun getInputNotesWithProduct() : List<InputNoteWithProduct>

}