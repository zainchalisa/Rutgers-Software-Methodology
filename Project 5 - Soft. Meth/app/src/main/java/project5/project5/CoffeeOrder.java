package project5.project5;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ObservableArrayList;

import java.util.ArrayList;
import java.util.Iterator;
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

    public static final int SHORT_INDEX = 0;
    public static final int TALL_INDEX = 1;
    public static final int GRANDE_INDEX = 2;
    public static final int VENTI_INDEX = 3;
    public static final int INCREMENT = 1;
    public static final int ZERO = 0;

    private ArrayList<String> coffeeSizesList = new ArrayList<>(List.of("Short", "Tall", "Grande", "Venti"));
    private ArrayList<Integer> coffeeQuantityList = new ArrayList<>(List.of(1,2,3,4,5));

    public CoffeeOrder() {
    }

    public void createItems(){
        sweetCream = findViewById(R.id.sweetCream);
        irishCream = findViewById(R.id.irishCream);
        caramel = findViewById(R.id.caramel);
        mocha = findViewById(R.id.mocha);
        frenchVanilla = findViewById(R.id.frenchVanilla);
        coffeeSizes = findViewById(R.id.coffeeSizes);
        addToOrderButton = findViewById(R.id.addToOrderButton);
        quantity = findViewById(R.id.quantity);
        subtotalAmount = findViewById(R.id.subtotalAmount);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_order);
        createItems();
        ArrayAdapter<String> coffeeSizeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, coffeeSizesList);
        coffeeSizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        coffeeSizes.setAdapter(coffeeSizeAdapter);
        ArrayAdapter<Integer> quantityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, coffeeQuantityList);
        coffeeSizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantity.setAdapter(quantityAdapter);
        addToOrderButton.setOnClickListener(view -> {
            coffeeAlert();
        });
        checkBoxListener();
        quantityListener();
        coffeeSizeListener();
    }

    public void checkBoxListener(){
        irishCream.setOnClickListener(view ->{
            subtotalAmount();
        });
        sweetCream.setOnClickListener(view ->{
            subtotalAmount();
        });
        frenchVanilla.setOnClickListener(view ->{
            subtotalAmount();
        });
        caramel.setOnClickListener(view ->{
            subtotalAmount();
        });
        mocha.setOnClickListener(view ->{
            subtotalAmount();
        });
    }

    public void quantityListener(){
        quantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                subtotalAmount();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void coffeeSizeListener(){
        coffeeSizes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                subtotalAmount();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void coffeeAlert(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        Context context = this;
        alert.setTitle("Add to order");
        alert.setMessage("Your coffee is going to be added to your cart. Would you like to proceed?");
        //handle the "YES" click
        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                addItem();
                Toast.makeText(context, "Coffee was added to order.", Toast.LENGTH_SHORT).show();

            }
            //handle the "NO" click
        }).setNegativeButton("no", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    public void addItem(){
        Coffee newCoffee = newCoffeeItem();
        boolean sameCoffee = false;
        ObservableArrayList<MenuItem> arrayList = CurrentOrder.getOrder();
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).equals(newCoffee)) {
                sameCoffee = true;
                newCoffee.setQuantity(arrayList.get(i).getQuantity() + newCoffee.getQuantity());
                arrayList.remove(i);
                CurrentOrder.getOrder().add(newCoffee);
            }
        }



        if(sameCoffee != true){
            CurrentOrder.addToBasket(newCoffee);
        }

        sweetCream.setChecked(false);
        frenchVanilla.setChecked(false);
        irishCream.setChecked(false);
        caramel.setChecked(false);
        mocha.setChecked(false);
        quantity.setSelection(ZERO);
        coffeeSizes.setSelection(ZERO);
        subtotalAmount.setText(String.format("$1.89"));
    }

    public Coffee newCoffeeItem(){
        int coffeeSizeIndex = coffeeSizes.getSelectedItemPosition();
        String coffeeSize = getCoffeeSize(coffeeSizeIndex);
        int coffeeQuantityIndex = quantity.getSelectedItemPosition();
        int coffeeQuantity = coffeeQuantityIndex + INCREMENT;
        ObservableArrayList<String> toppings = new ObservableArrayList<>();
        toppingsSelected(toppings);
        Coffee newCoffee = new Coffee(coffeeSize, toppings);
        newCoffee.setQuantity(coffeeQuantity);
        return newCoffee;
    }

    public String getCoffeeSize(int coffeeSizeIndex){
        if(coffeeSizeIndex == SHORT_INDEX){
            return "Short";
        } else if(coffeeSizeIndex == TALL_INDEX){
            return "Tall";
        } else if(coffeeSizeIndex == GRANDE_INDEX){
            return "Grande";
        } else if(coffeeSizeIndex == VENTI_INDEX){
            return "Venti";
        } else{
            return "";
        }
    }

    public void subtotalAmount(){
        Coffee currentCoffee = newCoffeeItem();
        subtotalAmount.setText(String.format("$" + "%.2f", currentCoffee.itemPrice()));
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
