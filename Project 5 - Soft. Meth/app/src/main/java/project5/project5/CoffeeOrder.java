package project5.project5;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CoffeeOrder extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private CheckBox sweetCream;
    private CheckBox frenchVanilla;
    private CheckBox irishCream;
    private CheckBox caramel;
    private CheckBox mocha;
    private Spinner coffeeSizes;
    private Spinner quantity;
    private Button addToOrderButton;
    private TextView subtotalAmount;

    public static final String SWEET_CREAM = "Sweet Cream";
    public static final String FRENCH_VANILLA = "French Vanilla";
    public static final String IRISH_CREAM = "Irish Cream";
    public static final String CARAMEL = "Caramel";
    public static final String MOCHA = "Mocha";

    private ArrayList<String> coffeeSizesList = new ArrayList<>(List.of("Short", "Tall", "Grande", "Venti"));
    private ArrayList<Integer> coffeeQuantityList = new ArrayList<>(List.of(1,2,3,4,5));
    public static Order coffeeOrder = new Order();

    public CoffeeOrder() {
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_order);
        coffeeSizes = findViewById(R.id.coffeeSizes);
        ArrayAdapter<String> coffeeSizeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, coffeeSizesList);
        coffeeSizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        coffeeSizes.setAdapter(coffeeSizeAdapter);
        quantity = findViewById(R.id.quantity);
        ArrayAdapter<Integer> quantityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, coffeeQuantityList);
        coffeeSizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantity.setAdapter(quantityAdapter);
        /*addToOrderButton.setOnClickListener(view -> {
            addItem();
            //updateBalance();
        });*/

    }

    public void addItem(){
        Coffee newCoffee = newCoffeeItem();
        boolean duplicate = false;

        for (MenuItem item: coffeeOrder.getOrder()) {
            if(item.compare(newCoffee)){
                duplicate = true;
                newCoffee.setQuantity(item.getQuantity() + newCoffee.getQuantity());
            }
        }

        if (duplicate != true){
            coffeeOrder.add(newCoffee);
        }

        sweetCream.setChecked(false);
        frenchVanilla.setChecked(false);
        irishCream.setChecked(false);
        caramel.setChecked(false);
        mocha.setChecked(false);
    }

    public Coffee newCoffeeItem(){
        int coffeeSizeIndex = coffeeSizes.getSelectedItemPosition();
        String coffeeSize = getCoffeeSize(coffeeSizeIndex);
        int coffeeQuantityIndex = quantity.getSelectedItemPosition();
        int coffeeQuantity = coffeeQuantityIndex + 1;
        ArrayList<String> toppings = new ArrayList<>();
        toppingsSelected(toppings);
        Coffee newCoffee = new Coffee(coffeeSize, toppings);
        newCoffee.setQuantity(coffeeQuantity);
        return newCoffee;
    }

    public String getCoffeeSize(int coffeeSizeIndex){
        if(coffeeSizeIndex == 0){
            return "Short";
        } else if(coffeeSizeIndex == 1){
            return "Tall";
        } else if(coffeeSizeIndex == 2){
            return "Grande";
        } else if(coffeeSizeIndex == 3){
            return "Venti";
        } else{
            return "";
        }
    }

    public void subtotalAmount(){
        Coffee currentCoffee = newCoffeeItem();
        subtotalAmount.setText(String.valueOf(currentCoffee.itemPrice()));
    }

    public void toppingsSelected(ArrayList<String> toppings){

        toppings.remove(SWEET_CREAM);
        toppings.remove(FRENCH_VANILLA);
        toppings.remove(IRISH_CREAM);
        toppings.remove(CARAMEL);
        toppings.remove(MOCHA);


        if(sweetCream.isChecked()){
            toppings.add(SWEET_CREAM);
        }

        if(frenchVanilla.isChecked()){
            toppings.add(FRENCH_VANILLA);
        }

        if(irishCream.isChecked()){
            toppings.add(IRISH_CREAM);
        }

        if(caramel.isChecked()){
            toppings.add(CARAMEL);
        }

        if(mocha.isChecked()){
            toppings.add(MOCHA);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
