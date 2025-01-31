package com.example.notesapplication

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.notesapplication.entity.Note
import com.example.notesapplication.viewmodel.NotesViewModel

@Composable
fun NotesListScreen(viewModel: NotesViewModel) {
    val noteListState = viewModel.noteList.observeAsState(emptyList())
    val noteList = noteListState.value
    var showModal = remember { mutableStateOf(false) }
    var showUpdateModal = remember { mutableStateOf(false) }
    var noteToUpdate = remember { mutableStateOf<Note?>(null) }
    val title = remember { mutableStateOf("") }
    val category = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    title.value = ""
                    category.value = ""
                    description.value = ""
                    showModal.value = true
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Note")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {
            items(noteList) { note ->
                NoteItem(
                    note,
                    onDeleteNote = { noteToDelete ->
                        viewModel.clearNote(noteToDelete)
                    },
                    onUpdateNote = { updatedNote ->
                        noteToUpdate.value = updatedNote
                        showUpdateModal.value = true
                    }
                )
            }
        }

        if (showModal.value) {
            NoteInputDialog(
                onDismiss = { showModal.value = false },
                onAddNote = { newNote ->
                    viewModel.addNote(newNote)
                    showModal.value = false
                },
                title = title.value,
                onTitleChange = { title.value = it },
                category = category.value,
                onCategoryChange = { category.value = it },
                description = description.value,
                onDescriptionChange = { description.value = it }
            )
        }

        if (showUpdateModal.value && noteToUpdate.value != null) {
            ShowUpdateDialog(
                note = noteToUpdate.value!!,
                onDismiss = { showUpdateModal.value = false },
                onUpdateNote = { updatedNote ->
                    viewModel.updateNote(updatedNote)
                    showUpdateModal.value = false
                }
            )
        }
    }
}
