package com.example.appnomiodev;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.content.res.AppCompatResources;

import com.example.appnomiodev.databinding.ActivityProductsBinding;
import com.example.appnomiodev.databinding.CustomDialogBinding;

public class MyCustomDialog {
    private final Dialog dialog;
//    private final TextView tv_baslik;
//    private final TextView tv_content;
//    private final ImageView iv_dialog;
//    public Button positive, negative, neutral;

    CustomDialogBinding binding;

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

//        int nightModeFlags = activity.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
//        switch (nightModeFlags) {
//            case Configuration.UI_MODE_NIGHT_YES:
//                dialog.getWindow().setBackgroundDrawable(AppCompatResources.getDrawable(activity, R.drawable.dialog_background_night));
//                break;
//            case Configuration.UI_MODE_NIGHT_NO:
//                dialog.getWindow().setBackgroundDrawable(AppCompatResources.getDrawable(activity, R.drawable.dialog_background));
//                break;
//            case Configuration.UI_MODE_NIGHT_UNDEFINED:
//                break;
//        }
//
//        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//
//        positive = dialog.findViewById(R.id.btn_positive);
//        negative = dialog.findViewById(R.id.btn_negative);
//        neutral = dialog.findViewById(R.id.btn_neutral);
//        tv_baslik = dialog.findViewById(R.id.tv_dialogBaslik);
//        tv_content = dialog.findViewById(R.id.tv_dialogtext1);
//        iv_dialog = dialog.findViewById(R.id.iv_dialog);
    }


//    public void Toast(String info) {
//        tv_content.setText(info);
//        show(false);
//    }
//
//    public void setCaption(String baslik) {
//        tv_baslik.setText(baslik);
//    }
//
//    public void setContent(String yazi) {
//        tv_content.setText(yazi);
//    }
//
//    public void setImage(Bitmap bitmap) {
//        iv_dialog.setVisibility(View.VISIBLE);
//        iv_dialog.setImageBitmap(bitmap);
//    }
//
//    public void setButtons(String positiveString) {
//        positive.setText(positiveString);
//        positive.setVisibility(View.VISIBLE);
//    }
//
//    public void setButtons(String positiveString, String negativeString) {
//        setButtons(positiveString);
//        negative.setText(negativeString);
//        negative.setVisibility(View.VISIBLE);
//    }
//
//    public void setButtons(String positiveString, String negativeString, String neutralString) {
//        setButtons(positiveString, negativeString);
//        neutral.setText(neutralString);
//        neutral.setVisibility(View.VISIBLE);
//    }

    public void show(boolean locked) {
        dialog.setCancelable(!locked);
        dialog.show();
    }

    public void dissmiss() {
        dialog.dismiss();
    }
}
