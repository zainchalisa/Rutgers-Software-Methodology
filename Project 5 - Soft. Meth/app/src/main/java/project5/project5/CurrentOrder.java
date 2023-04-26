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

import java.util.ArrayList;

/**
 * This class sets up the currentOrder activity. It populates the
 * activities views and also allows you to send the correct order over to
 * the store.
 *
 * @author zainchalisa
 * @author nanaafriyie
 *
 */
public class CurrentOrder extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener {

    /**
     * This listView displays all the order's menuItems
     */
    private ListView order_list;

    /**
     * These textViews holds the prices for the current's orders. They're
     * also dynamic and change everytime something is added or removed
     * from the item.
     */
    private TextView subtotalView, salesTaxView, totalAmountView;

    /**
     * This button sends the currentOrder over to the shop cart.
     */
    private Button placeOrderButton;

    private static final ObservableArrayList<MenuItem> currentOrders =
            new ObservableArrayList<>();
    private double runningSubtotal;
    private double runningSalesTax;
    private double runningTotal;
    public static final double SALES_TAX = .06625;
    public static final int ZERO = 0;

    public static ObservableArrayList<MenuItem> getOrder() {
        return currentOrders;
    }

    /**
     * This method creates the currentOrder activity
     * @param savedInstanceState If the activity is being re-initialized
     *                           after previously being shut down then this
     *                           Bundle contains the data it most recently
     *                           supplied in {@link #onSaveInstanceState}.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        ArrayAdapter<MenuItem> adapter = new ArrayAdapter<MenuItem>(this,
                android.R.layout.simple_list_item_1, currentOrders);
        order_list = findViewById(R.id.order_list);
        order_list.setAdapter(adapter);
        createViews();
        calculateCart();
        remove();
        placeOrderButton.setOnClickListener(view -> {
        placeOrderAlert();
        });
    }

    /**
     * This method creates a alert for the user to confirm that they're
     * good to send an order over to the cart.
     */
    public void placeOrderAlert(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        Context context = this;
        alert.setTitle("Place the Order");
        alert.setMessage(
                "Your Order is going to be sent to the store. Would " +
                        "you like to proceed?");
        //handle the "YES" click
        alert.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        placeOrder();
                        Toast.makeText(context, "Order has been placed.",
                                Toast.LENGTH_SHORT).show();

                    }
                }).setNegativeButton("no", new DialogInterface.
                OnClickListener() {
            public void onClick(DialogInterface dialog,
                                int which) {
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    /**
     * This method removes the menuItem selected from the ListView
     */
    private void remove(){
        order_list.setOnItemClickListener((parent, view, position, id) -> {
            ArrayAdapter<MenuItem> adapter1 = (ArrayAdapter<MenuItem>)
                    parent.getAdapter();
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            Context context = this;
            alert.setTitle("Remove from Order");
            alert.setMessage("Your item is going to be removed from " +
                    "your cart. Would you like to proceed?");
            alert.setPositiveButton("yes", new DialogInterface.
                    OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    adapter1.remove(adapter1.getItem(position));
                    adapter1.notifyDataSetChanged();
                    calculateCart();
                    Toast.makeText(context, "Item was removed from order.",
                            Toast.LENGTH_SHORT).show();
                }
            }).setNegativeButton("no", new DialogInterface.
                    OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            AlertDialog dialog = alert.create();
            dialog.show();
        });
    }


    /**
     * This method creates and assigns all the id's to the views.
     */
    private void createViews() {
        subtotalView = findViewById(R.id.subtotalView);
        salesTaxView = findViewById(R.id.salesTaxView);
        totalAmountView = findViewById(R.id.totalAmountView);
        placeOrderButton = findViewById(R.id.placeOrderButton);
        placeOrderButton.setText("Place Order");
    }

    /**
     * This method calculates the current carts subtotal, salesTax, and
     * totalOrder amounts.
     */
    private void calculateCart() {
        runningSubtotal = ZERO;
        for (MenuItem item : currentOrders) {
            runningSubtotal += item.itemPrice();
        }
        runningSalesTax = runningSubtotal * SALES_TAX;
        runningTotal = runningSubtotal + runningSalesTax;

        subtotalView.setText("$" + String.format("%.2f", runningSubtotal));
        salesTaxView.setText("$" + String.format("%.2f", runningSalesTax));
        totalAmountView.setText("$" + String.format("%.2f", runningTotal));
    }


    /**
     * This method sends the currentOrder over to the shop's orderSpinner
     * and ListView
     */
    private void placeOrder() {
        if (currentOrders.size() != ZERO) {
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
            emptyOrderAlert();
        }
    }

    /**
     * This method creates a alert when the currentOrder is empty
     */
    public void emptyOrderAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Order is Empty");
        builder.setMessage("You cannot send an empty order to the store.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener
                () {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // do something when the OK button is clicked
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }


    /**
     * This static method is used in CoffeeOrder and DonutOrder to send
     * Donut and Coffee objects to the currentOrder arraylist.
     * @param item the item being added to the order
     */
    public static void addToBasket(MenuItem item) {
        currentOrders.add(item);
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

    }

    /**
     * Performs no actions if no items are selected
     * @param adapterView The AdapterView that now contains no selected
     *                    item.
     */
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
