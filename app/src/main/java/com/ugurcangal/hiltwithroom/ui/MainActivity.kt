package com.ugurcangal.hiltwithroom.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.ugurcangal.hiltwithroom.R
import com.ugurcangal.hiltwithroom.adapter.NoteAdapter
import com.ugurcangal.hiltwithroom.databinding.ActivityMainBinding
import com.ugurcangal.hiltwithroom.db.NoteDao
import com.ugurcangal.hiltwithroom.db.NoteEntitity
import com.ugurcangal.hiltwithroom.repository.DbRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    @Inject
    lateinit var repository: DbRepository
    @Inject
    lateinit var noteAdapter: NoteAdapter
    @Inject
    lateinit var noteEntitity: NoteEntitity
    @Inject
    lateinit var noteDao: NoteDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddNote.setOnClickListener {
            startActivity(Intent(this, AddNoteActivity::class.java))
        }

    }

    override fun onResume() {
        super.onResume()
        checkItem()
    }

    fun checkItem(){
        binding.apply {
            if (repository.getAllNotes().isNotEmpty()){
                rvNoteList.visibility = View.VISIBLE
                tvEmptyText.visibility = View.GONE
                noteAdapter.differ.submitList(repository.getAllNotes())
                setupRecyclerView()
            }else{
                rvNoteList.visibility = View.GONE
                tvEmptyText.visibility = View.VISIBLE
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvNoteList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = noteAdapter
        }
    }
}