package com.geektach.kotlin2.data.repository

import com.geektach.kotlin2.data.mapper.NoteMapper
import com.geektach.kotlin2.data.model.NoteDto
import com.geektach.kotlin2.domain.model.Note
import com.geektach.kotlin2.domain.repository.NoteRepository

class NoteRepositoryImpl : NoteRepository {

    private val noteMapper = NoteMapper()
    private val notes = arrayListOf<NoteDto>()

    override fun addNote(note: Note) {
        notes.add(noteMapper.toNoteDto(note))
    }

    override fun getAllNotes(): List<Note> {
        return notes.map {
            noteMapper.toNote(it)
        }
    }

    override fun deleteNote(note: Note) {
        notes.removeLast()
    }
}