package com.geektach.kotlin2.domain.use_case

import com.geektach.kotlin2.domain.model.Note
import com.geektach.kotlin2.domain.repository.NoteRepository

class DeleteNoteUseCase(private val noteRepository: NoteRepository) {

    fun delete(note: Note) = noteRepository.deleteNote(note)

}