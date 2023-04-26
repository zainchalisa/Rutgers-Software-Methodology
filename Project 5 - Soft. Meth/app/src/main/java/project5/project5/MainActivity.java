package project5.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createCoffeeView();
        createDonutView();
        createCurrentOrderView();
        createStoreOrderView();
    }

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