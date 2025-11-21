package com.example.roong;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ShopFragment extends Fragment {

    private TextView tvSelectedCategory;
    private Spinner spinnerCategory;
    private RecyclerView rvProducts;
    private ImageView ivFilter;
    private ProductAdapter productAdapter;
    private String[] categories;
    private String currentCategory = "Organic";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);

        // Initialize views
        tvSelectedCategory = view.findViewById(R.id.tv_selected_category);
        spinnerCategory = view.findViewById(R.id.spinner_category);
        rvProducts = view.findViewById(R.id.rv_products);
        ivFilter = view.findViewById(R.id.iv_filter);

        // Setup categories
        categories = new String[]{
                "Organic",
                "Milk Products",
                "Rice",
                "Baby Care",
                "Personal Care",
                "Coffee",
                "House Hold",
                "Tea",
                "Spreads",
                "Condiments",
                "Cooking Essentials",
                "Biscuits",
                "Beverages",
                "Desserts",
                "Noodles",
                "Chocolates",
                "Spices",
                "Cakes"
        };

        setupSpinner();
        setupRecyclerView();
        setupFilterButton();

        // Load initial products
        loadProductsForCategory(currentCategory);

        return view;
    }

    private void setupSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(),
                R.layout.spinner_item,
                categories
        );
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);

        // Set default selection to "Organic"
        spinnerCategory.setSelection(0);

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentCategory = categories[position];
                tvSelectedCategory.setText(currentCategory);
                loadProductsForCategory(currentCategory);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }

    private void setupRecyclerView() {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        rvProducts.setLayoutManager(layoutManager);
    }

    private void setupFilterButton() {
        ivFilter.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Filter options", Toast.LENGTH_SHORT).show();
            // TODO: Open filter dialog or activity
        });
    }

    private void loadProductsForCategory(String category) {
        List<Product> products = getProductsForCategory(category);
        productAdapter = new ProductAdapter(products, getContext(), true);
        rvProducts.setAdapter(productAdapter);
    }

    private List<Product> getProductsForCategory(String category) {
        List<Product> products = new ArrayList<>();

        // Generate sample products based on category
        for (int i = 1; i <= 12; i++) {
            String productName = category + " Product " + i;
            String description = "Quality " + category.toLowerCase() + " product";
            String price = "Rs. " + (250 + (i * 75));
            products.add(new Product(productName, description, price, R.drawable.ic_product));
        }

        return products;
    }
}