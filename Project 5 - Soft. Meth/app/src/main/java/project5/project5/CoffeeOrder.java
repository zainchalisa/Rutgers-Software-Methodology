package project5.project5;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CoffeeOrder extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private CheckBox sweetCream;
    private CheckBox frenchVanilla;
    private CheckBox irishCream;
    private CheckBox caramel;
    private CheckBox mocha;
    public static final String SWEET_CREAM = "Sweet Cream";
    public static final String FRENCH_VANILLA = "French Vanilla";
    public static final String IRISH_CREAM = "Irish Cream";
    public static final String CARAMEL = "Caramel";
    public static final String MOCHA = "Mocha";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_order);

    }

    public void addItem(){

        ArrayList<String> toppings = new ArrayList<>();
        toppingsSelected(toppings);




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
