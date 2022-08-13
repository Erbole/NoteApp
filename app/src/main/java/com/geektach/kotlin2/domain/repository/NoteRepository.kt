package com.geektach.kotlin2.domain.repository

import com.geektach.kotlin2.core.Resource
import com.geektach.kotlin2.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    suspend fun addNote(note: Note): Flow<Resource<Boolean>>

    fun deleteNote(note: Note): Flow<Resource<Boolean>>

    suspend fun getAllNotes(): Flow<Resource<List<Note>>>
}