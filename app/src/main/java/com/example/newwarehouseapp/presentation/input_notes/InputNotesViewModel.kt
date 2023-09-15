package com.example.newwarehouseapp.presentation.input_notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newwarehouseapp.domain.models.InputNoteWithProduct
import com.example.newwarehouseapp.domain.models.OutputNoteWithProduct
import com.example.newwarehouseapp.domain.use_cases.input_note_with_product.GetInputNotesWithProductByIdUseCase
import com.example.newwarehouseapp.domain.use_cases.output_note_with_product.GetOutputNotesWithProductByIdUseCase
import com.example.newwarehouseapp.presentation.screen_state.ScreenState
import kotlinx.coroutines.launch

class InputNotesViewModel(
    private val getInputNotesWithProductByIdUseCase: GetInputNotesWithProductByIdUseCase
)  : ViewModel() {

    private val _screenState = MutableLiveData<ScreenState>()
    val screenState: LiveData<ScreenState> = _screenState

    private val _notesList = MutableLiveData<List<InputNoteWithProduct>>()
    val notesList: LiveData<List<InputNoteWithProduct>> = _notesList

    fun fetchNotes(id : Int, isReceiver : Boolean) {
        _screenState.value = ScreenState.Loading
        viewModelScope.launch {

            try {
                _notesList.value = getInputNotesWithProductByIdUseCase.execute(id)

                _screenState.value =
                    if(isReceiver) ScreenState.NoAccess else if (notesList.value?.isEmpty() == true) ScreenState.Empty else ScreenState.Content

            } catch (e: Exception) {
                _screenState.value = ScreenState.Error
            }
        }
    }

}