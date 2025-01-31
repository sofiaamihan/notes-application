package com.example.notesapplication.repository

import androidx.lifecycle.LiveData
import com.example.notesapplication.dao.NoteDao
import com.example.notesapplication.entity.Note

class NotesRepository(private val noteDao: NoteDao) {

    fun getNotes(): LiveData<List<Note>> {
        return noteDao.getNotes()
    }

    suspend fun insertNotes(note: Note){
        noteDao.insertNotes(note)
    }

    suspend fun updateNotes(note: Note) {
        noteDao.updateNotes(note)
    }

    suspend fun deleteNotes(note: Note){
        noteDao.clearNotes(note.id)
    }

}