package com.IFP.audio_player_app;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBaseSQL extends SQLiteOpenHelper {
    public DataBaseSQL(Context context) {
        super(context, "audio", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS media (id INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT, url TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void insertAudio(String text, String url) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("titulo", text);
        values.put("url", url);
        db.insert("media", null, values);
        db.close();
    }

    public void deleteAllAudios() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("media", null, null);
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = 'media'");
        db.close();
    }

    public void deleteAudioById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("media", "id=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public int numberOfaudio() {
        SQLiteDatabase db = this.getReadableDatabase();
        int num = (int) DatabaseUtils.queryNumEntries(db, "media");
        db.close();
        return num;
    }

    @SuppressLint("Range")
    public ArrayList<String> getAllAudios() {
        ArrayList<String> filas = new ArrayList<String>();
        String contenido = "";
        if (numberOfaudio() > 0) {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor res = db.rawQuery("SELECT * FROM media", null);
            res.moveToFirst();
            while (!res.isAfterLast()) {
                contenido = res.getInt(res.getColumnIndex("id")) + ".-" + res.getString(res.getColumnIndex("titulo")) + " .- " + res.getString(res.getColumnIndex("url"));
                res.moveToNext();
                filas.add(contenido);
            }
            db.close();
        }
        return filas;
    }

    public ArrayList<String> getAllAudioTitlesAndIds() {
        ArrayList<String> titlesAndIds = new ArrayList<>();
        if (numberOfaudio() > 0) {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor res = db.rawQuery("SELECT id, titulo FROM media", null);
            res.moveToFirst();
            while (!res.isAfterLast()) {
                @SuppressLint("Range") int id = res.getInt(res.getColumnIndex("id"));
                @SuppressLint("Range") String titulo = res.getString(res.getColumnIndex("titulo"));
                String titleAndId = id + ": " + titulo;
                titlesAndIds.add(titleAndId);
                res.moveToNext();
            }
            res.close();
            db.close();
        }
        return titlesAndIds;
    }
}
