package com.example.roong;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

    private ImageView ivProfileImage;
    private TextView tvUsername;
    private TextView tvMemberType;
    private TextView tvWishlistCount;
    private TextView tvCouponsCount;
    private TextView tvPointsCount;
    private RecyclerView rvHotSale;
    private LinearLayout layoutWishlist;
    private LinearLayout layoutCoupons;
    private LinearLayout layoutPoints;
    private LinearLayout layoutPending;
    private LinearLayout layoutProcessing;
    private LinearLayout layoutShipped;
    private LinearLayout layoutReview;
    private ProductAdapter hotSaleAdapter;

    // Member types
    public static final String MEMBER_SILVER = "silver";
    public static final String MEMBER_GOLD = "gold";
    public static final String MEMBER_PLATINUM = "platinum";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Initialize views
        initializeViews(view);

        // Load user data
        loadUserProfile();

        // Setup click listeners
        setupClickListeners();

        // Load hot sale products
        loadHotSaleProducts();

        return view;
    }

    private void initializeViews(View view) {
        ivProfileImage = view.findViewById(R.id.iv_profile_image);
        tvUsername = view.findViewById(R.id.tv_username);
        tvMemberType = view.findViewById(R.id.tv_member_type);
        tvWishlistCount = view.findViewById(R.id.tv_wishlist_count);
        tvCouponsCount = view.findViewById(R.id.tv_coupons_count);
        tvPointsCount = view.findViewById(R.id.tv_points_count);
        rvHotSale = view.findViewById(R.id.rv_hot_sale);

        // Clickable layouts
        layoutWishlist = view.findViewById(R.id.layout_wishlist);
        layoutCoupons = view.findViewById(R.id.layout_coupons);
        layoutPoints = view.findViewById(R.id.layout_points);
        layoutPending = view.findViewById(R.id.layout_pending);
        layoutProcessing = view.findViewById(R.id.layout_processing);
        layoutShipped = view.findViewById(R.id.layout_shipped);
        layoutReview = view.findViewById(R.id.layout_review);
    }

    private void loadUserProfile() {
        // Load user data - In real app, get from database or API
        tvUsername.setText("John Doe");
        tvMemberType.setText(MEMBER_GOLD);
        tvWishlistCount.setText("5");
        tvCouponsCount.setText("2");
        tvPointsCount.setText("46");

        // Set profile image if available
        // ivProfileImage.setImageResource(R.drawable.user_profile_image);
    }

    private void setupClickListeners() {
        // Profile image click
        ivProfileImage.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Change profile picture", Toast.LENGTH_SHORT).show();
            // TODO: Open image picker
        });

        // Wishlist click
        layoutWishlist.setOnClickListener(v -> {
            Toast.makeText(getContext(), "View Wishlist", Toast.LENGTH_SHORT).show();
            // TODO: Navigate to wishlist screen
        });

        // Coupons click
        layoutCoupons.setOnClickListener(v -> {
            Toast.makeText(getContext(), "View Coupons", Toast.LENGTH_SHORT).show();
            // TODO: Navigate to coupons screen
        });

        // Points click
        layoutPoints.setOnClickListener(v -> {
            Toast.makeText(getContext(), "View Points History", Toast.LENGTH_SHORT).show();
            // TODO: Navigate to points screen
        });

        // Order status clicks
        layoutPending.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Pending Orders", Toast.LENGTH_SHORT).show();
            // TODO: Navigate to pending orders
        });

        layoutProcessing.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Processing Orders", Toast.LENGTH_SHORT).show();
            // TODO: Navigate to processing orders
        });

        layoutShipped.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Shipped Orders", Toast.LENGTH_SHORT).show();
            // TODO: Navigate to shipped orders
        });

        layoutReview.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Orders to Review", Toast.LENGTH_SHORT).show();
            // TODO: Navigate to review orders
        });
    }

    private void loadHotSaleProducts() {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        rvHotSale.setLayoutManager(layoutManager);

        List<Product> products = getHotSaleProducts();
        hotSaleAdapter = new ProductAdapter(products, getContext(), true);
        rvHotSale.setAdapter(hotSaleAdapter);
    }

    private List<Product> getHotSaleProducts() {
        List<Product> products = new ArrayList<>();

        // Sample hot sale products
        products.add(new Product("Hot Sale Item 1", "Special discount today", "Rs. 450", R.drawable.ic_product));
        products.add(new Product("Hot Sale Item 2", "Limited time offer", "Rs. 680", R.drawable.ic_product));
        products.add(new Product("Hot Sale Item 3", "Best seller", "Rs. 320", R.drawable.ic_product));
        products.add(new Product("Hot Sale Item 4", "50% off", "Rs. 890", R.drawable.ic_product));

        return products;
    }

    // Method to update user stats (can be called from other parts of app)
    public void updateUserStats(int wishlistCount, int couponsCount, int pointsCount) {
        tvWishlistCount.setText(String.valueOf(wishlistCount));
        tvCouponsCount.setText(String.valueOf(couponsCount));
        tvPointsCount.setText(String.valueOf(pointsCount));
    }

    // Method to update member type
    public void updateMemberType(String memberType) {
        tvMemberType.setText(memberType);
    }
}