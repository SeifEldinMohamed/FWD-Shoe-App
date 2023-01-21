package com.seif.fwdshoestore.presentation.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.seif.fwdshoestore.R
import com.seif.fwdshoestore.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    private lateinit var email: String
    private lateinit var password: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            getUserInput()
            findNavController().navigate(R.id.action_loginFragment_to_viewPagerFragment)
            Timber.d("onViewCreated: $email - $password")
        }
        binding.btnRegister.setOnClickListener {
            getUserInput()
            findNavController().navigate(R.id.action_loginFragment_to_viewPagerFragment)
            Timber.d("onViewCreated: $email - $password")
        }
    }

    private fun getUserInput() {
        email = binding.etEmail.text.toString()
        password = binding.etPassword.text.toString()
    }
}