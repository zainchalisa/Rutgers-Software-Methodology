package project5.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

/**
 * This class allows you to navigate between the activities in the app
 *
 * @author nanaafriyie
 * @author zainchalisa
 */

public class MainActivity extends AppCompatActivity {

    /**
     * Sets up the initial state of UI components
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createCoffeeView();
        createDonutView();
        createCurrentOrderView();
        createStoreOrderView();
    }

    /**
     * Creates the image button for the coffee and starts activity on click
     */
    public void createCoffeeView() {
        ImageButton coffeeButton = findViewById(R.id.CoffeeOrderButton);
        coffeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =
                        new Intent(MainActivity.this,
                                CoffeeOrder.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Creates the image button for the donut and starts activity on click
     */
    public void createDonutView() {
        ImageButton donutButton = findViewById(R.id.donutOrderButton);
        donutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =
                        new Intent(MainActivity.this,
                                DonutOrder.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Creates the image button for the cart and starts activity on click
     */
    public void createCurrentOrderView() {
        ImageButton currentOrderButton =
                findViewById(R.id.currentOrderButton);
        currentOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =
                        new Intent(MainActivity.this,
                                CurrentOrder.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Creates the image button for the store orders and starts activity
     * on click
     */
    public void createStoreOrderView() {
        ImageButton storeOrderButton = findViewById(R.id.storeOrderButton);
        storeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =
                        new Intent(MainActivity.this,
                                StoreOrders.class);
                startActivity(intent);
            }
        });
    }

}