package com.example.imperium_scorpio.home

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.imperium_scorpio.R
import com.example.imperium_scorpio.database.CardDB
import com.example.imperium_scorpio.database.Cards

class   Installer : Fragment(R.layout.installer) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val cardDAO= CardDB.getInstanceLoading(requireActivity().application).cardDAO()


       /*for (a in 0 until 100){
            getView()?.findViewById<TextView>(R.id.loading_per)?.text = (getView()?.findViewById<TextView>(
                R.id.loading_per
            )?.text.toString().toInt()+1).toString()
            //findViewById<ProgressBar>(R.id.progressBar).incrementProgressBy(1)
           val newCard= Cards(a, "Giuseppe$a", a, a, a, a,"Bella",
               a, a, a,a)
           cardDAO.insert(newCard)
       }*/

        cardDAO.insert( Cards(1,"Valden great pilot",0,2,0,3,"He is one of the ace Pilot of the Santur Imperium",8,2,5,175))
        cardDAO.insert( Cards(2,"Varga, the master",3,3,0,1,getString(R.string.card_2_descr),5,8,8,155))
        cardDAO.insert( Cards(3,"",0,0,0,1,"",0,3,1,155))
        cardDAO.insert( Cards(4,"",1,0,0,0,"",3,0,1,155))
        cardDAO.insert( Cards(5,"",3,0,1,0,"",9,0,3,155))
        cardDAO.insert( Cards(6,"",0,3,1,0,"",0,9,3,155))
        cardDAO.insert( Cards(7,"",0,0,1,2,"",2,2,5,155))
        cardDAO.insert( Cards(8,"",2,5,3,0,"",6,15,9,155))
        cardDAO.insert( Cards(9,"",0,1,0,1,"",1,4,2,155))
        cardDAO.insert( Cards(10,"",4,0,8,0,"",12,0,24,155))
        cardDAO.insert( Cards(11,"",1,1,0,0,"",3,3,1,155))
        cardDAO.insert( Cards(12,"",1,0,0,0,"",3,0,1,155))
        cardDAO.insert( Cards(13,"",0,0,0,0,"",0,0,1,155))
        cardDAO.insert( Cards(14,"",0,1,0,0,"",0,4,1,155))
        cardDAO.insert( Cards(15,"",0,2,0,0,"",0,7,1,155))
        cardDAO.insert( Cards(16,"",1,2,0,0,"",3,6,1,155))
        cardDAO.insert( Cards(17,"",3,0,0,0,"",10,0,1,155))
        cardDAO.insert( Cards(18,"",1,1,0,3,"",6,6,6,155))
        cardDAO.insert( Cards(19,"",1,2,1,0,"",3,6,4,155))
        cardDAO.insert( Cards(20,"Golden Smile",2,0,2,0,"",6,0,6,155))

        view.findNavController().navigate(R.id.action_installer_to_menu)


    }

}