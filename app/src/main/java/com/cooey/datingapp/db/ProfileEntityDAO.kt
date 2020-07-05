package com.cooey.datingapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProfileEntityDAO {
    // Method to insert the answers fetched from api to room
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertResponse(profileEntity: ProfileEntity)

    // Method to fetch the answers stored locally
    @Query("SELECT * FROM `profileEntity`")
    fun getResponse(): List<ProfileEntity>
}
