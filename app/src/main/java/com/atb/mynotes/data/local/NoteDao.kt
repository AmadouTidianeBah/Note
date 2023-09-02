package com.atb.mynotes.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.atb.mynotes.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Upsert
    suspend fun upsert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * FROM note ORDER BY time DESC")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNote(id: Int): Note?
}