package com.example.gymapp.View.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gymapp.Adaper.PlanAdapter
import com.example.gymapp.Constants.ConstantApp
import com.example.gymapp.Model.Plan
import com.example.gymapp.Model.PlanResponse
import com.example.gymapp.R
import com.example.gymapp.View.MainActivity
import com.example.gymapp.ViewModel.MainViewModel
import com.example.gymapp.ViewModel.MainViewModelFactory
import com.example.gymapp.ViewModel.UIEventManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(), UIEventManager {
    private lateinit var viewModel: MainViewModel
    private var mAuth: FirebaseAuth? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModelFactory = MainViewModelFactory(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        mAuth = FirebaseAuth.getInstance()

        init()

        ly_home.setOnClickListener {
            mAuth?.signOut()
            val getStartFragment: GetStartFragment = GetStartFragment()
            MainActivity.replaceFragment(requireActivity().supportFragmentManager, getStartFragment)
        }
    }

    private fun init() {
        loadData()
    }

    private fun loadData() {
        viewModel.getPlan().observe(requireActivity(), Observer {

            setDataToRecyclerView(it)
        })
    }

    fun printAll(listPlan: List<Plan>?) {
        if (listPlan != null) {
            for (plan in listPlan) Log.d(ConstantApp.TAG, "Name " + plan.id + " Time " + plan.name)
        }
    }

    private fun setDataToRecyclerView(it: List<Plan>?) {
        rvPlan.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = PlanAdapter(it as ArrayList<Plan>)
        }

    }

    override fun showToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
    }

    override fun showProgressBar() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progress_bar.visibility = View.GONE
    }


}