package com.atb.mynotes.core

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

object Constant {
    const val NOTE_ID = "noteId"
    val noteItemShape = RoundedCornerShape(
        topStart = 24.dp, topEnd = 0.dp,
        bottomStart = 0.dp, bottomEnd = 24.dp
    )

    val floatingBtnShape = RoundedCornerShape(
        topStart = 12.dp, topEnd = 0.dp,
        bottomStart = 0.dp, bottomEnd = 12.dp
    )

    val lowSpace = 8.dp
    val mediumSpace = 16.dp

    val noteItemColors = listOf(
        Color(0xFF03DAC5),
        Color(0xFFEF9A9A),
        Color(0xFFCE93D8),
        Color(0xFFA5D6A7),
        Color(0xFFFFE082)
    )
}