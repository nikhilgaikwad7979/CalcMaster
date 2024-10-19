package com.nick.calcmaster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class tryAct extends AppCompatActivity {

    private static final int REQUEST_CODE_ADD_NOTE = 1;
    private MyAdapter adapter;
    private List<String> items;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try);

        ListView listView = findViewById(R.id.listView);
        Button addNoteButton = findViewById(R.id.add_button);

        // Initialize the list of items
        items = new ArrayList<>();

        // Create the adapter and set it to the ListView
        adapter = new MyAdapter(this, items);
        listView.setAdapter(adapter);

        // Initialize Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference("notes");

        // Fetch notes from Firebase
        fetchNotesFromFirebase();

        // Set click listener for the add note button
        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tryAct.this, AddNoteActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_NOTE);
            }
        });
    }

    private void fetchNotesFromFirebase() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                items.clear(); // Clear existing items
                for (DataSnapshot noteSnapshot : dataSnapshot.getChildren()) {
                    String note = noteSnapshot.getValue(String.class);
                    items.add(note);
                }
                adapter.notifyDataSetChanged(); // Notify adapter of data change
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle potential errors
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_ADD_NOTE && resultCode == RESULT_OK) {
            // No need to add the note again to Firebase here
            // The note is already added in AddNoteActivity
            String newNote = data.getStringExtra("NEW_NOTE");
            if (newNote != null) {
                // Optionally, you could still add it to the local list if needed
                // items.add(newNote);
                // adapter.notifyDataSetChanged();
            }
        }
    }
}
