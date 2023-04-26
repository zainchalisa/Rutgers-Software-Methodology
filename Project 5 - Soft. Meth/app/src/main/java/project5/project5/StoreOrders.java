package project5.project5;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import java.util.ArrayList;
import java.util.List;


/**
 * This class provides functionality for the Store Orders screen in app
 *
 * @author nanaafriyie
 * @author zainchalisa
 */
public class StoreOrders extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener {

    private static final ObservableArrayList<Order> storeOrders =
            new ObservableArrayList<>();
    private static ObservableArrayList<MenuItem> holderOrder;

    /**
     * This spinner holds the different storeOrders
     */
    private Spinner orderSpinner;

    /**
     * This Listview displays the storeOrders
     */
    private ListView contentOfOrder;

    /**
     * This textView displays the current orders different amounts
     */
    private TextView totalAmount;
    public static final int ZERO = 0;
    public static final int ONE = 1;
    public static final double SALES_TAX = .06625;

    private static final DecimalFormat decimalFormat = new DecimalFormat
            ("'$'0.00");


    /**
     * Creates the UI components and sets up view elements for this
     * activity
     * @param savedInstanceState If the activity is being re-initialized
     *                           after previously being shut down then this
     *                           Bundle contains the data it most recently
     *                           supplied in {@link #onSaveInstanceState}.
     *                           <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);
        createViews();
        orderSpinner.setSelection(ZERO);
        int spinnerIndex = orderSpinner.getSelectedItemPosition();
        totalAmount.setText("$0.00");

        ArrayAdapter<Order> adapter = new ArrayAdapter<Order>(this,
                android.R.layout.simple_list_item_1, storeOrders);
        orderSpinner.setAdapter(adapter);
        if (spinnerIndex != ZERO) {
            ArrayAdapter<MenuItem> ordersAdapter = new
                    ArrayAdapter<MenuItem>(this, android.R.layout.
                    simple_list_item_1, storeOrders.get(spinnerIndex).
                    getOrder());
            contentOfOrder.setAdapter(ordersAdapter);
        }
        orderSpinner.setOnItemSelectedListener(new AdapterView.
                OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView,
                                       View view, int i, long l) {
                getSelectedOrder();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        remove();
    }

    /**
     * Removes an order from the store by clicking an item from the order
     */
    private void remove(){
        contentOfOrder.setOnItemClickListener((parent, view, position, id)
                -> {
            ArrayAdapter<Order> adapter1 = (ArrayAdapter<Order>)
                    parent.getAdapter();
            Context context = this;
            ArrayAdapter<Order> adapter2 = new ArrayAdapter<Order>(this,
                    android.R.layout.simple_list_item_1,
                    new ObservableArrayList<Order>());
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Remove from Store Orders");
            alert.setMessage("Your order is going to be removed from " +
                    "the stores orders. Would you like to proceed?");
            alert.setPositiveButton("yes", new DialogInterface.
                    OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    int currentPosition = getSelectedPosition();
                    removesOrder(adapter2, adapter1, context,
                            currentPosition);
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
     * As orders are removed, this method repopulates the spinner
     * @param adapter2 resets the arraylist
     * @param adapter1 listView
     * @param context state of activity
     * @param currentPosition position of item in listView
     */
    public void removesOrder(ArrayAdapter<Order> adapter2,
                             ArrayAdapter<Order> adapter1,
                             Context context, int currentPosition) {
        adapter1.remove(storeOrders.remove
                (currentPosition));
        if (storeOrders.size() >= ONE) {
            orderSpinner.setSelection(ZERO);
            ArrayAdapter<Order> adapter = new
                    ArrayAdapter<Order>(context, android.
                    R.layout.simple_list_item_1, storeOrders);
            orderSpinner.setAdapter(adapter);
            getSelectedOrder();
            totalAmount.setText(String.format(decimalFormat.
                    format(getTotalAmount())));
        } else{
        adapter1.clear();
        orderSpinner.setAdapter(adapter2);
        totalAmount.setText("$0.00");
    }
                    adapter1.notifyDataSetChanged();
                    Toast.makeText(context, "Order was removed from store."
            , Toast.LENGTH_SHORT).show();
    }

    /**
     * Returns index of item in spinner
     * @return integer representing position of item
     */
    public int getSelectedPosition(){
        return orderSpinner.getSelectedItemPosition();
    }


    /**
     * Displays the items for order selected in dropdown menu
     */
    private void getSelectedOrder(){
        int spinnerIndex = getSelectedPosition();
        ArrayAdapter<MenuItem> ordersAdapter = new ArrayAdapter<MenuItem>
                (this, android.R.layout.simple_list_item_1, storeOrders.
                        get(spinnerIndex).getOrder());
        contentOfOrder.setAdapter(ordersAdapter);
        totalAmount.setText(
                String.format(decimalFormat.format(getTotalAmount())));
    }


    /**
     * Initializes variables for views
     */
    private void createViews(){
        orderSpinner = findViewById(R.id.orderSpinner);
        contentOfOrder = findViewById(R.id.storeOrders);
        totalAmount = findViewById(R.id.totalAmount);
    }

    /**
     * Calculates the total amount of an order
     * @return the total amounnt of the current order
     */
    private double getTotalAmount() {
        int spinnerIndex = orderSpinner.getSelectedItemPosition();
        ObservableArrayList<MenuItem> currentOrder =
                storeOrders.get(spinnerIndex).getOrder();
        double runningSubtotal = ZERO;
        for (MenuItem item : currentOrder) {
            runningSubtotal += item.itemPrice();
        }
        double salesTax = runningSubtotal * SALES_TAX;
        return runningSubtotal + salesTax;
    }


    /**
     * Adds order to Store Orders view
     * @param customerOrder order to send to Store Orders
     */
    public static void addToStoreOrders(Order customerOrder) {
        holderOrder = customerOrder.getOrder();
        storeOrders.add(customerOrder);
        customerOrder.setOrderList(holderOrder);
    }

    /**
     * Performs no action on item selected
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
     * Performs no action on no item being selected
     * @param adapterView The AdapterView that now contains no selected
     *                    item.
     */
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
