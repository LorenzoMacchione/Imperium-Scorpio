package com.example.imperium_scorpio

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

class installer : Fragment(R.layout.installer) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




       for (a in 0 until 100){
            getView()?.findViewById<TextView>(R.id.loading_per)?.text = (getView()?.findViewById<TextView>(R.id.loading_per)?.text.toString().toInt()+1).toString()
            //findViewById<ProgressBar>(R.id.progressBar).incrementProgressBy(1)

       }



    }


}