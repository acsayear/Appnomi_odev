package com.example.appnomiodev;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.content.res.AppCompatResources;

import com.example.appnomiodev.databinding.CustomDialogBinding;

public class MyCustomDialog {
    private Dialog dialog;
    private CustomDialogBinding binding;

    public MyCustomDialog(Activity activity, Drawable icon,String title,double price,double campPrice,String desc,int stock) {
        dialog = new Dialog(activity);
        binding = CustomDialogBinding.inflate(activity.getLayoutInflater());
        View view = binding.getRoot();
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawable(AppCompatResources.getDrawable(activity, R.drawable.dialog_background));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        binding.imageView4.setImageDrawable(icon);
        binding.textView8.setText(title);

        if(stock==0)
        {
            binding.imageView3.setVisibility(View.VISIBLE);
            binding.button3.setText("Stokta kalmadı");
            binding.button3.setEnabled(false);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if (campPrice == -1)
                binding.textView9.setText(Html.fromHtml("<p>Price:<br>" + price + " $</p>", Html.FROM_HTML_MODE_COMPACT));
            else
                binding.textView9.setText(Html.fromHtml("<p>Price:<br><del>" + price + " $</del><br>" + campPrice + " $</p>", Html.FROM_HTML_MODE_COMPACT));
        } else {
            if (campPrice == -1)
                binding.textView9.setText("Price:\n" + price);
            else binding.textView9.setText("Discount:\n" + price);
        }

        binding.textView10.setText(Html.fromHtml(desc));
    }

    public void show(boolean locked) {
        dialog.setCancelable(!locked);
        dialog.show();
    }

    public void dissmiss() {
        dialog.dismiss();
    }
}
