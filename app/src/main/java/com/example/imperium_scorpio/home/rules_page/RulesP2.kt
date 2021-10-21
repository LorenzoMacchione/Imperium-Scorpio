package com.example.imperium_scorpio.home.rules_page

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.imperium_scorpio.R


class RulesP2 : Fragment(R.layout.fragment_rules_p2) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireView().findViewById<Button>(R.id.rules_p2_b1).setOnClickListener{
            requireView().findViewById<TextView>(R.id.rules_p2_descr).text=getString(R.string.RulesP2_1)
        }

        requireView().findViewById<Button>(R.id.rules_p2_b2).setOnClickListener{
            requireView().findViewById<TextView>(R.id.rules_p2_descr).text=getString(R.string.RulesP2_2)
        }

        requireView().findViewById<Button>(R.id.rules_p2_b3).setOnClickListener{
            requireView().findViewById<TextView>(R.id.rules_p2_descr).text=getString(R.string.RulesP2_3)
        }

        requireView().findViewById<Button>(R.id.rules_p2_b4).setOnClickListener{
            requireView().findViewById<TextView>(R.id.rules_p2_descr).text=getString(R.string.RulesP2_4)
        }

        requireView().findViewById<Button>(R.id.rules_p2_b5).setOnClickListener{
            requireView().findViewById<TextView>(R.id.rules_p2_descr).text=getString(R.string.RulesP2_5)
        }

        requireView().findViewById<Button>(R.id.rules_p2_b6).setOnClickListener{
            requireView().findViewById<TextView>(R.id.rules_p2_descr).text=getString(R.string.RulesP2_6)
        }
    }

}