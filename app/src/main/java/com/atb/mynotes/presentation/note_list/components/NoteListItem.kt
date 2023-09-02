package com.atb.mynotes.presentation.note_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.atb.mynotes.core.Constant
import com.atb.mynotes.domain.model.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteListItem(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClick: (Note) -> Unit,
    onDeleteClick: (Note) -> Unit
) {
    Card(
        onClick = { onNoteClick(note) },
        shape = Constant.noteItemShape,
        colors = CardDefaults.cardColors(Color(note.color)),
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(Constant.lowSpace)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(Constant.lowSpace)
            ) {
                Text(
                    text = note.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = note.description,
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "delete icon",
                modifier = Modifier
                    .align(Alignment.Bottom)
                    .size(28.dp)
                    .clickable { onDeleteClick(note) }
            )
        }
    }
}
