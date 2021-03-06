package com.example.librarymanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "BookLibrary.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "my_library";
    private static final String COLUMN_ID = "book_id";
    private static final String COLUMN_NAME = "book_name";
    private static final String COLUMN_AUTHOR = "author_name";
    private static final String COLUMN_YEAR = "publication_year";
    private static final String COLUMN_PHOUSE = "publishing_house";
    private static final String COLUMN_DEPT = "department";
    private static final String COLUMN_ISBN = "isbn_number";
    private static final String COLUMN_COPIES = "number_copies";

    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_AUTHOR + " TEXT, " +
                        COLUMN_YEAR + " INTEGER, " +
                        COLUMN_PHOUSE + " TEXT, " +
                        COLUMN_DEPT + " TEXT, " +
                        COLUMN_ISBN + " INTEGER, " +
                        COLUMN_COPIES + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
    void addBook(String name, String author, int year, String phouse, String dept, int isbn, int copies){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_AUTHOR, author);
        cv.put(COLUMN_YEAR, year);
        cv.put(COLUMN_PHOUSE, phouse);
        cv.put(COLUMN_DEPT, dept);
        cv.put(COLUMN_ISBN, isbn);
        cv.put(COLUMN_COPIES, copies);

        long result = db.insert(TABLE_NAME, null, cv);
        if(result==-1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String id, String name, String author, String year, String phouse, String dept, String isbn, String copies){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_AUTHOR, author);
        cv.put(COLUMN_YEAR, year);
        cv.put(COLUMN_PHOUSE, phouse);
        cv.put(COLUMN_DEPT, dept);
        cv.put(COLUMN_ISBN, isbn);
        cv.put(COLUMN_COPIES, copies);

        long result = db.update(TABLE_NAME, cv, "book_id=?", new String[]{id});
        if(result==-1){
            Toast.makeText(context, "Failed to update.", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneRow(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "book_id=?", new String[]{id});
        if(result==-1){
            Toast.makeText(context, "Failed to delete.", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
