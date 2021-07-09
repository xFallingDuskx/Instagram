package com.example.instagram;

import android.text.format.DateUtils;
import android.util.Log;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@ParseClassName("User")
public class User extends ParseObject {
    // Ensure that your subclass has a public default constructor

    public static final String KEY_FULLNAME = "fullName";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_CREATED_AT = "createdAt";
    public static final String KEY_EMAIL = "email";

    // Assign a getter and setter method for each key value
    public String getFullname() {
        return getString(KEY_FULLNAME);
    }

    public String getUsername() {return getString(KEY_USERNAME);}

    public Date getCreatedAt() {return getDate(KEY_CREATED_AT);}

    public String getEmail() {return getString(KEY_EMAIL);}

    public String getTime() {
        Date createdAt = getCreatedAt();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(createdAt);
        return strDate;
    }
}
