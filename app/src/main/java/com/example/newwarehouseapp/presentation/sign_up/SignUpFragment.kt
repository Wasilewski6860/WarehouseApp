package com.example.newwarehouseapp.presentation.sign_up

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.findNavController
import com.example.newwarehouseapp.R
import com.example.newwarehouseapp.databinding.FragmentSignUpBinding
import com.example.newwarehouseapp.domain.models.Receiver
import com.example.newwarehouseapp.domain.models.Supplier
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SignUpViewModel by viewModel()
    private var receiverId = -1
    private var supplierId = -1
    private lateinit var receiver: Receiver
    private lateinit var supplier: Supplier
    private var isReceiver = true



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSignUp.setOnClickListener{
            showDialog()
        }

        emailFocusListener()
        passwordFocusListener()
        phoneFocusListener()

    }

    fun showSnackbar(text : String){
        Snackbar.make(
            requireActivity().findViewById(R.id.rootView),
            text,
            Snackbar.LENGTH_LONG
        ).show()
    }

    fun showNoReceiver() = showSnackbar("No receiver with this name")
    fun showNoSupplier() = showSnackbar("No supplier with this name")


    private fun signUp(isReceiver: Boolean) {

        if (!isInputNameValid()) showSnackbar("Input name")
        if (!isInputEmailValid()) showSnackbar("Email invalid")
        if (!isInputPhoneValid()) showSnackbar("Phone invalid")

        if (isInputValid()){

            if (isReceiver){

                viewModel.addNewReceiver(
                    name = binding.signUpName.text.toString(),
                    password = binding.signUpPassword.text.toString(),
                    phone = viewModel.standartizingPhone( binding.signUpPhone.text.toString()),
                    email = binding.signUpEmail.text.toString()
                )
                showSnackbar("Successfully created ")
                val action = SignUpFragmentDirections.actionSignUpFragmentToSignInFragment()
                findNavController().navigate(action)

            }
            else{
                viewModel.addNewSupplier(
                    name = binding.signUpName.text.toString(),
                    password = binding.signUpPassword.text.toString(),
                    phone = viewModel.standartizingPhone( binding.signUpPhone.text.toString()),
                    email = binding.signUpEmail.text.toString()
                )
                showSnackbar("Successfully created ")
                val action = SignUpFragmentDirections.actionSignUpFragmentToSignInFragment()
                findNavController().navigate(action)
            }
        }

    }


    private fun isInputNameValid(): Boolean {
        return viewModel.isInputIsValid(
            name = binding.signUpName.text.toString(),
        )
    }

    private fun emailFocusListener() {
        binding.signUpEmail.setOnFocusChangeListener { _, focused ->
            if(!focused) {
                if (!viewModel.isEmailValid(email = binding.signUpEmail.text.toString()))
                    binding.layoutSignUpEmail.helperText = "Invalid email"
            }
        }
    }
    private fun passwordFocusListener()
    {
        binding.signUpPassword.setOnFocusChangeListener { _, focused ->
            if(!focused){
                binding.layoutSignUpPassword.helperText = viewModel.isPasswordInvalid(passwordText = binding.signUpPassword.text.toString())
            }
        }
    }
    private fun phoneFocusListener() {
        binding.signUpPhone.setOnFocusChangeListener { _, focused ->
            if(!focused) {
                binding.layoutSignUpPhone.helperText = viewModel.validPhone(binding.signUpPhone.text.toString())
            }
        }
    }



    private fun isInputEmailValid(): Boolean {
        return viewModel.isEmailValid(
            email = binding.signUpEmail.text.toString()
        )
    }
    private fun isInputPhoneValid(): Boolean {
        return viewModel.isPhoneValid(
            phone = binding.signUpPhone.text.toString()
        )
    }
    private fun isInputValid() : Boolean = isInputNameValid() && isInputEmailValid() && isInputPhoneValid()

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
                signUp(isReceiver)
            }
            .setPositiveButton("Supplier") { _, _ ->
                isReceiver = false
                signUp(isReceiver)
            }
            .show()
    }


}