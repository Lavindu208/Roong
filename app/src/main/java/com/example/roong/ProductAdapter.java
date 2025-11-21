package com.example.roong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private final List<Product> products;
    private final Context context;
    private final boolean isGridLayout;

    // Constructor for horizontal scroll (Featured Products)
    public ProductAdapter(List<Product> products, Context context) {
        this.products = products;
        this.context = context;
        this.isGridLayout = false;
    }

    // Constructor with grid layout option (Organic Products, Shop Screen)
    public ProductAdapter(List<Product> products, Context context, boolean isGridLayout) {
        this.products = products;
        this.context = context;
        this.isGridLayout = isGridLayout;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutId = isGridLayout ? R.layout.item_product_grid : R.layout.item_product;
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = products.get(position);
        holder.productName.setText(product.getName());
        holder.productDescription.setText(product.getDescription());
        holder.productPrice.setText(product.getPrice());
        holder.productImage.setImageResource(product.getImageResource());

        holder.btnAddToCart.setOnClickListener(v -> {
            Toast.makeText(context, product.getName() + " added to cart", Toast.LENGTH_SHORT).show();
        });

        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(context, "View " + product.getName(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName;
        TextView productDescription;
        TextView productPrice;
        Button btnAddToCart;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productName = itemView.findViewById(R.id.product_name);
            productDescription = itemView.findViewById(R.id.product_description);
            productPrice = itemView.findViewById(R.id.product_price);
            btnAddToCart = itemView.findViewById(R.id.btn_add_to_cart);
        }
    }
}