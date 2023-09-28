package com.example.newwarehouseapp.data.repository

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
import com.example.newwarehouseapp.domain.repositories.WarehouseRepository
import org.koin.core.qualifier.named

class WarehouseRepositoryImpl(private val warehouseStorage: WarehouseStorage) : WarehouseRepository {


    private fun mapToData(receiver: Receiver): ReceiverDto {
        with(receiver) {
            return ReceiverDto(
                id = id,
                name = name,
                phone = phone,
                email = email,
                password = password
            )
        }
    }
    private fun mapToDomain(receiverDto: ReceiverDto): Receiver {
        with(receiverDto) {
            return Receiver(
                id = id,
                name = name,
                phone = phone,
                email = email,
                password = password
            )
        }
    }


    private fun mapToData(supplier: Supplier): SupplierDto {
        with(supplier) {
            return SupplierDto(
                id = id,
                name = name,
                phone = phone,
                email = email,
                password = password
            )
        }
    }
    private fun mapToDomain(supplierDto: SupplierDto): Supplier {
        with(supplierDto) {
            return Supplier(
                id = id,
                name = name,
                phone = phone,
                email = email,
                password = password
            )
        }
    }

    private fun mapToData(product: Product): ProductDto {
        with(product) {
            return ProductDto(
                id = id,
                name = name,
                description = description,
                price = price
            )
        }
    }
    private fun mapToDomain(productDto: ProductDto): Product {
        with(productDto) {
            return Product(
                id = id,
                name = name,
                description = description,
                price = price
            )
        }
    }

    private fun mapToData(outputNote: OutputNote): OutputNoteDto {
        with(outputNote) {
            return OutputNoteDto(
                id = id,
                idOfReceiver = idOfReceiver,
                outputNoteIdOfProduct = outputNoteIdOfProduct,
                count = count
            )
        }
    }
    private fun mapToDomain(outputNoteDto: OutputNoteDto): OutputNote {
        with(outputNoteDto) {
            return OutputNote(
                id = id,
                idOfReceiver = idOfReceiver,
                outputNoteIdOfProduct = outputNoteIdOfProduct,
                count = count
            )
        }
    }

    private fun mapToData(inputNote: InputNote): InputNoteDto {
        with(inputNote) {
            return InputNoteDto(
                id = id,
                idOfSupplier = idOfSupplier,
                inputNoteIdOfProduct = inputNoteIdOfProduct,
                count = count
            )
        }
    }
    private fun mapToDomain(inputNoteDto: InputNoteDto): InputNote {
        with(inputNoteDto) {
            return InputNote(
                id = id,
                idOfSupplier = idOfSupplier,
                inputNoteIdOfProduct = inputNoteIdOfProduct,
                count = count
            )
        }
    }

    private fun mapToData(productOnWarehouse: ProductOnWarehouse): ProductOnWarehouseDto {
        with(productOnWarehouse) {
            return ProductOnWarehouseDto(
                id =id,
                warehouseIdOfProduct = warehouseIdOfProduct,
                count = count
            )
        }
    }
    private fun mapToDomain(productOnWarehouseDto: ProductOnWarehouseDto): ProductOnWarehouse {
        with(productOnWarehouseDto) {
            return ProductOnWarehouse(
                id =id,
                warehouseIdOfProduct = warehouseIdOfProduct,
                count = count
            )
        }
    }

    private fun mapToData(productWithProductOnWarehouse: ProductWithProductOnWarehouse): ProductWithProductOnWarehouseDto {
        with(productWithProductOnWarehouse) {
            return ProductWithProductOnWarehouseDto(
                id = id,
                name = name,
                description = description,
                price= price,
                productOnWarehouseDto = mapToData(productOnWarehouse)
            )
        }
    }
    private fun mapToDomain(productWithProductOnWarehouseDto: ProductWithProductOnWarehouseDto): ProductWithProductOnWarehouse {
        with(productWithProductOnWarehouseDto) {
            return ProductWithProductOnWarehouse(
                id = id,
                name = name,
                description = description,
                price= price,
                productOnWarehouse = mapToDomain(productOnWarehouseDto)
            )
        }
    }


    private fun mapToData(receiverWithOutputNotes : ReceiverWithOutputNotes): ReceiverWithOutputNotesDto {
        with(receiverWithOutputNotes) {
            return ReceiverWithOutputNotesDto(
                id = id,
                name = name,
                phone = phone,
                email = email,
                password = password,
                outputNotes = outputNotes.map { outputNote ->
                    mapToData(outputNote)
                }
            )
        }
    }
    private fun mapToDomain(receiverWithOutputNotesDto : ReceiverWithOutputNotesDto): ReceiverWithOutputNotes {
        with(receiverWithOutputNotesDto) {
            return ReceiverWithOutputNotes(
                id = id,
                name = name,
                phone = phone,
                email = email,
                password = password,
                outputNotes = outputNotes.map { outputNote ->
                    mapToDomain(outputNote)
                }
            )
        }
    }



    private fun mapToData(supplierWithInputNotes: SupplierWithInputNotes): SupplierWithInputNotesDto {
        with(supplierWithInputNotes) {
            return SupplierWithInputNotesDto(
                id = id,
                name = name,
                phone = phone,
                email = email,
                password = password,
                inputNotes = inputNotes.map { inputNote ->
                    mapToData(inputNote)
                }
            )
        }
    }
    private fun mapToDomain(supplierWithInputNotesDto : SupplierWithInputNotesDto): SupplierWithInputNotes {
        with(supplierWithInputNotesDto) {
            return SupplierWithInputNotes(
                id = id,
                name = name,
                phone = phone,
                email = email,
                password = password,
                inputNotes = inputNotes.map { inputNote ->
                    mapToDomain(inputNote)
                }
            )
        }
    }


    private fun mapToData(outputNoteWithProduct: OutputNoteWithProduct): OutputNoteWithProductDto {
        with(outputNoteWithProduct) {
            return OutputNoteWithProductDto(
                id =id,
                idOfReceiver = idOfReceiver,
                outputNoteIdOfProduct = outputNoteIdOfProduct,
                count = count,
                product = mapToData(product)
            )
        }
    }
    private fun mapToDomain(outputNoteWithProductDto: OutputNoteWithProductDto): OutputNoteWithProduct {
        with(outputNoteWithProductDto) {
            return OutputNoteWithProduct(
                id =id,
                idOfReceiver = idOfReceiver,
                outputNoteIdOfProduct = outputNoteIdOfProduct,
                count = count,
                product = mapToDomain(product)
            )
        }
    }


    private fun mapToData(inputNoteWithProduct: InputNoteWithProduct): InputNoteWithProductDto {
        with(inputNoteWithProduct) {
            return InputNoteWithProductDto(
                id =id,
                idOfSupplier = idOfSupplier,
                inputNoteIdOfProduct = inputNoteIdOfProduct,
                count = count,
                product = mapToData(product)
            )
        }
    }
    private fun mapToDomain(inputNoteWithProductDto: InputNoteWithProductDto): InputNoteWithProduct {
        with(inputNoteWithProductDto) {
            return InputNoteWithProduct(
                id =id,
                idOfSupplier = idOfSupplier,
                inputNoteIdOfProduct = inputNoteIdOfProduct,
                count = count,
                product = mapToDomain(product)
            )
        }
    }


    override suspend fun addReceiver(receiver: Receiver) {
        val mappedReceiver = mapToData(receiver)
        warehouseStorage.insertReceiver(mappedReceiver)
    }
    override suspend fun editReceiver(receiver: Receiver) {
        val mappedReceiver = mapToData(receiver)
        warehouseStorage.editReceiver(mappedReceiver)
    }
    override suspend fun deleteReceiver(receiver: Receiver) {
        val mappedReceiver = mapToData(receiver)
        warehouseStorage.deleteReceiver(mappedReceiver)
    }
    override suspend fun getReceiverById(id: Int): Receiver {
        val resultFromData = warehouseStorage.getReceiverById(id)
        return mapToDomain(resultFromData)
    }

    override suspend fun getReceiverByName(name: String): Receiver {
        val resultFromData = warehouseStorage.getReceiverByName(name)
        return mapToDomain(resultFromData)
    }

    override suspend fun getReceiversByName(name: String): List<Receiver> {
        val resultFromData = warehouseStorage.getReceiversByName(name)
        return resultFromData.map { CatDto ->
            mapToDomain(CatDto)
        }
    }

    override suspend fun getReceiversByNameAndPassword(
        name: String,
        password: String
    ): List<Receiver> {
        val resultFromData = warehouseStorage.getReceiversByNameAndPassword(name,password)
        return resultFromData.map { CatDto ->
            mapToDomain(CatDto)
        }
    }

    override suspend fun getReceivers(): List<Receiver> {
        val resultFromData = warehouseStorage.getReceivers()
        return resultFromData.map { CatDto ->
            mapToDomain(CatDto)
        }
    }


    override suspend fun addSupplier(supplier: Supplier) {
        val mappedReceiver = mapToData(supplier)
        warehouseStorage.insertSupplier(mappedReceiver)
    }
    override suspend fun editSupplier(supplier: Supplier) {
        val mappedReceiver = mapToData(supplier)
        warehouseStorage.editSupplier(mappedReceiver)
    }
    override suspend fun deleteSupplier(supplier: Supplier) {
        val mappedSupplier = mapToData(supplier)
        warehouseStorage.deleteSupplier(mappedSupplier)
    }
    override suspend fun getSupplierById(id: Int): Supplier {
        val resultFromData = warehouseStorage.getSupplierById(id)
        return mapToDomain(resultFromData)
    }
    override suspend fun getSupplierByName(name: String): Supplier {
        val resultFromData = warehouseStorage.getSupplierByName(name)
        return mapToDomain(resultFromData)
    }

    override suspend fun getSuppliersByName(name: String): List<Supplier> {
        val resultFromData = warehouseStorage.getSuppliersByName(name)
        return resultFromData.map { SupplierDto ->
            mapToDomain(SupplierDto)
        }
    }

    override suspend fun getSuppliersByNameAndPassword(
        name: String,
        password: String
    ): List<Supplier> {
        val resultFromData = warehouseStorage.getSuppliersByNameAndPassword(name,password)
        return resultFromData.map { SupplierDto ->
            mapToDomain(SupplierDto)
        }
    }

    override suspend fun getSuppliers(): List<Supplier> {
        val resultFromData = warehouseStorage.getSuppliers()
        return resultFromData.map { SupplierDto ->
            mapToDomain(SupplierDto)
        }
    }


    override suspend fun addProduct(product: Product) : Long {
        val mappedProduct = mapToData(product)
        return warehouseStorage.insertProduct(mappedProduct)
    }
    override suspend fun editProduct(product: Product) {
        val mappedProduct = mapToData(product)
        warehouseStorage.editProduct(mappedProduct)
    }
    override suspend fun deleteProduct(product: Product) {
        val mappedProduct = mapToData(product)
        warehouseStorage.deleteProduct(mappedProduct)
    }
    override suspend fun getProductByName(name: String): Product {
        val resultFromData = warehouseStorage.getProductByName(name)
        return mapToDomain(resultFromData)
    }
    override suspend fun getProductById(id: Int): Product {
        val resultFromData = warehouseStorage.getProductById(id)
        return mapToDomain(resultFromData)
    }
    override suspend fun getProducts(): List<Product> {
        val resultFromData = warehouseStorage.getProducts()
        return resultFromData.map { productDto ->
            mapToDomain(productDto)
        }
    }


    override suspend fun addOutputNote(outputNote: OutputNote) {
        val mappedOutputNote = mapToData(outputNote)
        warehouseStorage.insertOutputNote(mappedOutputNote)
    }
    override suspend fun editOutputNote(outputNote: OutputNote) {
        val mappedOutputNote = mapToData(outputNote)
        warehouseStorage.editOutputNote(mappedOutputNote)
    }
    override suspend fun deleteOutputNote(outputNote: OutputNote) {
        val mappedOutputNote = mapToData(outputNote)
        warehouseStorage.deleteOutputNote(mappedOutputNote)
    }
    override suspend fun getOutputNoteById(id: Int): OutputNote {
        val resultFromData = warehouseStorage.getOutputNoteById(id)
        return mapToDomain(resultFromData)
    }
    override suspend fun getOutputNotes(): List<OutputNote> {
        val resultFromData = warehouseStorage.getOutputNotes()
        return resultFromData.map { outputNoteDto ->
            mapToDomain(outputNoteDto)
        }
    }


    override suspend fun addInputNote(inputNote: InputNote) {
        val mappedInputNote = mapToData(inputNote)
        warehouseStorage.insertInputNote(mappedInputNote)
    }
    override suspend fun editInputNote(inputNote: InputNote) {
        val mappedInputNote = mapToData(inputNote)
        warehouseStorage.editInputNote(mappedInputNote)
    }
    override suspend fun deleteInputNote(inputNote: InputNote) {
        val mappedInputNote = mapToData(inputNote)
        warehouseStorage.deleteInputNote(mappedInputNote)
    }
    override suspend fun getInputNoteById(id: Int): InputNote {
        val resultFromData = warehouseStorage.getInputNoteById(id)
        return mapToDomain(resultFromData)
    }
    override suspend fun getInputNotes(): List<InputNote> {
        val resultFromData = warehouseStorage.getInputNotes()
        return resultFromData.map { inputNoteDto ->
            mapToDomain(inputNoteDto)
        }
    }


    override suspend fun addProductWithProductOnWarehouse(productWithProductOnWarehouse: ProductWithProductOnWarehouse) {
        var mappedProduct = mapToData(
            Product(
                id = productWithProductOnWarehouse.id,
                name = productWithProductOnWarehouse.name,
                description = productWithProductOnWarehouse.description,
                price = productWithProductOnWarehouse.price
            )
        )
        var mappedProductOnWarehouse = mapToData(productWithProductOnWarehouse.productOnWarehouse)
        warehouseStorage.insertProduct(mappedProduct)
        warehouseStorage.insertProductOnWarehouse(mappedProductOnWarehouse)
    }

    override suspend fun addProductOnWarehouse(productOnWarehouse: ProductOnWarehouse) {
        val mappedOutputNote = mapToData(productOnWarehouse)
        warehouseStorage.insertProductOnWarehouse(mappedOutputNote)
    }

    override suspend fun editProductWithProductOnWarehouse(productWithProductOnWarehouse: ProductWithProductOnWarehouse) {
        var mappedProduct = mapToData(
            Product(
                id = productWithProductOnWarehouse.id,
                name = productWithProductOnWarehouse.name,
                description = productWithProductOnWarehouse.description,
                price = productWithProductOnWarehouse.price
            )
        )
        var mappedProductOnWarehouse = mapToData(productWithProductOnWarehouse.productOnWarehouse)
        warehouseStorage.editProduct(mappedProduct)
        warehouseStorage.editProductOnWarehouse(mappedProductOnWarehouse)
    }
    override suspend fun getProductWithProductOnWarehouseById(id: Int): ProductWithProductOnWarehouse {
        val resultFromData = warehouseStorage.getProductWithProductOnWarehouseById(id)
        return mapToDomain(resultFromData)
    }
    override suspend fun getProductsWithProductOnWarehouse(): List<ProductWithProductOnWarehouse> {
        val resultFromData = warehouseStorage.getProductsWithProductOnWarehouse()
        return resultFromData.map { productWithProductOnWarehouseDto ->
            mapToDomain(productWithProductOnWarehouseDto)
        }
    }

    override suspend fun getProductsWithProductOnWarehouseByName(name: String): List<ProductWithProductOnWarehouse> {
        val resultFromData = warehouseStorage.getProductsWithProductOnWarehouseByName(name)
        return resultFromData.map { productWithProductOnWarehouseDto ->
            mapToDomain(productWithProductOnWarehouseDto)
        }
    }

    override suspend fun getProductsWithProductOnWarehouseSortedByName(): List<ProductWithProductOnWarehouse> {
        val resultFromData = warehouseStorage.getProductsWithProductOnWarehouseSortedByName()
        return resultFromData.map { productWithProductOnWarehouseDto ->
            mapToDomain(productWithProductOnWarehouseDto)
        }
    }

    override suspend fun getProductsWithProductOnWarehouseSortedByPrice(): List<ProductWithProductOnWarehouse> {
        val resultFromData = warehouseStorage.getProductsWithProductOnWarehouseSortedByPrice()
        return resultFromData.map { productWithProductOnWarehouseDto ->
            mapToDomain(productWithProductOnWarehouseDto)
        }
    }


    override suspend fun getReceiverWithOutputNotesById(id: Int): ReceiverWithOutputNotes {
        val resultFromData = warehouseStorage.getReceiverWithOutputNotesById(id)
        return mapToDomain(resultFromData)
    }
    override suspend fun getReceiverWithOutputNotesByName(name: String): ReceiverWithOutputNotes {
        val resultFromData = warehouseStorage.getReceiverWithOutputNotesByName(name)
        return mapToDomain(resultFromData)
    }
    override suspend fun getReceiversWithOutputNotes(): List<ReceiverWithOutputNotes> {
        val resultFromData = warehouseStorage.getReceiversWithOutputNotes()
        return resultFromData.map { receiverWithOutputNotesDto ->
            mapToDomain(receiverWithOutputNotesDto)
        }
    }


    override suspend fun getSupplierWithInputNotesById(id: Int): SupplierWithInputNotes {
        val resultFromData = warehouseStorage.getSupplierWithInputNotesById(id)
        return mapToDomain(resultFromData)
    }
    override suspend fun getSupplierWithInputNotesByName(name: String): SupplierWithInputNotes {
        val resultFromData = warehouseStorage.getSupplierWithInputNotesByName(name)
        return mapToDomain(resultFromData)
    }
    override suspend fun getSupplierWithInputNotes(): List<SupplierWithInputNotes> {
        val resultFromData = warehouseStorage.getSupplierWithInputNotes()
        return resultFromData.map { supplierWithInputNotesDto ->
            mapToDomain(supplierWithInputNotesDto)
        }
    }


    override suspend fun getOutputNoteWithProductById(id: Int): OutputNoteWithProduct {
        val resultFromData = warehouseStorage.getOutputNoteWithProductById(id)
        return mapToDomain(resultFromData)
    }

    override suspend fun getOutputNotesWithProductById(id: Int): List<OutputNoteWithProduct> {
        val resultFromData = warehouseStorage.getOutputNotesWithProductById(id)
        return resultFromData.map { outputNoteWithProductDto ->
            mapToDomain(outputNoteWithProductDto)
        }
    }

    override suspend fun getOutputNotesWithProduct(): List<OutputNoteWithProduct> {
        val resultFromData = warehouseStorage.getOutputNotesWithProduct()
        return resultFromData.map { outputNoteWithProductDto ->
            mapToDomain(outputNoteWithProductDto)
        }
    }


    override suspend fun getInputNoteWithProductById(id: Int): InputNoteWithProduct {
        val resultFromData = warehouseStorage.getInputNoteWithProductById(id)
        return mapToDomain(resultFromData)
    }

    override suspend fun getInputNotesWithProductById(id: Int): List<InputNoteWithProduct> {
        val resultFromData = warehouseStorage.getInputNotesWithProductById(id)
        return resultFromData.map { outputNoteWithProductDto ->
            mapToDomain(outputNoteWithProductDto)
        }
    }

    override suspend fun getInputNotesWithProduct(): List<InputNoteWithProduct> {
        val resultFromData = warehouseStorage.getInputNotesWithProduct()
        return resultFromData.map { outputNoteWithProductDto ->
            mapToDomain(outputNoteWithProductDto)
        }
    }

}