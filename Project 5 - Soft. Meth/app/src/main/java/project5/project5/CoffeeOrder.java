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

/**
 * This class instantiates the coffee order activity. It populates the
 * activities views and also allows you to send the correct order over to
 * the cart.
 *
 * @author zainchalisa
 * @author nanaafriyie
 *
 */
public class CoffeeOrder extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener {

    /**
     * This check boxes allow for the users to select the toppings which
     * they would like to add to their coffee
     */
    private CheckBox sweetCream, frenchVanilla, irishCream, caramel, mocha;

    /**
     * This spinners allow for the user to select the desired coffee size
     * and quantity of that specific coffee they would like to order
     */
    private Spinner coffeeSizes, quantity;

    /**
     * This button allows the user to send the coffee order to the
     * currentOrder activity
     */
    private Button addToOrderButton;

    /**
     * This textView holds the subtotal price of the order
     */
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

    private ArrayList<String> coffeeSizesList =
            new ArrayList<>(List.of("Short", "Tall", "Grande", "Venti"));
    private ArrayList<Integer> coffeeQuantityList =
            new ArrayList<>(List.of(1, 2, 3, 4, 5));

    /**
     * This is the constructor for the coffeeOrder class
     */
    public CoffeeOrder() {
    }

    /**
     * This method sets the id for the different Android Studio views
     */
    public void createItems() {
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


    /**
     * This method creates the coffeeOrder activity
     * @param savedInstanceState If the activity is being re-initialized
     *                           after previously being shut down then this
     *                           Bundle contains the data it most recently
     *                           supplied in {@link #onSaveInstanceState}.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_order);
        createItems();
        ArrayAdapter<String> coffeeSizeAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, coffeeSizesList);
        coffeeSizeAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        coffeeSizes.setAdapter(coffeeSizeAdapter);
        ArrayAdapter<Integer> quantityAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, coffeeQuantityList);
        coffeeSizeAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        quantity.setAdapter(quantityAdapter);
        addToOrderButton.setOnClickListener(view -> {
            coffeeAlert();
        });
        checkBoxListener();
        quantityListener();
        coffeeSizeListener();
    }

    /**
     * This method listens for when a checkbox is clicked and updates the
     * subtotal textview
     */
    public void checkBoxListener() {
        irishCream.setOnClickListener(view -> {
            subtotalAmount();
        });
        sweetCream.setOnClickListener(view -> {
            subtotalAmount();
        });
        frenchVanilla.setOnClickListener(view -> {
            subtotalAmount();
        });
        caramel.setOnClickListener(view -> {
            subtotalAmount();
        });
        mocha.setOnClickListener(view -> {
            subtotalAmount();
        });
    }

    /**
     * This method checks for when the quantity spinner is clicked and
     * updates the subtotal amount accordingly
     */
    public void quantityListener() {
        quantity.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView,
                                               View view, int i, long l) {
                        subtotalAmount();
                    }
                    @Override
                    public void onNothingSelected(
                            AdapterView<?> adapterView) {
                    }
                });
    }

    /**
     * This method checks for when the coffeeSize spinner is clicked and
     * updates the subtotal accordingly
     */
    public void coffeeSizeListener() {
        coffeeSizes.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView,
                                               View view, int i, long l) {
                        subtotalAmount();
                    }
                    @Override
                    public void onNothingSelected(
                            AdapterView<?> adapterView) {

                    }
                });
    }

    /**
     * This method creates a alert when you add a coffee to the order
     */
    private void coffeeAlert() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        Context context = this;
        alert.setTitle("Add to order");
        alert.setMessage(
                "Your coffee is going to be added to your cart. Would " +
                        "you like to proceed?");
        //handle the "YES" click
        alert.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        addItem();
                        Toast.makeText(context,
                                "Coffee was added to order.",
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
     * This method adds a coffee to the currentOrder's arraylist in the
     * currentOrders activity
     */
    public void addItem() {
        Coffee newCoffee = newCoffeeItem();
        boolean sameCoffee = false;
        ObservableArrayList<MenuItem> arrayList = CurrentOrder.getOrder();
        for (int i = ZERO; i < arrayList.size(); i++) {
            if (arrayList.get(i).equals(newCoffee)) {
                sameCoffee = true;
                newCoffee.setQuantity(arrayList.get(i).getQuantity() +
                        newCoffee.getQuantity());
                arrayList.remove(i);
                CurrentOrder.getOrder().add(newCoffee);
            }
        }

        if (sameCoffee != true) {
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

    /**
     * This method creates a new coffee object to send over to the
     * currentOrders activity
     * @return returns a coffee object
     */
    public Coffee newCoffeeItem() {
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

    /**
     * Gets the current coffeeSize selected
     * @param coffeeSizeIndex the index of the coffeeSize spinner
     * @return returns the coffeeSize as a string
     */
    public String getCoffeeSize(int coffeeSizeIndex) {
        if (coffeeSizeIndex == SHORT_INDEX) {
            return "Short";
        } else if (coffeeSizeIndex == TALL_INDEX) {
            return "Tall";
        } else if (coffeeSizeIndex == GRANDE_INDEX) {
            return "Grande";
        } else if (coffeeSizeIndex == VENTI_INDEX) {
            return "Venti";
        } else {
            return "";
        }
    }

    /**
     * Calculates the subtotal amount for the current Coffee Item
     */
    public void subtotalAmount() {
        Coffee currentCoffee = newCoffeeItem();
        subtotalAmount.setText(
                String.format("$" + "%.2f", currentCoffee.itemPrice()));
    }

    /**
     * Finds the toppings which are selected for the current coffee and
     * adds them to an arrayList of toppings
     * @param toppings the empty arraylist of toppings for the coffee item
     */
    public void toppingsSelected(ArrayList<String> toppings) {
        toppings.remove(SWEET_CREAM);
        toppings.remove(FRENCH_VANILLA);
        toppings.remove(IRISH_CREAM);
        toppings.remove(CARAMEL);
        toppings.remove(MOCHA);


        if (sweetCream.isChecked()) {
            toppings.add(SWEET_CREAM);
        }

        if (frenchVanilla.isChecked()) {
            toppings.add(FRENCH_VANILLA);
        }

        if (irishCream.isChecked()) {
            toppings.add(IRISH_CREAM);
        }

        if (caramel.isChecked()) {
            toppings.add(CARAMEL);
        }

        if (mocha.isChecked()) {
            toppings.add(MOCHA);
        }
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
     * Performs no actions if no items are selcted
     * @param adapterView The AdapterView that now contains no selected
     *                    item.
     */
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
