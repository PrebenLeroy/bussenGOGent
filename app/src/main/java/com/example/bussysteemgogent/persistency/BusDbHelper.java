package com.example.bussysteemgogent.persistency;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by prebe on 30/01/2018.
 */

public class BusDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "bus.db";
    private static final int DATABASE_VERSION = 1;

    private final String SQL_CREATE_CHARACTER_TABLE
            = "CREATE TABLE " + BusContract.BusEntry.TABLE_NAME
            + " (" + BusContract.BusEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + BusContract.BusEntry.COLUMN_NUMMERPLAAT + " TEXT NOT NULL "
            + ");";

    private static final String SQL_DROP_CHARACTER_TABLE =
            "DROP TABLE IF EXISTS " + BusContract.BusEntry.TABLE_NAME;

    public BusDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_CHARACTER_TABLE);

        ContentValues cv = new ContentValues();
        cv.put(BusContract.BusEntry.COLUMN_NUMMERPLAAT, "1-LRB-935");
        db.insert(BusContract.BusEntry.TABLE_NAME, null,cv);

        cv.put(BusContract.BusEntry.COLUMN_NUMMERPLAAT, "1-LCY-525");
        db.insert(BusContract.BusEntry.TABLE_NAME, null,cv);

        cv.put(BusContract.BusEntry.COLUMN_NUMMERPLAAT, "1-LRB-893");
        db.insert(BusContract.BusEntry.TABLE_NAME, null,cv);

        cv.put(BusContract.BusEntry.COLUMN_NUMMERPLAAT, "1-SGV-936");
        db.insert(BusContract.BusEntry.TABLE_NAME, null,cv);

        cv.put(BusContract.BusEntry.COLUMN_NUMMERPLAAT, "1-LUJ-179");
        db.insert(BusContract.BusEntry.TABLE_NAME, null,cv);

        cv.put(BusContract.BusEntry.COLUMN_NUMMERPLAAT, "752-BJV");
        db.insert(BusContract.BusEntry.TABLE_NAME, null,cv);

        cv.put(BusContract.BusEntry.COLUMN_NUMMERPLAAT, "1-BSD-211");
        db.insert(BusContract.BusEntry.TABLE_NAME, null,cv);

        cv.put(BusContract.BusEntry.COLUMN_NUMMERPLAAT, "1-LCR-982");
        db.insert(BusContract.BusEntry.TABLE_NAME, null,cv);

        cv.put(BusContract.BusEntry.COLUMN_NUMMERPLAAT, "1-KER-716");
        db.insert(BusContract.BusEntry.TABLE_NAME, null,cv);

        cv.put(BusContract.BusEntry.COLUMN_NUMMERPLAAT, "YQR-359");
        db.insert(BusContract.BusEntry.TABLE_NAME, null,cv);

        cv.put(BusContract.BusEntry.COLUMN_NUMMERPLAAT, "355-ARH");
        db.insert(BusContract.BusEntry.TABLE_NAME, null,cv);

        cv.put(BusContract.BusEntry.COLUMN_NUMMERPLAAT, "1-EID-238");
        db.insert(BusContract.BusEntry.TABLE_NAME, null,cv);

        cv.put(BusContract.BusEntry.COLUMN_NUMMERPLAAT, "1-FFX-467");
        db.insert(BusContract.BusEntry.TABLE_NAME, null,cv);

        cv.put(BusContract.BusEntry.COLUMN_NUMMERPLAAT, "1-FFX-096");
        db.insert(BusContract.BusEntry.TABLE_NAME, null,cv);

        cv.put(BusContract.BusEntry.COLUMN_NUMMERPLAAT, "1-FFX-085");
        db.insert(BusContract.BusEntry.TABLE_NAME, null,cv);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Ideally we wouldn't want to delete all of our entries!
        db.execSQL(SQL_DROP_CHARACTER_TABLE);
        onCreate(db);	// Call to create a new db with upgraded schema and version
    }
}
