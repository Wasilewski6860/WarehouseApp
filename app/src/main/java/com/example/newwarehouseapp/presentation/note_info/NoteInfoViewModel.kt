package com.example.newwarehouseapp.presentation.note_info

import androidx.lifecycle.ViewModel
import com.example.newwarehouseapp.domain.use_cases.input_note_with_product.GetInputNotesWithProductUseCase
import com.example.newwarehouseapp.domain.use_cases.output_note_with_product.GetOutputNotesWithProductUseCase

class NoteInfoViewModel(
    private val getOutputNotesWithProductUseCase: GetOutputNotesWithProductUseCase,
    private val getInputNotesWithProductUseCase: GetInputNotesWithProductUseCase
) : ViewModel() {



}