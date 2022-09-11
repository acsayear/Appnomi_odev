package com.example.appnomiodev;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.appnomiodev.databinding.ActivityCategoriesBinding;

import java.io.IOException;
import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity {

    private ActivityCategoriesBinding binding;
    private ArrayList<ArrayList> arrayListArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoriesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        load_categories();
    }

    private void show_categories() throws IOException {
        binding.imageView.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), R.anim.fadeout));

        ArrayList<CategorieItem> categorieItems =new ArrayList<>();

        for (ArrayList array: arrayListArrayList
             ) {
            String id = array.get(0).toString();
            String name = array.get(1).toString();
            String icon_address = array.get(2).toString();

            CategorieItem categorieItem =new CategorieItem(id,name,icon_address);
            categorieItems.add(categorieItem);

            Log.e("test","test: "+name);
        }

        CategoriesAdapter myAdapter = new CategoriesAdapter(this, categorieItems);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        binding.reci.setLayoutManager(layoutManager);
        binding.reci.setAdapter(myAdapter);
    }

    private void load_categories() {
        JSONWorkbench jsonWorkbench = new JSONWorkbench(getBaseContext());
        arrayListArrayList = jsonWorkbench.GET();

        new CountDownTimer(10000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                if (jsonWorkbench.finishStatus) {
                    cancel();
                    onFinish();
                }
            }

            @Override
            public void onFinish() {
                if (jsonWorkbench.finishStatus) {

                    try {
                        show_categories();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.e("hata",""+e);
                    }

                } else {
                    Toast.makeText(CategoriesActivity.this, "Please check your internet connection.", Toast.LENGTH_SHORT).show();
                    arrayListArrayList = jsonWorkbench.GET();
                    start();
                }
            }
        }.start();
    }
}