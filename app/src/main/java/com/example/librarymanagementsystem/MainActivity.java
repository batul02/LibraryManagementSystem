package com.example.librarymanagementsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyDatabaseHelper myDB;
    ArrayList<String> book_id, book_name, author_name, p_year, p_house, dept, isbn_no, copies;
    CustomAdapter customAdapter;
    ImageView empty_bin;
    TextView nodata_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        empty_bin = findViewById(R.id.empty_bin);
        nodata_txt = findViewById(R.id.nodata_txt);

        myDB = new MyDatabaseHelper(MainActivity.this);
        book_id = new ArrayList<>();
        book_name = new ArrayList<>();
        author_name = new ArrayList<>();
        p_year = new ArrayList<>();
        p_house = new ArrayList<>();
        dept = new ArrayList<>();
        isbn_no = new ArrayList<>();
        copies = new ArrayList<>();

        storeDataInArray();

        customAdapter = new CustomAdapter(MainActivity.this, this, book_id, book_name, author_name, p_year, p_house, dept, isbn_no, copies);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            recreate();
        }
    }

    void storeDataInArray(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            empty_bin.setVisibility(View.VISIBLE);
            nodata_txt.setVisibility(View.VISIBLE);
        }else {
            while (cursor.moveToNext()){
                book_id.add(cursor.getString(0));
                book_name.add(cursor.getString(1));
                author_name.add(cursor.getString(2));
                p_year.add(cursor.getString(3));
                p_house.add(cursor.getString(4));
                dept.add(cursor.getString(5));
                isbn_no.add(cursor.getString(6));
                copies.add(cursor.getString(7));
                empty_bin.setVisibility(View.GONE);
                nodata_txt.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int item_id = item.getItemId();
        if (item_id == R.id.add_book) {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivity(intent);
        }
        else if(item_id == R.id.delete_all){
            Cursor cursor = myDB.readAllData();
            if(cursor.getCount() == 0){
                Toast.makeText(this, "No Data to delete.", Toast.LENGTH_SHORT).show();
            }else {
                confirmDialog();
            }
        }
        else {

        }
        return true;
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all Data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(MainActivity.this);
                myDB.deleteAllData();
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}