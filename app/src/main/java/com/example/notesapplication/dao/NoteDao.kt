package com.example.notesapplication.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.notesapplication.entity.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes_table")
    fun getNotes(): LiveData<List<Note>>

    @Upsert
    suspend fun insertNotes(notes: Note)

    @Update
    suspend fun updateNotes(notes: Note)

    @Query("DELETE FROM notes_table WHERE id = :noteId")
    suspend fun clearNotes(noteId: Int)

}