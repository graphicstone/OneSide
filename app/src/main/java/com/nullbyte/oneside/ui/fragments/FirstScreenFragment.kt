package com.nullbyte.oneside.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nullbyte.oneside.R


class FirstScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        val icon = BitmapFactory.decodeResource(resources, R.drawable.screen_1);
        return inflater.inflate(R.layout.fragment_first_screen, container, false)
    }
}
