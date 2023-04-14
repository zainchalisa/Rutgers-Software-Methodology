package project5.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       ImageButton coffeeButton = findViewById(R.id.CoffeeOrderButton);
       coffeeButton.setOnClickListener(v ->
               startActivity(new Intent(MainActivity.this, CoffeeOrder.class)));


    }
}