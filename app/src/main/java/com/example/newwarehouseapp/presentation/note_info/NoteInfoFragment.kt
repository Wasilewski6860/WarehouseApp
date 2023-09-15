package com.example.newwarehouseapp.presentation.note_info

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import com.example.newwarehouseapp.R
import com.example.newwarehouseapp.domain.use_cases.input_note_with_product.GetInputNoteWithProductByIdUseCase
import com.example.newwarehouseapp.domain.use_cases.input_note_with_product.GetInputNotesWithProductUseCase
import com.example.newwarehouseapp.domain.use_cases.output_note_with_product.GetOutputNoteWithProductByIdUseCase
import com.example.newwarehouseapp.domain.use_cases.output_note_with_product.GetOutputNotesWithProductUseCase

class NoteInfoFragment(
    private val getInputNoteWithProductByIdUseCase : GetInputNoteWithProductByIdUseCase,
    private val getOutputNoteWithProductByIdUseCase : GetOutputNoteWithProductByIdUseCase
) : ViewModel() {



}