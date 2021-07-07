package com.example.instagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    // Initializes Parse SDK as soon as the application is created
    @Override
    public void onCreate() {
        super.onCreate();

        // Register your parse models
        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("VBtYy3TBA8vrFmb9nexBO2pExZRx3zgfa973kMyk")
                .clientKey("TvOpfZH6Juz7s15wNjUxkRp3uWHJ4Ez6IuD3oKrm")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}

