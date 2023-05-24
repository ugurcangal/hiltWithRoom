package com.ugurcangal.hiltwithroom.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ugurcangal.hiltwithroom.databinding.ItemNoteBinding
import com.ugurcangal.hiltwithroom.db.NoteEntitity
import com.ugurcangal.hiltwithroom.ui.UpdateNoteActivity
import javax.inject.Inject

class NoteAdapter @Inject constructor() : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    private lateinit var binding: ItemNoteBinding
    private lateinit var context : Context

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NoteEntitity) {
            binding.apply {
                tvTitle.text = item.noteTitle
                tvDesc.text = item.noteDesc
                root.setOnClickListener {
                    val intent = Intent(context, UpdateNoteActivity::class.java)
                    intent.putExtra("NOTE_ID", item.noteId)
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemNoteBinding.inflate(inflater, parent, false)
        context = parent.context
        return ViewHolder()
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    private val differCallback = object : DiffUtil.ItemCallback<NoteEntitity>() {
        override fun areItemsTheSame(oldItem: NoteEntitity, newItem: NoteEntitity): Boolean {
            return oldItem.noteId == newItem.noteId
        }

        override fun areContentsTheSame(oldItem: NoteEntitity, newItem: NoteEntitity): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

}