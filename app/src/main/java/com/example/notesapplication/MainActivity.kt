package com.example.notesapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.notesapplication.ui.theme.NotesApplicationTheme
import com.example.notesapplication.viewmodel.NotesViewModel
import com.example.notesapplication.viewmodel.NotesViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModelFactory = NotesViewModelFactory(application)
        val viewModel: NotesViewModel = ViewModelProvider(this, viewModelFactory)[NotesViewModel::class.java]

        enableEdgeToEdge()
        setContent {
            NotesApplicationTheme {
                NotesListScreen(viewModel)
            }
        }
    }
}
