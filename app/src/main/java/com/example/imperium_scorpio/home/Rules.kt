/**
 * Classe che gestisce le pagine delle regole
 */

package com.example.imperium_scorpio.home

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.findNavController
import com.example.imperium_scorpio.R
import kotlin.properties.Delegates


class Rules : Fragment(R.layout.fragment_rules) {

    private var page: Int by Delegates.observable(1){ property, oldValue, newValue ->

        if (newValue==1){
            requireView().findViewById<ImageButton>(R.id.previus_rule_page).visibility = View.INVISIBLE
        }else{
            requireView().findViewById<ImageButton>(R.id.previus_rule_page).visibility = View.VISIBLE
        }

        when (newValue){
            1 -> requireView().findViewById<FragmentContainerView>(R.id.rules_page).findNavController().navigate(R.id.action_rulesP2_to_rules_p1)
            2-> if (newValue>oldValue) requireView().findViewById<FragmentContainerView>(R.id.rules_page).findNavController().navigate(R.id.action_rules_p1_to_rulesP2)
                else requireView().findViewById<FragmentContainerView>(R.id.rules_page).findNavController().navigate(R.id.action_rulesP3_to_rulesP2)
            3-> if (newValue>oldValue) requireView().findViewById<FragmentContainerView>(R.id.rules_page).findNavController().navigate(R.id.action_rulesP2_to_rulesP3)
                //else requireView().findViewById<FragmentContainerView>(R.id.rules_page).findNavController().navigate(R.id.action_rulesP3_to_rulesP2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireView().findViewById<ImageButton>(R.id.previus_rule_page).visibility = View.INVISIBLE

        requireView().findViewById<ImageView>(R.id.rule_to_home).setOnClickListener {
            view.findNavController().navigate(R.id.action_rules_to_menu)
        }

        requireView().findViewById<ImageButton>(R.id.next_rule_page).setOnClickListener {
            page+=1
        }

        requireView().findViewById<ImageButton>(R.id.previus_rule_page).setOnClickListener {
            page-=1
        }
    }


}