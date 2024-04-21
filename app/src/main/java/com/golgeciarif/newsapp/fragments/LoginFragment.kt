package com.golgeciarif.newsapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.golgeciarif.newsapp.R
import com.golgeciarif.newsapp.activities.MainActivity
import com.golgeciarif.newsapp.databinding.FragmentLoginBinding
import com.golgeciarif.newsapp.resource.Resource
import com.golgeciarif.newsapp.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    private val viewModel by viewModels<LoginViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        onLoginClick()
       // observerLogin()
     //  observerLoginError()


        lifecycleScope.launchWhenStarted {
            viewModel.login.collect {
                when (it) {
                    is Resource.Loading -> {
                   //     binding.btnLoginButton.startAnimation()
                    }
                    is Resource.Success -> {
                        binding.btnLoginButton.clearAnimation()
                        Intent(requireActivity(), MainActivity::class.java).also { intent ->
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                        }
                    }
                    is Resource.Error -> {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                        binding.btnLoginButton.clearAnimation()
                    }
                    else -> Unit

                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.resetPassword.collect{
                when (it) {
                    is Resource.Loading -> {
                    }
                    is Resource.Success -> {
                        Snackbar.make(requireView(),"Reset link was sent to your email", Snackbar.LENGTH_LONG).show()
                    }
                    is Resource.Error -> {
                        Snackbar.make(requireView(),"Error: ${it.message}", Snackbar.LENGTH_LONG).show()
                    }
                    else -> Unit

                }
            }
        }

    }




    private fun onLoginClick() {
        binding.btnLoginButton.setOnClickListener {
            //btnLogin.spinningBarColor = resources.getColor(R.color.white)
            //btnLogin.spinningBarWidth = resources.getDimension(R.dimen._3sdp)

            val email = getEmail()?.trim()
            val password = getPassword()
            email?.let {
                password?.let {
                   // btnLogin.startAnimation()
                    viewModel.login(email, password)
                }
            }
        }
    }

    private fun getPassword(): String? {
        val password = binding.etPassword.text.toString()

        if (password.isEmpty()) {
            binding.etPassword.apply {
         //edit
                error = resources.getString(R.string.hello_blank_fragment)
                requestFocus()
            }
            return null
        }

        if (password.length < 6) {
            binding.etPassword.apply {
                error = resources.getString(R.string.hello_blank_fragment)
                requestFocus()
                //edit

            }
            return null
        }
        return password
    }

    private fun getEmail(): String? {
        val email = binding.etEmail.text.toString().trim()

        if (email.isEmpty()) {
            binding.etEmail.apply {
                error = resources.getString(R.string.hello_blank_fragment)
                requestFocus()
            }
            return null
        }


        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.etEmail.apply {
                error = resources.getString(R.string.hello_blank_fragment)
                requestFocus()
            }
            return null
        }


        return email

    }
}