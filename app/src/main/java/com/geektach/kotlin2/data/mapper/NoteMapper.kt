package com.geektach.kotlin2.data.mapper

import com.geektach.kotlin2.data.model.NoteDto
import com.geektach.kotlin2.domain.model.Note

class NoteMapper {

    fun toNoteDto(note: Note): NoteDto {
        return NoteDto(
            title = note.title,
            description = note.description
        )
    }

    fun toNote(noteDto: NoteDto): Note {
        return Note(
            title = noteDto.title,
            description = noteDto.description
        )
    }
}