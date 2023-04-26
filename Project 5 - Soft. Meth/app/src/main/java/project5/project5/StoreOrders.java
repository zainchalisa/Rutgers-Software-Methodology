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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import java.util.List;


public class StoreOrders extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener {

    private static final ObservableArrayList<Order> storeOrders =
            new ObservableArrayList<>();
    private static ObservableArrayList<MenuItem> holderOrder;

    private Spinner orderSpinner;

    private ListView contentOfOrder;

    private TextView totalAmount;

    public static final int ZERO = 0;
    public static final double SALES_TAX = .06625;

    private static final DecimalFormat decimalFormat = new DecimalFormat
            ("'$'0.00");


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);
        createViews();
        orderSpinner.setSelection(0);
        int spinnerIndex = orderSpinner.getSelectedItemPosition();
        //System.out.println(spinnerIndex);
        totalAmount.setText("$0.00");

        ArrayAdapter<Order> adapter = new ArrayAdapter<Order>(this, android.R.layout.simple_list_item_1, storeOrders);
        ;
        orderSpinner.setAdapter(adapter);
        if (spinnerIndex != -1) {
            ArrayAdapter<MenuItem> ordersAdapter = new ArrayAdapter<MenuItem>(this, android.R.layout.simple_list_item_1, storeOrders.get(spinnerIndex).getOrder());
            contentOfOrder.setAdapter(ordersAdapter);
        }
        orderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                   @Override
                                                   public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                       getSelectedOrder();
                                                       System.out.println(spinnerIndex);
                                                   }

                                                   @Override
                                                   public void onNothingSelected(AdapterView<?> adapterView) {

                                                   }
                                               }

        );
        if (storeOrders.size() != 0 || spinnerIndex != -1) {
            contentOfOrder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Remove item from ArrayList

                    storeOrders.remove(spinnerIndex);
                    adapter.notifyDataSetChanged();
                    if (storeOrders.size() != 0) {
                        //orderSpinner.setSelection(0);
                        //getSelectedOrderForRemove();
                    }


                    System.out.println(storeOrders);
                    Toast.makeText(getApplicationContext(), "ListView item clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }


    }

    private void remove() {
        contentOfOrder.setOnItemClickListener((parent, view, position, id) -> {
            ArrayAdapter<Order> adapter1 = (ArrayAdapter<Order>) parent.getAdapter();
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            Context context = this;
            alert.setTitle("Add to order");
            alert.setMessage("Your item is going to be removed from your cart. Would you like to proceed?");
            alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    adapter1.remove(storeOrders.get(position));
                    adapter1.notifyDataSetChanged();
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


    private void getSelectedOrder(){
        int spinnerIndex = orderSpinner.getSelectedItemPosition();
        ArrayAdapter<MenuItem> ordersAdapter =
                new ArrayAdapter<MenuItem>(this,
                        android.R.layout.simple_list_item_1,
                        storeOrders.get(spinnerIndex).getOrder());
        contentOfOrder.setAdapter(ordersAdapter);
        totalAmount.setText(
                String.format(decimalFormat.format(getTotalAmount())));
    }


    private void getSelectedOrderForRemove(){
        int spinnerIndex = orderSpinner.getSelectedItemPosition();
        ArrayAdapter<MenuItem> ordersAdapter = new ArrayAdapter<MenuItem>(this, android.R.layout.simple_list_item_1, storeOrders.get(0).getOrder());
        contentOfOrder.setAdapter(ordersAdapter);
        totalAmount.setText(String.format(decimalFormat.format(getTotalAmount())));
    }



    private void createViews(){
        orderSpinner = findViewById(R.id.orderSpinner);
        contentOfOrder = findViewById(R.id.storeOrders);
        totalAmount = findViewById(R.id.totalAmount);
        //System.out.println(storeOrders.get(0).getOrder());
    }

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


    public static void addToStoreOrders(Order customerOrder) {
        holderOrder = customerOrder.getOrder();
        storeOrders.add(customerOrder);
        customerOrder.setOrderList(holderOrder);
        //System.out.println(storeOrders.get(0).getOrder());
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view,
                               int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
