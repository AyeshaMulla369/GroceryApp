package com.rajendra.onlinedailygroceries;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rajendra.onlinedailygroceries.adapter.CategoryAdapter;
import com.rajendra.onlinedailygroceries.adapter.DiscountedProductAdapter;
import com.rajendra.onlinedailygroceries.adapter.RecentlyViewedAdapter;
import com.rajendra.onlinedailygroceries.model.CartItem;
import com.rajendra.onlinedailygroceries.model.Category;
import com.rajendra.onlinedailygroceries.model.DiscountedProducts;
import com.rajendra.onlinedailygroceries.model.RecentlyViewed;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.rajendra.onlinedailygroceries.R.drawable.*;

public class MainActivity extends AppCompatActivity {

    RecyclerView discountRecyclerView, categoryRecyclerView;
    public RecyclerView recentlyViewedRecycler;
    DiscountedProductAdapter discountedProductAdapter;
    List<DiscountedProducts> discountedProductsList;

    CategoryAdapter categoryAdapter;
    List<Category> categoryList;

    public RecentlyViewedAdapter recentlyViewedAdapter;
    public List<RecentlyViewed> recentlyViewedListMain;

    TextView allCategory;
    ImageView cartPage;
    DbHelper dbHelper;
    Cursor c;
    long id1,id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DbHelper(this);
        discountRecyclerView = findViewById(R.id.discountedRecycler);
        categoryRecyclerView = findViewById(R.id.categoryRecycler);
        allCategory = findViewById(R.id.allCategoryImage);
        recentlyViewedRecycler = findViewById(R.id.recently_item);
        cartPage = findViewById(R.id.imageView);


        allCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, VeggiesAll.class);
                startActivity(i);
            }
        });

        cartPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Cart.class);
                startActivity(i);
            }
        });

        // adding data to model
        discountedProductsList = new ArrayList<>();
        discountedProductsList.add(new DiscountedProducts(1, discountberry));
        discountedProductsList.add(new DiscountedProducts(2, discountbrocoli));
        discountedProductsList.add(new DiscountedProducts(3, discountmeat));
        discountedProductsList.add(new DiscountedProducts(4, discountberry));
        discountedProductsList.add(new DiscountedProducts(5, discountbrocoli));
        discountedProductsList.add(new DiscountedProducts(6, discountmeat));

        // adding data to model
        categoryList = new ArrayList<>();
        categoryList.add(new Category(1, ic_home_fruits));
        categoryList.add(new Category(2, ic_home_fish));
        categoryList.add(new Category(3, ic_home_meats));
        categoryList.add(new Category(4, ic_home_veggies));
        categoryList.add(new Category(5, ic_home_fruits));
        categoryList.add(new Category(6, ic_home_fish));
        categoryList.add(new Category(7, ic_home_meats));
        categoryList.add(new Category(8, ic_home_veggies));

        // adding data to model
       recentlyViewedListMain = new ArrayList<>();

       RVitemsRecycle();

        setDiscountedRecycler(discountedProductsList);
        setCategoryRecycler(categoryList);
       setRecentlyViewedRecyclerMain(recentlyViewedListMain);

    }

    private void RVitemsRecycle() {
        Cursor cursor = dbHelper.getRVTable();

        recentlyViewedListMain.clear();
        while(cursor.moveToNext()){

            String name = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.RV_ITEM_NAME_KEY));
            String des = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.RV_ITEM_DESC_KEY));
            String p = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.RV_PRICE));
            String q = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.RV_QNTY));
            String u = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.RV_UNIT));
            int card = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.RV_CARD));
            int cardImg = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.RV_IMAGE));

            recentlyViewedListMain.add(new RecentlyViewed(name,des,p,q,u,card,cardImg));
        }
        Collections.reverse(recentlyViewedListMain);
        cursor.close();

    }

    private void setDiscountedRecycler(List<DiscountedProducts> dataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        discountRecyclerView.setLayoutManager(layoutManager);
        discountedProductAdapter = new DiscountedProductAdapter(this,dataList);
        discountRecyclerView.setAdapter(discountedProductAdapter);
    }


    private void setCategoryRecycler(List<Category> categoryDataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        categoryRecyclerView.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter(this,categoryDataList);
        categoryRecyclerView.setAdapter(categoryAdapter);
    }

    public void setRecentlyViewedRecyclerMain(List<RecentlyViewed> recentlyViewedDataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recentlyViewedRecycler.setLayoutManager(layoutManager);
        recentlyViewedAdapter = new RecentlyViewedAdapter(this,recentlyViewedDataList);
        recentlyViewedRecycler.setAdapter(recentlyViewedAdapter);
    }



    //Now again we need to create a adapter and model class for recently viewed items.
    // lets do it fast.
}
