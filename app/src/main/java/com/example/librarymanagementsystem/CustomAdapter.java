package com.example.librarymanagementsystem;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList book_id, book_name, author_name, p_year, p_house, dept, isbn_no, copies;
    String name, id;

    CustomAdapter(Activity activity, Context context,ArrayList book_id, ArrayList book_name,
                  ArrayList author_name,
                  ArrayList p_year,
                  ArrayList p_house,
                  ArrayList dept,
                  ArrayList isbn_no,
                  ArrayList copies){
        this.activity = activity;
        this.context = context;
        this.book_id = book_id;
        this.book_name = book_name;
        this.author_name = author_name;
        this.p_year = p_year;
        this.p_house = p_house;
        this.dept = dept;
        this.isbn_no = isbn_no;
        this.copies = copies;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.book_id_txt.setText(String.valueOf(book_id.get(position)));
        holder.book_name_txt.setText(String.valueOf(book_name.get(position)));
        holder.author_name_txt.setText(String.valueOf(author_name.get(position)));
        holder.p_year_txt.setText(String.valueOf(p_year.get(position)));
        holder.p_house_txt.setText(String.valueOf(p_house.get(position)));
        holder.dept_txt.setText(String.valueOf(dept.get(position)));
        holder.isbn_no_txt.setText(String.valueOf(isbn_no.get(position)));
        holder.copies_txt.setText(String.valueOf(copies.get(position)));
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(book_id.get(position)));
                intent.putExtra("name", String.valueOf(book_name.get(position)));
                intent.putExtra("author", String.valueOf(author_name.get(position)));
                intent.putExtra("year", String.valueOf(p_year.get(position)));
                intent.putExtra("house", String.valueOf(p_house.get(position)));
                intent.putExtra("dept", String.valueOf(dept.get(position)));
                intent.putExtra("isbn", String.valueOf(isbn_no.get(position)));
                intent.putExtra("copies", String.valueOf(copies.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });

        holder.delOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = String.valueOf(book_name.get(position)).trim();
                id = String.valueOf(book_id.get(position)).trim();
                confirmDialog();
            }
        });

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReadDataActivity.class);
                intent.putExtra("id", String.valueOf(book_id.get(position)));
                intent.putExtra("name", String.valueOf(book_name.get(position)));
                intent.putExtra("author", String.valueOf(author_name.get(position)));
                intent.putExtra("year", String.valueOf(p_year.get(position)));
                intent.putExtra("house", String.valueOf(p_house.get(position)));
                intent.putExtra("dept", String.valueOf(dept.get(position)));
                intent.putExtra("isbn", String.valueOf(isbn_no.get(position)));
                intent.putExtra("copies", String.valueOf(copies.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return book_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView book_id_txt, book_name_txt, author_name_txt, p_year_txt, p_house_txt, dept_txt, isbn_no_txt, copies_txt;
        LinearLayout mainLayout;
        ImageView delOne, edit;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            book_id_txt = itemView.findViewById(R.id.book_id);
            book_name_txt = itemView.findViewById(R.id.book_name);
            author_name_txt = itemView.findViewById(R.id.author_name);
            p_year_txt = itemView.findViewById(R.id.p_year);
            p_house_txt = itemView.findViewById(R.id.p_house);
            dept_txt = itemView.findViewById(R.id.dept);
            isbn_no_txt = itemView.findViewById(R.id.isbn_no);
            copies_txt = itemView.findViewById(R.id.no_copies);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            edit = itemView.findViewById(R.id.edit);
            delOne = itemView.findViewById(R.id.delOne);

            edit.setClickable(true);
            delOne.setClickable(true);
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(context);
                myDB.deleteOneRow(id);
                Intent intent = new Intent(context, MainActivity.class);
                activity.startActivityForResult(intent, 1);
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
