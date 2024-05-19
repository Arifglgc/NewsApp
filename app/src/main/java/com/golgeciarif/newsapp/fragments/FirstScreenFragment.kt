package com.golgeciarif.newsapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.golgeciarif.newsapp.R
import com.golgeciarif.newsapp.activities.MainActivity
import com.golgeciarif.newsapp.databinding.FragmentFirstScreenBinding


class FirstScreenFragment : Fragment() {
    private lateinit var binding: FragmentFirstScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentFirstScreenBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLoginButton.setOnClickListener {
            findNavController().navigate(R.id.action_firstScreenFragment_to_loginFragment)
        }

        binding.btnRegisterButton.setOnClickListener {
            findNavController().navigate(R.id.action_firstScreenFragment_to_registerFragment)
        }

        binding.tvGuestLogin.setOnClickListener {

            Intent(requireActivity(), MainActivity::class.java).also { intent ->
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        }

    }


}