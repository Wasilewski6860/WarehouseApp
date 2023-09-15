package com.example.newwarehouseapp.presentation.output_notes

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import com.example.newwarehouseapp.R
import com.example.newwarehouseapp.databinding.FragmentOutputNotesBinding
import com.example.newwarehouseapp.presentation.add_input_note.AddInputNoteFragmentDirections
import com.example.newwarehouseapp.presentation.output_notes.adapter.OutputNotesAdapter
import com.example.newwarehouseapp.presentation.screen_state.ScreenState
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class OutputNotesFragment : Fragment() {

    private var _binding: FragmentOutputNotesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: OutputNotesViewModel by viewModel()
    private lateinit var notesAdapter: OutputNotesAdapter

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
        _binding = FragmentOutputNotesBinding.inflate(layoutInflater, container, false)
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

        binding.buttonAddOutputNoteOutputNotesFragment.setOnClickListener {
            val action = OutputNotesFragmentDirections.actionOutputNotesFragmentToAddOutputNoteFragment()
            findNavController().navigate(action)
        }

    }

    private fun initRcView() {
        notesAdapter = OutputNotesAdapter()

        binding.recyclerViewOutputNotes.adapter = notesAdapter
        binding.recyclerViewOutputNotes.setHasFixedSize(true)
    }

    private fun showContent() {
        with(binding) {
            noContentLayoutOutputNotes.visibility = View.GONE
            contentOutputLayoutOutputNotes.visibility = View.VISIBLE
            errorLayoutOutputNotes.visibility = View.GONE
            noAccessLayoutOutputNotes.visibility = View.GONE
        }
    }

    private fun showLoading() {
        with(binding) {
            noContentLayoutOutputNotes.visibility = View.GONE
            contentOutputLayoutOutputNotes.visibility = View.GONE
            errorLayoutOutputNotes.visibility = View.GONE
            noAccessLayoutOutputNotes.visibility = View.GONE
        }
    }

    private fun showEmptyScreen() {
        with(binding) {
            noContentLayoutOutputNotes.visibility = View.VISIBLE
            contentOutputLayoutOutputNotes.visibility = View.GONE
            errorLayoutOutputNotes.visibility = View.GONE
            noAccessLayoutOutputNotes.visibility = View.GONE
        }
    }

    private fun showError() {
        with(binding) {
            noContentLayoutOutputNotes.visibility = View.GONE
            contentOutputLayoutOutputNotes.visibility = View.GONE
            errorLayoutOutputNotes.visibility = View.VISIBLE
            noAccessLayoutOutputNotes.visibility = View.GONE
        }
    }

    private fun showNoAccess() {
        with(binding) {
            noContentLayoutOutputNotes.visibility = View.GONE
            contentOutputLayoutOutputNotes.visibility = View.GONE
            errorLayoutOutputNotes.visibility = View.GONE
            noAccessLayoutOutputNotes.visibility = View.VISIBLE

            buttonAddOutputNoteOutputNotesFragment.visibility = View.GONE
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
                val action = OutputNotesFragmentDirections.actionOutputNotesFragmentToSignInFragment()
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