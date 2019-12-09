package com.example.futmanagerapp.DataBase;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Usuario.class}, version = 1)
public abstract class UsuariosDataBase extends RoomDatabase {


    public abstract UsuariosDAO dao();

    private static UsuariosDataBase INSTANCE;

    public static UsuariosDataBase getInstance(final Context context){
        if(INSTANCE == null){
            synchronized (UsuariosDataBase.class){
                INSTANCE = Room.databaseBuilder(context, UsuariosDataBase.class, "usuarios-db")
                        .fallbackToDestructiveMigration()
                        .build();

            }
        }
        return INSTANCE;
    }
}
