package com.atb.mynotes.presentation.add_edit_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atb.mynotes.core.Constant
import com.atb.mynotes.domain.model.Note
import com.atb.mynotes.domain.use_cases.NoteUseCases
import com.atb.mynotes.domain.utils.IllegalNoteException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private var _titleState = mutableStateOf(AddEditTextFieldState(hint = "Enter the title..."))
    val titleState: State<AddEditTextFieldState> = _titleState
    private var _descriptionState = mutableStateOf(AddEditTextFieldState(hint = "Enter the description..."))
    val descriptionState: State<AddEditTextFieldState> = _descriptionState
    private var _colorState = mutableIntStateOf(Constant.noteItemColors.random().toArgb())
    val colorState: State<Int> = _colorState
    var currentNoteId: Int? = null
    private var _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent: SharedFlow<UiEvent> = _uiEvent

    init {
        savedStateHandle.get<Int>("noteId")?.let {noteId ->
            if (noteId != -1) {
                viewModelScope.launch {
                    noteUseCases.getNoteById(noteId)?.also {note ->
                        currentNoteId = note.id
                        _titleState.value = _titleState.value.copy(
                            text = note.title,
                            isHintVisible = false
                        )
                        _descriptionState.value = _descriptionState.value.copy(
                            text = note.description,
                            isHintVisible = false
                        )
                        _colorState.intValue = note.color
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditNoteEvent) {
        when(event) {
            is AddEditNoteEvent.OnColorChanged -> {
                _colorState.intValue = event.color
            }
            is AddEditNoteEvent.OnDescriptionChanged -> {
                _descriptionState.value = _descriptionState.value.copy(text = event.description)
            }
            is AddEditNoteEvent.OnDescriptionFocusChanged -> {
                _descriptionState.value = _descriptionState.value.copy(
                    isHintVisible = !event.focusState.isFocused && descriptionState.value.text.isBlank()
                )
            }
            is AddEditNoteEvent.OnTitleChanged -> {
                _titleState.value = _titleState.value.copy(text = event.title)
            }
            is AddEditNoteEvent.OnTitleFocusChanged -> {
                _titleState.value = _titleState.value.copy(
                    isHintVisible = !event.focusState.isFocused && titleState.value.text.isBlank()
                )
            }
            is AddEditNoteEvent.onSaveClicked -> {
                viewModelScope.launch {
                    try {
                        noteUseCases.addEditNote(
                            Note(
                                title = titleState.value.text,
                                description = descriptionState.value.text,
                                color = colorState.value,
                                time = System.currentTimeMillis(),
                                id = currentNoteId
                            )
                        )
                        _uiEvent.emit(UiEvent.SaveNote)
                    } catch (e: IllegalNoteException) {
                        _uiEvent.emit(UiEvent.ShowSnackbar(
                            e.message ?: "Couldn't save the note!"
                        ))
                    }
                }
            }
        }
    }

    sealed interface UiEvent {
        data class ShowSnackbar(val message: String): UiEvent
        object SaveNote: UiEvent
    }
}