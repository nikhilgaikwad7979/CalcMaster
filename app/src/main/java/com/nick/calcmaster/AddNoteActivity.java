package com.nick.calcmaster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNoteActivity extends AppCompatActivity {

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        EditText editText = findViewById(R.id.editTextNote);
        Button buttonAdd = findViewById(R.id.buttonAddNote);

        // Initialize Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference("notes");

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newNote = editText.getText().toString().trim();
                if (!newNote.isEmpty()) {
                    // Push the new note to Firebase
                    String noteId = databaseReference.push().getKey();
                    databaseReference.child(noteId).setValue(newNote)
                            .addOnSuccessListener(aVoid -> {
                                Toast.makeText(AddNoteActivity.this, "Note added", Toast.LENGTH_SHORT).show();
                                Intent returnIntent = new Intent();
                                returnIntent.putExtra("NEW_NOTE", newNote); // Make sure 'newNote' is your note string
                                setResult(RESULT_OK, returnIntent);
                                finish();

                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(AddNoteActivity.this, "Failed to add note: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            });
                } else {
                    Toast.makeText(AddNoteActivity.this, "Please enter a note", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
