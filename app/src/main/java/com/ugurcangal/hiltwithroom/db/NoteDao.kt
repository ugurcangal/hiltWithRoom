package com.ugurcangal.hiltwithroom.db

import androidx.room.*
import com.ugurcangal.hiltwithroom.utils.Constants.NOTE_TABLE

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(noteEntitity: NoteEntitity)

    @Update
    fun updateNote(noteEntitity: NoteEntitity)

    @Delete
    fun deleteNote(noteEntitity: NoteEntitity)

    @Query("SELECT * FROM $NOTE_TABLE ORDER BY noteId DESC")
    fun getAllNotes() : MutableList<NoteEntitity>

    @Query("SELECT * FROM $NOTE_TABLE WHERE noteId like :id")
    fun getNote(id : Int) : NoteEntitity

}