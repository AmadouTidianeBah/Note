package com.atb.mynotes.presentation.add_edit_note

import androidx.compose.ui.focus.FocusState

sealed class AddEditNoteEvent {
    data class OnTitleChanged(val title: String): AddEditNoteEvent()
    data class OnTitleFocusChanged(val focusState: FocusState): AddEditNoteEvent()
    data class OnDescriptionChanged(val description: String): AddEditNoteEvent()
    data class OnDescriptionFocusChanged(val focusState: FocusState): AddEditNoteEvent()
    data class OnColorChanged(val color: Int): AddEditNoteEvent()
    object onSaveClicked: AddEditNoteEvent()
}
