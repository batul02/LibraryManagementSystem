package com.example.librarymanagementsystem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ReadDataActivity extends AppCompatActivity {

    TextView book_id3, book_name3, author_name3, p_year3, p_house3, dept3, isbn_no3, copies3;
    Button back;

    String id, name, author, year, house, deptm, isbn, no_copies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_data);

        book_id3 = findViewById(R.id.book_id3);
        book_name3 = findViewById(R.id.book_name3);
        author_name3 = findViewById(R.id.author_name3);
        p_year3 = findViewById(R.id.p_year3);
        p_house3 = findViewById(R.id.p_house3);
        dept3 = findViewById(R.id.dept3);
        isbn_no3 = findViewById(R.id.isbn_no3);
        copies3 = findViewById(R.id.no_copies3);
        back = findViewById(R.id.back);

        getAndSetIntentData();
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") &&
                getIntent().hasExtra("name") &&
                getIntent().hasExtra("author") &&
                getIntent().hasExtra("year") &&
                getIntent().hasExtra("house") &&
                getIntent().hasExtra("dept") &&
                getIntent().hasExtra("isbn") &&
                getIntent().hasExtra("copies")){
            //getting data from intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            author = getIntent().getStringExtra("author");
            year = getIntent().getStringExtra("year");
            house = getIntent().getStringExtra("house");
            deptm = getIntent().getStringExtra("dept");
            isbn = getIntent().getStringExtra("isbn");
            no_copies = getIntent().getStringExtra("copies");

            //setting data from intent
            book_id3.setText(id);
            book_name3.setText(name);
            author_name3.setText(author);
            p_year3.setText(year);
            p_house3.setText(house);
            dept3.setText(deptm);
            isbn_no3.setText(isbn);
            copies3.setText(no_copies);
        }else{
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        }
    }
}