package com.rajendra.onlinedailygroceries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PhoneNumber extends AppCompatActivity {

    Button b;
    EditText ph , nm , ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number);

        b = findViewById(R.id.button2);
        ph = findViewById(R.id.editTextP);
        nm = findViewById(R.id.editTextName);
        ad = findViewById(R.id.editTextAdress);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String p = ph.getText().toString();
                String n = nm.getText().toString();
                String a = ad.getText().toString();
                Intent i = new Intent(PhoneNumber.this , Bill.class);
                i.putExtra("Phone",p);
                i.putExtra("Name",n);
                i.putExtra("Adress",a);
                finish();
                startActivity(i);
            }
        });
    }
}