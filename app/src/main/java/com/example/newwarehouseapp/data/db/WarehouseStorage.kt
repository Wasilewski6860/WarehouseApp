package com.example.newwarehouseapp.data.db

import com.example.newwarehouseapp.data.db.dto.ProductDto
import com.example.newwarehouseapp.data.db.dto.ProductOnWarehouseDto
import com.example.newwarehouseapp.data.db.dto.input.InputNoteDto
import com.example.newwarehouseapp.data.db.dto.input.SupplierDto
import com.example.newwarehouseapp.data.db.dto.output.OutputNoteDto
import com.example.newwarehouseapp.data.db.dto.output.ReceiverDto
import com.example.newwarehouseapp.data.db.dto.relations.InputNoteWithProductDto
import com.example.newwarehouseapp.data.db.dto.relations.ProductWithProductOnWarehouseDto
import com.example.newwarehouseapp.data.db.dto.relations.SupplierWithInputNotesDto
import com.example.newwarehouseapp.data.db.relations.OutputNoteWithProductDto
import com.example.newwarehouseapp.data.db.relations.ReceiverWithOutputNotesDto

interface WarehouseStorage {


    suspend fun insertReceiver(receiverDto: ReceiverDto)
    suspend fun editReceiver(receiverDto: ReceiverDto)
    suspend fun deleteReceiver(receiverDto: ReceiverDto)
    suspend fun getReceiverByName(name : String): ReceiverDto
    suspend fun getReceiversByName(name : String): List<ReceiverDto>
    suspend fun getReceiversByNameAndPassword(name : String,password : String): List<ReceiverDto>
    suspend fun getReceiverById(id : Int): ReceiverDto
    suspend fun getReceivers(): List<ReceiverDto>


    suspend fun insertSupplier(supplierDto: SupplierDto)
    suspend fun editSupplier(supplierDto: SupplierDto)
    suspend fun deleteSupplier(supplierDto: SupplierDto)
    suspend fun getSupplierByName(name : String): SupplierDto
    suspend fun getSuppliersByName(name : String): List<SupplierDto>
    suspend fun getSuppliersByNameAndPassword(name : String, password: String): List<SupplierDto>
    suspend fun getSupplierById(id : Int): SupplierDto
    suspend fun getSuppliers(): List<SupplierDto>


    suspend fun insertProduct(productDto: ProductDto) : Long
    suspend fun editProduct(productDto: ProductDto)
    suspend fun deleteProduct(productDto: ProductDto)
    suspend fun getProductByName(name : String): ProductDto
    suspend fun getProductById(id : Int): ProductDto
    suspend fun getProducts(): List<ProductDto>


    suspend fun insertOutputNote(outNoteDto: OutputNoteDto)
    suspend fun editOutputNote(outNoteDto: OutputNoteDto)
    suspend fun deleteOutputNote(outNoteDto: OutputNoteDto)
    suspend fun getOutputNoteById(id : Int): OutputNoteDto
    suspend fun getOutputNotes(): List<OutputNoteDto>

    suspend fun insertInputNote(inputNoteDto: InputNoteDto)
    suspend fun editInputNote(inputNoteDto: InputNoteDto)
    suspend fun deleteInputNote(inputNoteDto: InputNoteDto)
    suspend fun getInputNoteById(id : Int): InputNoteDto
    suspend fun getInputNotes(): List<InputNoteDto>

    suspend fun getReceiverWithOutputNotesById(id : Int) : ReceiverWithOutputNotesDto
    suspend fun getReceiverWithOutputNotesByName(name : String) : ReceiverWithOutputNotesDto
    suspend fun getReceiversWithOutputNotes() : List<ReceiverWithOutputNotesDto>

    suspend fun getSupplierWithInputNotesById(id : Int) : SupplierWithInputNotesDto
    suspend fun getSupplierWithInputNotesByName(name : String) : SupplierWithInputNotesDto
    suspend fun getSupplierWithInputNotes() : List<SupplierWithInputNotesDto>


    suspend fun getOutputNoteWithProductById(id : Int) : OutputNoteWithProductDto
    suspend fun getOutputNotesWithProductById(id : Int) : List<OutputNoteWithProductDto>
    suspend fun getOutputNotesWithProduct() : List<OutputNoteWithProductDto>

    suspend fun getInputNoteWithProductById(id : Int) : InputNoteWithProductDto
    suspend fun getInputNotesWithProductById(id : Int) : List<InputNoteWithProductDto>
    suspend fun getInputNotesWithProduct() : List<InputNoteWithProductDto>

    suspend fun getProductWithProductOnWarehouseById(id : Int) : ProductWithProductOnWarehouseDto
    suspend fun getProductsWithProductOnWarehouse() : List<ProductWithProductOnWarehouseDto>
    suspend fun getProductsWithProductOnWarehouseByName(name : String) : List<ProductWithProductOnWarehouseDto>
    suspend fun getProductsWithProductOnWarehouseSortedByName() : List<ProductWithProductOnWarehouseDto>
    suspend fun getProductsWithProductOnWarehouseSortedByPrice() : List<ProductWithProductOnWarehouseDto>

    suspend fun insertProductOnWarehouse(productOnWarehouseDto: ProductOnWarehouseDto)
    suspend fun editProductOnWarehouse(productOnWarehouseDto: ProductOnWarehouseDto)
}