package com.example.mini_projet_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {
    private Context mcontext;
    private List<Post> mposts;
    public PostAdapter(Context mcontext , List<Post>mposts){
        this.mcontext=mcontext;
        this.mposts=mposts;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;
        LayoutInflater mInflater = LayoutInflater.from((Context) mcontext);
        view = mInflater.inflate(R.layout.cardpost,parent,false);
        return new PostAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Post post = mposts.get(position);
        holder.titre.setText(String.valueOf(post.getTitre()));
        holder.description.setText(String.valueOf(post.getDescription()));

    }

    @Override
    public int getItemCount() {
        return mposts.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView titre,description;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
           titre=itemView.findViewById(R.id.titre);
           description=itemView.findViewById(R.id.description);



        }
    }
}