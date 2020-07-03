package com.cooey.datingapp

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lorentzos.flingswipe.SwipeFlingAdapterView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    lateinit var mFlingContainer:SwipeFlingAdapterView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //add the view via xml or programmatically
        mFlingContainer = findViewById(R.id.id_swipe_fling_adapter_view) as SwipeFlingAdapterView

//        var al: ArrayList<String> = ArrayList<String>()
//        al.add("php")
//        al.add("c")
//        al.add("python")
//        al.add("java")

        //choose your favorite adapter
//        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(this, R.layout.card_layout, R.id.id_tv, al)
//
//        //set the listener and the adapter
//        flingContainer.adapter = arrayAdapter
//        flingContainer.setFlingListener(object : SwipeFlingAdapterView.onFlingListener {
//            override fun removeFirstObjectInAdapter() {
//                al.removeAt(0);
//                arrayAdapter.notifyDataSetChanged();
//            }
//
//            override fun onLeftCardExit(p0: Any?) {
//            }
//
//            override fun onRightCardExit(p0: Any?) {
//            }
//
//            override fun onAdapterAboutToEmpty(p0: Int) {
//                arrayAdapter.notifyDataSetChanged();
//            }
//
//            override fun onScroll(p0: Float) {
//            }
//        })
//
//        flingContainer.setOnItemClickListener { itemPosition, dataObject ->
//            Toast.makeText(
//                this@MainActivity,
//                "Clicked!", Toast.LENGTH_LONG
//            ).show()
//        }


        NetworkService.getInstance().jsonApi.fetchApiResponse()
            .enqueue(object : Callback<List<ApiResponse>> {
                override fun onResponse(
                    call: Call<List<ApiResponse>>,
                    response: Response<List<ApiResponse>>
                ) {

                    decodeResponseAndDoNecessaryActions(response)

                }

                override fun onFailure(call: Call<List<ApiResponse>>, t: Throwable) {
                    Log.d("MainActivity","Test");
                }
            });
    }

    fun decodeResponseAndDoNecessaryActions(iResponse:Response<List<ApiResponse>>){

        var response: ArrayList<ApiResponse>? = iResponse.body() as ArrayList<ApiResponse>
        Log.d("Response", response.toString())

        val arrayAdapter = ListAdapter(this@MainActivity,R.layout.card_layout,response);

        //set the listener and the adapter
        mFlingContainer.adapter = arrayAdapter
        arrayAdapter.notifyDataSetChanged()
        mFlingContainer.setFlingListener(object : SwipeFlingAdapterView.onFlingListener {
            override fun removeFirstObjectInAdapter() {
                 response?.removeAt(0)
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

    }
}