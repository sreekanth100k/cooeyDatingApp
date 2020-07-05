package com.cooey.datingapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cooey.datingapp.db.AppDb
import com.cooey.datingapp.db.ProfileEntity
import com.lorentzos.flingswipe.SwipeFlingAdapterView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    lateinit var mFlingContainer:SwipeFlingAdapterView
    lateinit var mViewLikedPhotos:Button
    var mLastRemovedItem: ApiResponse? =null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //add the view via xml or programmatically
        mFlingContainer = findViewById(R.id.id_swipe_fling_adapter_view) as SwipeFlingAdapterView

        mViewLikedPhotos    =   findViewById(R.id.id_btn_view_liked_photos)

        mViewLikedPhotos.setOnClickListener(View.OnClickListener {

            val intent = Intent(this, LikedPhotosActivity::class.java)
            startActivity(intent)

        })

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

        if (response != null) {
            for(apiResponseIterator:ApiResponse in response){
                var gender:String           =    apiResponseIterator.gender
                var age:Int                 =    apiResponseIterator.age
                var email:String            =    apiResponseIterator.email
                var favColor:String         =    apiResponseIterator.favoriteColor
                var id:String               =    apiResponseIterator._id
                var name:String             =    apiResponseIterator.name
                var phone:String            =    apiResponseIterator.phone
                var picture:String          =    apiResponseIterator.picture
                var lastSeen:String         =    apiResponseIterator.lastSeen
//                var geoLocation:Geolocation      =    apiResponseIterator.geoLocation


                var profileEntity = ProfileEntity(picture,name,gender,favColor,age.toString(),phone,lastSeen,id,email,"");
                AppDb.getInMemoryDatabase(applicationContext).profileEntityMappingDAO().insertResponse(profileEntity)


            }
        }

        val arrayAdapter = ListAdapter(this@MainActivity,R.layout.card_layout,response)

        //set the listener and the adapter
        mFlingContainer.adapter = arrayAdapter
        arrayAdapter.notifyDataSetChanged()
        mFlingContainer.setFlingListener(object : SwipeFlingAdapterView.onFlingListener {
            override fun removeFirstObjectInAdapter() {
                  response?.removeAt(0)

                 arrayAdapter.notifyDataSetChanged();
            }

            override fun onLeftCardExit(p0: Any?) {
                mLastRemovedItem = p0 as ApiResponse
                Toast.makeText(this@MainActivity,"Discarded",Toast.LENGTH_SHORT).show()
            }

            override fun onRightCardExit(p0: Any?) {
                var apiResponse:ApiResponse = p0 as ApiResponse
                Toast.makeText(this@MainActivity,"Liked",Toast.LENGTH_SHORT).show()

                //Save the value...

               AppDb.getInMemoryDatabase(applicationContext).profileEntityMappingDAO().updateProfileAsLiked(apiResponse._id,"true")

            }

            override fun onAdapterAboutToEmpty(p0: Int) {
                arrayAdapter.notifyDataSetChanged();
            }

            override fun onScroll(p0: Float) {
            }
        })

    }
}