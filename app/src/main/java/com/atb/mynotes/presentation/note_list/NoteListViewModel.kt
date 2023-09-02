package com.atb.mynotes.presentation.note_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atb.mynotes.domain.model.Note
import com.atb.mynotes.domain.use_cases.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
): ViewModel() {
    private var _uiState = MutableStateFlow(NoteListState())
    val uiState: StateFlow<NoteListState> = _uiState
    private var job: Job? = null

    init {
        getNotes()
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            noteUseCases.deleteNote(note)
            _uiState.update {
                it.copy(recentDeletedNote = note)
            }
        }
    }

    fun restoreRecentDeletedNote() {
        viewModelScope.launch {
            uiState.value.recentDeletedNote?.let { noteUseCases.addEditNote(it) }
        }
        _uiState.update {
            it.copy(recentDeletedNote = null)
        }
    }

    private fun getNotes() {
        job?.cancel()
        job = noteUseCases.getAllNotes().onEach {notes ->
            _uiState.update {
                it.copy(notes = notes)
            }
        }.launchIn(viewModelScope)
    }
}