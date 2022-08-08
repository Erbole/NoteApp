package com.geektach.kotlin2.domain.repository

import com.geektach.kotlin2.domain.model.Note

interface NoteRepository {

    fun addNote(note: Note)

    fun getAllNotes(): List<Note>

    fun deleteNote(note: Note)

}