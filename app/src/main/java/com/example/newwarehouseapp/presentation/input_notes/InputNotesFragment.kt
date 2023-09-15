package com.example.newwarehouseapp.presentation.input_notes

import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.newwarehouseapp.R
import com.example.newwarehouseapp.databinding.FragmentInputNotesBinding
import com.example.newwarehouseapp.presentation.add_input_note.AddInputNoteFragmentDirections

import com.example.newwarehouseapp.presentation.input_notes.adapter.InputNotesAdapter

import com.example.newwarehouseapp.presentation.screen_state.ScreenState
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.android.ext.android.inject

class InputNotesFragment : Fragment() {

    private var _binding: FragmentInputNotesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: InputNotesViewModel by viewModel()
    private lateinit var notesAdapter: InputNotesAdapter

    private val sharedPreferences: SharedPreferences by inject()
    private val sharedPreferencesEditor: SharedPreferences.Editor by inject()
    private  var isReceiver = true
    private  var receiverId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInputNotesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initRcView()

        isReceiver = sharedPreferences.getBoolean("IS_RECEIVER", true)
        receiverId = sharedPreferences.getInt("ID", -1)
        viewModel.fetchNotes(receiverId,isReceiver)

        viewModel.notesList.observe(viewLifecycleOwner) { notesList ->
            notesAdapter.submitList(notesList)
        }

        viewModel.screenState.observe(viewLifecycleOwner) { screenState ->
            when (screenState) {
                ScreenState.Content -> showContent()
                ScreenState.Empty -> showEmptyScreen()
                ScreenState.Error -> showError()
                ScreenState.Loading -> showLoading()
                ScreenState.NoAccess -> showNoAccess()
            }
        }

        binding.buttonAddInputNoteInputNotesFragment.setOnClickListener {
            val action = InputNotesFragmentDirections.actionInputNotesFragmentToAddInputNoteFragment()
            findNavController().navigate(action)
        }

    }

    private fun initRcView() {
        notesAdapter = InputNotesAdapter()

        binding.recyclerViewInputNotes.adapter = notesAdapter
        binding.recyclerViewInputNotes.setHasFixedSize(true)
    }

    private fun showContent() {
        with(binding) {
            noContentLayoutInputNotes.visibility = View.GONE
            contentInputLayoutInputNotes.visibility = View.VISIBLE
            errorLayoutInputNotes.visibility = View.GONE
            noAccessLayoutInputNotes.visibility = View.GONE
        }
    }

    private fun showLoading() {
        with(binding) {
            noContentLayoutInputNotes.visibility = View.GONE
            contentInputLayoutInputNotes.visibility = View.GONE
            errorLayoutInputNotes.visibility = View.GONE
            noAccessLayoutInputNotes.visibility = View.GONE
        }
    }

    private fun showEmptyScreen() {
        with(binding) {
            noContentLayoutInputNotes.visibility = View.VISIBLE
            contentInputLayoutInputNotes.visibility = View.GONE
            errorLayoutInputNotes.visibility = View.GONE
            noAccessLayoutInputNotes.visibility = View.GONE
        }
    }

    private fun showError() {
        with(binding) {
            noContentLayoutInputNotes.visibility = View.GONE
            contentInputLayoutInputNotes.visibility = View.GONE
            errorLayoutInputNotes.visibility = View.VISIBLE
            noAccessLayoutInputNotes.visibility = View.GONE
        }
    }

    private fun showNoAccess() {
        with(binding) {
            noContentLayoutInputNotes.visibility = View.GONE
            contentInputLayoutInputNotes.visibility = View.GONE
            errorLayoutInputNotes.visibility = View.GONE
            noAccessLayoutInputNotes.visibility = View.VISIBLE

            buttonAddInputNoteInputNotesFragment.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.sign_out -> {
                writePersonalDataToSharedPref()
                val action = InputNotesFragmentDirections.actionInputNotesFragmentToSignInFragment()
                findNavController().navigate(action)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun writePersonalDataToSharedPref(): Boolean {
        sharedPreferences.edit()
            .putBoolean("KEY_IS_FIRST",true)
            .apply()
        return true
    }
}