package com.example.newwarehouseapp.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
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
import com.example.newwarehouseapp.domain.models.ProductOnWarehouse

@Dao
interface WarehouseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReceiver(receiverDto: ReceiverDto)
    @Update
    suspend fun editReceiver(receiverDto: ReceiverDto)
    @Delete
    suspend fun deleteReceiver(receiverDto: ReceiverDto)
    @Query("SELECT * FROM receivers WHERE name = :name  LIMIT 1")
    suspend fun getReceiverByName(name : String): ReceiverDto
    @Query("SELECT * FROM receivers WHERE name LIKE '%' || :name || '%'")
    suspend fun getReceiversByName(name : String): List<ReceiverDto>

    @Query("SELECT * FROM receivers WHERE name LIKE '%' || :name || '%' and password LIKE :password")
    suspend fun getReceiversByNameAndPassword(name : String, password : String): List<ReceiverDto>

    @Query("SELECT * FROM receivers WHERE id = :id  LIMIT 1")
    suspend fun getReceiverById(id : Int): ReceiverDto
    @Query("SELECT * FROM receivers")
    suspend fun getReceivers(): List<ReceiverDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSupplier(supplierDto: SupplierDto)
    @Update
    suspend fun editSupplier(supplierDto: SupplierDto)
    @Delete
    suspend fun deleteSupplier(supplierDto: SupplierDto)
    @Query("SELECT * FROM suppliers WHERE name = :name  LIMIT 1")
    suspend fun getSupplierByName(name : String): SupplierDto
    @Query("SELECT * FROM suppliers WHERE name LIKE '%' || :name || '%'")
    suspend fun getSuppliersByName(name : String): List<SupplierDto>

    @Query("SELECT * FROM suppliers WHERE name LIKE :name and password LIKE :password")
    suspend fun getSuppliersByNameAndPassword(name : String, password : String): List<SupplierDto>

    @Query("SELECT * FROM suppliers WHERE id = :id  LIMIT 1")
    suspend fun getSupplierById(id : Int): SupplierDto
    @Query("SELECT * FROM suppliers")
    suspend fun getSuppliers(): List<SupplierDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(productDto: ProductDto) : Long
    @Update
    suspend fun editProduct(productDto: ProductDto)
    @Delete
    suspend fun deleteProduct(productDto: ProductDto)
    @Query("SELECT * FROM products WHERE name = :name  LIMIT 1")
    suspend fun getProductByName(name : String): ProductDto
    @Query("SELECT * FROM products WHERE id = :id  LIMIT 1")
    suspend fun getProductById(id : Int): ProductDto
    @Query("SELECT * FROM products")
    suspend fun getProducts(): List<ProductDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOutputNote(outNoteDto: OutputNoteDto)
    @Update
    suspend fun editOutputNote(outNoteDto: OutputNoteDto)
    @Delete
    suspend fun deleteOutputNote(outNoteDto: OutputNoteDto)
    @Query("SELECT * FROM output_notes WHERE id = :id LIMIT 1")
    suspend fun getOutputNoteById(id : Int): OutputNoteDto
    @Query("SELECT * FROM output_notes")
    suspend fun getOutputNotes(): List<OutputNoteDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInputNote(inputNoteDto: InputNoteDto)
    @Update
    suspend fun editInputNote(inputNoteDto: InputNoteDto)
    @Delete
    suspend fun deleteInputNote(inputNoteDto: InputNoteDto)
    @Query("SELECT * FROM input_notes WHERE id = :id LIMIT 1")
    suspend fun getInputNoteById(id : Int): InputNoteDto
    @Query("SELECT * FROM input_notes")
    suspend fun getInputNotes(): List<InputNoteDto>

    @Transaction
    @Query("SELECT id,name, phone,email,password,password FROM receivers WHERE id = :id  LIMIT 1")
    suspend fun getReceiverWithOutputNotesById(id : Int) : ReceiverWithOutputNotesDto
    @Transaction
    @Query("SELECT id,name, phone,email,password FROM receivers WHERE name LIKE :name  LIMIT 1")
    suspend fun getReceiverWithOutputNotesByName(name : String) : ReceiverWithOutputNotesDto
    @Query("SELECT id,name, phone,email,password FROM receivers")
    suspend fun getReceiversWithOutputNotes() : List<ReceiverWithOutputNotesDto>

    @Transaction
    @Query("SELECT id,name, phone,email,password FROM suppliers WHERE id = :id  LIMIT 1")
    suspend fun getSupplierWithInputNotesById(id : Int) : SupplierWithInputNotesDto
    @Transaction
    @Query("SELECT id,name, phone,email,password FROM suppliers WHERE name LIKE :name  LIMIT 1")
    suspend fun getSupplierWithInputNotesByName(name : String) : SupplierWithInputNotesDto
    @Query("SELECT id,name, phone,email,password FROM suppliers")
    suspend fun getSupplierWithInputNotes() : List<SupplierWithInputNotesDto>

    @Query("SELECT output_notes.id, output_notes.id_of_receiver," +
            " output_notes.output_note_id_of_product,output_notes.count FROM output_notes  WHERE output_notes.id = :id  LIMIT 1")
    suspend fun getOutputNoteWithProductById(id : Int) : OutputNoteWithProductDto
    @Query("SELECT output_notes.id, output_notes.id_of_receiver," +
            " output_notes.output_note_id_of_product,output_notes.count FROM output_notes")
    suspend fun getOutputNotesWithProduct() : List<OutputNoteWithProductDto>
    @Query("SELECT output_notes.id, output_notes.id_of_receiver," +
            " output_notes.output_note_id_of_product,output_notes.count FROM output_notes WHERE output_notes.id_of_receiver == :id")
    suspend fun getOutputNotesWithProductById(id : Int) : List<OutputNoteWithProductDto>

    @Query("SELECT input_notes.id, input_notes.id_of_supplier," +
            " input_notes.input_note_id_of_product,input_notes.count FROM input_notes  WHERE input_notes.id = :id  LIMIT 1")
    suspend fun getInputNoteWithProductById(id : Int) : InputNoteWithProductDto
    @Query("SELECT input_notes.id, input_notes.id_of_supplier," +
            " input_notes.input_note_id_of_product,input_notes.count FROM input_notes  WHERE input_notes.id_of_supplier = :id")
    suspend fun getInputNotesWithProductById(id : Int) : List<InputNoteWithProductDto>
    @Query("SELECT input_notes.id, input_notes.id_of_supplier," +
            " input_notes.input_note_id_of_product,input_notes.count FROM input_notes")
    suspend fun getInputNotesWithProduct() : List<InputNoteWithProductDto>

    @Query("SELECT products.id, products.name,products.description,products.price FROM products WHERE products.id = :id  LIMIT 1")
    suspend fun getProductWithProductOnWarehouseById(id : Int) : ProductWithProductOnWarehouseDto
    @Query("SELECT products.id, products.name,products.description,products.price FROM products")
    suspend fun getProductsWithProductOnWarehouse() : List<ProductWithProductOnWarehouseDto>
    @Query("SELECT products.id, products.name,products.description,products.price FROM products  WHERE name LIKE '%' || :name || '%'")
    suspend fun getProductsWithProductOnWarehouseByName(name : String) : List<ProductWithProductOnWarehouseDto>

    @Query("SELECT products.id, products.name,products.description,products.price FROM products  ORDER BY name DESC")
    suspend fun getProductsWithProductOnWarehouseSortedByName() : List<ProductWithProductOnWarehouseDto>
    @Query("SELECT products.id, products.name,products.description,products.price FROM products  ORDER BY price DESC")
    suspend fun getProductsWithProductOnWarehouseSortedByPrice() : List<ProductWithProductOnWarehouseDto>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductOnWarehouse(productOnWarehouseDto: ProductOnWarehouseDto)
    @Update
    suspend fun editProductOnWarehouse(productOnWarehouseDto: ProductOnWarehouseDto)
//    @Query("SELECT employee.name, employee.salary, department.name AS department_name " +
//            "FROM employee, department " +
//            "WHERE department.id == employee.department_id")
//    suspend fun getEmployeeWithDepartment(): List<EmployeeDepartment>

//    @Transaction
//    @Query("SELECT id, name from department")
//    suspend fun getDepartmentsWithEmployees(): List<DepartmentWithEmployees>
}