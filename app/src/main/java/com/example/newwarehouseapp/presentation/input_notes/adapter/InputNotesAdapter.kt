package com.example.newwarehouseapp.presentation.input_notes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newwarehouseapp.databinding.NoteItemBinding
import com.example.newwarehouseapp.domain.models.InputNoteWithProduct
import com.example.newwarehouseapp.domain.models.OutputNoteWithProduct

class InputNotesAdapter() : ListAdapter<InputNoteWithProduct, InputNotesAdapter.InputNoteWithProductAdapter>(DiffCallBack) {

    class InputNoteWithProductAdapter(val binding: NoteItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InputNoteWithProductAdapter {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NoteItemBinding.inflate(inflater, parent, false)
        return InputNoteWithProductAdapter(binding)
    }

    override fun onBindViewHolder(holder: InputNoteWithProductAdapter, position: Int) {
        val item = getItem(position)

        with(holder.binding) {
            productNameNoteItem.text = item.product.name.toString()
            productDescriptionNoteItem.text = item.product.description.toString()
            productCountNoteItem.text = item.count.toString()
        }
    }


    companion object {
        val DiffCallBack = object : DiffUtil.ItemCallback<InputNoteWithProduct>() {

            override fun areItemsTheSame(oldItem: InputNoteWithProduct, newItem: InputNoteWithProduct): Boolean {
                return oldItem === newItem
            }
            override fun areContentsTheSame(oldItem: InputNoteWithProduct, newItem: InputNoteWithProduct): Boolean {
                return oldItem == newItem
            }
        }
    }

}