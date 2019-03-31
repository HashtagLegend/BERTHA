package com.example.bertha.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bertha.Model.User;
import com.example.bertha.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class RegisterUserActivity extends AppCompatActivity {



    //Firebase
    FirebaseDatabase database;
    DatabaseReference users;


    Button btnRegisterUser, btnGoBackToLogin;
    EditText registerUsername, registerPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);



        //Firebase
        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");

        registerUsername = (EditText) findViewById(R.id.registerUserName);
        registerPassword = (EditText) findViewById(R.id.registerPassword);

        btnRegisterUser = (Button) findViewById(R.id.btnRegisterUser);

        btnRegisterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final User user = new User(registerUsername.getText().toString(), registerPassword.getText().toString());


                //Toast.makeText(RegisterUserActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                Log.d("MINE", "Username: " + user.getUsernname().toString() + ", Password: " + user.getPassword().toString());
                users.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if(dataSnapshot.child(user.getUsernname()).exists())
                            Toast.makeText(RegisterUserActivity.this, "Username already exists", Toast.LENGTH_SHORT).show();

                        else{

                            users.child(user.getUsernname()).setValue(user);
                            Toast.makeText(RegisterUserActivity.this, "User created successfully", Toast.LENGTH_SHORT).show();
                            Log.d("MINE", "onDataChange:good " + user);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        //Navigation
        btnGoBackToLogin = findViewById(R.id.btnCancelGoBackToLogin);
        btnGoBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
