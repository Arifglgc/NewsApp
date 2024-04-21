package com.golgeciarif.newsapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.kelineyt.util.RegisterValidation
import com.github.leandroborgesferreira.loadingbutton.customViews.CircularProgressButton
import com.golgeciarif.newsapp.R
import com.golgeciarif.newsapp.databinding.FragmentRegisterBinding
import com.golgeciarif.newsapp.model.User
import com.golgeciarif.newsapp.resource.Resource
import com.golgeciarif.newsapp.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
private const val TAG = "RegisterFragment"

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    lateinit var binding: FragmentRegisterBinding
    //lateinit var viewModelOld: LaunchViewModel
    lateinit var btnRegister: CircularProgressButton

    private val viewModel by viewModels<RegisterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // viewModel = (activity as LaunchActivity).viewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding= FragmentRegisterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnRegister = view.findViewById(R.id.btn_register_button)

        //onRegisterBtnClick()
        //observeSaveUserInformation()
        //onLoginClick()

        binding.apply {
            btnRegisterButton.setOnClickListener {
                val user = User(
                    etName.text.toString().trim(),
                    etEmail.text.toString().trim()
                )
                val password = etPassword.text.toString()
                viewModel.createAccountWithEmailAndPassword(user, password)
            }
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.register.collect { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            binding.btnRegisterButton.startAnimation()
                        }
                        is Resource.Success -> {
                            Toast.makeText(context, "Succecfully Registered!", Toast.LENGTH_LONG).show()
                            binding.btnRegisterButton.revertAnimation()
                            //   findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                        }
                        is Resource.Error -> {
                            Log.e(TAG, resource.message.toString())
                            binding.btnRegisterButton.revertAnimation()
                        }
                        else -> Unit
                    }
                }
            }
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.validation.collect { validation ->
                    if (validation.email is RegisterValidation.Failed) {
                        withContext(Dispatchers.Main) {
                            binding.etEmail.apply {
                                requestFocus()
                                error = validation.email.message
                            }
                        }
                    }

                    if (validation.password is RegisterValidation.Failed) {
                        withContext(Dispatchers.Main) {
                            binding.etPassword.apply {
                                requestFocus()
                                error = validation.password.message
                            }
                        }
                    }
                }
            }
        }
    }

    private fun onLoginClick() {
        binding.tvLogin
            .setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }


    private fun getUser(): User? {
        val firstName = binding.etName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()

        if (firstName.isEmpty()) {
            binding.etName.apply {
                error = resources.getString(R.string.hello_blank_fragment)
                requestFocus()
            }
            return null
        }

        if (email.isEmpty()) {
            binding.etEmail.apply {
                error = resources.getString(R.string.hello_blank_fragment)
                //edit
                requestFocus()
            }
            return null
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.etEmail.apply {
                error = resources.getString(R.string.hello_blank_fragment)
                //edit
                requestFocus()
            }
            return null
        }


        return User(firstName, email)
    }

    private fun getPassword(): String?{
        val password = binding.etPassword.text.toString().trim()
        if (password.isEmpty()) {
            binding.etPassword.apply {
                error = resources.getString(R.string.hello_blank_fragment)
                // password edit later
                requestFocus()
            }
            return null
        }
        if (password.length < 6) {
            binding.etPassword.apply {
                error = resources.getString(R.string.hello_blank_fragment)
                //password edit later
                requestFocus()
            }
            return null
        }
        return password
    }
}