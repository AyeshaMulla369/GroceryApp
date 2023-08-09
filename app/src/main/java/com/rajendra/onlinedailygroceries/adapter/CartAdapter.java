package com.rajendra.onlinedailygroceries.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rajendra.onlinedailygroceries.Cart;
import com.rajendra.onlinedailygroceries.DbHelper;
import com.rajendra.onlinedailygroceries.R;
import com.rajendra.onlinedailygroceries.model.CartItem;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {


    ArrayList<CartItem> cartItems;
    Context context;

    //remove
    private OnRemoveClickListener onRemoveClickListener;
    public interface OnRemoveClickListener{
        void onClickR(int position);
    }

    public void setOnRemoveClickListener(OnRemoveClickListener onItemClickListener) {
        this.onRemoveClickListener = onItemClickListener;
    }

    //back

    private OnBackClickListener onBackClickListener;
    public interface OnBackClickListener{
        void onClickB(int position);
    }

    public void setOnBackClickListener(OnBackClickListener onBackClickListener) {
        this.onBackClickListener = onBackClickListener;
    }

    //forward

    private OnForwardClickListener onForwardClickListener;
    public interface OnForwardClickListener{
        void onClickF(int position);
    }

    public void setOnForwardClickListener(OnForwardClickListener onForwardClickListener) {
        this.onForwardClickListener = onForwardClickListener;
    }


    public CartAdapter(Context context , ArrayList<CartItem> cartItems) {
        this.cartItems = cartItems;
        this.context = context;
    }
    public class CartViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        TextView priceing;
        TextView remove;
        TextView q;
        ImageView back;
        ImageView forward;
        public CartViewHolder(@NonNull View itemView ) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName_tv);
            priceing = itemView.findViewById(R.id.price_tv);
            remove = itemView.findViewById(R.id.remove);
            q = itemView.findViewById(R.id.number);
            back = itemView.findViewById(R.id.imageView9);
            forward = itemView.findViewById(R.id.imageView10);

            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onRemoveClickListener.onClickR(getAdapterPosition());
                }
            });

            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //incrementQnty();
                    int qty = Integer.parseInt(q.getText().toString());
                    if(qty == 1){
                        back.setEnabled(false);
                        Toast.makeText(back.getContext(), "1 is minimum try remove item", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        onBackClickListener.onClickB(getAdapterPosition());
                    }

                }
            });

            forward.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                        //decrementQnty();
                    int qty = Integer.parseInt(q.getText().toString());
                    if(qty >6){
                        forward.setEnabled(false);
                        Toast.makeText(forward.getContext(), "max is 6, try tommorow", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        onForwardClickListener.onClickF(getAdapterPosition());
                    }

                }
            });

        }
    }




    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item , parent, false);
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {

        holder.itemName.setText(cartItems.get(position).getItemName());
        holder.priceing.setText(cartItems.get(position).getPrice());
        holder.q.setText(Integer.toString(cartItems.get(position).getQ()));
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }
}
