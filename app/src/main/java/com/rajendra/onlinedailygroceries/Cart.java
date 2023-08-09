package com.rajendra.onlinedailygroceries;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rajendra.onlinedailygroceries.adapter.CartAdapter;
import com.rajendra.onlinedailygroceries.model.CartItem;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {

    RecyclerView recyclerView;
    CartAdapter classAdapter;
    double totalPrice;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<CartItem> cartItems = new ArrayList<>();
    DbHelper dbHelper;
    TextView tP;
    Button buy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        dbHelper = new DbHelper(this);
        tP = findViewById(R.id.totalP);
        buy = findViewById(R.id.buyNowBtn);

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Cart.this , PhoneNumber.class);
                finish();
                startActivity(i);
            }
        });


        loadData();
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        classAdapter = new CartAdapter(this,cartItems);
        recyclerView.setAdapter(classAdapter);
        getTotalPrice();
        tP.setText(String.valueOf(totalPrice));
        if(totalPrice == 0){
            buy.setEnabled(false);
        }
        classAdapter.setOnRemoveClickListener(position-> removeItem(position));
        classAdapter.setOnBackClickListener(position-> decreaseQ(position));
        classAdapter.setOnForwardClickListener(position-> increaseQ(position));
    }

    private void increaseQ(int position) {
        int qt = dbHelper.getQt(cartItems.get(position).getIId());
        //Toast.makeText(this, "qnty is "+dbHelper.getQt(cartItems.get(position).getQ()), Toast.LENGTH_SHORT).show();

        qt++;
        String p = dbHelper.getPricing(cartItems.get(position).getIId());
        totalPrice += Integer.parseInt(p.substring(2,4));
        tP.setText(String.valueOf(totalPrice));
        dbHelper.updateQnty(cartItems.get(position).getIId() , qt);
        cartItems.get(position).setQ(qt);
        //Toast.makeText(this, "qnty is "+dbHelper.getQt(cartItems.get(position).getQ()), Toast.LENGTH_SHORT).show();
        classAdapter.notifyItemChanged(position);
    }

    private void decreaseQ(int position) {
        int qt = dbHelper.getQt(cartItems.get(position).getIId());
        //Toast.makeText(this, "qnty is "+dbHelper.getQt(cartItems.get(position).getQ()), Toast.LENGTH_SHORT).show();
        qt--;
        String p = dbHelper.getPricing(cartItems.get(position).getIId());
        totalPrice -= Integer.parseInt(p.substring(2,4));
        tP.setText(String.valueOf(totalPrice));
        dbHelper.updateQnty(cartItems.get(position).getIId() , qt);
        cartItems.get(position).setQ(qt);
        //Toast.makeText(this, "qnty is "+dbHelper.getQt(cartItems.get(position).getQ()), Toast.LENGTH_SHORT).show();
        classAdapter.notifyItemChanged(position);
    }

    private void removeItem(int position) {
        String p = dbHelper.getPricing(cartItems.get(position).getIId());
        int q = dbHelper.getQt(cartItems.get(position).getIId());
        totalPrice -= q * Integer.parseInt(p.substring(2,4));
        tP.setText(String.valueOf(totalPrice));
        dbHelper.deleteCartItems(cartItems.get(position).getIId());
        cartItems.remove(position);
        classAdapter.notifyItemRemoved(position);
    }

    private void getTotalPrice() {
        Cursor cursor = dbHelper.getCartTable();

        totalPrice = 0;
        while(cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.I_ID));
            totalPrice += Double.parseDouble(dbHelper.getPricing(id).substring(2,4));
        }

    }

    private void loadData() {
        Cursor cursor = dbHelper.getCartTable();

        cartItems.clear();
        while(cursor.moveToNext()){

            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.I_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.ITEM_NAME_KEY));
            String p = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.PRICE));
            int q = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.QNTY));

            cartItems.add(new CartItem(id ,name , p, q));
        }
        cursor.close();

    }

    public void checkChange()
    {
        int i = R.layout.cart_item;
    }


}