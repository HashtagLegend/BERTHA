package com.example.bertha.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bertha.Model.User;
import com.example.bertha.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    //Preferences
    public static final String PREF_FILE_NAME = "pref_file_name";
    public static final String USER_NAME = "user_name";
    public static final String PASSWORD = "password";

    private CheckBox checkBox;
    private SharedPreferences prefs;

    //Firebase
    FirebaseDatabase database;
    DatabaseReference users;

    Button btnLogin, btnGoToRegisterUser;
    EditText sndUsername, sndPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnGoToRegisterUser = (Button) findViewById(R.id.btnGoToRegisterUser);

        btnGoToRegisterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterUserActivity.class);
                startActivity(intent);
            }
        });

        sndUsername = (EditText) findViewById(R.id.sendUserName);
        sndPassword = (EditText) findViewById(R.id.sendPassword);

        //Preferences
        prefs = getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE);

        checkBox = (CheckBox)findViewById(R.id.checkBox);
        String usernamePrefs = prefs.getString(USER_NAME,null);
        String passwordPrefs = prefs.getString(PASSWORD,null);

        if(usernamePrefs != null && passwordPrefs != null){
            sndUsername.setText(usernamePrefs);
            sndPassword.setText(passwordPrefs);
            checkBox.setChecked(true);
        }


        //Firebase
        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");



        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn(sndUsername.getText().toString(), sndPassword.getText().toString());
                Log.d("MINE", "onClick: Clicked");
            }
        });
    }

    private void signIn(final String username, final String password) {
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("MINE", "onDataChange: got in");
                if(dataSnapshot.child(username).exists()){
                    if(!username.isEmpty()){
                        User login = dataSnapshot.child(username).getValue(User.class);
                        if(login.getPassword().equals(password)){
                            Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_LONG).show();
                            navigateOnLogin();
                            savePrefs(username, password);
                            Log.d("MINE", "login succes");
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Password is incorrect!", Toast.LENGTH_LONG).show();
                        }
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "There is no user registered with that name!", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

            public void navigateOnLogin(){
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                Log.d("MINE", "navigateOnLogin: Logged in");
            }
        });
    }

    //Preferences
    public void savePrefs(String username,String password){
        SharedPreferences.Editor editor = prefs.edit();
        if(checkBox.isChecked()){
            editor.putString(USER_NAME,username);
            editor.putString(PASSWORD,password);
        }

        else{
            editor.remove(USER_NAME);
            editor.remove(PASSWORD);
        }

        editor.apply();
    }


}
