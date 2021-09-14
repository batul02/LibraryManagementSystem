package com.example.librarymanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText book_name, author_name, p_year, p_house, dept, isbn_no, copies;
    Button save_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        book_name = findViewById(R.id.book_name);
        author_name = findViewById(R.id.author_name);
        p_year = findViewById(R.id.p_year);
        p_house = findViewById(R.id.p_house);
        dept = findViewById(R.id.dept);
        isbn_no = findViewById(R.id.isbn_no);
        copies = findViewById(R.id.no_copies);
        save_btn = findViewById(R.id.save_button);

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addBook(book_name.getText().toString().trim(),
                        author_name.getText().toString().trim(),
                        Integer.valueOf(p_year.getText().toString().trim()),
                        p_house.getText().toString().trim(),
                        dept.getText().toString().trim(),
                        Integer.valueOf(isbn_no.getText().toString().trim()),
                        Integer.valueOf(copies.getText().toString().trim()));
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}