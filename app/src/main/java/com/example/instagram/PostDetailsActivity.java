package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import org.parceler.Parcels;

public class PostDetailsActivity extends AppCompatActivity {

    public static final String TAG = "PostDetailsActivity";

    Post post;
    TextView tvUsername;
    ImageView ivPostImage;
    TextView tvDescription;
    TextView tvTimestamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        tvUsername = findViewById(R.id.tvUsername_Details);
        ivPostImage = findViewById(R.id.ivPostImage_Post_Details);
        tvDescription = findViewById(R.id.tvDescription_Details);
        tvTimestamp = findViewById(R.id.tvTimestamp_Details);

        post = Parcels.unwrap(getIntent().getParcelableExtra(Post.class.getSimpleName()));
        Log.i(TAG, "Post details have been wrapped by Parcel");

        tvUsername.setText(post.getUser().getUsername());
        tvDescription.setText(post.getDescription());
        tvTimestamp.setText(post.getTime());
        // For images specifically
        ParseFile image = post.getImage();
        if (image != null) {
            Glide.with(this)
                    .load(image.getUrl())
                    .centerInside()
                    .into(ivPostImage);
        }
    }
}