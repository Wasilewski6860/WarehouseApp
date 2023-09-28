package com.example.newwarehouseapp.presentation.add_output_note

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import com.example.newwarehouseapp.R
import com.example.newwarehouseapp.databinding.FragmentAddOutputNoteBinding
import com.example.newwarehouseapp.domain.models.Product
import com.example.newwarehouseapp.domain.models.ProductWithProductOnWarehouse
import com.example.newwarehouseapp.presentation.add_output_note.adapter.ProductsAdapter
import com.example.newwarehouseapp.presentation.screen_state.ScreenState
import com.example.newwarehouseapp.presentation.warehouse.WarehouseFragmentDirections
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddOutputNoteFragment : Fragment() {

    private var _binding: FragmentAddOutputNoteBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AddOutputNoteViewModel by viewModel()
    private lateinit var productsAdapter: ProductsAdapter

    private val sharedPreferences: SharedPreferences by inject()
    private val sharedPreferencesEditor: SharedPreferences.Editor by inject()
    private  var isReceiver = true
    private  var receiverId = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddOutputNoteBinding.inflate(layoutInflater, container, false)
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

        binding.buttonSaveOutputNote.setOnClickListener {
            var success = saveNote()
            if(success) {
                val action = AddOutputNoteFragmentDirections.actionAddOutputNoteFragmentToOutputNotesFragment()
                findNavController().navigate(action)
                Snackbar.make(requireView(), "Successfully added", Snackbar.LENGTH_SHORT).show()
            } else {
                Snackbar.make(requireView(), "Something went wrong", Snackbar.LENGTH_SHORT).show()
            }
        }

        binding.searchViewAddOutputNote.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(text: String?): Boolean {
                if (text != null) {
                    viewModel.fetchByName(text)
                }
                else viewModel.fetchProducts()
                return true
            }
        })

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
        binding.recyclerViewAddOutputNotes.adapter = productsAdapter
        binding.recyclerViewAddOutputNotes.setHasFixedSize(true)
    }

    private fun showSettingsView() {
        with(binding) {
            addOutputNoteInfoLayout.visibility = View.VISIBLE
            addOutputNoteListLayout.visibility = View.GONE
        }
    }

    private fun bindViews(productWithProductOnWarehouse: ProductWithProductOnWarehouse) {
        binding.apply {
            addOutputNoteNameTv.setText("Name: "+productWithProductOnWarehouse.name, TextView.BufferType.SPANNABLE)
            addOutputNoteDescriptionTv.setText("Description: "+productWithProductOnWarehouse.description, TextView.BufferType.SPANNABLE)
            addOutputNotePriceTv.setText("Price: "+productWithProductOnWarehouse.price.toString(), TextView.BufferType.SPANNABLE)
            addOutputNoteCountTv.setText("Count: "+productWithProductOnWarehouse.productOnWarehouse.count.toString(), TextView.BufferType.SPANNABLE)
        }
    }
    fun isCountValid() : Boolean{
        return viewModel.isInputCountValid(binding.editOutputNoteCount.text.toString())
    }
    fun saveNote() : Boolean{
        if (!isCountValid()) {
            Snackbar.make(requireView(), "Count invalid", Snackbar.LENGTH_SHORT).show()
            return false
        }
        else{
            viewModel.addOutputNote(receiverId,binding.editOutputNoteCount.text.toString().toInt())
            return true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}