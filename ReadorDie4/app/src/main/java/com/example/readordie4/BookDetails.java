package com.example.readordie4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.readordie4.Database.Database;
import com.example.readordie4.Model.Book;
import com.example.readordie4.Model.Order;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class BookDetails extends AppCompatActivity {

    TextView book_detail_name, book_price, book_description;
    ImageView book_detail_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;
    EditText quantityNumber;


    String bookId = "", bookName = "";

    DatabaseReference myRef;

    Book currentBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        myRef = FirebaseDatabase.getInstance().getReference("Books");

        quantityNumber = findViewById(R.id.quantity_number);
        btnCart = findViewById(R.id.btnCart);

        book_detail_name = findViewById(R.id.book_details_name);
        book_price = findViewById(R.id.book_price);
        book_description = findViewById(R.id.book_description);
        book_detail_image = findViewById(R.id.book_details_image);


        collapsingToolbarLayout = findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);

        if(getIntent() != null){
            bookId = getIntent().getStringExtra("BookId");
            bookName = getIntent().getStringExtra("BookName");
            setTitle(""+bookName);
            if(!bookId.isEmpty() && bookId != null){
                getDetailBook(bookId);
            }
        }

        if(bookId != null){
            btnCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new Database(getBaseContext()).addToCart(new Order(
                            bookId,
                            currentBook.getName(),
                            quantityNumber.getText().toString(),
                            currentBook.getBorrowRate(),
                            currentBook.getDiscount()



                    ));

                    Toast.makeText(BookDetails.this, "Added to Cart", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void getDetailBook(String bookId) {
        myRef.child(bookId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                currentBook = snapshot.getValue(Book.class);

                Glide.with(getBaseContext()).load(currentBook.getImage()).into(book_detail_image);

                collapsingToolbarLayout.setTitle(currentBook.getName());

                book_price.setText(currentBook.getBorrowRate());
                book_detail_name.setText(currentBook.getName());
                book_description.setText(currentBook.getDescription());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}