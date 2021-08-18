package com.example.golftracker

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Tracker : AppCompatActivity() {
    var holesList: MutableList<Hole> = ArrayList()
    var listIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tracker)
        supportActionBar?.hide()



        val intent: Intent = intent
        val intentExtraData: Bundle = intent.extras
        if (intentExtraData != null) {
            if (intentExtraData.containsKey("hole")) {
                val intentData = intentExtraData.getString("hole")
                val trackerTitle: TextView = findViewById(R.id.hole_number)
                trackerTitle.text = intentData
            }

            if (intentExtraData.containsKey("numberOfShots")) {
                val intentData = intentExtraData.getString("numberOfShots")
                val numberOfShots: TextView = findViewById(R.id.number_of_shots)
                numberOfShots.text = intentData
            }



            if (intentExtraData.containsKey("holesList")) {

                if (intentExtraData.containsKey("index")) {
                    listIndex =  intentExtraData.getInt("index")
                } else listIndex = 0

                holesList = intent.getParcelableArrayListExtra<Hole>("holesList")
                val trackerTitle: TextView = findViewById(R.id.hole_number)
                val numberOfShots: TextView = findViewById(R.id.number_of_shots)
                trackerTitle.text = holesList[listIndex].hole
                numberOfShots.text = holesList[listIndex].numberOfShots.toString()
            }
        }

        val addButton: Button = findViewById(R.id.add_button)
        addButton.setOnClickListener {
            var numberTextView = findViewById<TextView>(R.id.number_of_shots)
            var shots = Integer.parseInt(numberTextView.text.toString())
            shots += 1
            numberTextView.text = shots.toString()
            holesList[listIndex].numberOfShots = shots
        }

        val subtractButton: Button = findViewById(R.id.subtract_button)
        subtractButton.setOnClickListener {
            var numberTextView = findViewById<TextView>(R.id.number_of_shots)
            var shots = Integer.parseInt(numberTextView.text.toString())
            shots -= 1
            if (shots < 0) shots = 0
            numberTextView.text = shots.toString()
            holesList[listIndex].numberOfShots = shots
        }

        val nextButton: Button = findViewById(R.id.next_button)
        nextButton.setOnClickListener {
            listIndex += 1
            if (listIndex == holesList.size) {
                val intent = Intent(this, QuantitySelection::class.java)
                intent.putExtra("holesList", ArrayList(holesList))
                setResult(Activity.RESULT_OK, intent)
                finish()
            } else {
                val trackerTitle: TextView = findViewById(R.id.hole_number)
                val numberOfShots: TextView = findViewById(R.id.number_of_shots)
                trackerTitle.text = holesList[listIndex].hole
                numberOfShots.text = holesList[listIndex].numberOfShots.toString()
            }
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this, QuantitySelection::class.java)
        intent.putExtra("holesList", ArrayList(holesList))
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}