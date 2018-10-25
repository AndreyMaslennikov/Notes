package com.example.andreimaslennikov.notes;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.andreimaslennikov.notes.Adapters.NotesListAdapter;
import com.example.andreimaslennikov.notes.Database.DBContext;
import com.example.andreimaslennikov.notes.Models.Note;

import java.util.List;

import static com.example.andreimaslennikov.notes.Constants.ID;
import static com.example.andreimaslennikov.notes.Constants.IS_NEW_NOTE;
import static com.example.andreimaslennikov.notes.Constants.TEXT;
import static com.example.andreimaslennikov.notes.Constants.TITLE;

public class NotesActivity extends AppCompatActivity {

    private List<Note> notes;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_notes);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        notes = DBContext.getInstance().getNotes();

        NotesListAdapter adapter = new NotesListAdapter(this, R.layout.note_item, notes);
        ListView lvNotes = findViewById(R.id.lvNotes);
        lvNotes.setAdapter(adapter);
        lvNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Note note = notes.get(position);
                Intent intent = new Intent(context, EditNoteActivity.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean(IS_NEW_NOTE, false);
                bundle.putInt(ID, note.getId());
                bundle.putString(TITLE, note.getTitle());
                bundle.putString(TEXT, note.getText());
                startActivity(intent);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditNoteActivity.class);
                intent.putExtra(IS_NEW_NOTE, true);
                startActivity(intent);
            }
        });
    }

    public void onAddNoteClick(View v) {

    }
}
