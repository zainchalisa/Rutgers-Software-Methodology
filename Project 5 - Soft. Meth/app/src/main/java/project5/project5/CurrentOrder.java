package project5.project5;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;

public class CurrentOrder extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
