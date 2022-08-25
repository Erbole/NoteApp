package com.geektach.kotlin2.presentation.ui.mainActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geektach.kotlin2.core.Resource
import com.geektach.kotlin2.core.UiState
import com.geektach.kotlin2.domain.model.Note
import com.geektach.kotlin2.domain.use_case.AddNoteUseCase
import com.geektach.kotlin2.domain.use_case.DeleteNoteUseCase
import com.geektach.kotlin2.domain.use_case.GetAllNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val addNoteUseCase: AddNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase

) : ViewModel() {

    private val _notesState = MutableStateFlow<UiState<List<Note>>>(UiState.Loading())
    val notesState = _notesState.asStateFlow()

    private val _addNotesState = MutableStateFlow<UiState<Boolean>>(UiState.Loading())
    val addNotesState = _addNotesState.asStateFlow()

    private val _deleteNoteState = MutableStateFlow<UiState<Boolean>>(UiState.Loading())
    val deleteNoteState = _deleteNoteState.asStateFlow()

    fun addNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            addNoteUseCase.addNote(note).collect {
                when (it) {
                    is Resource.Error -> {
                        _addNotesState.value = UiState.Error(it.message ?: "Something went wrong")
                    }
                    is Resource.Loading -> {
                        _addNotesState.value = UiState.Loading()
                    }
                    is Resource.Success -> {
                        if (it.data != null)
                            _addNotesState.value = UiState.Success(it.data)
                    }
                }
            }
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            deleteNoteUseCase.delete(note).collect {
                when (it) {
                    is Resource.Error -> {
                        _deleteNoteState.value = UiState.Error(it.message ?: "Something went wrong")
                    }
                    is Resource.Loading -> {
                        _deleteNoteState.value = UiState.Loading()
                    }
                    is Resource.Success -> {
                        if (it.data != null)
                            _deleteNoteState.value = UiState.Success(it.data)
                    }
                }
            }
        }
    }

    fun getAllNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            getAllNotesUseCase.getAllNotes().collect {
                when (it) {
                    is Resource.Error -> {
                        _notesState.value = UiState.Error(it.message ?: "Something went wrong")
                    }
                    is Resource.Loading -> {
                        _notesState.value = UiState.Loading()
                    }
                    is Resource.Success -> {
                        if (it.data != null)
                            _notesState.value = UiState.Success(it.data)
                    }
                }
            }
        }
    }
}