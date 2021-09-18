package com.example.librarymanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity {

    EditText book_name1, author_name1, p_year1, p_house1, dept1, isbn_no1, copies1;
    Button save_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        book_name1 = findViewById(R.id.book_name1);
        author_name1 = findViewById(R.id.author_name1);
        p_year1 = findViewById(R.id.p_year1);
        p_house1 = findViewById(R.id.p_house1);
        dept1 = findViewById(R.id.dept1);
        isbn_no1 = findViewById(R.id.isbn_no1);
        copies1 = findViewById(R.id.no_copies1);
        save_btn = findViewById(R.id.save_button);

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addBook(book_name1.getText().toString().trim(),
                        author_name1.getText().toString().trim(),
                        Integer.valueOf(p_year1.getText().toString().trim()),
                        p_house1.getText().toString().trim(),
                        dept1.getText().toString().trim(),
                        Integer.valueOf(isbn_no1.getText().toString().trim()),
                        Integer.valueOf(copies1.getText().toString().trim()));
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}