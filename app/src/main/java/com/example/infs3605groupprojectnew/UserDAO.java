package com.example.infs3605groupprojectnew;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserDAO {
    // define database private reference
    private DatabaseReference databaseReference;
    public UserDAO()
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        // get our reference and the parameter is a name of our node database
        databaseReference = db.getReference(User.class.getSimpleName());
    }
    // insert value to the database - return Task as a value because we want to separate the pattern - let user know record has been inserted through message display

    public Task<Void> add (User user)
    {
        // generate a unique key in the node - no need to worry about a duplicate key because it generated from the time stamp
        // managing our data before we sent it to the server

//        if(emp == null ) // throw exception
        return databaseReference.push().setValue(user);
    }

}
