package com.geektach.kotlin2.data.repository

import com.geektach.kotlin2.core.Resource
import com.geektach.kotlin2.data.base.BaseRepository
import com.geektach.kotlin2.data.mapper.NoteMapper
import com.geektach.kotlin2.data.network.RickAndMortyApiService
import com.geektach.kotlin2.data.room.NoteDao
import com.geektach.kotlin2.domain.model.room.Note
import com.geektach.kotlin2.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao,
    private val apiService: RickAndMortyApiService
) : NoteRepository, BaseRepository() {
    private val noteMapper = NoteMapper()

    //    override suspend fun addNote(note: Note): Flow<Resource<Boolean>> = flow {
//        emit(Resource.Loading())
//        try {
//            notes.add(noteMapper.toNoteDto(note))
//            emit(Resource.Success(true))
//        } catch (e: Exception) {
//            emit(Resource.Error("Something went wrong", false))
//        }
//    }

    override suspend fun addNote(note: Note): Flow<Resource<Unit>> = doRequest {
        noteDao.addNote(noteMapper.toNoteEntity(note))
    }

    override fun deleteNote(note: Note): Flow<Resource<Unit>> = doRequest {
        noteDao.deleteNote(noteMapper.toNoteEntity(note))
    }

    override fun getAllNotes(): Flow<Resource<List<Note>>> = doRequest {
        noteDao.getAllNotes().map { noteEntity ->
            noteMapper.toNote(noteEntity)
        }
    }

    override fun editing(note: Note): Flow<Resource<Unit>> = doRequest {
        noteDao.updateNote(noteMapper.toNoteEntity(note))
    }
}
