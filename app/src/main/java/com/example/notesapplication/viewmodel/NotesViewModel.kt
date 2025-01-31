package com.example.notesapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notesapplication.entity.Note
import com.example.notesapplication.database.NotesDatabase
import com.example.notesapplication.repository.NotesRepository
import kotlinx.coroutines.launch

class NotesViewModel(application: Application): AndroidViewModel(application) {

    private val repository: NotesRepository

    val noteList: LiveData<List<Note>>

    init {
        val database = NotesDatabase.Companion.getDatabase(application)
        val noteDao = database.noteDao
        repository = NotesRepository(noteDao)
        noteList = repository.getNotes()
    }

    fun addNote(note: Note) {
        viewModelScope.launch {
            repository.insertNotes(note)
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            repository.updateNotes(note)
        }
    }

    fun clearNote(note: Note) {
        viewModelScope.launch{
            repository.deleteNotes(note)
        }
    }

}