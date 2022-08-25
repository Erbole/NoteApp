package com.geektach.kotlin2.data.mapper

import com.geektach.kotlin2.data.network.CharacterDto
import com.geektach.kotlin2.data.network.InfoDto
import com.geektach.kotlin2.data.network.MainResultDto
import com.geektach.kotlin2.data.room.NoteEntity
import com.geektach.kotlin2.domain.model.network.Character
import com.geektach.kotlin2.domain.model.network.Info
import com.geektach.kotlin2.domain.model.network.MainResult
import com.geektach.kotlin2.domain.model.room.Note

class NoteMapper {

    fun toNoteEntity(note: Note): NoteEntity {
        return NoteEntity(
            id = note.id,
            title = note.title,
            description = note.description
        )
    }

    fun toNote(noteEntity: NoteEntity): Note {
        return Note(
            id = noteEntity.id,
            title = noteEntity.title,
            description = noteEntity.description
        )
    }

    fun toCharacterDto(character: Character) = CharacterDto(
        id = character.id,
        name = character.name,
        status = character.status,
        species = character.species,
        gender = character.gender,
        image = character.image,
    )

    fun toCharacter(characterDto: CharacterDto) = Character(
        id = characterDto.id,
        name = characterDto.name,
        status = characterDto.status,
        species = characterDto.species,
        gender = characterDto.gender,
        image = characterDto.image,
    )

    fun toInfo(infoDto: InfoDto) = Info(
        count = infoDto.count,
        next = infoDto.next,
        pages = infoDto.pages,
    )

    fun toMainResult(mainResultDto: MainResultDto<CharacterDto>) = MainResult(
        info = mainResultDto.info?.let { toInfo(it) },
        result = mainResultDto.result.map {
            toCharacter(it)
        }
    )
}