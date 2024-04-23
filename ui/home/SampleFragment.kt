package com.example.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class SampleFragment : Fragment() {

    companion object {
        private const val ARG_TEXT = "text"


        // Method to create new instances of SampleFragment
        fun newInstance(text: String): SampleFragment {
            val fragment = SampleFragment()
            val args = Bundle()
            args.putString(ARG_TEXT, text)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(android.R.layout.simple_list_item_1, container, false)
        val textView = view.findViewById<TextView>(android.R.id.text1)
        // Set the text from the arguments
        textView.text = arguments?.getString(ARG_TEXT)
        return view
    }
}
