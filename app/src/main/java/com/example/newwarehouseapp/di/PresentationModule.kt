package com.example.newwarehouseapp.di

import com.example.newwarehouseapp.presentation.add_input_note.AddInputNoteViewModel
import com.example.newwarehouseapp.presentation.add_output_note.AddOutputNoteViewModel
import com.example.newwarehouseapp.presentation.add_product.AddProductViewModel
import com.example.newwarehouseapp.presentation.input_notes.InputNotesViewModel
import com.example.newwarehouseapp.presentation.login.LoginViewModel
import com.example.newwarehouseapp.presentation.note_info.NoteInfoViewModel
import com.example.newwarehouseapp.presentation.output_notes.OutputNotesViewModel

import com.example.newwarehouseapp.presentation.sign_in.SignInViewModel
import com.example.newwarehouseapp.presentation.sign_up.SignUpViewModel
import com.example.newwarehouseapp.presentation.warehouse.WarehouseViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel<AddInputNoteViewModel>{
        AddInputNoteViewModel(
            addInputNoteUseCase = get(),
            getProductsWithProductOnWarehouseUseCase = get(),
            editProductWithProductOnWarehouseUseCase = get()
        )
    }
    viewModel<AddOutputNoteViewModel>{
        AddOutputNoteViewModel(
            addOutputNoteUseCase = get(),
            getProductsWithProductOnWarehouseUseCase = get(),
            editProductWithProductOnWarehouseUseCase = get()
        )
    }
    viewModel<AddProductViewModel> {
        AddProductViewModel(
           addProductOnWarehouseUseCase = get(),
            addProductUseCase = get(),
            editProductUseCase = get(),
            getProductByIdUseCase = get()
        )
    }
    viewModel<LoginViewModel> {
        LoginViewModel(
            addReceiverUseCase = get(),
            addSupplierUseCase = get(),
            getReceiverByNameUseCase = get(),
            getSupplierByNameUseCase = get()
        )
    }

    viewModel<SignInViewModel> {
        SignInViewModel(
            getReceiverByNameUseCase = get(),
            getSupplierByNameUseCase = get(),
            getReceiversByNameUseCase = get(),
            getSuppliersByNameUseCase = get(),
            getReceiversByNameAndPasswordUseCase = get(),
            getSuppliersByNameAndPasswordUseCase = get()
        )
    }

    viewModel<SignUpViewModel> {
        SignUpViewModel(
            addReceiverUseCase = get(),
            addSupplierUseCase = get()
        )
    }

    viewModel<NoteInfoViewModel> {
        NoteInfoViewModel(
            getOutputNotesWithProductUseCase = get(),
            getInputNotesWithProductUseCase = get()
        )
    }

    viewModel<WarehouseViewModel>{
        WarehouseViewModel(
            getProductsWithProductOnWarehouseUseCase = get(),
            getProductsUseCase = get()
        )
    }

    viewModel<OutputNotesViewModel>{
        OutputNotesViewModel(
            getOutputNotesWithProductByIdUseCase = get()
        )
    }

    viewModel<InputNotesViewModel>{
        InputNotesViewModel(
            getInputNotesWithProductByIdUseCase = get()
        )
    }
}