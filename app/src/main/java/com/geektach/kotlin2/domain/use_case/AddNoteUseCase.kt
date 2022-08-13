package com.geektach.kotlin2.domain.use_case

import com.geektach.kotlin2.domain.model.Note
import com.geektach.kotlin2.domain.repository.NoteRepository
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(private val noteRepository: NoteRepository) {

    suspend fun addNote(note: Note) = noteRepository.addNote(note)

}