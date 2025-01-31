package com.example.notesapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.notesapplication.entity.Note

@Composable
fun NoteInputDialog(
    onDismiss: () -> Unit,
    onAddNote: (Note) -> Unit,
    title: String,
    onTitleChange: (String) -> Unit,
    category: String,
    onCategoryChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Note") },
        text = {
            Column {
                TextField(
                    value = title,
                    onValueChange = onTitleChange,
                    label = { Text("Title") }
                )
                TextField(
                    value = category,
                    onValueChange = onCategoryChange,
                    label = { Text("Category") }
                )
                TextField(
                    value = description,
                    onValueChange = onDescriptionChange,
                    label = { Text("Description") }
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onAddNote(Note(title = title, category = category, description = description))
                }
            ) {
                Text("Add")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun ShowUpdateDialog(
    note: Note,
    onDismiss: () -> Unit,
    onUpdateNote: (Note) -> Unit
) {
    val title = remember { mutableStateOf(note.title) }
    val category = remember { mutableStateOf(note.category) }
    val description = remember { mutableStateOf(note.description) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Update Note") },
        text = {
            Column {
                TextField(
                    value = title.value,
                    onValueChange = { title.value = it },
                    label = { Text("Title") }
                )
                TextField(
                    value = category.value,
                    onValueChange = { category.value = it },
                    label = { Text("Category") }
                )
                TextField(
                    value = description.value,
                    onValueChange = { description.value = it },
                    label = { Text("Description") }
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onUpdateNote(
                        Note(
                            id = note.id,
                            title = title.value,
                            category = category.value,
                            description = description.value
                        )
                    )
                    onDismiss()
                }
            ) {
                Text("Update")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun NoteItem(
    note: Note,
    onUpdateNote: (Note) -> Unit,
    onDeleteNote: (Note) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row {
                Text(text = "Title: ", fontWeight = FontWeight.Bold)
                Text(text = note.title)
            }
            Row {
                Text(text = "Category: ", fontWeight = FontWeight.Bold)
                Text(text = note.category)
            }
            Row {
                Text(text = "Description: ", fontWeight = FontWeight.Bold)
                Text(text = note.description)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                TextButton(
                    onClick = {
                        onUpdateNote(note)
                    },
                ) {
                    Text("Edit")
                }
                TextButton(
                    onClick = {
                        onDeleteNote(note)
                    },
                ) {
                    Text("Delete")
                }
            }
        }
    }
}