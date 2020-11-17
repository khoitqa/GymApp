package com.example.gymapp.View.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.gymapp.Constants.ConstantApp
import com.example.gymapp.Constants.ConstantApp.Companion.TAG
import com.example.gymapp.Constants.TextUtils
import com.example.gymapp.R
import com.example.gymapp.View.MainActivity
import com.google.firebase.auth.*
import kotlinx.android.synthetic.main.fragment_sign_up.*


class SignUpFragment : Fragment() {
    private var mAuth: FirebaseAuth? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        btn_login.setOnClickListener {
            val loginFragment: SignInFragment = SignInFragment()
            MainActivity.replaceFragment(requireActivity().supportFragmentManager, loginFragment)
        }

        btn_signup.setOnClickListener {
            val name: String = edt_name.text.toString()
            val email: String = edt_email.text.toString().trim()
            val password: String = edt_password.text.toString()
            if (checkInfoCreate(email, password, name)) createAccount(email, password, name)

        }

    }

    private fun checkInfoCreate(email: String, password: String, name: String): Boolean {
        if (TextUtils.emptyCheck(requireContext(), name, ConstantApp.NAME)
            && TextUtils.emptyCheck(requireContext(), email, ConstantApp.EMAIL)
            && TextUtils.emptyCheck(requireContext(), password, ConstantApp.PASSWORD)
        ) return true
        return false
    }

    fun createAccount(email: String, password: String, name: String) {
        mAuth?.createUserWithEmailAndPassword(email, password)
            ?.addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(ConstantApp.TAG, "createUserWithEmail:success")
                    val user = mAuth?.currentUser
                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(name)
                        .build()
                    user!!.updateProfile(profileUpdates)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Log.d(ConstantApp.TAG, "User profile updated.")
                                Log.d(ConstantApp.TAG, "createUserWithEmail:success")
                                val homeFragment = HomeFragment();
                                MainActivity.replaceFragment(
                                    requireActivity().supportFragmentManager,
                                    homeFragment
                                )
                            }
                        }
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(ConstantApp.TAG, "createUserWithEmail:failure", task.exception)
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
                        Log.e(TAG, e.message!!)
                    }
                }

            }

    }

}