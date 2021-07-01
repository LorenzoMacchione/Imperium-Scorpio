package com.example.imperium_scorpio.home

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.imperium_scorpio.R
import com.example.imperium_scorpio.database.CardDB
import com.example.imperium_scorpio.database.Cards

class Installer : Fragment(R.layout.installer) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val cardDAO= CardDB.getInstanceLoading(requireActivity().application).cardDAO()


       for (a in 0 until 100){
            getView()?.findViewById<TextView>(R.id.loading_per)?.text = (getView()?.findViewById<TextView>(
                R.id.loading_per
            )?.text.toString().toInt()+1).toString()
            //findViewById<ProgressBar>(R.id.progressBar).incrementProgressBy(1)
           val newCard= Cards(a, "Giuseppe$a", a, a, a, a,"Bella",
               a, a, a,a)
           cardDAO.insert(newCard)
       }

        view.findNavController().navigate(R.id.action_installer_to_menu)


    }

}