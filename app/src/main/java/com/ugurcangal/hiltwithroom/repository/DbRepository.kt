package com.ugurcangal.hiltwithroom.repository

import com.ugurcangal.hiltwithroom.db.NoteDao
import com.ugurcangal.hiltwithroom.db.NoteEntitity
import javax.inject.Inject

class DbRepository @Inject constructor(private val dao: NoteDao) {

    fun saveNote(note: NoteEntitity) = dao.insertNote(note)
    fun updateNote(note: NoteEntitity) = dao.updateNote(note)
    fun deleteNote(note: NoteEntitity) = dao.deleteNote(note)
    fun getNote(id : Int) = dao.getNote(id)
    fun getAllNotes() = dao.getAllNotes()

}