package project5.project5;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import java.util.ArrayList;


public class CurrentOrder extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView orderNumberView;
    private TextView subtotalView;
    private TextView salesTaxView;
    private TextView totalAmountView;
    private static ObservableArrayList<MenuItem> currentOrders = new ObservableArrayList<>();
    public static ObservableArrayList<MenuItem> getOrder() {
        return currentOrders;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        ArrayAdapter<MenuItem> adapter = new ArrayAdapter<MenuItem>(this, android.R.layout.simple_list_item_1, currentOrders);;
        ListView listView = findViewById(R.id.order_list);
        listView.setAdapter(adapter);
        createViews();

    }

    private void createViews() {
        orderNumberView = findViewById(R.id.orderNumber);
        subtotalView = findViewById(R.id.subtotalView);
        salesTaxView = findViewById(R.id.salesTaxView);
        totalAmountView = findViewById(R.id.totalAmountView);
    }

    public static void addToBasket(MenuItem item) {
        currentOrders.add(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
