package project5.project5;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import java.util.ArrayList;


public class CurrentOrder extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ListView order_list;
    private TextView orderNumberView;
    private TextView subtotalView;
    private TextView salesTaxView;
    private TextView totalAmountView;
    private Button placeOrderButton;

    private Order order = new Order();
    private static ObservableArrayList<MenuItem> currentOrders = new ObservableArrayList<>();

    private double runningSubtotal;
    private double runningSalesTax;
    private double runningTotal;
    public static final double SALES_TAX = .06625;
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
        order.setOrderList(currentOrders);
        order_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        createViews();
        calculateCart();

    }

    private void createViews() {
        orderNumberView = findViewById(R.id.orderNumber);
        subtotalView = findViewById(R.id.subtotalView);
        salesTaxView = findViewById(R.id.salesTaxView);
        totalAmountView = findViewById(R.id.totalAmountView);
        placeOrderButton = findViewById(R.id.placeOrderButton);

        placeOrderButton.setText("Place Order");
    }

    private void calculateCart() {
        for (MenuItem item : currentOrders) {
            runningSubtotal += item.itemPrice();
        }
        runningSalesTax = runningSubtotal * SALES_TAX;
        runningTotal = runningSubtotal + runningSalesTax;

        subtotalView.setText("$" + String.format("%.2f",runningSubtotal));
        salesTaxView.setText("$" + String.format("%.2f",runningSalesTax));
        totalAmountView.setText("$" + String.format("%.2f",runningTotal));
    }

    private void deleteItem() {

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
