package com.example.bertha.Activities

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.TextView

import com.example.bertha.R

class UserSettings : AppCompatActivity() {
    private var prefs: SharedPreferences? = null

    //View
    private var userNameTv: TextView? = null
    private var userEmailTv: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_settings)

        userNameTv = findViewById(R.id.userSettingsUsername)
        userEmailTv = findViewById(R.id.userSettingsEmail)

        //Preferences
        prefs = getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE)

        val usernamePrefs = prefs!!.getString(USER_NAME, null)
        val passwordPrefs = prefs!!.getString(PASSWORD, null)

        if (usernamePrefs != null) {
            userNameTv!!.text = usernamePrefs
            userEmailTv!!.text = "PatrickOerum@icloud.com"
        } else {
            userNameTv!!.text = "Du har ikke gemt dit brugernavn"
        }
    }

    companion object {

        //Preferences
        val PREF_FILE_NAME = "pref_file_name"
        val USER_NAME = "user_name"
        val PASSWORD = "password"
    }
}
