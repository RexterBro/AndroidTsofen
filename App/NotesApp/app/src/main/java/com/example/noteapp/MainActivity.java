package com.example.noteapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.noteapp.models.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView notesListView;
    private NotesAdapter notesAdapter;

    private final List<Note> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notesListView = (ListView) findViewById(R.id.notesList);
        notesAdapter = new NotesAdapter(this, R.layout.note_list_item, notes, this::removeNote);
        notesListView.setAdapter(notesAdapter);
        FloatingActionButton addNoteBtn = findViewById(R.id.addNoteBtn);
        addNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNote();
            }
        });
        setUpAllNotes();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpAllNotes();
    }

    private void setUpAllNotes() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.0.5:8080/notes/all";

        System.out.println("getting all the notes");
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    Gson gson = new Gson();
                    notes.clear();
                    notes.addAll(Arrays.asList(gson.fromJson(response, Note[].class)));
                    notesAdapter.notifyDataSetChanged();
                }, error -> {
        });

        queue.add(stringRequest);
    }

    private void removeNote(Integer noteId) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = String.format("http://10.0.0.5:8080/notes/delete/%d", noteId);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    System.out.println("removed note " + response);
                    setUpAllNotes();
                }, error -> {
        });

        queue.add(stringRequest);
    }

    private void addNote() {
        Intent intent = new Intent(this, AddNoteActivity.class);
        startActivity(intent);
    }
}