package com.example.newwarehouseapp.presentation.add_input_note

import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.newwarehouseapp.R
import com.example.newwarehouseapp.databinding.FragmentAddInputNoteBinding
import com.example.newwarehouseapp.databinding.FragmentAddOutputNoteBinding
import com.example.newwarehouseapp.domain.models.ProductWithProductOnWarehouse
import com.example.newwarehouseapp.presentation.add_output_note.AddOutputNoteFragmentDirections
import com.example.newwarehouseapp.presentation.add_output_note.AddOutputNoteViewModel
import com.example.newwarehouseapp.presentation.add_output_note.adapter.ProductsAdapter
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddInputNoteFragment : Fragment() {

    private var _binding: FragmentAddInputNoteBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AddInputNoteViewModel by viewModel()
    private lateinit var productsAdapter: ProductsAdapter

    private val sharedPreferences: SharedPreferences by inject()
    private val sharedPreferencesEditor: SharedPreferences.Editor by inject()
    private  var isReceiver = true
    private  var receiverId = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddInputNoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initRcView()
        receiverId = sharedPreferences.getInt("ID", -1)
        viewModel.fetchProducts()


        viewModel.productsList.observe(viewLifecycleOwner) { productsList ->
            productsAdapter.submitList(productsList)
        }

        viewModel.productsList.observe(viewLifecycleOwner) { productsList ->
            productsAdapter.submitList(productsList)
        }

        binding.buttonSaveInputNote.setOnClickListener {
            var success = saveNote()
            if(success) {
                val action = AddInputNoteFragmentDirections.actionAddInputNoteFragmentToInputNotesFragment()
                findNavController().navigate(action)
                Snackbar.make(requireView(), "Successfully added", Snackbar.LENGTH_SHORT).show()
            } else {
                Snackbar.make(requireView(), "Something went wrong", Snackbar.LENGTH_SHORT).show()
            }
        }

    }

    private fun initRcView() {
        productsAdapter =
            ProductsAdapter(requireContext(), object : ProductsAdapter.ProductsActionListener {
                override fun onClickItem(productWithProductOnWarehouse: ProductWithProductOnWarehouse) {
                    viewModel.product = productWithProductOnWarehouse
                    showSettingsView()
                    bindViews(productWithProductOnWarehouse)
                }
            })
        binding.recyclerViewAddInputNotes.adapter = productsAdapter
        binding.recyclerViewAddInputNotes.setHasFixedSize(true)
    }

    private fun showSettingsView() {
        with(binding) {
            addInputNoteInfoLayout.visibility = View.VISIBLE
            addInputNoteListLayout.visibility = View.GONE
        }
    }

    private fun bindViews(productWithProductOnWarehouse: ProductWithProductOnWarehouse) {
        binding.apply {
            addInputNoteNameTv.setText(productWithProductOnWarehouse.name, TextView.BufferType.SPANNABLE)
            addInputNoteDescriptionTv.setText(productWithProductOnWarehouse.description, TextView.BufferType.SPANNABLE)
            addInputNotePriceTv.setText(productWithProductOnWarehouse.price.toString(), TextView.BufferType.SPANNABLE)
            addInputNoteCountTv.setText(productWithProductOnWarehouse.productOnWarehouse.count.toString(), TextView.BufferType.SPANNABLE)
        }
    }
    fun isCountValid() : Boolean{
        return viewModel.isInputCountValid(binding.editInputNoteCount.text.toString())
    }
    fun saveNote() : Boolean{
        if (!isCountValid()) {
            Snackbar.make(requireView(), "Count invalid", Snackbar.LENGTH_SHORT).show()
            return false
        }
        else{
            viewModel.addInputNote(receiverId,binding.editInputNoteCount.text.toString().toInt())
            return true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}