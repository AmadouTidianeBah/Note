package com.atb.mynotes.domain.use_cases

import com.atb.mynotes.domain.use_cases.add_edit_note.AddEditNoteUseCase
import com.atb.mynotes.domain.use_cases.delete_note.DeleteNoteUseCase
import com.atb.mynotes.domain.use_cases.get_note.GetNoteByIdUseCase
import com.atb.mynotes.domain.use_cases.get_notes_list.GetAllNotesUseCase

data class NoteUseCases(
    val addEditNote: AddEditNoteUseCase,
    val deleteNote: DeleteNoteUseCase,
    val getNoteById: GetNoteByIdUseCase,
    val getAllNotes: GetAllNotesUseCase
)
