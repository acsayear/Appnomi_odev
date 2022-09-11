package com.example.appnomiodev;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.appnomiodev.databinding.ActivityProductsBinding;

import java.util.ArrayList;

public class ProductsActivity extends AppCompatActivity {

    public String category_id;
    private ActivityProductsBinding binding;
    private String sorted_by;
    private ArrayList<ProductItem> productItems;
    private boolean timer = false;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Bundle bundle = getIntent().getExtras();
        category_id = bundle.getString("category_id");

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.sortby, R.layout.customspinner);
        adapter.setDropDownViewResource(R.layout.customspinneritems);
        binding.spinner.setAdapter(adapter);

        binding.textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sorted_by = parent.getItemAtPosition(position).toString();
                load_products(category_id, sorted_by);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void load_products(String category_id, String sorted_by) {
        binding.progressBar.setVisibility(View.VISIBLE);
        JSONWorkbench jsonWorkbench = new JSONWorkbench(getBaseContext());
        productItems = jsonWorkbench.GETPRODUCTS(category_id, sorted_by);
        if (timer)
            countDownTimer.cancel();
        countDownTimer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer=true;
                if (jsonWorkbench.finishStatus) {
                    cancel();
                    onFinish();
                }
            }

            @Override
            public void onFinish() {
                timer=false;
                if (jsonWorkbench.finishStatus) {
                    binding.progressBar.setVisibility(View.GONE);
                    ProductsAdapter myAdapter = new ProductsAdapter(ProductsActivity.this, productItems);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
                    binding.recyclerView.setLayoutManager(layoutManager);
                    binding.recyclerView.setAdapter(myAdapter);
                } else {
                    Toast.makeText(ProductsActivity.this, "Please check your internet connection.", Toast.LENGTH_SHORT).show();
                    productItems = jsonWorkbench.GETPRODUCTS(category_id, sorted_by);
                    start();
                }
            }
        }.start();
    }
}