package project5.project5;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DonutOrder extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private ArrayList<DonutItem> donutItems = new ArrayList<>();

    private int [] donutImages = {R.drawable.chocolate_yeast_donut,
            R.drawable.vanilla_yeast_donut, R.drawable.jelly_yeast_donut,
            R.drawable.sugar_yeast_donut, R.drawable.glazed_yeast_donut,
            R.drawable.maple_iced_yeast_donut,
            R.drawable.boston_creme_cake_donut,
            R.drawable.vanilla_cake_donut, R.drawable.strawberry_cake_donut,
            R.drawable.chocolate_donut_holes, R.drawable.glazed_donut_holes,
            R.drawable.blueberry_donut_holes};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut_order);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        createDonutItems();
        DonutItemsAdapter adapter = new DonutItemsAdapter(this,donutItems); // this corresponds to the current DonutOrder Activity
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void createDonutItems() {
        String [] donutNames = getResources().getStringArray(R.array.donutItemNames);

        for (int i = 0; i < donutNames.length; i++) {
            if (donutNames[i].contains(Donut.YEAST_DONUT)) {
                donutItems.add(new DonutItem((donutNames[i]),donutImages[i],"$" + Donut.YEAST_DONUT_PRICE));
            } else if (donutNames[i].contains(Donut.CAKE_DONUT)) {
                donutItems.add(new DonutItem((donutNames[i]),donutImages[i],"$" + Donut.CAKE_DONUT_PRICE));
            } else if (donutNames[i].contains(Donut.DONUT_HOLES)) {
                donutItems.add(new DonutItem((donutNames[i]),donutImages[i],"$" + Donut.DONUT_HOLES_PRICE));
            }
        }
    }

}
