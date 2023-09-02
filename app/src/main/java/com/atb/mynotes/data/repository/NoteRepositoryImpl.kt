package com.atb.mynotes.data.repository

import com.atb.mynotes.data.local.NoteDao
import com.atb.mynotes.domain.model.Note
import com.atb.mynotes.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    private val dao: NoteDao
) : NoteRepository {
    override suspend fun upsertNote(note: Note) {
        dao.upsert(note)
    }

    override suspend fun deleteNote(note: Note) {
        dao.delete(note)
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNote(id)
    }

    override fun getAllNotes(): Flow<List<Note>> {
        return dao.getNotes()
    }
}