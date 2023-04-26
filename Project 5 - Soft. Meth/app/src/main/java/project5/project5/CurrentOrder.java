package project5.project5;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import java.util.ArrayList;


public class CurrentOrder extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener {

    private ListView order_list;
    private TextView orderNumberView;
    private  TextView subtotalView;
    private  TextView salesTaxView;
    private  TextView totalAmountView;
    private Button placeOrderButton;
    private static final ObservableArrayList<MenuItem> currentOrders = new ObservableArrayList<>();
    private  double runningSubtotal;
    private  double runningSalesTax;
    private double runningTotal;
    public static final double SALES_TAX = .06625;

    public static ObservableArrayList<MenuItem> getOrder() {
        return currentOrders;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        ArrayAdapter<MenuItem> adapter = new ArrayAdapter<MenuItem>(this,
                android.R.layout.simple_list_item_1, currentOrders);
        ;
        order_list = findViewById(R.id.order_list);
        order_list.setAdapter(adapter);
        createViews();
        calculateCart();

        remove();
        placeOrderButton.setOnClickListener(view -> {
            placeOrder();
            Toast.makeText(this, "Order has been placed.",
                    Toast.LENGTH_SHORT).show();
        });

    }

    private void remove(){
        order_list.setOnItemClickListener((parent, view, position, id) -> {
            ArrayAdapter<MenuItem> adapter1 = (ArrayAdapter<MenuItem>) parent.getAdapter();
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            Context context = this;
            alert.setTitle("Add to order");
            alert.setMessage("Your item is going to be removed from your cart. Would you like to proceed?");
            alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    adapter1.remove(adapter1.getItem(position));
                    adapter1.notifyDataSetChanged();
                    calculateCart();
                    Toast.makeText(context, "Item was removed from order.", Toast.LENGTH_SHORT).show();
                }
            }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            AlertDialog dialog = alert.create();
            dialog.show();
        });
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
        runningSubtotal = 0;
        for (MenuItem item : currentOrders) {
            runningSubtotal += item.itemPrice();
            System.out.println("Running Subtotal: $" + runningSubtotal);
        }
        runningSalesTax = runningSubtotal * SALES_TAX;
        runningTotal = runningSubtotal + runningSalesTax;

        subtotalView.setText("$" + String.format("%.2f", runningSubtotal));
        salesTaxView.setText("$" + String.format("%.2f", runningSalesTax));
        totalAmountView.setText("$" + String.format("%.2f", runningTotal));
    }


    private void placeOrder() {
        if (currentOrders != null) {
            ObservableArrayList<MenuItem> temp =
                    new ObservableArrayList<>();
            for (MenuItem item : currentOrders) {
                temp.add(item);
            }
            Order newOrder = new Order();
            newOrder.setOrderList(temp);
            StoreOrders.addToStoreOrders(newOrder);
            currentOrders.clear();
            ListAdapter adapter =
                    new ArrayAdapter<>(getApplicationContext(),
                            android.R.layout.simple_list_item_1,
                            new ArrayList<>());
            order_list.setAdapter(adapter);
            subtotalView.setText("$ 0.00");
            salesTaxView.setText("$ 0.00");
            totalAmountView.setText("$ 0.00");
        } else {
            Toast.makeText(this, "Order is empty.", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    private void deleteItem(int index) {
        currentOrders.remove(index);
        System.out.println(currentOrders);
    }

    public static void addToBasket(MenuItem item) {
        currentOrders.add(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view,
                               int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
