package com.example.readordie4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.readordie4.Common.Common;
import com.example.readordie4.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignUp extends AppCompatActivity {

    MaterialEditText edtPhone, edtName, edtPassword;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setTitle("Sign Up");

        edtName = findViewById(R.id.edt_name);
        edtPhone = findViewById(R.id.edt_phone);
        edtPassword = findViewById(R.id.edt_password);

        btnSignUp = findViewById(R.id.btn_sign_up);

        //Init Firebase
        final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = firebaseDatabase.getReference("User");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog progressDialog = new ProgressDialog(SignUp.this);
                progressDialog.setMessage("Please waiting....");
                progressDialog.show();

                databaseReference.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        ///Check Exists or Not
                        if(edtPhone.getText().toString().length() == 11 && edtName.getText().toString().length() > 3 && edtPassword.getText().toString().length() > 7){
                            if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {
                                //Get User Information
                                progressDialog.dismiss();
                                Toast.makeText(SignUp.this, "Already Register !!!", Toast.LENGTH_SHORT).show();
                                Intent loginIntent = new Intent(SignUp.this, SignIn.class);
                                startActivity(loginIntent);
                                finish();
                            } else{
                                progressDialog.dismiss();
                                User user = new User(edtName.getText().toString(), edtPassword.getText().toString(), null);
                                databaseReference.child(edtPhone.getText().toString()).setValue(user);
                                Toast.makeText(SignUp.this, "Successfully Registered !!!", Toast.LENGTH_SHORT).show();
                                //Login Suggested
                                Intent loginIntent = new Intent(SignUp.this, SignIn.class);
                                startActivity(loginIntent);
                                finish();
                            }
                        }else{
                            if(edtPhone.getText().toString().length() < 11){
                                Toast.makeText(SignUp.this, "Valid Phone Number Required !!!", Toast.LENGTH_SHORT).show();
                            }else if(edtName.getText().toString().length() <= 3){
                                Toast.makeText(SignUp.this, "Name Required !!!", Toast.LENGTH_SHORT).show();
                            }else if(edtPassword.getText().toString().length() <= 7){
                                Toast.makeText(SignUp.this, "Valid Password Required !!!", Toast.LENGTH_SHORT).show();
                            }
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}