package com.example.newwarehouseapp.data.db.impl

import com.example.newwarehouseapp.data.db.WarehouseDatabase
import com.example.newwarehouseapp.data.db.WarehouseStorage
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

class WarehouseStorageImpl(warehouseDatabase: WarehouseDatabase): WarehouseStorage {

    private val warehouseDao = warehouseDatabase.dao

    override suspend fun insertReceiver(receiverDto: ReceiverDto) = warehouseDao.insertReceiver(receiverDto)
    override suspend fun editReceiver(receiverDto: ReceiverDto) = warehouseDao.editReceiver(receiverDto)
    override suspend fun deleteReceiver(receiverDto: ReceiverDto) = warehouseDao.deleteReceiver(receiverDto)
    override suspend fun getReceiverByName(name: String): ReceiverDto = warehouseDao.getReceiverByName(name)
    override suspend fun getReceiversByName(name: String): List<ReceiverDto> = warehouseDao.getReceiversByName(name)
    override suspend fun getReceiversByNameAndPassword(name: String, password: String): List<ReceiverDto> = warehouseDao.getReceiversByNameAndPassword(name, password)

    override suspend fun getReceiverById(id: Int): ReceiverDto = warehouseDao.getReceiverById(id)
    override suspend fun getReceivers(): List<ReceiverDto>  = warehouseDao.getReceivers()

    override suspend fun insertSupplier(supplierDto: SupplierDto)= warehouseDao.insertSupplier(supplierDto)
    override suspend fun editSupplier(supplierDto: SupplierDto) = warehouseDao.editSupplier(supplierDto)
    override suspend fun deleteSupplier(supplierDto: SupplierDto) = warehouseDao.deleteSupplier(supplierDto)
    override suspend fun getSupplierByName(name: String): SupplierDto = warehouseDao.getSupplierByName(name)
    override suspend fun getSuppliersByName(name: String): List<SupplierDto> = warehouseDao.getSuppliersByName(name)
    override suspend fun getSuppliersByNameAndPassword(name: String, password: String): List<SupplierDto> = warehouseDao.getSuppliersByNameAndPassword(name,password)

    override suspend fun getSupplierById(id: Int): SupplierDto = warehouseDao.getSupplierById(id)
    override suspend fun getSuppliers(): List<SupplierDto> = warehouseDao.getSuppliers()

    override suspend fun insertProduct(productDto: ProductDto) : Long = warehouseDao.insertProduct(productDto)
    override suspend fun editProduct(productDto: ProductDto)  = warehouseDao.editProduct(productDto)
    override suspend fun deleteProduct(productDto: ProductDto)  = warehouseDao.deleteProduct(productDto)
    override suspend fun getProductByName(name: String): ProductDto  = warehouseDao.getProductByName(name)
    override suspend fun getProductById(id: Int): ProductDto = warehouseDao.getProductById(id)
    override suspend fun getProducts(): List<ProductDto> = warehouseDao.getProducts()

    override suspend fun insertOutputNote(outNoteDto: OutputNoteDto) = warehouseDao.insertOutputNote(outNoteDto)
    override suspend fun editOutputNote(outNoteDto: OutputNoteDto) = warehouseDao.editOutputNote(outNoteDto)
    override suspend fun deleteOutputNote(outNoteDto: OutputNoteDto) = warehouseDao.deleteOutputNote(outNoteDto)
    override suspend fun getOutputNoteById(id: Int): OutputNoteDto = warehouseDao.getOutputNoteById(id)
    override suspend fun getOutputNotes(): List<OutputNoteDto> = warehouseDao.getOutputNotes()

    override suspend fun insertInputNote(inputNoteDto: InputNoteDto) = warehouseDao.insertInputNote(inputNoteDto)
    override suspend fun editInputNote(inputNoteDto: InputNoteDto) = warehouseDao.editInputNote(inputNoteDto)
    override suspend fun deleteInputNote(inputNoteDto: InputNoteDto) = warehouseDao.deleteInputNote(inputNoteDto)
    override suspend fun getInputNoteById(id: Int): InputNoteDto = warehouseDao.getInputNoteById(id)
    override suspend fun getInputNotes(): List<InputNoteDto> = warehouseDao.getInputNotes()

    override suspend fun getReceiverWithOutputNotesById(id: Int): ReceiverWithOutputNotesDto = warehouseDao.getReceiverWithOutputNotesById(id)
    override suspend fun getReceiverWithOutputNotesByName(name: String): ReceiverWithOutputNotesDto = warehouseDao.getReceiverWithOutputNotesByName(name)
    override suspend fun getReceiversWithOutputNotes(): List<ReceiverWithOutputNotesDto> = warehouseDao.getReceiversWithOutputNotes()

    override suspend fun getSupplierWithInputNotesById(id: Int): SupplierWithInputNotesDto = warehouseDao.getSupplierWithInputNotesById(id)
    override suspend fun getSupplierWithInputNotesByName(name: String): SupplierWithInputNotesDto = warehouseDao.getSupplierWithInputNotesByName(name)
    override suspend fun getSupplierWithInputNotes(): List<SupplierWithInputNotesDto> = warehouseDao.getSupplierWithInputNotes()

    override suspend fun getOutputNoteWithProductById(id: Int): OutputNoteWithProductDto = warehouseDao.getOutputNoteWithProductById(id)
    override suspend fun getOutputNotesWithProductById(id: Int): List<OutputNoteWithProductDto> = warehouseDao.getOutputNotesWithProductById(id)
    override suspend fun getOutputNotesWithProduct(): List<OutputNoteWithProductDto> = warehouseDao.getOutputNotesWithProduct()

    override suspend fun getInputNoteWithProductById(id: Int): InputNoteWithProductDto = warehouseDao.getInputNoteWithProductById(id)
    override suspend fun getInputNotesWithProductById(id: Int): List<InputNoteWithProductDto> = warehouseDao.getInputNotesWithProductById(id)
    override suspend fun getInputNotesWithProduct(): List<InputNoteWithProductDto> = warehouseDao.getInputNotesWithProduct()

    override suspend fun getProductWithProductOnWarehouseById(id: Int): ProductWithProductOnWarehouseDto = warehouseDao.getProductWithProductOnWarehouseById(id)
    override suspend fun getProductsWithProductOnWarehouse(): List<ProductWithProductOnWarehouseDto> = warehouseDao.getProductsWithProductOnWarehouse()
    override suspend fun getProductsWithProductOnWarehouseByName(name: String): List<ProductWithProductOnWarehouseDto> = warehouseDao.getProductsWithProductOnWarehouseByName(name)
    override suspend fun getProductsWithProductOnWarehouseSortedByName(): List<ProductWithProductOnWarehouseDto> = warehouseDao.getProductsWithProductOnWarehouseSortedByName()

    override suspend fun getProductsWithProductOnWarehouseSortedByPrice(): List<ProductWithProductOnWarehouseDto> = warehouseDao.getProductsWithProductOnWarehouseSortedByPrice()

    override suspend fun insertProductOnWarehouse(productOnWarehouseDto: ProductOnWarehouseDto) = warehouseDao.insertProductOnWarehouse(productOnWarehouseDto)
    override suspend fun editProductOnWarehouse(productOnWarehouseDto: ProductOnWarehouseDto) = warehouseDao.editProductOnWarehouse(productOnWarehouseDto)
}