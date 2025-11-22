package com.example.roong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private final List<CartItem> cartItems;
    private final Context context;
    private final OnCartChangeListener listener;

    public interface OnCartChangeListener {
        void onCartChanged();

        void onItemRemoved(int position);
    }

    public CartAdapter(List<CartItem> cartItems, Context context, OnCartChangeListener listener) {
        this.cartItems = cartItems;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem item = cartItems.get(position);

        holder.productName.setText(item.getProductName());
        holder.quantity.setText(String.valueOf(item.getQuantity()));
        holder.price.setText(item.getPrice());
        holder.productImage.setImageResource(item.getImageResource());

        // Increase quantity
        holder.btnIncrease.setOnClickListener(v -> {
            int currentQuantity = item.getQuantity();
            item.setQuantity(currentQuantity + 1);
            holder.quantity.setText(String.valueOf(item.getQuantity()));
            holder.price.setText(item.getPrice());
            if (listener != null) {
                listener.onCartChanged();
            }
        });

        // Decrease quantity
        holder.btnDecrease.setOnClickListener(v -> {
            int currentQuantity = item.getQuantity();
            if (currentQuantity > 1) {
                item.setQuantity(currentQuantity - 1);
                holder.quantity.setText(String.valueOf(item.getQuantity()));
                holder.price.setText(item.getPrice());
                if (listener != null) {
                    listener.onCartChanged();
                }
            }
        });

        // Delete item
        holder.btnDelete.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemRemoved(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        ImageView btnDelete;
        TextView productName;
        TextView quantity;
        TextView price;
        Button btnIncrease;
        Button btnDecrease;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.iv_cart_product_image);
            btnDelete = itemView.findViewById(R.id.iv_delete_item);
            productName = itemView.findViewById(R.id.tv_cart_product_name);
            quantity = itemView.findViewById(R.id.tv_quantity);
            price = itemView.findViewById(R.id.tv_cart_product_price);
            btnIncrease = itemView.findViewById(R.id.btn_increase);
            btnDecrease = itemView.findViewById(R.id.btn_decrease);
        }
    }
}