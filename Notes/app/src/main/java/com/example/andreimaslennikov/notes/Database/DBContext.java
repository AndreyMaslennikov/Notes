package com.example.andreimaslennikov.notes.Database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import com.example.andreimaslennikov.notes.Models.Note;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.andreimaslennikov.notes.Constants.DB_NAME;

public class DBContext {
    private static DBContext instance = null;
    private static SQLiteDatabase dbInstance = null;

    private DBContext() {
        createDB();
    }

    private static void createDB() {
        dbInstance = SQLiteDatabase.openOrCreateDatabase(DB_NAME,  null);
        String query = "CREATE TABLE IF NOT EXISTS "
                + Note.TABLE_NAME +"("
                + Note.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Note.TITLE_COL + " VARCHAR, "
                + Note.TEXT_COL + " VARCHAR);";
        dbInstance.execSQL(query);
        query = "INSERT INTO " + Note.TABLE_NAME + " (" + Note.TITLE_COL + ", "
                + Note.TEXT_COL + ") VALUES (\"first\", \"first text\")";
        dbInstance.execSQL(query);
        query = "INSERT INTO " + Note.TABLE_NAME + " (" + Note.TITLE_COL + ", "
                + Note.TEXT_COL + ") VALUES (\"second\", \"seconds text\")";
        dbInstance.execSQL(query);
        query = "INSERT INTO " + Note.TABLE_NAME + " (" + Note.TITLE_COL + ", "
                + Note.TEXT_COL + ") VALUES (\"third\", \"third text\")";
        dbInstance.execSQL(query);
    }

    public SQLiteDatabase getDb() {
        return dbInstance;
    }

    public List<Note> getNotes() {
        List<Note> res = new ArrayList<>();

        String query = "SELECT * FROM " + Note.TABLE_NAME;
        Cursor c = dbInstance.rawQuery(query, null);
        final List<String> columnNames = Arrays.asList(c.getColumnNames());
        final int idIndex = columnNames.indexOf(Note.ID_COL);
        final int titleIndex = columnNames.indexOf(Note.TITLE_COL);
        final int textIndex = columnNames.indexOf(Note.TEXT_COL);

        if (c.moveToFirst()) {
            while(!c.isLast()) {
                res.add(new Note(
                        c.getInt(idIndex),
                        c.getString(titleIndex),
                        c.getString(textIndex)
                ));
            }
        }
        c.close();
        return res;
    }

    public void createNote(Note note) {
        String query = "INSERT INTO " + Note.TABLE_NAME + " (" + Note.TITLE_COL + ", "
                + Note.TEXT_COL + ") VALUES (\"" + note.getTitle() + "\", \"" + note.getText() + "\")";
        dbInstance.execSQL(query);
    }

    public void updateNote(Note note) {
        String query = "UPDATE " + Note.TABLE_NAME + "SET " + note.TITLE_COL + " = " + note.getText()
                + ", " + note.TEXT_COL + " = " + note.getText() + " WHERE " + Note.ID_COL + " = " + note.getId();
    }

    public void deleteNote(Note note) {
        String query = "DELETE FROM " + Note.TABLE_NAME + " WHERE ID=" + note.getId();
        dbInstance.execSQL(query);
    }

    public static DBContext getInstance() {
        if (instance == null) {
            return new DBContext();
        }
        return instance;
    }
}
