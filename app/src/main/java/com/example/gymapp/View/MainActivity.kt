package com.example.gymapp.View

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.gymapp.Constants.ConstantApp
import com.example.gymapp.R
import com.example.gymapp.View.Fragment.GetStartFragment
import com.example.gymapp.View.Fragment.HomeFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class MainActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private val manager = getSupportFragmentManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance()
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth?.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            val homeFragment = HomeFragment();
            addFragmentToActivity(manager, homeFragment)
        } else {
            val getStartFragment = GetStartFragment();
            addFragmentToActivity(manager, getStartFragment)
        }
    }

    private fun addFragmentToActivity(manager: FragmentManager, fragment: Fragment) {
        val transaction: FragmentTransaction = manager.beginTransaction()
        transaction.add(R.id.main_fragment, fragment).commit()
    }

    companion object {
        fun replaceFragment(manager: FragmentManager, fragment: Fragment) {
            val transaction: FragmentTransaction = manager.beginTransaction()
            transaction.replace(R.id.main_fragment, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }

    }


}