package com.cooey.datingapp

import android.graphics.PointF
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lorentzos.flingswipe.FlingCardListener
import com.lorentzos.flingswipe.SwipeFlingAdapterView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //add the view via xml or programmatically
        val flingContainer = findViewById(R.id.id_swipe_fling_adapter_view) as SwipeFlingAdapterView

        var al:ArrayList<String>  = ArrayList<String>()
        al.add("php")
        al.add("c")
        al.add("python")
        al.add("java")

        //choose your favorite adapter
        val arrayAdapter:ArrayAdapter<String> = ArrayAdapter<String>(this, R.layout.card_layout, R.id.id_tv, al)

        //set the listener and the adapter
        flingContainer.adapter = arrayAdapter
        flingContainer.setFlingListener(object: SwipeFlingAdapterView.onFlingListener {
            override fun removeFirstObjectInAdapter() {
                al.removeAt(0);
                arrayAdapter.notifyDataSetChanged();
            }

            override fun onLeftCardExit(p0: Any?) {
            }

            override fun onRightCardExit(p0: Any?) {
            }

            override fun onAdapterAboutToEmpty(p0: Int) {
                arrayAdapter.notifyDataSetChanged();
            }

            override fun onScroll(p0: Float) {
            }
        })

        flingContainer.setOnItemClickListener { itemPosition, dataObject ->
            Toast.makeText(
                this@MainActivity,
                "Clicked!", Toast.LENGTH_LONG
            ).show()
        }
    }
}