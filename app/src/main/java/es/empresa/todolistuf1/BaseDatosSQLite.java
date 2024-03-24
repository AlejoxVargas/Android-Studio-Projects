package es.empresa.todolistuf1;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BaseDatosSQLite extends SQLiteOpenHelper {

    public BaseDatosSQLite(Context context) {
        super(context, "notitas", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS notas (id INTEGER PRIMARY KEY AUTOINCREMENT, nota TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void insertNote(String nota) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nota", nota);
        db.insert("notas", null, values);
        db.close();
    }

    public void deleteAllNotes() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("notas", null, null);
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = 'notas'");
        db.close();
    }

    public void deleteNoteById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("notas", "id=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public int numberOfNotes() {
        SQLiteDatabase db = this.getReadableDatabase();
        int num = (int) DatabaseUtils.queryNumEntries(db, "notas");
        db.close();
        return num;
    }

    @SuppressLint("Range")
    public ArrayList<String> getAllNotes() {
        ArrayList<String> filas = new ArrayList<String>();
        String contenido = "";
        if (numberOfNotes() > 0) {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor res = db.rawQuery("SELECT * FROM notas", null);
            res.moveToFirst();
            while (!res.isAfterLast()) {
                contenido = res.getInt(res.getColumnIndex("id")) + ".-" + res.getString(res.getColumnIndex("nota"));
                res.moveToNext();
                filas.add(contenido);
            }
            db.close();
        }
        return filas;
    }
}
