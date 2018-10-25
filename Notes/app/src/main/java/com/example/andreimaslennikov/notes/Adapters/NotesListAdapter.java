package com.example.andreimaslennikov.notes.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.andreimaslennikov.notes.Models.Note;
import com.example.andreimaslennikov.notes.R;

import java.util.List;

public class NotesListAdapter extends ArrayAdapter<Note> {

    private List<Note> notes;
    private Context context;
    private static LayoutInflater inflater;
    private int listItem;

    public NotesListAdapter(Context context, int resource, List<Note> notes) {
        super(context, resource, notes);

        this.notes = notes;
        this.context = context;
        this.listItem = resource;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(listItem, null);
        }

        Note note = notes.get(position);
        String title = note.getTitle() == null
                ? context.getString(R.string.without_title)
                : note.getTitle();
        String text = note.getText() == null
                ? context.getString(R.string.without_text)
                : note.getText();

        TextView tvTitle = convertView.findViewById(R.id.tvItemTitle);
        tvTitle.setText(title);
        TextView tvText = convertView.findViewById(R.id.tvItemNote);
        tvText.setText(text);
        return convertView;
    }
}
