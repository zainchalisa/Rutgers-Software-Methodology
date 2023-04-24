package project5.project5;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import java.util.ArrayList;


public class CurrentOrder extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

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
