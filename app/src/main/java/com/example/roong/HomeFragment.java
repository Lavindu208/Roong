package com.example.roong;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView categoriesRecyclerView;
    private RecyclerView featuredRecyclerView;
    private RecyclerView organicRecyclerView;
    private CategoryAdapter categoryAdapter;
    private ProductAdapter featuredAdapter;
    private ProductAdapter organicAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        categoriesRecyclerView = view.findViewById(R.id.categories_recycler);
        featuredRecyclerView = view.findViewById(R.id.featured_recycler);
        organicRecyclerView = view.findViewById(R.id.organic_recycler);

        setupCategoriesRecycler();
        setupFeaturedRecycler();
        setupOrganicRecycler();

        return view;
    }

    private void setupCategoriesRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        categoriesRecyclerView.setLayoutManager(layoutManager);

        List<Category> categories = getCategoriesList();
        categoryAdapter = new CategoryAdapter(categories, getContext());
        categoriesRecyclerView.setAdapter(categoryAdapter);
    }

    private void setupFeaturedRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        featuredRecyclerView.setLayoutManager(layoutManager);

        List<Product> products = getFeaturedProducts();
        featuredAdapter = new ProductAdapter(products, getContext(), false);
        featuredRecyclerView.setAdapter(featuredAdapter);
    }

    private void setupOrganicRecycler() {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        organicRecyclerView.setLayoutManager(layoutManager);

        List<Product> products = getOrganicProducts();
        organicAdapter = new ProductAdapter(products, getContext(), true);
        organicRecyclerView.setAdapter(organicAdapter);
    }

    private List<Category> getCategoriesList() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Baby Care", R.drawable.ic_category));
        categories.add(new Category("Beverages", R.drawable.ic_category));
        categories.add(new Category("Biscuits", R.drawable.ic_category));
        categories.add(new Category("Cakes", R.drawable.ic_category));
        categories.add(new Category("Chocolate", R.drawable.ic_category));
        categories.add(new Category("Coffee", R.drawable.ic_category));
        categories.add(new Category("Condiments", R.drawable.ic_category));
        categories.add(new Category("Cooking Essentials", R.drawable.ic_category));
        categories.add(new Category("Dessert", R.drawable.ic_category));
        categories.add(new Category("Food", R.drawable.ic_category));
        categories.add(new Category("Fresh Vegetables", R.drawable.ic_category));
        return categories;
    }

    private List<Product> getFeaturedProducts() {
        List<Product> products = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            products.add(new Product("Featured Product " + i, "Description of product " + i, "Rs. " + (500 + i * 100), R.drawable.ic_product));
        }
        return products;
    }

    private List<Product> getOrganicProducts() {
        List<Product> products = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            products.add(new Product("Organic Product " + i, "Fresh organic product " + i, "Rs. " + (300 + i * 50), R.drawable.ic_product));
        }
        return products;
    }
}