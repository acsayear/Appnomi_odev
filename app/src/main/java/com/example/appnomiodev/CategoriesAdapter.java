package com.example.appnomiodev;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {
    private Activity activity;
    private ArrayList<CategorieItem> categorieItems;

    public CategoriesAdapter(Activity activity, ArrayList<CategorieItem> categorieItems) {
        this.activity = activity;
        this.categorieItems = categorieItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categorie_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategorieItem categorieItem = categorieItems.get(position);
        String id = categorieItem.categoryId;
        String name = categorieItem.name;
        String icon = categorieItem.icon;

        holder.textView.setText(name);
        Glide.with(activity).load(icon).into(holder.imageView2);

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, ProductsActivity.class);
                i.putExtra("category_id",id);
                activity.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categorieItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView2;
        Button button;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView2 = itemView.findViewById(R.id.imageView2);
            button = itemView.findViewById(R.id.button);
            textView = itemView.findViewById(R.id.textView7);
        }
    }

}
