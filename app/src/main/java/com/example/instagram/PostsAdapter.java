package com.example.instagram;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder>{

    public static final String TAG = "PostsAdapter";

    Context context;
    List<Post> posts;

    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    // Inflate the proper xml document
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    // Bind values based on the position of the element in the list
    public void onBindViewHolder(@NonNull PostsAdapter.ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvUsername;
        ImageView ivPostImage;
        TextView tvDescription;
        TextView tvTimestamp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            ivPostImage = itemView.findViewById(R.id.ivPostImage_Post);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvTimestamp = itemView.findViewById(R.id.tvTimestamp);
        }

        public void bind(Post post) {
            tvUsername.setText(post.getUser().getUsername());
            tvDescription.setText(post.getDescription());
            tvTimestamp.setText(post.getTime());
            // For images specifically
            ParseFile image = post.getImage();
            if (image != null) {
                Glide.with(context)
                        .load(image.getUrl())
                        .centerInside()
                        .into(ivPostImage);
            }
        }
    }

// SwipeRefreshLayout
    // Clean all elements of the recycler
    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Post> post) {
        posts.addAll(post);
        notifyDataSetChanged();
    }
}
