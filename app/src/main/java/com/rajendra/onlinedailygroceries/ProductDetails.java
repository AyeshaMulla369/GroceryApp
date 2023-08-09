package com.rajendra.onlinedailygroceries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rajendra.onlinedailygroceries.adapter.RecentlyViewedAdapter;

public class ProductDetails extends AppCompatActivity {

    ImageView img, back , cart , cartItemsSee;
    TextView proName, proPrice, proDesc, proQty, proUnit;

    String name, price, desc, qty, unit;
    int image , card;
    DbHelper dbHelper;


    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Intent i = getIntent();

         name = i.getStringExtra("name");
         image = i.getIntExtra("image", R.drawable.b1);
         card = i.getIntExtra("card", R.drawable.card1);
         price = i.getStringExtra("price");
         desc = i.getStringExtra("desc");
         qty = i.getStringExtra("qty");
         unit = i.getStringExtra("unit");

         proName = findViewById(R.id.productName);
         proDesc = findViewById(R.id.prodDesc);
         proPrice = findViewById(R.id.prodPrice);
         img = findViewById(R.id.big_image1);
         back = findViewById(R.id.back2);
         proQty = findViewById(R.id.qty);
         proUnit = findViewById(R.id.unit);
         cart = findViewById(R.id.imageView7);
         cartItemsSee = findViewById(R.id.cart);
         b = findViewById(R.id.button);

         proName.setText(name);
         proPrice.setText(price);
         proDesc.setText(desc);
         proQty.setText(qty);
         proUnit.setText(unit);


         dbHelper = new DbHelper(this);
        img.setImageResource(image);

        Cursor c = dbHelper.getRVTable();
        long id=1;

        if(dbHelper.getItemRvCount()==3){
            c.moveToFirst();
            int idrv = c.getInt(c.getColumnIndexOrThrow(DbHelper.RV_ID));
            dbHelper.deleteRVItems(idrv);
        }
        dbHelper.addToRV(name, desc ,price,qty,unit ,card ,image);

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int q =Integer.parseInt(qty);
                dbHelper.addInCart(name,price,q);
                Toast.makeText(ProductDetails.this, "Product added", Toast.LENGTH_LONG).show();

            }
        });

        cartItemsSee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ProductDetails.this, Cart.class);
                finish();
                startActivity(i);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ProductDetails.this, MainActivity.class);
                finish();
                startActivity(i);
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ProductDetails.this, PhoneNumber2Buy.class);
                i.putExtra("name",name);
                i.putExtra("price",price);
                i.putExtra("q",qty);
                finish();
                startActivity(i);
            }
        });

    }



}
