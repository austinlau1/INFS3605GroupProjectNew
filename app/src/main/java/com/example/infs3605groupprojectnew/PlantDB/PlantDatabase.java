package com.example.infs3605groupprojectnew.PlantDB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Plant2.class}, version=1)
public abstract class PlantDatabase extends RoomDatabase{
    public abstract PlantDAO plantDAO();

    private static PlantDatabase INSTANCE;

    public static PlantDatabase getDbInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), PlantDatabase.class, "DB_NAME")
                    .allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

}
