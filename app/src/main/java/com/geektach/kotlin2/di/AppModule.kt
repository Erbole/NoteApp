package com.geektach.kotlin2.di

import com.geektach.kotlin2.data.mapper.NoteMapper
import com.geektach.kotlin2.data.repository.NoteRepositoryImpl
import com.geektach.kotlin2.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNoteRepository():NoteRepository {
        return NoteRepositoryImpl(noteMapper = NoteMapper())
    }
}