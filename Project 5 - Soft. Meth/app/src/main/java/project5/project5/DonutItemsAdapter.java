package project5.project5;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;

class DonutItemsAdapter extends RecyclerView.Adapter<DonutItemsAdapter.DonutItemsHolder> {
    private Context context;
    private ArrayList<DonutItem> donutItems;

    public DonutItemsAdapter(Context context, ArrayList<DonutItem> donutItems) {
        this.context = context;
        this.donutItems = donutItems;
    }
    @NonNull
    @Override
    public DonutItemsHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_view, parent, false);

        return new DonutItemsHolder(view); // returns the next row-view
    }

    @Override
    public void onBindViewHolder(
            @NonNull DonutItemsHolder holder,
            int position) {
        ArrayAdapter<String> spnAdapter = new ArrayAdapter<String>(this,R.layout.row_view,donutQuantity);
        holder.tv_name.setText(donutItems.get(position).getDonutName());
        holder.tv_price.setText(donutItems.get(position).getDonutPrice());
        holder.im_item.setImageResource(donutItems.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return donutItems.size();
    }

    public static class DonutItemsHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_price;
        private ImageView im_item;
        private Button btn_add;
        private Spinner spn_quantity;
        private TextView textView;
        private String [] donutQuantity = {"1","2","3","4","5"};
        //private ArrayAdapter<String> spnAdapter;
        private ConstraintLayout parentLayout; //this is the row layout

        public DonutItemsHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_flavor);
            tv_price = itemView.findViewById(R.id.tv_price);
            im_item = itemView.findViewById(R.id.im_item);
            btn_add = itemView.findViewById(R.id.btn_add);
            spn_quantity = itemView.findViewById(R.id.spn_quantity);

            //spnAdapter = new ArrayAdapter<String>(this,R.layout.row_view,donutQuantity);
            spn_quantity.setAdapter(spnAdapter);
            parentLayout = itemView.findViewById(R.id.rowLayout);
            setAddButtonOnClick(itemView); //register the onClicklistener for the button on each row.

            /* set onClickListener for the row layout,
             * clicking on a row will navigate to another Activity
             */
            /*
            parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), ItemSelectedActivity.class);
                    intent.putExtra("ITEM", tv_name.getText());
                    itemView.getContext().startActivity(intent);
                }
            });

             */
            /* Alternatively, use a lamda expression to set the onClickListener for the row layout
            parentLayout.setOnClickListener(view -> {
                    Intent intent = new Intent(itemView.getContext(), ItemSelectedActivity.class);
                    intent.putExtra("ITEM", tv_name.getText());
                    itemView.getContext().startActivity(intent);
                }); */
        }

        /**
         * Set the onClickListener for the button on each row.
         * Clicking on the button will create an AlertDialog with the options of YES/NO.
         * @param itemView
         */
        private void setAddButtonOnClick(@NonNull View itemView) {
            btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(itemView.getContext());
                    alert.setTitle("Add to order");
                    alert.setMessage(tv_name.getText().toString());
                    //handle the "YES" click
                    alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(itemView.getContext(),
                                    tv_name.getText().toString() + " added.", Toast.LENGTH_LONG).show();
                        }
                        //handle the "NO" click
                    }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(itemView.getContext(),
                                    tv_name.getText().toString() + " not added.", Toast.LENGTH_LONG).show();
                        }
                    });
                    AlertDialog dialog = alert.create();
                    dialog.show();
                }
            });
        }
    }

}

