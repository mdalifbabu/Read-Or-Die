package com.example.readordie4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.readordie4.Adapter.BookListAdapter;
import com.example.readordie4.Adapter.CategoryListAdapter;
import com.example.readordie4.Common.Common;
import com.example.readordie4.Interface.ItemClickListener;
import com.example.readordie4.Model.Book;
import com.example.readordie4.Model.Category;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookList extends AppCompatActivity{

    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    private DatabaseReference myRef;
    private ArrayList<Book> bookList;
    private BookListAdapter bookListAdapter;
    private Context mContext;
    ItemClickListener itemClickListener;
    private Category category;

    String categoryId = "", Name = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);



        FloatingActionButton fab = findViewById(R.id.fab_book);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ///Load Menu
        recyclerView = findViewById(R.id.recycler_book);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        myRef = FirebaseDatabase.getInstance().getReference();

        bookList = new ArrayList<>();
        ClearAll();


       if(getIntent() != null){
            categoryId = getIntent().getStringExtra("CategoryId");
            Name = getIntent().getStringExtra("Name");
            setTitle(Name + " Books");
            if(!categoryId.isEmpty() && categoryId != null){
                GetDataFromFirebase(categoryId);

            }
        }

        //GetDataFromFirebase();
    }

    private void GetDataFromFirebase(String categoryId) {
        Query query = myRef.child("Books");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //ClearAll();

                for (DataSnapshot snapsho1: snapshot.getChildren()){
                    if(snapsho1.child("MenuId").getValue().toString().equals(categoryId)){
                        Book book = new Book();
                        book.setKey(snapsho1.getKey());
                        book.setImage(snapsho1.child("Image").getValue().toString());
                        book.setName(snapsho1.child("Name").getValue().toString());
                        bookList.add(book);
                    }
                }

                setOnClickListener();
                bookListAdapter = new BookListAdapter(getApplicationContext(), bookList, itemClickListener);
                recyclerView.setAdapter(bookListAdapter);
                bookListAdapter.notifyDataSetChanged();
            }

            private void setOnClickListener() {
                itemClickListener = new ItemClickListener() {
                    @Override
                    public void onClick(View v, int adapterPosition) {
                        Intent bookDetails = new Intent(BookList.this, BookDetails.class);
                        bookDetails.putExtra("BookId", bookList.get(adapterPosition).getKey());
                        bookDetails.putExtra("BookName", bookList.get(adapterPosition).getName());
                        startActivity(bookDetails);

                        //Toast.makeText(BookList.this, "" + bookList.get(adapterPosition).getName(), Toast.LENGTH_SHORT).show();
                    }
                };
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void ClearAll(){
        if(bookList != null){
            bookList.clear();

            if(bookListAdapter != null){
                bookListAdapter.notifyDataSetChanged();
            }
        }
        bookList = new ArrayList<>();
    }
}