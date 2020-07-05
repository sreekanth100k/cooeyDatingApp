package com.cooey.datingapp

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView.SmoothScroller
import com.cooey.datingapp.db.AppDb
import com.cooey.datingapp.db.ProfileEntity
import kotlinx.android.synthetic.main.layout_liked_photos_activity.*
import java.util.*


class LikedPhotosActivity:Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.layout_liked_photos_activity)

       var  profileList:List<ProfileEntity> = AppDb.getInMemoryDatabase(applicationContext).profileEntityMappingDAO().getProfilesWithStatus("true")



        var likedListAdapterObj:LikedListAdapter = LikedListAdapter(this,R.layout.card_layout,
            profileList as ArrayList<ProfileEntity>?
        )

        rv_liked_photos.setLayoutManager( LinearLayoutManager(this));





        rv_liked_photos.adapter = likedListAdapterObj

        (rv_liked_photos.adapter as LikedListAdapter).notifyDataSetChanged()

//        val smoothScroller: SmoothScroller = object : LinearSmoothScroller(this) {
//            override fun getVerticalSnapPreference(): Int {
//                return SNAP_TO_START
//            }
//        }
//
//        smoothScroller.setTargetPosition(profileList.size);
//        rv_liked_photos.layoutManager?.startSmoothScroll(smoothScroller);



    }

    override fun onPause() {
        super.onPause()
    }

    override fun onRestart() {
        super.onRestart()
    }
}