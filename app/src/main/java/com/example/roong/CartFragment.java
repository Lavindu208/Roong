package com.example.roong;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment implements CartAdapter.OnCartChangeListener {

    private RecyclerView rvCartItems;
    private TextView tvSubtotal;
    private TextView tvTotal;
    private Button btnCheckout;
    private LinearLayout emptyCartLayout;
    private LinearLayout cartSummaryContainer;
    private CartAdapter cartAdapter;
    private List<CartItem> cartItems;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        // Initialize views
        rvCartItems = view.findViewById(R.id.rv_cart_items);
        tvSubtotal = view.findViewById(R.id.tv_subtotal);
        tvTotal = view.findViewById(R.id.tv_total);
        btnCheckout = view.findViewById(R.id.btn_checkout);
        emptyCartLayout = view.findViewById(R.id.empty_cart_layout);
        cartSummaryContainer = view.findViewById(R.id.cart_summary_container);

        // Setup RecyclerView
        setupRecyclerView();

        // Load cart items
        loadCartItems();

        // Setup checkout button
        btnCheckout.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Proceeding to checkout...", Toast.LENGTH_SHORT).show();
            // TODO: Navigate to checkout screen
        });

        // Update UI
        updateCartUI();

        return view;
    }

    private void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvCartItems.setLayoutManager(layoutManager);
    }

    private void loadCartItems() {
        // Sample cart items - In real app, get from database or shared preferences
        cartItems = new ArrayList<>();
        cartItems.add(new CartItem("Organic Fresh Milk", 250.00, 2, R.drawable.ic_product));
        cartItems.add(new CartItem("Brown Rice 5kg", 850.00, 1, R.drawable.ic_product));
        cartItems.add(new CartItem("Ceylon Tea 100g", 450.00, 3, R.drawable.ic_product));

        cartAdapter = new CartAdapter(cartItems, getContext(), this);
        rvCartItems.setAdapter(cartAdapter);
    }

    private void updateCartUI() {
        if (cartItems.isEmpty()) {
            emptyCartLayout.setVisibility(View.VISIBLE);
            cartSummaryContainer.setVisibility(View.GONE);
            rvCartItems.setVisibility(View.GONE);
        } else {
            emptyCartLayout.setVisibility(View.GONE);
            cartSummaryContainer.setVisibility(View.VISIBLE);
            rvCartItems.setVisibility(View.VISIBLE);
            calculateTotal();
        }
    }

    private void calculateTotal() {
        double subtotal = 0.0;

        for (CartItem item : cartItems) {
            subtotal += item.getTotalPrice();
        }

        tvSubtotal.setText("Rs : " + String.format("%.2f", subtotal));
        tvTotal.setText("Rs : " + String.format("%.2f", subtotal));
    }

    @Override
    public void onCartChanged() {
        calculateTotal();
    }

    @Override
    public void onItemRemoved(int position) {
        cartItems.remove(position);
        cartAdapter.notifyItemRemoved(position);
        cartAdapter.notifyItemRangeChanged(position, cartItems.size());
        updateCartUI();
        Toast.makeText(getContext(), "Item removed from cart", Toast.LENGTH_SHORT).show();
    }
}