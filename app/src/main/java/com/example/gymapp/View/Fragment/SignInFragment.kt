package com.example.gymapp.View.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.gymapp.Constants.ConstantApp
import com.example.gymapp.Constants.TextUtils
import com.example.gymapp.R
import com.example.gymapp.View.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import kotlinx.android.synthetic.main.fragment_login.*


class SignInFragment : Fragment() {
    private var mAuth: FirebaseAuth? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        btn_create.setOnClickListener {
            val signUpFragment: SignUpFragment = SignUpFragment()
            MainActivity.replaceFragment(requireActivity().supportFragmentManager, signUpFragment)
        }

        btn_login.setOnClickListener {
            val email: String = edt_email.text.toString().trim()
            val password: String = edt_password.text.toString()
            if (checkInfoLogin(email, password)) login(email, password)
        }
    }

    private fun checkInfoLogin(email: String, password: String): Boolean {
        if (TextUtils.emptyCheck(requireContext(), email, ConstantApp.EMAIL)
            && TextUtils.emptyCheck(requireContext(), password, ConstantApp.PASSWORD)

        ) return true
        return false
    }

    private fun login(email: String, password: String) {
        mAuth?.signInWithEmailAndPassword(email, password)
            ?.addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(ConstantApp.TAG, "signInWithEmail:success " + email)
                    val homeFragment = HomeFragment();
                    MainActivity.replaceFragment(
                        requireActivity().supportFragmentManager,
                        homeFragment
                    )
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(ConstantApp.TAG, "signInWithEmail:failure", task.exception)
                    try {
                        throw task.exception!!
                    } catch (e: FirebaseAuthWeakPasswordException) {
                        TextUtils.showToast(
                            requireContext(),
                            task.exception?.localizedMessage.toString()
                        )
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        TextUtils.showToast(
                            requireContext(),
                            task.exception?.localizedMessage.toString()
                        )
                    } catch (e: FirebaseAuthUserCollisionException) {
                        TextUtils.showToast(
                            requireContext(),
                            task.exception?.localizedMessage.toString()
                        )
                    } catch (e: Exception) {
                        Log.e(ConstantApp.TAG, e.message!!)
                    }
                }

            }
    }

}