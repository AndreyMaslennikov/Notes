package com.example.andreimaslennikov.notes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.andreimaslennikov.notes.Database.DBContext;
import com.example.andreimaslennikov.notes.Models.Note;

import static com.example.andreimaslennikov.notes.Constants.ID;
import static com.example.andreimaslennikov.notes.Constants.IS_NEW_NOTE;
import static com.example.andreimaslennikov.notes.Constants.TEXT;
import static com.example.andreimaslennikov.notes.Constants.TITLE;

public class EditNoteActivity extends AppCompatActivity {

    private boolean mCreate;
    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        //TODO: add checking for update/create

        Bundle args = getIntent().getExtras();
        mCreate = args.getBoolean(IS_NEW_NOTE);
        if (!mCreate) {
            note = new Note(
                    args.getInt(ID),
                    args.getString(TITLE),
                    args.getString(TEXT));
            TextView tvTitle = findViewById(R.id.etTitle);
            tvTitle.setText(note.getTitle());
            TextView tvText = findViewById(R.id.etNote);
            tvText.setText(note.getText());
        }
    }

    public void onSaveClick(View v) {
        EditText et = findViewById(R.id.etTitle);
        note.setTitle(et.getText().toString());
        et = findViewById(R.id.etNote);
        note.setText(et.getText().toString());

        if (mCreate) {
            DBContext.getInstance().createNote(note);
        } else {
            DBContext.getInstance().updateNote(note);
        }
    }
}
