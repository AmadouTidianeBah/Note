package com.atb.mynotes.core.components

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.atb.mynotes.core.Constant

@Composable
fun NoteFloatingBtn(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    contentDescription: String?,
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onClick,
        shape = Constant.floatingBtnShape,
        modifier = modifier
    ) {
        Icon(imageVector = imageVector, contentDescription = contentDescription)
    }
}