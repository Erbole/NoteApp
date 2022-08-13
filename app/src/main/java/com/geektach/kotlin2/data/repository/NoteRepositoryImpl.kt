package com.geektach.kotlin2.data.repository

import com.geektach.kotlin2.core.Resource
import com.geektach.kotlin2.data.mapper.NoteMapper
import com.geektach.kotlin2.data.model.NoteDto
import com.geektach.kotlin2.domain.model.Note
import com.geektach.kotlin2.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.Exception

class NoteRepositoryImpl @Inject constructor(private val noteMapper: NoteMapper) : NoteRepository {

    private val notes = arrayListOf<NoteDto>()

    override suspend fun addNote(note: Note): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            notes.add(noteMapper.toNoteDto(note))
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error("Something went wrong", false))
        }
    }

    override fun deleteNote(note: Note): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            notes.removeLast()
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error("Something went wrong", false))
        }
    }

    override suspend fun getAllNotes(): Flow<Resource<List<Note>>> = flow {
        emit(Resource.Loading())
        try {
            val data = notes.map {
                noteMapper.toNote(it)
            }
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error("Something went wrong", emptyList()))
        }
    }
}