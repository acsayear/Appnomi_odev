package com.example.appnomiodev;

import android.app.Activity;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {
    private Activity activity;
    private ArrayList<ProductItem> productItems;

    public ProductsAdapter(Activity activity, ArrayList<ProductItem> productItems) {
        this.activity = activity;
        this.productItems = productItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductItem productItem = productItems.get(position);
        if (position<1)
            holder.constraintLayout.setPadding(0, 200, 0, 0);
        String id = productItem.id;
        String image = productItem.image;
        String title = productItem.title;
        String desc = productItem.desc;
        int stock = productItem.stock;
        double price = productItem.price;
        double campprice = productItem.campprice;

        Glide.with(activity).load(image).into(holder.imageView);
        holder.textView.setText(title);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if (campprice == -1)
                holder.textView6.setText(Html.fromHtml("<p>Price:<br>" + price + " $</p>", Html.FROM_HTML_MODE_COMPACT));
            else
                holder.textView6.setText(Html.fromHtml("<p>Price:<br><del>" + price + " $</del><br>" + campprice + " $</p>", Html.FROM_HTML_MODE_COMPACT));
        } else {
            if (campprice == -1)
                holder.textView.setText("Price:\n" + price);
            else holder.textView.setText("Discount:\n" + price);
        }

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyCustomDialog myCustomDialog=new MyCustomDialog(activity,holder.imageView.getDrawable(),title,price,campprice,desc,stock);
                myCustomDialog.show(false);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout constraintLayout;
        ImageView imageView;
        TextView textView;
        TextView textView6;
        Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.cls);
            imageView = itemView.findViewById(R.id.imageView2);
            textView = itemView.findViewById(R.id.textView5);
            textView6 = itemView.findViewById(R.id.textView6);
            button = itemView.findViewById(R.id.button2);
        }
    }

}
