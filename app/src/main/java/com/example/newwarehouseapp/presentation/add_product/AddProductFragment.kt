package com.example.newwarehouseapp.presentation.add_product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.newwarehouseapp.R
import com.example.newwarehouseapp.databinding.FragmentAddProductBinding
import com.example.newwarehouseapp.domain.models.Product
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddProductFragment : Fragment() {

    private var _binding: FragmentAddProductBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AddProductViewModel by viewModel()
    private var productId = -1
    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            productId = it.getInt(ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddProductBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (productId > 0) {
            viewModel.getProduct(productId)
            viewModel.product.observe(viewLifecycleOwner) {
                bindViews(it)
            }
            binding.buttonSave.setOnClickListener {
                saveProduct(productId)
            }
        }
        else {
            binding.buttonSave.setOnClickListener { saveProduct(productId) }
        }

        binding.buttonCancel.setOnClickListener { findNavController().navigateUp() }
    }

    private fun isNameValid(): Boolean {
        return viewModel.isInputIsValid(
            name = binding.editProductName.text.toString(),
        )
    }
    private fun isDescriptionValid(): Boolean {
        return viewModel.isInputIsValid(
            name = binding.editProductDescription.text.toString(),
        )
    }
    private fun isPriceValid(): Boolean {
        return viewModel.isPriceValid(
            price = binding.editProductPrice.text.toString(),
        )
    }


    private fun bindViews(product: Product) {

        binding.apply {
            editProductName.setText(product.name, TextView.BufferType.SPANNABLE)
            editProductDescription.setText(product.description, TextView.BufferType.SPANNABLE)
            editProductPrice.setText(product.price.toString(), TextView.BufferType.SPANNABLE)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ID = "id"
    }

    fun showSnackbar(text : String){
        Snackbar.make(
            requireActivity().findViewById(R.id.rootView),
            text,
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun showDialog() {

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Product count")
            .setMessage("Create empty lot with this product on warehouse")
            .setCancelable(false)
            .setNegativeButton("Yes") { _, _ ->
                viewModel.addEmptyProductOnWarehouse(0)
                val action = AddProductFragmentDirections.actionAddProductFragmentToWarehouseFragment()
                findNavController().navigate(action)
            }
            .setPositiveButton("Yes, please") { _, _ ->
                viewModel.addEmptyProductOnWarehouse(0)
                val action = AddProductFragmentDirections.actionAddProductFragmentToWarehouseFragment()
                findNavController().navigate(action)
            }
            .show()
    }

    private fun saveProduct(cocktailId: Int) {

        if (!isNameValid()) showSnackbar("Name invalid ")
        if (!isDescriptionValid()) showSnackbar("Description invalid ")
        if (!isPriceValid()) showSnackbar("Price invalid ")

        if (isNameValid() && isDescriptionValid() && isPriceValid()) {

            if (productId>0){
                viewModel.editProduct(
                    id = cocktailId,
                    name = binding.editProductName.text.toString(),
                    description = binding.editProductDescription.text.toString(),
                    price = binding.editProductPrice.text.toString().toInt()
                )
                val action =
                    AddProductFragmentDirections.actionAddProductFragmentToWarehouseFragment()
                findNavController().navigate(action)
            }
            else{
                viewModel.addNewProduct(
                    name = binding.editProductName.text.toString(),
                    description = binding.editProductDescription.text.toString(),
                    price = binding.editProductPrice.text.toString().toInt()
                )

                showDialog()

            }

        }

    }

}