package com.example.readordie4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.readordie4.Adapter.BookListAdapter;
import com.example.readordie4.Adapter.CartAdapter;
import com.example.readordie4.Database.Database;
import com.example.readordie4.Interface.ItemClickListener;
import com.example.readordie4.Model.Book;
import com.example.readordie4.Model.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import info.hoang8f.widget.FButton;

public class Cart extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    DatabaseReference myRef;

    TextView textTotalPrice;
    FButton btnPlace;

    List<Order> cart = new ArrayList<>();
    CartAdapter cartAdapter;
    ItemClickListener itemClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        myRef = FirebaseDatabase.getInstance().getReference("Requests");

        recyclerView = findViewById(R.id.listCart);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        textTotalPrice = findViewById(R.id.total);
        btnPlace = findViewById(R.id.btn_place_order);

        cart = new ArrayList<>();
        ClearAll();

        //GetDataFromFirebase();
    }

    private void GetDataFromFirebase() {

        cart = new Database(getApplicationContext()).getCarts();
        cartAdapter = new CartAdapter(getApplicationContext(), cart);
        recyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

        int total = 0;

        if(cart != null){
            for(Order order: cart){
                total += (Integer.parseInt(order.getPrice())) * (Integer.parseInt(order.getQuantity()));
            }
        }
        Locale locale = new Locale("en", "US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

        textTotalPrice.setText(fmt.format(total));

        /*Query query = myRef.child("Books");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ClearAll();

                for (DataSnapshot snapsho1: snapshot.getChildren()){
                    Order order = new Order();

                    cart.add(order);
                }

                setOnClickListener();
                cartAdapter = new CartAdapter(getApplicationContext(), cart, itemClickListener);
                recyclerView.setAdapter(cartAdapter);
                cartAdapter.notifyDataSetChanged();
            }

            private void setOnClickListener() {
                itemClickListener = new ItemClickListener() {
                    @Override
                    public void onClick(View v, int adapterPosition) {


                        //Toast.makeText(BookList.this, "" + bookList.get(adapterPosition).getName(), Toast.LENGTH_SHORT).show();
                    }
                };
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/


    }

    private void ClearAll(){
        if(cart != null){
            cart.clear();

            if(cartAdapter != null){
                cartAdapter.notifyDataSetChanged();
            }
        }
        cart = new ArrayList<>();
    }
}