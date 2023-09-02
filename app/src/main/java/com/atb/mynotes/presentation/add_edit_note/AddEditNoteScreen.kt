package com.atb.mynotes.presentation.add_edit_note

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.atb.mynotes.core.Constant
import com.atb.mynotes.core.components.NoteFloatingBtn
import com.atb.mynotes.core.components.NoteTopBar
import com.atb.mynotes.presentation.add_edit_note.components.TransparentTextField
import kotlinx.coroutines.launch

@Composable
fun AddEditNoteScreen(
    modifier: Modifier = Modifier,
    noteColor: Int,
    viewModel: AddEditNoteViewModel = hiltViewModel(),
    onSaveClick: () -> Unit
) {
    val title = viewModel.titleState.value
    val description = viewModel.descriptionState.value
    val currentNoteId = viewModel.currentNoteId
    val scope = rememberCoroutineScope()
    val uiEvent = viewModel.uiEvent
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    val animatedNoteBackground = remember {
        Animatable(
            Color(if (noteColor != -1) noteColor
                    else viewModel.colorState.value)
        )
    }

    LaunchedEffect(key1 = true) {
        uiEvent.collect {event ->
            when(event) {
                is AddEditNoteViewModel.UiEvent.SaveNote -> {
                    onSaveClick()
                }
                is AddEditNoteViewModel.UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message,
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }
    }

    Scaffold(
        topBar = {
            NoteTopBar(
                text = if (currentNoteId == null) "Add Note" else "Edit Note"
            )
        },
        floatingActionButton = {
            NoteFloatingBtn(
                imageVector = Icons.Filled.Done,
                contentDescription = "save note",
                onClick = {
                    viewModel.onEvent(AddEditNoteEvent.onSaveClicked)
                }
            )
        },
        snackbarHost = {SnackbarHost(snackbarHostState)}
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(animatedNoteBackground.value)
                .padding(innerPadding)
                .padding(Constant.lowSpace),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Constant.noteItemColors.forEach {color ->
                    val colorInt = color.toArgb()

                    Box(
                        modifier = modifier
                            .size(50.dp)
                            .shadow(
                                elevation = 16.dp,
                                shape = CircleShape
                            )
                            .clip(CircleShape)
                            .background(color)
                            .border(
                                width = 2.dp,
                                color = if (colorInt == viewModel.colorState.value) Color.Black else Color.Transparent,
                                shape = CircleShape
                            )
                            .clickable {
                                scope.launch {
                                    animatedNoteBackground.animateTo(
                                        targetValue = Color(colorInt),
                                        animationSpec = tween(
                                            durationMillis = 500
                                        )
                                    )
                                }
                                viewModel.onEvent(AddEditNoteEvent.OnColorChanged(colorInt))
                            }
                    )
                }
            }

            TransparentTextField(
                text = title.text,
                onTextChange = { viewModel.onEvent(AddEditNoteEvent.OnTitleChanged(it)) },
                singleLine = true,
                textStyle = MaterialTheme.typography.titleLarge,
                onFocusChange = { viewModel.onEvent(AddEditNoteEvent.OnTitleFocusChanged(it))},
                hint = title.hint,
                isHintVisible = title.isHintVisible
            )

            TransparentTextField(
                text = description.text,
                onTextChange = { viewModel.onEvent(AddEditNoteEvent.OnDescriptionChanged(it)) },
                textStyle = MaterialTheme.typography.bodyLarge,
                onFocusChange = { viewModel.onEvent(AddEditNoteEvent.OnDescriptionFocusChanged(it)) },
                hint = description.hint,
                isHintVisible = description.isHintVisible,
                modifier = Modifier.fillMaxHeight()
            )
        }
    }
}