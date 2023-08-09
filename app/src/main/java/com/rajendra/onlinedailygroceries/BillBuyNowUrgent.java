package com.rajendra.onlinedailygroceries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class BillBuyNowUrgent extends AppCompatActivity {

    Button buyNow;
    TextView tptv;

    int tPrice;
    String n, p, a, in, pr, q;

    TextView ntv, phtv, adtv;
    TextView intv , qtv , prtv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_buy_now_urgent);

        Intent i = getIntent();
        n = i.getStringExtra("Name");
        p = i.getStringExtra("Phone");
        a = i.getStringExtra("Adress");
        in = i.getStringExtra("itemName");
        pr = i.getStringExtra("price");
        q = i.getStringExtra("qty");

        buyNow = findViewById(R.id.buyNow);
        tptv = findViewById(R.id.totalPrice_tv2);
        ntv = findViewById(R.id.name_cus_tv2);
        phtv = findViewById(R.id.ph_cus_tv2);
        adtv = findViewById(R.id.ad_cus_tv2);
        intv = findViewById(R.id.i_name);
        prtv = findViewById(R.id.price_item);
        qtv = findViewById(R.id.quanty);

        ntv.setText(n);
        phtv.setText(p);
        adtv.setText(a);
        intv.setText(in);
        prtv.setText(pr.substring(2,4));
        qtv.setText(q);

        tptv.setText(pr.substring(2,4));

        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(BillBuyNowUrgent.this, Done.class);
                finish();
                startActivity(i);
            }
        });




    }
}