package com.example.noteapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.noteapp.models.Note;

import java.util.List;
import java.util.function.Consumer;

public class NotesAdapter extends ArrayAdapter<Note> {

    private int notesListView;
    private Context context;
    private Consumer<Integer> onNoteRemoved;

    public NotesAdapter(@NonNull Context context, int notesListView, List<Note> notes, Consumer<Integer> onNoteRemoved) {
        super(context, notesListView, notes);

        this.context = context;
        this.notesListView = notesListView;
        this.onNoteRemoved = onNoteRemoved;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Note note = getItem(position);

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(notesListView, parent, false);

        TextView titleView = (TextView) convertView.findViewById(R.id.noteTitle);
        TextView priorityView = (TextView) convertView.findViewById(R.id.notePriority);
        ImageView hasReadView = convertView.findViewById(R.id.hasRead);

        if (!note.getHasBeenRead()) {
            hasReadView.setVisibility(View.INVISIBLE);
        }

        titleView.setText(note.getTitle());
        priorityView.setText(note.getPriority().toString());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDetails(note);
            }
        });

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onNoteRemoved.accept(note.getId());
                return true;
            }
        });

        return convertView;
    }

    private void showDetails(Note note) {
        Intent intent = new Intent(context, NoteDetails.class);
        intent.putExtra(NoteDetails.NOTE_ID, note.getId());
        intent.putExtra(NoteDetails.NOTE_TITLE, note.getTitle());
        intent.putExtra(NoteDetails.NOTE_DETAILS, note.getText());
        context.startActivity(intent);
    }
}
