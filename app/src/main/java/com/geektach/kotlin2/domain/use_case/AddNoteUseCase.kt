package com.geektach.kotlin2.domain.use_case

import com.geektach.kotlin2.domain.model.Note
import com.geektach.kotlin2.domain.repository.NoteRepository

class AddNoteUseCase(private val noteRepository: NoteRepository) {

    fun addNote(note: Note) = noteRepository.addNote(note)

}