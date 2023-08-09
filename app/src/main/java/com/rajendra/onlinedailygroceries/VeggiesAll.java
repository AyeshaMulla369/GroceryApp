package com.rajendra.onlinedailygroceries;

import static com.rajendra.onlinedailygroceries.R.drawable.b1;
import static com.rajendra.onlinedailygroceries.R.drawable.b2;
import static com.rajendra.onlinedailygroceries.R.drawable.b3;
import static com.rajendra.onlinedailygroceries.R.drawable.b4;
import static com.rajendra.onlinedailygroceries.R.drawable.be1;
import static com.rajendra.onlinedailygroceries.R.drawable.be1small;
import static com.rajendra.onlinedailygroceries.R.drawable.be2;
import static com.rajendra.onlinedailygroceries.R.drawable.be2small;
import static com.rajendra.onlinedailygroceries.R.drawable.card1;
import static com.rajendra.onlinedailygroceries.R.drawable.card2;
import static com.rajendra.onlinedailygroceries.R.drawable.card3;
import static com.rajendra.onlinedailygroceries.R.drawable.card4;
import static com.rajendra.onlinedailygroceries.R.drawable.e1small;
import static com.rajendra.onlinedailygroceries.R.drawable.e2small;
import static com.rajendra.onlinedailygroceries.R.drawable.egg1;
import static com.rajendra.onlinedailygroceries.R.drawable.egg2;
import static com.rajendra.onlinedailygroceries.R.drawable.fish1;
import static com.rajendra.onlinedailygroceries.R.drawable.fish1small;
import static com.rajendra.onlinedailygroceries.R.drawable.fish2;
import static com.rajendra.onlinedailygroceries.R.drawable.fish2small;
import static com.rajendra.onlinedailygroceries.R.drawable.fish3;
import static com.rajendra.onlinedailygroceries.R.drawable.fish3small;
import static com.rajendra.onlinedailygroceries.R.drawable.juice1;
import static com.rajendra.onlinedailygroceries.R.drawable.juice1small;
import static com.rajendra.onlinedailygroceries.R.drawable.meat1;
import static com.rajendra.onlinedailygroceries.R.drawable.meat1small;
import static com.rajendra.onlinedailygroceries.R.drawable.meat2;
import static com.rajendra.onlinedailygroceries.R.drawable.meat2small;
import static com.rajendra.onlinedailygroceries.R.drawable.meat3;
import static com.rajendra.onlinedailygroceries.R.drawable.meat3small;
import static com.rajendra.onlinedailygroceries.R.drawable.meat4;
import static com.rajendra.onlinedailygroceries.R.drawable.meat4small;
import static com.rajendra.onlinedailygroceries.R.drawable.spice1;
import static com.rajendra.onlinedailygroceries.R.drawable.spice1small;
import static com.rajendra.onlinedailygroceries.R.drawable.spice2;
import static com.rajendra.onlinedailygroceries.R.drawable.spice2small;
import static com.rajendra.onlinedailygroceries.R.drawable.spice3;
import static com.rajendra.onlinedailygroceries.R.drawable.spice3small;
import static com.rajendra.onlinedailygroceries.R.drawable.veg1;
import static com.rajendra.onlinedailygroceries.R.drawable.veg1small;
import static com.rajendra.onlinedailygroceries.R.drawable.veg2;
import static com.rajendra.onlinedailygroceries.R.drawable.veg2small;
import static com.rajendra.onlinedailygroceries.R.drawable.veg3;
import static com.rajendra.onlinedailygroceries.R.drawable.veg3small;
import static com.rajendra.onlinedailygroceries.R.drawable.veg4;
import static com.rajendra.onlinedailygroceries.R.drawable.veg4small;
import static com.rajendra.onlinedailygroceries.R.drawable.veg5;
import static com.rajendra.onlinedailygroceries.R.drawable.veg5small;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.rajendra.onlinedailygroceries.adapter.RecentlyViewedAdapter;
import com.rajendra.onlinedailygroceries.model.RecentlyViewed;

import java.util.ArrayList;
import java.util.List;

public class VeggiesAll extends AppCompatActivity {

    RecentlyViewedAdapter recentlyViewedAdapter;
    RecyclerView recentlyViewedRecyclerF;
    RecyclerView recentlyViewedRecyclerV;
    RecyclerView recentlyViewedRecyclerMeat;
    RecyclerView recentlyViewedRecyclerFishes;
    RecyclerView recentlyViewedRecyclerSpices;
    RecyclerView recentlyViewedRecyclerEggs;
    RecyclerView recentlyViewedRecyclerBev;
    List<RecentlyViewed> recentlyViewedListFruit;
    List<RecentlyViewed> recentlyViewedListVeg;
    List<RecentlyViewed> recentlyViewedListMeat;
    List<RecentlyViewed> recentlyViewedListFish;
    List<RecentlyViewed> recentlyViewedListSpices;
    List<RecentlyViewed> recentlyViewedListEggs;
    List<RecentlyViewed> recentlyViewedListBev;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veggies_all);

        recentlyViewedRecyclerF = findViewById(R.id.recently_item_fruits);
        recentlyViewedRecyclerV = findViewById(R.id.recently_item_veg);
        recentlyViewedRecyclerMeat = findViewById(R.id.recently_item_meat);
        recentlyViewedRecyclerFishes = findViewById(R.id.recently_item_fish);
        recentlyViewedRecyclerSpices = findViewById(R.id.recently_item_spices);
        recentlyViewedRecyclerEggs = findViewById(R.id.recently_item_eggs);
        recentlyViewedRecyclerBev = findViewById(R.id.recently_item_beverages);

        recentlyViewedListFruit = new ArrayList<>();
        recentlyViewedListFruit.add(new RecentlyViewed("Watermelon", "Watermelon has high water content and also provides some fiber.", "₹ 80", "1", "KG", card4, b4));
        recentlyViewedListFruit.add(new RecentlyViewed("Papaya", "Papayas are spherical or pear-shaped fruits that can be as long as 20 inches.", "₹ 85", "1", "KG", card3, b3));
        recentlyViewedListFruit.add(new RecentlyViewed("Strawberry", "The strawberry is a highly nutritious fruit, loaded with vitamin C.", "₹ 30", "1", "KG", card2, b1));
        recentlyViewedListFruit.add(new RecentlyViewed("Kiwi", "Full of nutrients like vitamin C, vitamin K, vitamin E, folate, and potassium.", "₹ 30", "1", "PC", card1, b2));

        setRecentlyViewedRecycler(recentlyViewedListFruit , recentlyViewedRecyclerF);

        recentlyViewedListVeg = new ArrayList<>();
        recentlyViewedListVeg.add(new RecentlyViewed("Carrots", "Carrots are high water content and also provides some fiber.", "₹ 40", "1", "KG", veg1, veg1small));
        recentlyViewedListVeg.add(new RecentlyViewed("Tomato", " Tomatoes are juicy and sweet, full of antioxidants, and may help fight several diseases.", "₹ 30", "1", "KG", veg2, veg2small));
        recentlyViewedListVeg.add(new RecentlyViewed("Cabbage", "Cabbage protect against cholesterol from clogging the arteries.", "₹ 30", "1", "KG", veg3, veg3small));
        recentlyViewedListVeg.add(new RecentlyViewed("Brinjal", "Full of nutrients like vitamin C, vitamin K, vitamin E, folate, and potassium.", "₹ 35", "1", "KG", veg4, veg4small));
        recentlyViewedListVeg.add(new RecentlyViewed("Onion", "Onions are highly nutritious with several benefits, including improved heart health.", "₹ 50", "1", "KG", veg5, veg5small));

        setRecentlyViewedRecycler(recentlyViewedListVeg , recentlyViewedRecyclerV);


        recentlyViewedListMeat = new ArrayList<>();
        recentlyViewedListMeat.add(new RecentlyViewed("Goat Meat", "Goat meat is a great source protein, iron, vitamin B12, zinc, and potassium.", "₹ 99", "1", "KG", meat1, meat1small));
        recentlyViewedListMeat.add(new RecentlyViewed("Meat", "Goat meat is a great source protein, iron, vitamin B12, zinc, and potassium.", "₹ 95", "1", "KG", meat2, meat2small));
        recentlyViewedListMeat.add(new RecentlyViewed("Chicken", "Chicken can help with weight management and reduce the risk of heart disease.", "₹ 93", "1", "KG", meat3, meat3small));
        recentlyViewedListMeat.add(new RecentlyViewed("Chicken", "chicken can help with weight management and reduce the risk of heart disease.", "₹ 95", "1", "KG", meat4, meat4small));

        setRecentlyViewedRecycler(recentlyViewedListMeat , recentlyViewedRecyclerMeat);

        recentlyViewedListFish = new ArrayList<>();
        recentlyViewedListFish.add(new RecentlyViewed("Salmon", "Salmon is a great source protein, iron, vitamin B12, zinc, and potassium.", "₹ 39", "1", "PC", fish1, fish1small));
        recentlyViewedListFish.add(new RecentlyViewed("Paplet", "Paplet is a great source protein, iron, vitamin B12, zinc, and potassium.", "₹ 55", "1", "PC", fish2, fish2small));
        recentlyViewedListFish.add(new RecentlyViewed("Kingfish", "Kingfish is a great source of protein", "₹ 53", "1", "PC", fish3, fish3small));

        setRecentlyViewedRecycler(recentlyViewedListFish , recentlyViewedRecyclerFishes);

        recentlyViewedListSpices = new ArrayList<>();
        recentlyViewedListSpices.add(new RecentlyViewed("Cardamom", " Cardamom can help in digestive health, circulation, and the respiratory system.", "₹ 20", "1", "KG", spice1, spice1small));
        recentlyViewedListSpices.add(new RecentlyViewed("Cinnamon", "Cinnamon is rich in antioxidants and other beneficial compounds.", "₹ 20", "1", "KG", spice2, spice2small));
        recentlyViewedListSpices.add(new RecentlyViewed("GarmMasala", "Garam Masala flavor soup recipes, stews, curries, and more.", "₹ 25", "1", "KG", spice3, spice3small));

        setRecentlyViewedRecycler(recentlyViewedListSpices , recentlyViewedRecyclerSpices);

        recentlyViewedListEggs = new ArrayList<>();
        recentlyViewedListEggs.add(new RecentlyViewed("EggsW", "Eggs are high source of protein.", "₹ 05", "1", "PC", egg1, e1small));
        recentlyViewedListEggs.add(new RecentlyViewed("EggsB", "Eggs are high source of protein.", "₹ 05", "1", "PC", egg2, e2small));

        setRecentlyViewedRecycler(recentlyViewedListEggs , recentlyViewedRecyclerEggs);

        recentlyViewedListBev = new ArrayList<>();
        recentlyViewedListBev.add(new RecentlyViewed("CocoCola", "Coco-cola is a beverage.", "₹ 30", "1", "PC", be1, be1small));
        recentlyViewedListBev.add(new RecentlyViewed("Pepsi", "Pepsi is a beverage.", "₹ 30", "1", "PC", be2, be2small));
        recentlyViewedListBev.add(new RecentlyViewed("Maaza", "Maaza is mango juice.", "₹ 30", "1", "PC", juice1, juice1small));

        setRecentlyViewedRecycler(recentlyViewedListBev , recentlyViewedRecyclerBev);


    }

    private void setRecentlyViewedRecycler(List<RecentlyViewed> recentlyViewedDataList , RecyclerView rv) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(layoutManager);
        recentlyViewedAdapter = new RecentlyViewedAdapter(this,recentlyViewedDataList);
        rv.setAdapter(recentlyViewedAdapter);
    }
}