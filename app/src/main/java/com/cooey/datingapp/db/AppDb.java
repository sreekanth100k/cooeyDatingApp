package com.cooey.datingapp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.cooey.datingapp.ApiResponse;

//@TypeConverters(Converters.class)
@Database(entities = {ProfileEntity.class}, version = 1,exportSchema = false)
public abstract class AppDb extends RoomDatabase  {

    private static AppDb INSTANCE;

    public abstract ProfileEntityDAO profileEntityMappingDAO();

    public static AppDb getInMemoryDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDb.class).allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}