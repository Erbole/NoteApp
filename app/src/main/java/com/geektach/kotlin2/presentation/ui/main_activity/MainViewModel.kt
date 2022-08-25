package com.geektach.kotlin2.presentation.ui.main_activity

import androidx.lifecycle.viewModelScope
import com.geektach.kotlin2.core.BaseViewModel
import com.geektach.kotlin2.core.UiState
import com.geektach.kotlin2.domain.model.room.Note
import com.geektach.kotlin2.domain.use_case.AddNoteUseCase
import com.geektach.kotlin2.domain.use_case.DeleteNoteUseCase
import com.geektach.kotlin2.domain.use_case.EditingNoteUseCase
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
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val editingNoteUseCase: EditingNoteUseCase

) : BaseViewModel() {

    private val _notesState = MutableStateFlow<UiState<List<Note>>>(UiState.Loading())
    val notesState = _notesState.asStateFlow()

    private val _addNotesState = MutableStateFlow<UiState<Unit>>(UiState.Loading())
    val addNotesState = _addNotesState.asStateFlow()

    private val _deleteNoteState = MutableStateFlow<UiState<Unit>>(UiState.Loading())
    val deleteNoteState = _deleteNoteState.asStateFlow()

    private val _editingNoteSate = MutableStateFlow<UiState<Unit>>(UiState.Loading())
    val editingNoteState = _editingNoteSate.asStateFlow()

    fun addNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            addNoteUseCase.addNote(note).getData(
                { errorMessage ->
                    _addNotesState.value = UiState.Error(errorMessage ?: "Error")
                },
                {
                    _addNotesState.value = UiState.Loading()
                },
                { data ->
                    if (data != null) {
                        _addNotesState.value = UiState.Success(data)
                    }
                })
        }
    }

    fun deleteNote(note: Note) {
        deleteNoteUseCase.delete(note).getData(
            { errorMessage ->
                _deleteNoteState.value = UiState.Error(errorMessage ?: "Error")
            },
            {
                _deleteNoteState.value = UiState.Loading()
            },
            { data ->
                if (data != null) {
                    _deleteNoteState.value = UiState.Success(data)
                }
            })
    }

    fun getAllNotes() {
        getAllNotesUseCase.getAllNotes().getData(
            { errorMessage ->
                _notesState.value = UiState.Error(errorMessage ?: "Error")
            },
            {
                _notesState.value = UiState.Loading()
            },
            { data ->
                if (data != null) {
                    _notesState.value = UiState.Success(data)
                }
            })
    }
    fun editingNote(note: Note) {
        editingNoteUseCase.editingNote(note).getData(
            { errorMessage ->
                _editingNoteSate.value = UiState.Error(errorMessage ?: "Error")
            },
            {
                _editingNoteSate.value = UiState.Loading()
            },
            { data ->
                if (data != null) {
                    _editingNoteSate.value = UiState.Success(data)
                }
            })
    }
}