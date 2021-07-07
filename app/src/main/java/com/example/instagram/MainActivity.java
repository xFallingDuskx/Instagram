package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private ImageView ivLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivLogout = findViewById(R.id.ivLogout);

        ivLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut(); // Log user out
                ParseUser currentUser = ParseUser.getCurrentUser(); // User is now null
                finish(); // navigate backwards to Login screen
                Toast.makeText(getApplicationContext(), "You've been logged out", Toast.LENGTH_SHORT).show();
            }
        });

        // Can get posts back from our database
        queryPost();
    }

    private void queryPost() {
        // Specify the class we are querying for
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        // To include the username in our query results
        query.include(Post.KEY_USER);
        // To retrieve all post
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting posts", e);
                }
                for (Post post : posts) {
                    Log.i(TAG, "Post: " + post.getDescription() + ", username: " + post.getUser().getUsername());
                }
            }
        });
    }
}