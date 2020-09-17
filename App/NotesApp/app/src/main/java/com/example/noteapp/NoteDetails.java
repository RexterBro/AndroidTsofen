package com.example.noteapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class NoteDetails extends AppCompatActivity {

    public static final String NOTE_ID = "noteId";
    public static final String NOTE_TITLE = "noteTitle";
    public static final String NOTE_DETAILS = "noteDetails";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        Intent intent = getIntent();
        Integer noteId = intent.getIntExtra(NOTE_ID, 0);
        String noteTitle = intent.getStringExtra(NOTE_TITLE);
        String noteText = intent.getStringExtra(NOTE_DETAILS);

        markNoteAsRead(noteId);

        TextView titleView = (TextView) findViewById(R.id.noteDetailsTitle);
        TextView textView = (TextView) findViewById(R.id.noteDetailsText);

        titleView.setText(noteTitle);
        textView.setText(noteText);
    }

    private void markNoteAsRead(Integer id) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = String.format("http://10.0.0.5:8080/notes/read/%d", id);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                }, error -> {
        });

        queue.add(stringRequest);
    }
}