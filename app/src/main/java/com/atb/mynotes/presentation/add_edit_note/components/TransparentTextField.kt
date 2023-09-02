package com.atb.mynotes.presentation.add_edit_note.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun TransparentTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    singleLine: Boolean = false,
    textStyle: TextStyle,
    onFocusChange: (FocusState) -> Unit,
    hint: String,
    isHintVisible: Boolean
) {
    Box(
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        BasicTextField(
            value = text,
            onValueChange = onTextChange,
            singleLine = singleLine,
            textStyle = textStyle,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { onFocusChange(it) }
        )

        if (isHintVisible) {
            Text(
                text = hint,
                style = textStyle
            )
        }
    }
}