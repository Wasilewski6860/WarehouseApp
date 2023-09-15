package com.example.newwarehouseapp.presentation.output_notes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newwarehouseapp.databinding.NoteItemBinding
import com.example.newwarehouseapp.domain.models.OutputNoteWithProduct

class OutputNotesAdapter() : ListAdapter<OutputNoteWithProduct, OutputNotesAdapter.OutputNoteWithProductAdapter>(DiffCallBack) {

    class OutputNoteWithProductAdapter(val binding: NoteItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OutputNoteWithProductAdapter {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NoteItemBinding.inflate(inflater, parent, false)
        return OutputNoteWithProductAdapter(binding)
    }

    override fun onBindViewHolder(holder: OutputNoteWithProductAdapter, position: Int) {
        val item = getItem(position)

        with(holder.binding) {
            productNameNoteItem.text = item.product.name.toString()
            productDescriptionNoteItem.text = item.product.description.toString()
            productCountNoteItem.text = item.count.toString()
        }
    }


    companion object {
        val DiffCallBack = object : DiffUtil.ItemCallback<OutputNoteWithProduct>() {

            override fun areItemsTheSame(oldItem: OutputNoteWithProduct, newItem: OutputNoteWithProduct): Boolean {
                return oldItem === newItem
            }
            override fun areContentsTheSame(oldItem: OutputNoteWithProduct, newItem: OutputNoteWithProduct): Boolean {
                return oldItem == newItem
            }
        }
    }

}