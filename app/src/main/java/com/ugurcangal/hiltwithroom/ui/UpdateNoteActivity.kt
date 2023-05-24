package com.ugurcangal.hiltwithroom.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ugurcangal.hiltwithroom.R
import com.ugurcangal.hiltwithroom.adapter.NoteAdapter
import com.ugurcangal.hiltwithroom.databinding.ActivityUpdateNoteBinding
import com.ugurcangal.hiltwithroom.db.NoteEntitity
import com.ugurcangal.hiltwithroom.repository.DbRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UpdateNoteActivity : AppCompatActivity() {

    lateinit var binding : ActivityUpdateNoteBinding

    @Inject
    lateinit var repository: DbRepository
    @Inject
    lateinit var noteAdapter: NoteAdapter
    @Inject
    lateinit var noteEntitity: NoteEntitity

    private var noteId = 0
    private var defaultTitle = ""
    private var defaultDesc = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.let {
            noteId=it.getInt("NOTE_ID")
        }

        binding.apply {
            defaultTitle = repository.getNote(noteId).noteTitle
            defaultDesc = repository.getNote(noteId).noteDesc

            edtTitle.setText(defaultTitle)
            edtDesc.setText(defaultDesc)

            btnDelete.setOnClickListener {
                noteEntitity = NoteEntitity(noteId,defaultTitle,defaultDesc)
                repository.deleteNote(noteEntitity)
                finish()
            }

            btnSave.setOnClickListener {
                val title = edtTitle.text.toString()
                val desc = edtDesc.text.toString()
                if (title.isNotEmpty() || desc.isNotEmpty()){
                    noteEntitity = NoteEntitity(noteId,title,desc)
                    repository.updateNote(noteEntitity)
                    finish()
                }else{
                    Toast.makeText(this@UpdateNoteActivity, "Title and Desc cant be Empty", Toast.LENGTH_SHORT).show()
                }
            }
        }


    }
}