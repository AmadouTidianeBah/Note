package com.atb.mynotes.domain.use_cases.add_edit_note

import com.atb.mynotes.domain.model.Note
import com.atb.mynotes.domain.repository.NoteRepository
import com.atb.mynotes.domain.utils.IllegalNoteException

class AddEditNoteUseCase(
    private val repository: NoteRepository
) {
    @Throws(IllegalNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank() || note.description.isBlank()) throw IllegalNoteException("Title or Description couldn't be empty!")
        repository.upsertNote(note)
    }
}