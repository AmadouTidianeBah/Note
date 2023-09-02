package com.atb.mynotes.presentation.note_list

import com.atb.mynotes.domain.model.Note

data class NoteListState(
    val notes: List<Note> = emptyList(),
    val recentDeletedNote: Note? = null
)
