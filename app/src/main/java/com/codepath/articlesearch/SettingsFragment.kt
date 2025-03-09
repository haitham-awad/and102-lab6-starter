package com.codepath.articlesearch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        val changeColorButton: Button = view.findViewById(R.id.change_color_button)

        changeColorButton.setOnClickListener {
            (activity as? MainActivity)?.toggleBackgroundColor()
        }

        return view
    }
}
