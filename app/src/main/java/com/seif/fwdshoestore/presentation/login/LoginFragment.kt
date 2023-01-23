package com.seif.fwdshoestore.presentation.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.seif.fwdshoestore.R
import com.seif.fwdshoestore.databinding.FragmentLoginBinding
import com.seif.fwdshoestore.utils.Constants.Companion.FIRST_TIME_KEY
import com.seif.fwdshoestore.utils.SharedPrefs
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    private lateinit var email: String
    private lateinit var password: String

    @Inject
    lateinit var sharedPrefs: SharedPrefs
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
            navigate()
            Timber.d("onViewCreated: $email - $password")
        }
        binding.btnRegister.setOnClickListener {
            getUserInput()
            navigate()
            Timber.d("onViewCreated: $email - $password")
        }
    }

    private fun getUserInput() {
        email = binding.etEmail.text.toString()
        password = binding.etPassword.text.toString()
    }

    private fun navigate() {
        if (sharedPrefs.get(FIRST_TIME_KEY, Boolean::class.java)) {
            findNavController().navigate(R.id.action_loginFragment_to_viewPagerFragment)
            sharedPrefs.put(FIRST_TIME_KEY, false)
        } else {
            findNavController().navigate(R.id.action_loginFragment_to_shoeListFragment)
        }
    }
}