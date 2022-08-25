package com.geektach.kotlin2.domain.use_case

import com.geektach.kotlin2.domain.model.room.Note
import com.geektach.kotlin2.domain.repository.NoteRepository
import javax.inject.Inject

class EditingNoteUseCase @Inject constructor(
    val noteRepository: NoteRepository
) {
    fun editingNote(note: Note) = noteRepository.editing(note)
}