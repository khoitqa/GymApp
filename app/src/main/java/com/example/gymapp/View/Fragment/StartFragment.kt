package com.example.gymapp.View.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.gymapp.R
import com.example.gymapp.View.MainActivity
import com.google.firebase.auth.FirebaseAuth


class StartFragment : Fragment() {
    private var mAuth: FirebaseAuth? = null
    private val TAG = "FirebaseTag"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnLogin: Button = view.findViewById(R.id.btn_login)
        val btnSignup: Button = view.findViewById(R.id.btn_sign_up)

        btnLogin.setOnClickListener {
            val loginFragment: SignInFragment = SignInFragment()
            MainActivity.replaceFragment(requireActivity().supportFragmentManager, loginFragment)
        }

        btnSignup.setOnClickListener {
            val signUpFragment: SignUpFragment = SignUpFragment()
            MainActivity.replaceFragment(requireActivity().supportFragmentManager, signUpFragment)
        }

    }
}