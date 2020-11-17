package com.example.gymapp.View.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.gymapp.R
import com.example.gymapp.View.MainActivity


class GetStartFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_get_start, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btn: Button = view.findViewById(R.id.btn_get_start)
        btn.setOnClickListener {
            val startFragment: StartFragment = StartFragment()
            MainActivity.replaceFragment(requireActivity().supportFragmentManager, startFragment)
        }
    }


}