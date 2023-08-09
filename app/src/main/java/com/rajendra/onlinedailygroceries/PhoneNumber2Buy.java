package com.rajendra.onlinedailygroceries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PhoneNumber2Buy extends AppCompatActivity {

    Button b;
    EditText ph , nm , ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number2_buy);
        b = findViewById(R.id.button23);
        ph = findViewById(R.id.editTextP2);
        nm = findViewById(R.id.editTextName2);
        ad = findViewById(R.id.editTextAdress2);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String p = ph.getText().toString();
                String n = nm.getText().toString();
                String a = ad.getText().toString();
                Intent it = getIntent();
                String in = it.getStringExtra("name");
                String price = it.getStringExtra("price");
                String qty = it.getStringExtra("q");
                Intent i = new Intent(PhoneNumber2Buy.this , BillBuyNowUrgent.class);
                i.putExtra("Phone",p);
                i.putExtra("Name",n);
                i.putExtra("Adress",a);
                i.putExtra("itemName",in);
                i.putExtra("price",price);
                i.putExtra("qty",qty);
                Toast.makeText(PhoneNumber2Buy.this, "in"+in+" p"+price+" qty"+qty, Toast.LENGTH_SHORT).show();
                finish();
                startActivity(i);
            }
        });
    }
}