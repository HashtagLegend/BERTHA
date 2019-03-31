package com.example.bertha.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bertha.Model.User;
import com.example.bertha.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ForgotPasswordActivity extends AppCompatActivity {

    //Firebase
    FirebaseDatabase database;
    DatabaseReference users;

    EditText username;
    TextView showPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        username = findViewById(R.id.forgotPasswordUserName);
        showPassword = findViewById(R.id.myPassword);

        //Firebase
        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");

    }

    private void signIn(String username) {
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("MINE", "onDataChange: got in");
                if(dataSnapshot.child(username).exists()){
                    if(!username.isEmpty()){
                        User login = dataSnapshot.child(username).getValue(User.class);

                        String pwd = login.getPassword();
                        showPassword.setText("Dit password er: " + pwd);
                        showPassword.setVisibility(View.VISIBLE);
                        Log.d("MINE", "get passsword: " + pwd);
                    }
                    else{
                        Toast.makeText(ForgotPasswordActivity.this, "There is no user registered with that name!", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void getPassword(View view) {
        signIn(username.getText().toString());
    }

    public void goBackToLogin(View view) {
        finish();
    }
}
