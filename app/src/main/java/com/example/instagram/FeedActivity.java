package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class FeedActivity extends AppCompatActivity {
    
    public static final String TAG = "FeedActivity";
    private SwipeRefreshLayout swipeContainer;

    RecyclerView rvPosts;
    List<Post> allPosts;
    PostsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        rvPosts = findViewById(R.id.rvPosts);
        allPosts = new ArrayList<>();
        adapter = new PostsAdapter(this, allPosts);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvPosts.setLayoutManager(linearLayoutManager);
        rvPosts.setAdapter(adapter);
        
        displayPosts();

        // Lookup the swipe container view
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchTimelineAsync(0);
                swipeContainer.setRefreshing(false);
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    private void fetchTimelineAsync(int i) {
        adapter.clear();
        displayPosts();
    }


    private void displayPosts() {
        // Specify the type of data we desire
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        // Include additional data (the user)
        query.include(Post.KEY_USER);
        // Set limit to query
        query.setLimit(20);
        // Order posts by creation data
        query.addDescendingOrder("createdAt");
        // Async call for posts
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue getting posts", e);
                    return;
                }
                // For debugging
                for (Post post : posts) {
                    Log.i(TAG, "Post: " + post.getDescription() + ", username: " + post.getUser().getUsername());
                }
                allPosts.addAll(posts);
                adapter.notifyDataSetChanged();
            }
        });
    }
}