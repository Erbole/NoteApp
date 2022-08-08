package com.geektach.kotlin2.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geektach.kotlin2.data.repository.NoteRepositoryImpl
import com.geektach.kotlin2.domain.model.Note
import com.geektach.kotlin2.domain.use_case.AddNoteUseCase
import com.geektach.kotlin2.domain.use_case.DeleteNoteUseCase
import com.geektach.kotlin2.domain.use_case.GetAllNotesUseCase

class MainViewModel : ViewModel() {

    private val repository = NoteRepositoryImpl()
    private val getAllNotesUseCase = GetAllNotesUseCase(repository)
    private val addNoteUseCase = AddNoteUseCase(repository)
    private val deleteNoteUseCase = DeleteNoteUseCase(repository)

    val liveData = MutableLiveData<List<Note>>()

    fun addNote(note: Note) {
        addNoteUseCase.addNote(note)
        getAllNotes()
    }

    fun deleteNote(note: Note) {
        deleteNoteUseCase.delete(note)
        getAllNotes()
    }

    private fun getAllNotes() {
        liveData.value = getAllNotesUseCase.getAllNotes()
    }

}