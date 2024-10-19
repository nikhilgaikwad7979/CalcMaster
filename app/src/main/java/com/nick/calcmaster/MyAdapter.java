package com.nick.calcmaster;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MyAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final List<String> values;
    private final DatabaseReference databaseReference;

    public MyAdapter(Context context, List<String> values) {
        super(context, R.layout.list_item, values);
        this.context = context;
        this.values = values;

        // Initialize Firebase Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("your_database_path"); // Change to your database path
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item, parent, false);

        TextView textView = rowView.findViewById(R.id.textView);
        Button openButton = rowView.findViewById(R.id.button);
        Button deleteButton = rowView.findViewById(R.id.delete_button);

        textView.setText(values.get(position));

        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("ITEM", values.get(position));
                context.startActivity(intent);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemToDelete;
                itemToDelete = values.get(position);
                databaseReference.child(itemToDelete).removeValue()
                        .addOnSuccessListener(aVoid -> {
                            values.remove(position);
                            notifyDataSetChanged();
                            Toast.makeText(context, "Item deleted from Firebase", Toast.LENGTH_SHORT).show();
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(context, "Failed to delete item: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
            }
        });

        return rowView;
    }
}
