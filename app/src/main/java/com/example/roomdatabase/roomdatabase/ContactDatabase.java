package com.example.roomdatabase.roomdatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.roomdatabase.Dao.ContactDAO;
import com.example.roomdatabase.model.Contacts;

@Database(entities = {Contacts.class}, version = 1)
public abstract class ContactDatabase extends RoomDatabase {

    //This method is an abstract method that will be used to retrieve
    // the Data Access Object (DAO) for the Contacts table.
    // DAOs are responsible for defining the methods to interact with the database.
    public abstract ContactDAO getContactDAO();
    private static ContactDatabase dbInstance;

    //This method is a C pattern implementation that ensures only one instance
    // of the database exists throughout the application. It uses the getInstance method
    // to obtain the database instance. If the dbInstance is null, it builds a new instance
    // of the ContactDatabase using Room.databaseBuilder().The .fallbackToDestructiveMigration()
    // method is used to handle database schema migrations by destroying and recreating the database
    // when the version number is incremented.
    public static synchronized ContactDatabase getInstance(Context context){
        if (dbInstance == null){
            dbInstance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            ContactDatabase.class,
                            "contacts_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return dbInstance;
    }
}
