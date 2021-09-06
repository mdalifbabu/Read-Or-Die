package com.example.readordie4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.readordie4.Adapter.CategoryListAdapter;
import com.example.readordie4.Common.Common;
import com.example.readordie4.Interface.ItemClickListener;
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

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    TextView txtFullName;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    private DatabaseReference myRef;
    private ArrayList<Category> categoryList;
    private CategoryListAdapter categoryListAdapter;
    private Context mContext;
    private ItemClickListener itemClickListener;
    String CategoryId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Menu");
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cartIntent = new Intent(Home.this, Cart.class);
                startActivity(cartIntent);
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, 0,0);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ///Set Name for user
        View headerView = navigationView.getHeaderView(0);
        txtFullName = headerView.findViewById(R.id.txt_full_name);
        txtFullName.setText(Common.currentUser.getName());

        ///Load Menu
        recyclerView = findViewById(R.id.recycler_menu);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        myRef = FirebaseDatabase.getInstance().getReference();

        categoryList = new ArrayList<>();
        ClearAll();

        GetDataFromFirebase();
    }

    private void GetDataFromFirebase() {
        Query query = myRef.child("Category");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ClearAll();

                for (DataSnapshot snapsho1: snapshot.getChildren()){
                    Category category = new Category();
                    category.setKey(snapsho1.getKey());
                    category.setImage(snapsho1.child("Image").getValue().toString());
                    category.setName(snapsho1.child("Name").getValue().toString());

                    categoryList.add(category);
                }

                setOnClickListener();
                categoryListAdapter = new CategoryListAdapter(getApplicationContext(), categoryList, itemClickListener);
                recyclerView.setAdapter(categoryListAdapter);
                categoryListAdapter.notifyDataSetChanged();
            }

            private void setOnClickListener() {
                itemClickListener = new ItemClickListener() {
                    @Override
                    public void onClick(View v, int adapterPosition) {
                        Intent bookList = new Intent(Home.this, BookList.class);
                        bookList.putExtra("CategoryId", categoryList.get(adapterPosition).getKey());
                        bookList.putExtra("Name", categoryList.get(adapterPosition).getName());
                        startActivity(bookList);

                        //Toast.makeText(Home.this, "" + categoryList.get(adapterPosition).getName(), Toast.LENGTH_SHORT).show();
                    }
                };
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void ClearAll(){
        if(categoryList != null){
            categoryList.clear();

            if(categoryListAdapter != null){
                categoryListAdapter.notifyDataSetChanged();
            }
        }
        categoryList = new ArrayList<>();
    }

    @Override
    public void onBackPressed(){
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StetementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.nav_menu){

        }else if(id == R.id.nav_cart){

        }else if(id == R.id.nav_orders){

        }else if(id == R.id.nav_log_out){

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}