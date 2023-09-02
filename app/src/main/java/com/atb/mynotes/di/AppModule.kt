package com.atb.mynotes.di

import android.app.Application
import androidx.room.Room
import com.atb.mynotes.data.local.NoteDatabase
import com.atb.mynotes.data.repository.NoteRepositoryImpl
import com.atb.mynotes.domain.repository.NoteRepository
import com.atb.mynotes.domain.use_cases.NoteUseCases
import com.atb.mynotes.domain.use_cases.add_edit_note.AddEditNoteUseCase
import com.atb.mynotes.domain.use_cases.delete_note.DeleteNoteUseCase
import com.atb.mynotes.domain.use_cases.get_note.GetNoteByIdUseCase
import com.atb.mynotes.domain.use_cases.get_notes_list.GetAllNotesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.dao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            addEditNote = AddEditNoteUseCase(repository),
            deleteNote = DeleteNoteUseCase(repository),
            getNoteById = GetNoteByIdUseCase(repository),
            getAllNotes = GetAllNotesUseCase(repository)
        )
    }
}