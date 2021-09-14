package com.example.librarymanagementsystem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {

    EditText author_name2, p_year2, p_house2, dept2, isbn_no2, copies2;
    Button update_btn, del_btn;
    TextView book_name2;

    String name, author, year, house, deptm, isbn, no_copies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        book_name2 = findViewById(R.id.book_name2);
        author_name2 = findViewById(R.id.author_name2);
        p_year2 = findViewById(R.id.p_year2);
        p_house2 = findViewById(R.id.p_house2);
        dept2 = findViewById(R.id.dept2);
        isbn_no2 = findViewById(R.id.isbn_no2);
        copies2 = findViewById(R.id.no_copies2);
        update_btn = findViewById(R.id.update_button);
        del_btn = findViewById(R.id.delete_button);

        getAndSetIntentData();
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                name = book_name2.getText().toString().trim();
                author = author_name2.getText().toString().trim();
                year = p_year2.getText().toString().trim();
                house = p_house2.getText().toString().trim();
                deptm = dept2.getText().toString().trim();
                isbn = isbn_no2.getText().toString().trim();
                no_copies = copies2.getText().toString().trim();

                myDB.updateData(name, author, year, house, deptm, isbn, no_copies);
                finish();
            }
        });

        del_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("name") &&
                getIntent().hasExtra("author") &&
                getIntent().hasExtra("year") &&
                getIntent().hasExtra("house") &&
                getIntent().hasExtra("dept") &&
                getIntent().hasExtra("isbn") &&
                getIntent().hasExtra("copies")){
            //getting data from intent
            name = getIntent().getStringExtra("name");
            author = getIntent().getStringExtra("author");
            year = getIntent().getStringExtra("year");
            house = getIntent().getStringExtra("house");
            deptm = getIntent().getStringExtra("dept");
            isbn = getIntent().getStringExtra("isbn");
            no_copies = getIntent().getStringExtra("copies");

            //setting data from intent
            book_name2.setText(name);
            author_name2.setText(author);
            p_year2.setText(year);
            p_house2.setText(house);
            dept2.setText(deptm);
            isbn_no2.setText(isbn);
            copies2.setText(no_copies);
        }else{
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(name);
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