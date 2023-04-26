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

/**
 * This class is the main activity for ordering donut
 *
 * @author nanaafriyie
 * @author zainchalisa
 */
public class DonutOrder extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener {

    private ArrayList<DonutItem> donutItems = new ArrayList<>();
    private DonutItemsAdapter adapter;

    private int[] donutImages = {R.drawable.chocolate_yeast_donut,
            R.drawable.vanilla_yeast_donut, R.drawable.jelly_yeast_donut,
            R.drawable.sugar_yeast_donut, R.drawable.glazed_yeast_donut,
            R.drawable.maple_iced_yeast_donut,
            R.drawable.boston_creme_cake_donut,
            R.drawable.vanilla_cake_donut,
            R.drawable.strawberry_cake_donut,
            R.drawable.chocolate_donut_holes,
            R.drawable.glazed_donut_holes,
            R.drawable.blueberry_donut_holes};

    public static final int ZERO = 0;

    /**
     * Sets content view and intial state of the UI components
     * @param savedInstanceState If the activity is being re-initialized
     *                           after previously being shut down then this
     *                           Bundle contains the data it most recently
     *                           supplied in {@link #onSaveInstanceState}.
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut_order);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        createDonutItems();
        adapter = new DonutItemsAdapter(this, donutItems);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    /**
     * Notifys adapter when an item is selected
     * @param adapterView The AdapterView where the selection happened
     * @param view The view within the AdapterView that was clicked
     * @param i The position of the view in the adapter
     * @param l The row id of the item that is selected
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view,
                               int i, long l) {
        adapter.notifyDataSetChanged();
    }

    /**
     * Performs no actions if no items are selcted
     * @param adapterView The AdapterView that now contains no selected
     *                    item.
     */
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    /**
     * Populates the activity with the data for each donut
     */
    private void createDonutItems() { // Initializes Donut
        String[] donutNames =
                getResources().getStringArray(R.array.donutItemNames);

        for (int i = ZERO; i < donutNames.length; i++) {
            if (donutNames[i].contains(Donut.YEAST_DONUT)) {
                donutItems.add(
                        new DonutItem((donutNames[i]), donutImages[i],
                                Donut.YEAST_DONUT_PRICE,
                                Donut.YEAST_DONUT));
            } else if (donutNames[i].contains(Donut.CAKE_DONUT)) {
                donutItems.add(
                        new DonutItem((donutNames[i]), donutImages[i],
                                Donut.CAKE_DONUT_PRICE, Donut.CAKE_DONUT));
            } else if (donutNames[i].contains(Donut.DONUT_HOLES)) {
                donutItems.add(
                        new DonutItem((donutNames[i]), donutImages[i],
                                Donut.DONUT_HOLES_PRICE,
                                Donut.DONUT_HOLES));
            }
        }
    }

}
