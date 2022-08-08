package com.geektach.kotlin2.domain.use_case

import com.geektach.kotlin2.domain.repository.NoteRepository

class GetAllNotesUseCase(private val noteRepository: NoteRepository) {

    fun getAllNotes() = noteRepository.getAllNotes()

}