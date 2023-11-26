package com.example.roomdatabase.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.roomdatabase.model.Contacts;
import com.example.roomdatabase.repository.ContactRepository;
import java.util.List;

public class MyViewModel extends AndroidViewModel {
    private ContactRepository myRepository;

    // LiveData
    private LiveData<List<Contacts>> allContacts;

    public MyViewModel(@NonNull Application application) {
        super(application);
        this.myRepository = new ContactRepository(application);
    }

    public LiveData<List<Contacts>> getAllContacts(){
        allContacts = myRepository.getAllContacts();
        return allContacts;
    }


    public void addNewContact(Contacts contact){
        myRepository.addContact(contact);
    }

    public void deleteContact(Contacts contact){
        myRepository.deleteContact(contact);
    }




    // AndroidViewModel class is a subclass of ViewModel
    // and similar to them, they are designed to store and
    // manage UI-related data are responsible to
    // prepare & provide data for UI and automatically
    // allow data to survive configuration change.
}

// MyViewModel extends AndroidViewModel, which is a subclass of ViewModel specifically designed to hold and
// manage UI-related data while maintaining the application's context.
//Repository myRepository; holds an instance of a repository class responsible for managing data operations.
//LiveData<List<Contacts>> allContacts; represents a LiveData object that holds a list of contacts.
// LiveData is an observable data holder class that can be observed by UI components to update the
// UI automatically when the data changes.
//The constructor MyViewModel(@NonNull Application application) initializes the ViewModel with the
// application context and creates an instance of the Repository class.
//getAllContacts() retrieves all contacts using the repository and returns a LiveData object representing the
// list of contacts. This LiveData can be observed by UI components to update the UI when the contact data changes.
//addNewContact(Contacts contact) and deleteContact(Contacts contact) are methods that delegate adding and deleting
// contacts to the repository, encapsulating these operations within the ViewModel.
//This ViewModel acts as a bridge between the UI (like Activities or Fragments) and the repository, providing an abstraction
// for handling contact data. It ensures that UI-related data survives configuration changes and follows best practices by separating
// concerns between UI-related operations and data management.