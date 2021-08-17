package com.example.imperium_scorpio.match
import android.content.ClipData
import android.content.ClipDescription
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.example.imperium_scorpio.Lock
import com.example.imperium_scorpio.R
import com.example.imperium_scorpio.database.CardDAO
import com.example.imperium_scorpio.database.CardDB
import com.example.imperium_scorpio.databinding.ActivityMatchBinding
import com.example.imperium_scorpio.postal.Ermes


class MatchActivity : AppCompatActivity() {

    lateinit var ermes: Ermes

    var activeCard = -1
    val mvm: MatchViewModel by viewModels()
    lateinit var cardDAO: CardDAO

    lateinit var deck: Deck

    val ringsY = mutableListOf<Int>()
    val ringsG = mutableListOf<Int>()
    val ringsR = mutableListOf<Int>()

    val replyIntent = Intent()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)

        mvm.setContext(this)

        cardDAO = CardDB.getInstanceLoading(application).cardDAO()

        val deck_number = (1..2).random()
        if(deck_number==1){
            deck = Deck("0101020203030404050506060707080809090a0a", cardDAO)
        }
        else{
            deck = Deck("0b0b0c0c0d0d0e0e0f0f10101111121213131414", cardDAO)
        }

        ermes= Ermes(cardDAO)
        ermes.setGame(intent.getStringExtra("player")!!
            ,intent.getStringExtra("enemy")!!,
            mvm)

        ringsG.add(R.id.rg1)
        ringsG.add(R.id.rg2)
        ringsG.add(R.id.rg3)
        ringsG.add(R.id.rg4)
        ringsG.add(R.id.rg5)
        ringsG.add(R.id.rg6)
        ringsG.add(R.id.rg7)
        ringsG.add(R.id.rg8)
        ringsG.add(R.id.rg9)


        ringsY.add(R.id.possibility1Y)
        ringsY.add(R.id.possibility2Y)
        ringsY.add(R.id.possibility3Y)
        ringsY.add(R.id.possibility4Y)
        ringsY.add(R.id.possibility5Y)
        ringsY.add(R.id.possibility6Y)
        ringsY.add(R.id.possibility7Y)
        ringsY.add(R.id.possibility8Y)
        ringsY.add(R.id.possibility9Y)


        ringsR.add(R.id.possibility1R)
        ringsR.add(R.id.possibility2R)
        ringsR.add(R.id.possibility3R)
        ringsR.add(R.id.possibility4R)
        ringsR.add(R.id.possibility5R)
        ringsR.add(R.id.possibility6R)
        ringsR.add(R.id.possibility7R)
        ringsR.add(R.id.possibility8R)
        ringsR.add(R.id.possibility9R)


        //INZIO BIDING
        val binding: ActivityMatchBinding = DataBindingUtil
            .setContentView(this, R.layout.activity_match)

        binding.hc1 = mvm.hand.HC1_model
        binding.hc2 = mvm.hand.HC2_model
        binding.hc3 = mvm.hand.HC3_model
        binding.hc4 = mvm.hand.HC4_model
        binding.hc5 = mvm.hand.HC5_model

        binding.enemy = mvm.enemy


        binding.pRes1 = mvm.pRes[0]
        binding.pRes2 = mvm.pRes[1]
        binding.pRes3 = mvm.pRes[2]
        binding.pRes4 = mvm.pRes[3]

        binding.explorer = mvm.explorer

        binding.p1 = mvm.planets[0]
        binding.p2 = mvm.planets[1]
        binding.p3 = mvm.planets[2]
        binding.p4 = mvm.planets[3]
        binding.p5 = mvm.planets[4]
        binding.p6 = mvm.planets[5]
        binding.p7 = mvm.planets[6]
        binding.p8 = mvm.planets[7]
        binding.p9 = mvm.planets[8]

        binding.lifecycleOwner = this

        //FINE BIDING




        setHand()
        setPlanets()
        setRings()
        setExplorer()
        setButton()

        findViewById<ImageView>(R.id.match_background).setOnClickListener{
            offAllRings()
            offAllResButton()
        }

        mvm.hand.addCard(deck.draw())
        mvm.hand.addCard(deck.draw())
        mvm.hand.addCard(deck.draw())

        mvm.enemy.draw()
        mvm.enemy.draw()
        mvm.enemy.draw()

        findViewById<TextView>(R.id.win).setOnClickListener{
            replyIntent.putExtra("result","win")
            setResult(RESULT_OK,intent)
            finish()
        }

        findViewById<TextView>(R.id.next).setOnClickListener{
            replyIntent.putExtra("result","win")
            setResult(RESULT_OK,intent)
            finish()
        }

        findViewById<TextView>(R.id.lose).setOnClickListener{
            replyIntent.putExtra("result","lose")
            setResult(RESULT_OK,intent)
            finish()
        }

        findViewById<TextView>(R.id.nextlose).setOnClickListener{
            replyIntent.putExtra("result","lose")
            setResult(RESULT_OK,intent)
            finish()
        }

        findViewById<ImageView>(R.id.planet1).setOnClickListener {
            for (r in mvm.pRes){
                r.minRes(10)
            }
        }
    }

    //Funzione per impostare l'explorer
    private fun setExplorer(){
        findViewById<ConstraintLayout>(R.id.CardExplorer).setOnClickListener {
            findViewById<ConstraintLayout>(R.id.CardExplorer).visibility = View.INVISIBLE
        }

        findViewById<ConstraintLayout>(R.id.CardExplorer).setOnDragListener{v,event->
            when (event.action){
                DragEvent.ACTION_DRAG_ENTERED->{findViewById<ConstraintLayout>(R.id.CardExplorer).visibility = View.INVISIBLE
                    return@setOnDragListener false}
            }
            true
        }
    }

    //Funzione per impostare la mano del giocatore
    private fun setHand() {

        //PRIMA CARTA
        findViewById<ConstraintLayout>(R.id.HandCard1).setOnClickListener {
            mvm.explorer.showCard(mvm.hand.read(0))
            findViewById<ConstraintLayout>(R.id.CardExplorer).visibility = View.VISIBLE
        }

        findViewById<ConstraintLayout>(R.id.HandCard1).setOnLongClickListener {
            val item = ClipData.Item("0")
            val mime = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData("0", mime, item)

            val dragShadow = View.DragShadowBuilder(it)
            if (mvm.lock.read())it.startDragAndDrop(data, dragShadow, it, 0)

            true
        }

        //SECONDA CARTA
        findViewById<ConstraintLayout>(R.id.HandCard2).setOnClickListener {
            mvm.explorer.showCard(mvm.hand.read(1))
            findViewById<ConstraintLayout>(R.id.CardExplorer).visibility = View.VISIBLE
        }

        findViewById<ConstraintLayout>(R.id.HandCard2).setOnLongClickListener {
            val item = ClipData.Item("1")
            val mime = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData("1", mime, item)

            val dragShadow = View.DragShadowBuilder(it)
            if (mvm.lock.read())it.startDragAndDrop(data, dragShadow, it, 0)

            true
        }

        //TERZA CARTA
        findViewById<ConstraintLayout>(R.id.HandCard3).setOnClickListener {
            mvm.explorer.showCard(mvm.hand.read(2))
            findViewById<ConstraintLayout>(R.id.CardExplorer).visibility = View.VISIBLE
        }

        findViewById<ConstraintLayout>(R.id.HandCard3).setOnLongClickListener {
            val item = ClipData.Item("2")
            val mime = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData("2", mime, item)

            val dragShadow = View.DragShadowBuilder(it)
            if (mvm.lock.read())it.startDragAndDrop(data, dragShadow, it, 0)

            true
        }

        //QUARTA CARTA
        findViewById<ConstraintLayout>(R.id.HandCard4).setOnClickListener {
            mvm.explorer.showCard(mvm.hand.read(3))
            findViewById<ConstraintLayout>(R.id.CardExplorer).visibility = View.VISIBLE
        }

        findViewById<ConstraintLayout>(R.id.HandCard4).setOnLongClickListener {
            val item = ClipData.Item("3")
            val mime = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData("3", mime, item)

            val dragShadow = View.DragShadowBuilder(it)
            if (mvm.lock.read())it.startDragAndDrop(data, dragShadow, it, 0)

            true
        }

        //QUINTA CARTA
        findViewById<ConstraintLayout>(R.id.HandCard5).setOnClickListener {
            mvm.explorer.showCard(mvm.hand.read(4))
            findViewById<ConstraintLayout>(R.id.CardExplorer).visibility = View.VISIBLE
        }

        findViewById<ConstraintLayout>(R.id.HandCard5).setOnLongClickListener {
            val item = ClipData.Item("4")
            val mime = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData("4", mime, item)

            val dragShadow = View.DragShadowBuilder(it)
            if (mvm.lock.read())it.startDragAndDrop(data, dragShadow, it, 0)

            true
        }
    }
        //Funzione per impostare i bottoni drawRes
        private fun setButton() {

            val buttons = mutableListOf<Int>()
            buttons.add(R.id.drawRes1)
            buttons.add(R.id.drawRes2)
            buttons.add(R.id.drawRes3)
            buttons.add(R.id.drawRes4)

            findViewById<TextView>(R.id.R1).setOnClickListener {
                offAllResButton()
                if (mvm.lock.read()){
                    findViewById<Button>(R.id.drawRes1).visibility = View.VISIBLE
                    mvm.lock.lock()
                }
            }

            findViewById<TextView>(R.id.R2).setOnClickListener {
                offAllResButton()
                if (mvm.lock.read()){
                    findViewById<Button>(R.id.drawRes2).visibility = View.VISIBLE
                    mvm.lock.lock()
                }
            }

            findViewById<TextView>(R.id.R3).setOnClickListener {
                offAllResButton()
                if (mvm.lock.read()){
                    findViewById<Button>(R.id.drawRes3).visibility = View.VISIBLE
                    mvm.lock.lock()
                }
            }

            findViewById<TextView>(R.id.R4).setOnClickListener {
                offAllResButton()
                if (mvm.lock.read()){
                    findViewById<Button>(R.id.drawRes4).visibility = View.VISIBLE
                    mvm.lock.lock()
                }
            }


            for (i in buttons) {


                findViewById<Button>(i).setOnClickListener {
                    if (mvm.hand.size() != 5) {
                        mvm.pRes[buttons.indexOf(i)].useRes(1)
                        mvm.hand.addCard(deck.draw())
                        ermes.drawMsg(buttons.indexOf(i))
                    } else {
                        Toast.makeText(this, "La mano è piena", Toast.LENGTH_LONG).show()
                    }
                    findViewById<Button>(i).visibility = View.INVISIBLE
                }
                findViewById<Button>(i).setOnDragListener { v, event ->
                    when (event.action) {

                        DragEvent.ACTION_DRAG_STARTED -> {
                            findViewById<Button>(i).visibility = View.INVISIBLE
                            return@setOnDragListener false
                        }
                    }
                    true
                }
            }
        }



    private fun offAllResButton() {
        findViewById<Button>(R.id.drawRes1).visibility = View.INVISIBLE
        findViewById<Button>(R.id.drawRes2).visibility = View.INVISIBLE
        findViewById<Button>(R.id.drawRes3).visibility = View.INVISIBLE
        findViewById<Button>(R.id.drawRes4).visibility = View.INVISIBLE
    }

    //Funzione per impostare i pianeti
    private fun setPlanets() {

        val planet = mutableListOf<ImageView>()

        planet.add(findViewById(R.id.planet1))
        planet.add(findViewById(R.id.planet2))
        planet.add(findViewById(R.id.planet3))
        planet.add(findViewById(R.id.planet4))
        planet.add(findViewById(R.id.planet5))
        planet.add(findViewById(R.id.planet6))
        planet.add(findViewById(R.id.planet7))
        planet.add(findViewById(R.id.planet8))
        planet.add(findViewById(R.id.planet9))


        val cOnPlanet = mutableListOf<ConstraintLayout>()
        cOnPlanet.add(findViewById(R.id.ConP1))
        cOnPlanet.add(findViewById(R.id.ConP2))
        cOnPlanet.add(findViewById(R.id.ConP3))
        cOnPlanet.add(findViewById(R.id.ConP4))
        cOnPlanet.add(findViewById(R.id.ConP5))
        cOnPlanet.add(findViewById(R.id.ConP6))
        cOnPlanet.add(findViewById(R.id.ConP7))
        cOnPlanet.add(findViewById(R.id.ConP8))
        cOnPlanet.add(findViewById(R.id.ConP9))

        for (i in 0..8) {

            if (i<3) {
                planet[i].setOnDragListener { v, event ->
                    when (event.action) {

                        DragEvent.ACTION_DRAG_STARTED -> {
                            if (!mvm.planets[i].controlled) {
                                findViewById<ImageView>(ringsG[i]).visibility = View.VISIBLE
                                return@setOnDragListener true
                            }
                            return@setOnDragListener !mvm.planets[i].controlled
                        }

                        DragEvent.ACTION_DRAG_ENDED -> {
                            findViewById<ImageView>(ringsG[i]).visibility = View.INVISIBLE
                        }

                        DragEvent.ACTION_DROP -> {

                            val item = event.clipData.getItemAt(0)
                            val card = item.text
                            val c = mvm.hand.read(card.toString().toInt())

                            if (mvm.pRes[0].read() >= c.res1 && mvm.pRes[1].read() >= c.res2 &&
                                mvm.pRes[2].read() >= c.res3 && mvm.pRes[3].read() >= c.res4
                            ) {
                                mvm.pRes[0].useRes(c.res1)
                                mvm.pRes[1].useRes(c.res2)
                                mvm.pRes[2].useRes(c.res3)
                                mvm.pRes[3].useRes(c.res4)

                                mvm.planets[i].moveTo(mvm.hand.takeCard(card.toString().toInt()))
                                ermes.playCard(c.id,i)
                                mvm.lock.lock()
                            }else{
                                Toast.makeText(this,"Non hai abbastanza risorse",Toast.LENGTH_LONG).show()
                            }
                            return@setOnDragListener true
                        }
                    }
                    true
                }
            }

            cOnPlanet[i].setOnLongClickListener {
                mvm.explorer.showCard(mvm.planets[i].read())
                true
            }

            cOnPlanet[i].setOnClickListener {
                if (mvm.planets[i].card.player != 1 && mvm.lock.read()) {
                    activeCard = i
                    offAllResButton()
                    findViewById<ImageView>(ringsY[i]).visibility = View.VISIBLE
                    val range1 = mvm.planets[i].getRange(1)
                    for (c in range1) {
                        if (mvm.planets[c].controlled) {
                            if (mvm.planets[c].read().player == 1) findViewById<ImageView>(ringsR[c]).visibility =
                                View.VISIBLE
                        } else {
                            findViewById<ImageView>(ringsG[c]).visibility = View.VISIBLE
                        }
                    }
                }
            }

        }
    }

    fun setRings(){
        for (c in 0..8){
            findViewById<ImageView>(ringsY[c]).setOnClickListener {
                val m = mvm.planets[c].takeRes()
                for (i in 0..3){
                    mvm.pRes[i].minRes(m[i])
                }
                ermes.mining(c)
                offAllRings()
                mvm.lock.lock()
            }

            findViewById<ImageView>(ringsG[c]).setOnClickListener {
                mvm.planets[c].moveTo(mvm.planets[activeCard].moveFrom())
                ermes.move(activeCard, c)
                mvm.lock.lock()

                offAllRings()

                if (c==8) {
                    if (mvm.planets[c].card.player==0) {
                        mvm.enemy.win=1

                        ermes.clearGame()
                    }
                }
            }

            findViewById<ImageView>(ringsR[c]).setOnClickListener {
                mvm.planets[c].takeDamage(mvm.planets[activeCard].attack.value!!)
                mvm.planets[activeCard].takeDamage(mvm.planets[c].attack.value!!)
                ermes.attackMsg(activeCard, c)
                mvm.lock.lock()

                offAllRings()
            }
        }

    }



    fun offAllRings(){
        activeCard = -1
        for (i in 0..8){
            findViewById<ImageView>(ringsR[i]).visibility = View.INVISIBLE
            findViewById<ImageView>(ringsG[i]).visibility = View.INVISIBLE
            findViewById<ImageView>(ringsY[i]).visibility = View.INVISIBLE
        }
    }


    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }

    private fun hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }
}

