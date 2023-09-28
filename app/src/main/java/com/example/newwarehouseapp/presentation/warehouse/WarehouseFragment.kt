package com.example.newwarehouseapp.presentation.warehouse

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.newwarehouseapp.R
import com.example.newwarehouseapp.databinding.FragmentOutputNotesBinding
import com.example.newwarehouseapp.databinding.FragmentWarehouseBinding
import com.example.newwarehouseapp.domain.constants.SortType
import com.example.newwarehouseapp.domain.models.ProductWithProductOnWarehouse
import com.example.newwarehouseapp.presentation.add_input_note.AddInputNoteFragmentDirections
import com.example.newwarehouseapp.presentation.output_notes.OutputNotesFragmentDirections
import com.example.newwarehouseapp.presentation.output_notes.adapter.OutputNotesAdapter
import com.example.newwarehouseapp.presentation.screen_state.ScreenState
import com.example.newwarehouseapp.presentation.warehouse.adapter.WarehouseAdapter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class WarehouseFragment : Fragment() {

    private var _binding: FragmentWarehouseBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WarehouseViewModel by viewModel()
    private lateinit var warehouseAdapter: WarehouseAdapter

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
        _binding = FragmentWarehouseBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true);

        initRcView()
        isReceiver = sharedPreferences.getBoolean("IS_RECEIVER", true)
        receiverId = sharedPreferences.getInt("ID", -1)
        viewModel.fetchProducts()


        viewModel.productsList.observe(viewLifecycleOwner) { productsList ->
            warehouseAdapter.submitList(productsList)
        }

        viewModel.screenState.observe(viewLifecycleOwner) { screenState ->

            when (screenState) {
                ScreenState.Content -> showContent()
                ScreenState.Empty -> showEmptyScreen()
                ScreenState.Error -> showError()
                ScreenState.Loading -> showLoading()
                ScreenState.NoAccess -> showContent()
            }
        }

        binding.buttonAddProductWarehouseFragment.setOnClickListener {
            val action = WarehouseFragmentDirections.actionWarehouseFragmentToAddProductFragment()
            findNavController().navigate(action)
        }

        when(viewModel.sortType) {
            SortType.NAME -> binding.spFilter.setSelection(0)
            SortType.PRICE -> binding.spFilter.setSelection(1)
            else -> {
                binding.spFilter.setSelection(0)
            }
        }

        binding.spFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                when(pos) {
                    0 -> viewModel.sortRuns(SortType.NAME)
                    1 -> viewModel.sortRuns(SortType.PRICE)
                }
            }
        }

    }

    private fun initRcView() {
        warehouseAdapter =
            WarehouseAdapter(requireContext(), object : WarehouseAdapter.WarehouseActionListener {
                override fun onClickItem(productWithProductOnWarehouse: ProductWithProductOnWarehouse) {
                    val action =
                        WarehouseFragmentDirections.actionWarehouseFragmentToAddProductFragment(
                            productWithProductOnWarehouse.id
                        )
                    findNavController().navigate(action)
                }
            })
        binding.recyclerViewWarehouse.adapter = warehouseAdapter
        binding.recyclerViewWarehouse.setHasFixedSize(true)
    }

    private fun showContent() {
        with(binding) {
            noContentLayoutWarehouse.visibility = View.GONE
            contentInputLayoutWarehouse.visibility = View.VISIBLE
            errorLayoutWarehouse.visibility = View.GONE
            loadingLayoutWarehouse.visibility = View.GONE
        }
    }

    private fun showLoading() {
        with(binding) {
            noContentLayoutWarehouse.visibility = View.GONE
            contentInputLayoutWarehouse.visibility = View.GONE
            errorLayoutWarehouse.visibility = View.GONE
            loadingLayoutWarehouse.visibility = View.VISIBLE
        }
    }

    private fun showEmptyScreen() {
        with(binding) {
            noContentLayoutWarehouse.visibility = View.VISIBLE
            contentInputLayoutWarehouse.visibility = View.GONE
            errorLayoutWarehouse.visibility = View.GONE
            loadingLayoutWarehouse.visibility = View.GONE
        }
    }

    private fun showError() {
        with(binding) {
            noContentLayoutWarehouse.visibility = View.GONE
            contentInputLayoutWarehouse.visibility = View.GONE
            errorLayoutWarehouse.visibility = View.VISIBLE
            loadingLayoutWarehouse.visibility = View.GONE
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
                val action = WarehouseFragmentDirections.actionWarehouseFragmentToSignInFragment()
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