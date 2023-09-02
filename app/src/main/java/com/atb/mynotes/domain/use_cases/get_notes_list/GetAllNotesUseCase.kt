package com.atb.mynotes.domain.use_cases.get_notes_list

import com.atb.mynotes.domain.model.Note
import com.atb.mynotes.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetAllNotesUseCase(
    private val repository: NoteRepository
) {
    operator fun invoke(): Flow<List<Note>> {
        return repository.getAllNotes()
    }
}