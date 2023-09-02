package com.atb.mynotes.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.atb.mynotes.presentation.add_edit_note.AddEditNoteScreen
import com.atb.mynotes.presentation.note_list.NoteListScreen

@Composable
fun MyNoteNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {

    NavHost(
        navController = navController,
        startDestination = "note_list",
        modifier = modifier
    ) {
        composable(route = "note_list") {
            NoteListScreen(
                onAddNoteClick = {
                    navController.navigate("add_edit_note")
                },
                onNoteClick = {
                    navController.navigate("add_edit_note?noteId=${it.id}&noteColor=${it.color}")
                }
            )
        }

        composable(
            route = "add_edit_note?noteId={noteId}&noteColor={noteColor}",
            arguments = listOf(
                navArgument("noteId"){
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument("noteColor"){
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            AddEditNoteScreen(
                noteColor = it.arguments?.getInt("noteColor") ?: -1,
                onSaveClick = {
                    navController.navigateUp()
                }
            )
        }
    }
}