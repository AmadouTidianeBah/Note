package com.atb.mynotes.domain.use_cases.get_note

import com.atb.mynotes.domain.model.Note
import com.atb.mynotes.domain.repository.NoteRepository

class GetNoteByIdUseCase(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id: Int): Note? {
        return repository.getNoteById(id)
    }
}