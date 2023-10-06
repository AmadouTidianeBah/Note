package com.atb.mynotes.presentation.note_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.atb.mynotes.core.components.NoteFloatingBtn
import com.atb.mynotes.core.components.NoteTopBar
import com.atb.mynotes.domain.model.Note
import com.atb.mynotes.presentation.note_list.components.NoteListItem
import kotlinx.coroutines.launch

@Composable
fun NoteListScreen(
    modifier: Modifier = Modifier,
    viewModel: NoteListViewModel = hiltViewModel(),
    onAddNoteClick: () -> Unit,
    onNoteClick: (Note) -> Unit
) {
    val state = viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    Scaffold(
        topBar = {
            NoteTopBar(text = "My Notes")
        },
        floatingActionButton = {
            NoteFloatingBtn(
                imageVector = Icons.Filled.Add,
                contentDescription = "add note",
                onClick = onAddNoteClick
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState)},
        modifier = modifier
            .fillMaxSize()
    ) {innerPadding ->
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                items(items = state.value.notes) {note ->
                    NoteListItem(
                        note = note,
                        onNoteClick = { onNoteClick(note) },
                        onDeleteClick = {
                            viewModel.deleteNote(it)
                            scope.launch {
                                val result = snackbarHostState
                                    .showSnackbar(
                                        message = "Do you want to restore your note ?",
                                        actionLabel = "Yes",
                                        duration = SnackbarDuration.Short

                                    )
                                if (result == SnackbarResult.ActionPerformed) {
                                    viewModel.restoreRecentDeletedNote()
                                }
                            }
                        },
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            if (state.value.notes.isEmpty()) {
                Text(text = "Empty", fontSize = 24.sp)
            }
        }
    }
}