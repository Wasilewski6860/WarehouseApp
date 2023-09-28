package com.example.newwarehouseapp.di

import com.example.newwarehouseapp.domain.use_cases.input_note.AddInputNoteUseCase
import com.example.newwarehouseapp.domain.use_cases.input_note.DeleteInputNoteUseCase
import com.example.newwarehouseapp.domain.use_cases.input_note.EditInputNoteUseCase
import com.example.newwarehouseapp.domain.use_cases.input_note.GetInputNoteByIdUseCase
import com.example.newwarehouseapp.domain.use_cases.input_note.GetInputNotesUseCase
import com.example.newwarehouseapp.domain.use_cases.input_note_with_product.GetInputNoteWithProductByIdUseCase
import com.example.newwarehouseapp.domain.use_cases.input_note_with_product.GetInputNotesWithProductByIdUseCase
import com.example.newwarehouseapp.domain.use_cases.input_note_with_product.GetInputNotesWithProductUseCase
import com.example.newwarehouseapp.domain.use_cases.output_note.AddOutputNoteUseCase
import com.example.newwarehouseapp.domain.use_cases.output_note.DeleteOutputNoteUseCase
import com.example.newwarehouseapp.domain.use_cases.output_note.EditOutputNoteUseCase
import com.example.newwarehouseapp.domain.use_cases.output_note.GetOutputNoteByIdUseCase
import com.example.newwarehouseapp.domain.use_cases.output_note.GetOutputNotesUseCase
import com.example.newwarehouseapp.domain.use_cases.output_note_with_product.GetOutputNoteWithProductByIdUseCase
import com.example.newwarehouseapp.domain.use_cases.output_note_with_product.GetOutputNotesWithProductByIdUseCase
import com.example.newwarehouseapp.domain.use_cases.output_note_with_product.GetOutputNotesWithProductUseCase
import com.example.newwarehouseapp.domain.use_cases.product.AddProductUseCase
import com.example.newwarehouseapp.domain.use_cases.product.DeleteProductUseCase
import com.example.newwarehouseapp.domain.use_cases.product.EditProductUseCase
import com.example.newwarehouseapp.domain.use_cases.product.GetProductByIdUseCase
import com.example.newwarehouseapp.domain.use_cases.product.GetProductByNameUseCase
import com.example.newwarehouseapp.domain.use_cases.product.GetProductsUseCase
import com.example.newwarehouseapp.domain.use_cases.product_on_warehouse.AddProductOnWarehouseUseCase
import com.example.newwarehouseapp.domain.use_cases.product_with_product_on_warehouse.AddProductWithProductOnWarehouseUseCase
import com.example.newwarehouseapp.domain.use_cases.product_with_product_on_warehouse.EditProductWithProductOnWarehouseUseCase
import com.example.newwarehouseapp.domain.use_cases.product_with_product_on_warehouse.GetProductWithProductOnWarehouseByIdUseCase
import com.example.newwarehouseapp.domain.use_cases.product_with_product_on_warehouse.GetProductsWithProductOnWarehouseByNameUseCase
import com.example.newwarehouseapp.domain.use_cases.product_with_product_on_warehouse.GetProductsWithProductOnWarehouseSortedByNameUseCase
import com.example.newwarehouseapp.domain.use_cases.product_with_product_on_warehouse.GetProductsWithProductOnWarehouseSortedByPriceUseCase
import com.example.newwarehouseapp.domain.use_cases.product_with_product_on_warehouse.GetProductsWithProductOnWarehouseUseCase
import com.example.newwarehouseapp.domain.use_cases.receiver.AddReceiverUseCase
import com.example.newwarehouseapp.domain.use_cases.receiver.DeleteReceiverUseCase
import com.example.newwarehouseapp.domain.use_cases.receiver.EditReceiverUseCase
import com.example.newwarehouseapp.domain.use_cases.receiver.GetReceiverByIdUseCase
import com.example.newwarehouseapp.domain.use_cases.receiver.GetReceiverByNameUseCase
import com.example.newwarehouseapp.domain.use_cases.receiver.GetReceiversByNameAndPasswordUseCase
import com.example.newwarehouseapp.domain.use_cases.receiver.GetReceiversByNameUseCase
import com.example.newwarehouseapp.domain.use_cases.receiver.GetReceiversUseCase
import com.example.newwarehouseapp.domain.use_cases.receiver_with_output_notes.GetReceiverWithOutputNotesByIdUseCase
import com.example.newwarehouseapp.domain.use_cases.receiver_with_output_notes.GetReceiverWithOutputNotesByNameUseCase
import com.example.newwarehouseapp.domain.use_cases.receiver_with_output_notes.GetReceiversWithOutputNotesUseCase
import com.example.newwarehouseapp.domain.use_cases.supplier.AddSupplierUseCase
import com.example.newwarehouseapp.domain.use_cases.supplier.DeleteSupplierUseCase
import com.example.newwarehouseapp.domain.use_cases.supplier.EditSupplierUseCase
import com.example.newwarehouseapp.domain.use_cases.supplier.GetSupplierByIdUseCase
import com.example.newwarehouseapp.domain.use_cases.supplier.GetSupplierByNameUseCase
import com.example.newwarehouseapp.domain.use_cases.supplier.GetSuppliersByNameAndPasswordUseCase
import com.example.newwarehouseapp.domain.use_cases.supplier.GetSuppliersByNameUseCase
import com.example.newwarehouseapp.domain.use_cases.supplier.GetSuppliersUseCase
import com.example.newwarehouseapp.domain.use_cases.supplier_with_input_notes.GetSupplierWithInputNotesByIdUseCase
import com.example.newwarehouseapp.domain.use_cases.supplier_with_input_notes.GetSupplierWithInputNotesByNameUseCase
import com.example.newwarehouseapp.domain.use_cases.supplier_with_input_notes.GetSupplierWithInputNotesUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<AddInputNoteUseCase> { AddInputNoteUseCase(warehouseRepository = get()) }
    factory<DeleteInputNoteUseCase> { DeleteInputNoteUseCase(warehouseRepository = get()) }
    factory<EditInputNoteUseCase> { EditInputNoteUseCase(warehouseRepository = get()) }
    factory<GetInputNoteByIdUseCase> { GetInputNoteByIdUseCase(warehouseRepository = get()) }
    factory<GetInputNotesUseCase> { GetInputNotesUseCase(warehouseRepository = get()) }
    factory<GetInputNotesWithProductUseCase> { GetInputNotesWithProductUseCase(warehouseRepository = get()) }
    factory<GetInputNoteWithProductByIdUseCase> { GetInputNoteWithProductByIdUseCase(warehouseRepository = get()) }
    factory<GetInputNotesWithProductByIdUseCase> { GetInputNotesWithProductByIdUseCase(warehouseRepository = get()) }
    factory<AddOutputNoteUseCase> { AddOutputNoteUseCase(warehouseRepository = get()) }
    factory<DeleteOutputNoteUseCase> { DeleteOutputNoteUseCase(warehouseRepository = get()) }
    factory<EditOutputNoteUseCase> { EditOutputNoteUseCase(warehouseRepository = get()) }
    factory<GetOutputNoteByIdUseCase> { GetOutputNoteByIdUseCase(warehouseRepository = get()) }
    factory<GetOutputNotesUseCase> { GetOutputNotesUseCase(warehouseRepository = get()) }
    factory<GetOutputNotesWithProductUseCase> { GetOutputNotesWithProductUseCase(warehouseRepository = get()) }
    factory<GetOutputNoteWithProductByIdUseCase> { GetOutputNoteWithProductByIdUseCase(warehouseRepository = get()) }
    factory<GetOutputNotesWithProductByIdUseCase> { GetOutputNotesWithProductByIdUseCase(warehouseRepository = get()) }
    factory<AddProductUseCase> { AddProductUseCase(warehouseRepository = get()) }
    factory<DeleteProductUseCase> { DeleteProductUseCase(warehouseRepository = get()) }
    factory<EditProductUseCase> { EditProductUseCase(warehouseRepository = get()) }
    factory<GetProductByIdUseCase> { GetProductByIdUseCase(warehouseRepository = get()) }
    factory<GetProductByNameUseCase> { GetProductByNameUseCase(warehouseRepository = get()) }
    factory<GetProductsUseCase> { GetProductsUseCase(warehouseRepository = get()) }
    factory<AddProductWithProductOnWarehouseUseCase> { AddProductWithProductOnWarehouseUseCase(warehouseRepository = get()) }
    factory<EditProductWithProductOnWarehouseUseCase> { EditProductWithProductOnWarehouseUseCase(warehouseRepository = get()) }
    factory<GetProductsWithProductOnWarehouseUseCase> { GetProductsWithProductOnWarehouseUseCase(warehouseRepository = get()) }
    factory<GetProductWithProductOnWarehouseByIdUseCase> { GetProductWithProductOnWarehouseByIdUseCase(warehouseRepository = get()) }
    factory<AddReceiverUseCase> { AddReceiverUseCase(warehouseRepository = get()) }
    factory<DeleteReceiverUseCase> { DeleteReceiverUseCase(warehouseRepository = get()) }
    factory<EditReceiverUseCase> { EditReceiverUseCase(warehouseRepository = get()) }
    factory<GetReceiverByIdUseCase> { GetReceiverByIdUseCase(warehouseRepository = get()) }
    factory<GetReceiverByNameUseCase> { GetReceiverByNameUseCase(warehouseRepository = get()) }
    factory<GetReceiversByNameUseCase> { GetReceiversByNameUseCase(warehouseRepository = get()) }
    factory<GetReceiversUseCase> { GetReceiversUseCase(warehouseRepository = get()) }
    factory<GetReceiversWithOutputNotesUseCase> { GetReceiversWithOutputNotesUseCase(warehouseRepository = get()) }
    factory<GetReceiverWithOutputNotesByIdUseCase> { GetReceiverWithOutputNotesByIdUseCase(warehouseRepository = get()) }
    factory<GetReceiverWithOutputNotesByNameUseCase> { GetReceiverWithOutputNotesByNameUseCase(warehouseRepository = get()) }
    factory<AddSupplierUseCase> { AddSupplierUseCase(warehouseRepository = get()) }
    factory<DeleteSupplierUseCase> { DeleteSupplierUseCase(warehouseRepository = get()) }
    factory<EditSupplierUseCase> { EditSupplierUseCase(warehouseRepository = get()) }
    factory<GetSupplierByIdUseCase> { GetSupplierByIdUseCase(warehouseRepository = get()) }
    factory<GetSupplierByNameUseCase> { GetSupplierByNameUseCase(warehouseRepository = get()) }
    factory<GetSuppliersByNameUseCase> { GetSuppliersByNameUseCase(warehouseRepository = get()) }
    factory<GetSuppliersUseCase> { GetSuppliersUseCase(warehouseRepository = get()) }
    factory<GetSupplierWithInputNotesByIdUseCase> { GetSupplierWithInputNotesByIdUseCase(warehouseRepository = get()) }
    factory<GetSupplierWithInputNotesByNameUseCase> { GetSupplierWithInputNotesByNameUseCase(warehouseRepository = get()) }
    factory<GetSupplierWithInputNotesUseCase> { GetSupplierWithInputNotesUseCase(warehouseRepository = get()) }
    factory<AddProductOnWarehouseUseCase>{AddProductOnWarehouseUseCase(warehouseRepository = get())}
    factory<GetReceiversByNameAndPasswordUseCase>{GetReceiversByNameAndPasswordUseCase(warehouseRepository = get())}
    factory<GetSuppliersByNameAndPasswordUseCase>{GetSuppliersByNameAndPasswordUseCase(warehouseRepository = get())}
    factory<GetProductsWithProductOnWarehouseByNameUseCase>{GetProductsWithProductOnWarehouseByNameUseCase(warehouseRepository = get())}
    factory<GetProductsWithProductOnWarehouseSortedByNameUseCase>{GetProductsWithProductOnWarehouseSortedByNameUseCase(warehouseRepository = get())}
    factory<GetProductsWithProductOnWarehouseSortedByPriceUseCase>{GetProductsWithProductOnWarehouseSortedByPriceUseCase(warehouseRepository = get())}
//    factory<AddCatUseCase> { AddCatUseCase(catsRepository = get()) }
//    factory<DeleteCatUseCase> { DeleteCatUseCase(catsRepository = get()) }
//    factory<EditCatUseCase> { EditCatUseCase(catsRepository = get()) }
//    factory<GetAllCatsUseCase> { GetAllCatsUseCase(catsRepository = get()) }
//    factory<GetCatsByNameUseCase> { GetCatsByNameUseCase(catsRepository = get()) }
//    factory<GetCatUseCase> { GetCatUseCase(catsRepository = get()) }
//    factory<GetImageUseCase> { GetImageUseCase(catsRepository = get()) }
//
//    factory<GetFavouriteCatsUseCase> { GetFavouriteCatsUseCase(catsRepository = get()) }
//    factory<GetFavouriteCatsByNameUseCase> { GetFavouriteCatsByNameUseCase(catsRepository = get()) }
}