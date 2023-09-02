@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.atb.mynotes.core.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteTopBar(
    modifier: Modifier = Modifier,
    text: String
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = text,
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            MaterialTheme.colorScheme.primary
        ),
        modifier = modifier.fillMaxWidth()
    )
}
