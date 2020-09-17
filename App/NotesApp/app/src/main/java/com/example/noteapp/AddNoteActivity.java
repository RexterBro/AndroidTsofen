package com.example.noteapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.noteapp.models.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class AddNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        FloatingActionButton addNoteBtn = findViewById(R.id.addNote);
        addNoteBtn.setOnClickListener(view -> onAddNoteClick());
    }

    private void onAddNoteClick() {
        TextInputEditText titleView = findViewById(R.id.addNoteTitle);
        EditText titleDetails = findViewById(R.id.addNoteDetails);
        SeekBar notePriority = findViewById(R.id.addNotePriority);

        Note note = new Note();
        note.setTitle(titleView.getText().toString());
        note.setText(titleDetails.getText().toString());
        note.setPriority(notePriority.getProgress());
        note.setHasBeenRead(false);

        System.out.println(note.getText());
        System.out.println(note.getTitle());

        addNote(note);
    }

    private void addNote(Note noteToAdd) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.0.5:8080/notes/add/";
        Gson gson = new Gson();

        try {
            JSONObject jsonObject = new JSONObject(gson.toJson(noteToAdd));
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                    response -> finish(),
                    error -> System.out.println(error));

            queue.add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}