package com.example.golftracker

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.wear.widget.WearableLinearLayoutManager
import androidx.wear.widget.WearableRecyclerView
import kotlinx.android.synthetic.main.activity_list.view.*
import kotlinx.android.synthetic.main.list_item.view.*
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList
import android.widget.Toast as Toast1

class QuantitySelection : AppCompatActivity(), OnCLickInterface {
    private var holesList = mutableListOf<Hole>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hole_quantity_selection)
        supportActionBar?.hide()

        val nineHoles: Button = findViewById(R.id.nine_holes)
        nineHoles.setOnClickListener {
            createList(9)
            val intent = Intent(this, Tracker::class.java)
            intent.putParcelableArrayListExtra("holesList", ArrayList(holesList))
            startActivityForResult(intent, 666)
        }

        val eighteenHoles: Button = findViewById(R.id.eighteen_holes)
        eighteenHoles.setOnClickListener {
            createList(18)
            val intent = Intent(this, Tracker::class.java)
            intent.putParcelableArrayListExtra("holesList", ArrayList(holesList))
            startActivityForResult(intent, 666)
        }
    }

    private fun createList(numberOfHoles: Int) {


        for (i in 1 until (numberOfHoles + 1)) {
            holesList.add(Hole("Hole #$i", 0))
        }

        setContentView(R.layout.activity_list)
        val wearableRecyclerView: WearableRecyclerView = findViewById(R.id.list_view)
        wearableRecyclerView.apply {
            isEdgeItemsCenteringEnabled = true
            WearableLinearLayoutManager(this@QuantitySelection).apply {
                wearableRecyclerView.layoutManager = this
            }
        }

        RecyclerViewAdapter(holesList, this).apply {
            wearableRecyclerView.adapter = this
        }


    }

    override fun onClickListener(holeList: MutableList<Hole>, index: Int) {
        val intent = Intent(this, Tracker::class.java)
        intent.putExtra("holesList", ArrayList(holesList))
        intent.putExtra("index", index)
        startActivityForResult(intent, 666)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 666 && resultCode == RESULT_OK) {
            super.onActivityResult(requestCode, resultCode, data)
            if (data != null) {
                holesList = data.getParcelableArrayListExtra("holesList")
                val wearableRecyclerView: WearableRecyclerView = findViewById(R.id.list_view)
                wearableRecyclerView.apply {
                    isEdgeItemsCenteringEnabled = true
                    WearableLinearLayoutManager(this@QuantitySelection).apply {
                        wearableRecyclerView.layoutManager = this
                    }
                }

                RecyclerViewAdapter(holesList, this).apply {
                    wearableRecyclerView.adapter = this
                }
            }
        }

    }

    override fun onBackPressed() {
        val dialog: Dialog = Dialog(this@QuantitySelection)
        val dialogView: View = layoutInflater.inflate(R.layout.dialog_face, null)
        val positiveButton: ImageButton = dialogView.findViewById(R.id.go_back)
        positiveButton.setOnClickListener {
            dialog.cancel()
        }
        val negativeButton: ImageButton = dialogView.findViewById(R.id.delete)
        negativeButton.setOnClickListener {
            super.onBackPressed()
        }
        dialog.setContentView(dialogView)
        dialog.show()
    }
}


