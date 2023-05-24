package com.ugurcangal.hiltwithroom.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ugurcangal.hiltwithroom.R
import com.ugurcangal.hiltwithroom.databinding.ActivityAddNoteBinding
import com.ugurcangal.hiltwithroom.db.NoteEntitity
import com.ugurcangal.hiltwithroom.repository.DbRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddNoteActivity : AppCompatActivity() {

    lateinit var binding : ActivityAddNoteBinding

    @Inject
    lateinit var repository: DbRepository
    @Inject
    lateinit var noteEntitity: NoteEntitity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnSave.setOnClickListener {
                val title = edtTitle.text.toString()
                val desc = edtDesc.text.toString()
                if (title.isNotEmpty() || desc.isNotEmpty()){
                    noteEntitity = NoteEntitity(0,title,desc)
                    repository.saveNote(noteEntitity)
                    finish()
                }else{
                    Toast.makeText(this@AddNoteActivity, "Title and Desc cant be Empty", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}