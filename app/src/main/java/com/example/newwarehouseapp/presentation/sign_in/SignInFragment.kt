package com.example.newwarehouseapp.presentation.sign_in

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.newwarehouseapp.R
import com.example.newwarehouseapp.databinding.FragmentSignInBinding

import com.example.newwarehouseapp.domain.models.Receiver
import com.example.newwarehouseapp.domain.models.Supplier
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SignInViewModel by viewModel()
    private var receiverId = -1
    private var supplierId = -1
    private lateinit var receiver: Receiver
    private lateinit var supplier: Supplier
    private var isReceiver = true

    var isFirstAppOpen = true
    private val sharedPreferences: SharedPreferences by inject()
    private val sharedPreferencesEditor: SharedPreferences.Editor by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSignIn.setOnClickListener{
            showDialog()
        }

        binding.buttonSignUpSignIn.setOnClickListener{
            findNavController().navigate(
                R.id.action_signInFragment_to_signUpFragment,
                savedInstanceState
            )
        }

        isFirstAppOpen = sharedPreferences.getBoolean("KEY_IS_FIRST", true)
        if(!isFirstAppOpen) {
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.signInFragment, true)
                .build()
            isReceiver = sharedPreferences.getBoolean("IS_RECEIVER", false)
            findNavController().navigate(
                if (isReceiver) R.id.action_signInFragment_to_outputNotesFragment else R.id.action_signInFragment_to_inputNotesFragment,
                savedInstanceState,
                navOptions
            )
        }

        viewModel.supplier.observe(viewLifecycleOwner) {
            if (it!=null && !isReceiver){
                val success = writePersonalDataToSharedPref()
                if(success) {
                    findNavController().navigate(R.id.action_signInFragment_to_inputNotesFragment)
                    Snackbar.make(requireView(), "Welcome, "+it.name.toString(), Snackbar.LENGTH_SHORT).show()
                } else {
                    Snackbar.make(requireView(), "Something went wrong", Snackbar.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.receiver.observe(viewLifecycleOwner) {
            if (it!=null && isReceiver){
                val success = writePersonalDataToSharedPref()
                if(success) {
                    findNavController().navigate(R.id.action_signInFragment_to_outputNotesFragment)
                    Snackbar.make(requireView(), "Welcome, "+it.name.toString(), Snackbar.LENGTH_SHORT).show()
                } else {
                    Snackbar.make(requireView(), "Something went wrong", Snackbar.LENGTH_SHORT).show()
                }
            }
        }

    }

    fun showSnackbar(text : String){
        Snackbar.make(
            requireActivity().findViewById(R.id.rootView),
            text,
            Snackbar.LENGTH_LONG
        ).show()
    }

    fun showNoReceiver() = showSnackbar("No receiver with this name")
    fun showWrongPassword() = showSnackbar("Password is wrong")
    fun showNoSupplier() = showSnackbar("No supplier with this name")

    private fun signIn(isReceiver: Boolean) {
        if(isInputValid()){
            if (isReceiver) viewModel.signInReceiver(binding.signInName.text.toString(),binding.signInPassword.text.toString() ,::showNoReceiver, ::showWrongPassword)
            else  viewModel.signInSupplier(binding.signInName.text.toString(),binding.signInPassword.text.toString(),::showNoSupplier, ::showWrongPassword)
        }
        else{
            showSnackbar("Input invalid")
        }
    }

    private fun isInputValid(): Boolean {
        return viewModel.isInputIsValid(
            name = binding.signInName.text.toString(),
        )
    }


    private fun hideKeyboard() {
        val inputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.scrollView.windowToken, 0)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ID = "id"
    }

    private fun showDialog() {

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("User type")
            .setMessage("Select type of user")
            .setCancelable(false)
            .setNegativeButton("Receiver") { _, _ ->
                isReceiver = true
                signIn(isReceiver)
            }
            .setPositiveButton("Supplier") { _, _ ->
                isReceiver = false
                signIn(isReceiver)
            }
            .show()
    }


    private fun writePersonalDataToSharedPref(): Boolean {
        val name = binding.signInName.text.toString()
        val id = if (isReceiver) viewModel.receiver.value?.id else viewModel.supplier.value?.id
        if(name.isEmpty() || id == null) {
            return false
        }
        sharedPreferences.edit()
            .putString("NAME", name)
            .putInt("ID", id)
            .putBoolean("IS_RECEIVER", isReceiver)
            .putBoolean("KEY_IS_FIRST",false)
            .apply()
        return true
    }
}