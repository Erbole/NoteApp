package com.geektach.kotlin2.domain.repository

import com.geektach.kotlin2.core.Resource
import com.geektach.kotlin2.domain.model.room.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    suspend fun addNote(note: Note): Flow<Resource<Unit>>
    fun deleteNote(note: Note): Flow<Resource<Unit>>
    fun getAllNotes(): Flow<Resource<List<Note>>>
    fun editing(note: Note): Flow<Resource<Unit>>
}