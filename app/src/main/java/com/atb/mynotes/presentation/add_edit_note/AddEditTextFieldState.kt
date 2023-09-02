package com.atb.mynotes.presentation.add_edit_note

data class AddEditTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)