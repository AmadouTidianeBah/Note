package com.atb.mynotes.domain.use_cases.delete_note

import com.atb.mynotes.domain.model.Note
import com.atb.mynotes.domain.repository.NoteRepository

class DeleteNoteUseCase(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}