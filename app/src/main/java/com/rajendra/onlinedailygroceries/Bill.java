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

import com.rajendra.onlinedailygroceries.adapter.CartAdapter;
import com.rajendra.onlinedailygroceries.model.CartItem;

public class Bill extends AppCompatActivity {

    Button buyNow;

    DbHelper dbHelper;
    TextView tptv;

    int tPrice ;
    String n,p,a;

    TextView ntv,phtv,adtv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        Intent i = getIntent();
        n =i.getStringExtra("Name");
        p =i.getStringExtra("Phone");
        a =i.getStringExtra("Adress");

        dbHelper = new DbHelper(this);

        buyNow = findViewById(R.id.buyNow);
        tptv = findViewById(R.id.totalPrice_tv);
        ntv = findViewById(R.id.name_cus_tv);
        phtv = findViewById(R.id.ph_cus_tv);
        adtv = findViewById(R.id.ad_cus_tv);

        ntv.setText(n);
        phtv.setText(p);
        adtv.setText(a);

        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor cursor = dbHelper.getCartTable();
                while(cursor.moveToNext()){
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.I_ID));
                    dbHelper.deleteCartItems(id);
                }
                cursor.close();
                Intent i = new Intent(Bill.this , Done.class);
                finish();
                startActivity(i);
            }
        });

        showTable();

    }

    private void showTable() {

        TableLayout tableLayout = findViewById(R.id.tableLayout);


        Log.d("SheetActivity", "showTable: Inside the new page");

        //row setup
        int rowSize=dbHelper.getNumberOfItems() +1;
        TableRow[] rows = new TableRow[rowSize];
        TextView[] srn_tvs = new TextView[rowSize];
        TextView[] item_name_tvs = new TextView[rowSize];
        TextView[] price_tvs = new TextView[rowSize];
        TextView[] qnty_tvs = new TextView[rowSize];
        TextView[] total_price_tvs = new TextView[rowSize];
        tPrice =0;

        for (int i=0;i< rowSize ;i++)
        {
            srn_tvs[i] = new TextView(this);
            item_name_tvs[i] = new TextView(this);
            price_tvs[i] = new TextView(this);
            qnty_tvs[i] = new TextView(this);
            total_price_tvs[i] = new TextView(this);

        }


        srn_tvs[0].setText("SRN");
        srn_tvs[0].setTypeface(item_name_tvs[0].getTypeface() , Typeface.BOLD);
        item_name_tvs[0].setText("Name");
        item_name_tvs[0].setTypeface(item_name_tvs[0].getTypeface() , Typeface.BOLD);
        price_tvs[0].setText("Price");
        price_tvs[0].setTypeface(price_tvs[0].getTypeface() , Typeface.BOLD);
        qnty_tvs[0].setText("Quantity");
        qnty_tvs[0].setTypeface(qnty_tvs[0].getTypeface() , Typeface.BOLD);
        total_price_tvs[0].setText("Total Price");
        total_price_tvs[0].setTypeface(total_price_tvs[0].getTypeface() , Typeface.BOLD);

        Cursor cursor = dbHelper.getCartTable();
        int i=1;

        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.ITEM_NAME_KEY));
            String p = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.PRICE));
            int q = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.QNTY));

            srn_tvs[i].setText(String.valueOf(i));
            item_name_tvs[i].setText(name);
            price_tvs[i].setText(p);
            qnty_tvs[i].setText(String.valueOf(q));
            float tp = Integer.parseInt(p.substring(2,4))*q;
            total_price_tvs[i].setText(String.valueOf(tp));
            i++;

            tPrice+= tp;

        }
        cursor.close();
        tptv.setText(String.valueOf(tPrice));


        for (i = 0; i <rowSize ; i++) {

            rows[i] = new TableRow(this);
            if(i%2 == 0)
                rows[i].setBackgroundColor(Color.parseColor("#EEEEEE"));
            else
                rows[i].setBackgroundColor(Color.parseColor("#E4E4E4"));

            srn_tvs[i].setPadding(16,16,16,16);
            item_name_tvs[i].setPadding(16,16,16,16);
            price_tvs[i].setPadding(16,16,16,16);
            qnty_tvs[i].setPadding(16,16,16,16);
            total_price_tvs[i].setPadding(16,16,16,16);
            rows[i].addView(srn_tvs[i]);
            rows[i].addView(item_name_tvs[i]);
            rows[i].addView(price_tvs[i]);
            rows[i].addView(qnty_tvs[i]);
            rows[i].addView(total_price_tvs[i]);
            tableLayout.addView(rows[i]);
        }

        tableLayout.setShowDividers(TableLayout.SHOW_DIVIDER_MIDDLE);
    }



}