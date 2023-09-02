package com.atb.mynotes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.atb.mynotes.domain.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase: RoomDatabase() {
    abstract val dao: NoteDao

    companion object {
        const val NAME = "note_db"
    }
}