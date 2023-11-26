package com.example.roomdatabase.repository;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import com.example.roomdatabase.Dao.ContactDAO;
import com.example.roomdatabase.model.Contacts;
import com.example.roomdatabase.roomdatabase.ContactDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ContactRepository {
    private final ContactDAO contactDAO;
    private final ExecutorService executor;
    private final Handler handler;

    public ContactRepository(Application application) {
        // Creating an instance of ContactDatabase and obtaining ContactDAO
        ContactDatabase contactDatabase = ContactDatabase.getInstance(application);
        this.contactDAO = contactDatabase.getContactDAO();

        // ExecutorService for background database operations
        executor = Executors.newSingleThreadExecutor();

        // Handler for updating the UI
        handler = new Handler(Looper.getMainLooper());
    }

    // Method to add a contact
    public void addContact(Contacts contact){
        executor.execute(() -> {
            // Execute the insertion on a background thread
            contactDAO.insert(contact);
        });
    }

    // Method to delete a contact
    public void deleteContact(Contacts contact){
        executor.execute(() -> {
            // Execute the deletion on a background thread
            contactDAO.delete(contact);
        });
    }

    // Method to retrieve all contacts as LiveData
    public LiveData<List<Contacts>> getAllContacts(){
        return contactDAO.getAllContacts();
    }
}

// Repository class acts as an intermediary between the ViewModel and the data sources.
//It holds an instance of ContactDAO which is responsible for database operations related to contacts.
//In the constructor, it initializes the database, gets the DAO instance, sets up an ExecutorService for
// background tasks, and a Handler for UI updates.
//The addContact method asynchronously inserts a contact into the database using the executor.
//The deleteContact method asynchronously deletes a contact from the database using the executor.
//The getAllContacts method returns a LiveData object containing a list of contacts, allowing observers
// to be notified of changes in the data.
