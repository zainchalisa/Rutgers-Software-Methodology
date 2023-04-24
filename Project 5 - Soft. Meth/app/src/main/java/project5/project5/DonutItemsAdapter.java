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
import java.util.List;
import java.util.Locale;

class DonutItemsAdapter extends RecyclerView.Adapter<DonutItemsAdapter.DonutItemsHolder> implements AdapterView.OnItemSelectedListener {
    private Context context;
    private static ArrayList<DonutItem> donutItems;

    private ArrayAdapter<String> spnAdapter;

    String [] quantity = {"1","2","3","4","5"};

    public DonutItemsAdapter(Context context, ArrayList<DonutItem> donutItems) {
        this.context = context;
        this.donutItems = donutItems;

    }
    @NonNull
    @Override
    public DonutItemsHolder onCreateViewHolder( // creates base layout of row on screen
            @NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_view, parent, false);
        DonutItemClickListener listener = new DonutItemClickListener() {
            @Override
            public void onItemClick(DonutItem item) {
                // Do something with the clicked item
            }
        };

        return new DonutItemsHolder(view,donutItems,listener); // returns the next row-view
    }

    @Override
    public void onBindViewHolder( // populates row with specific data
            @NonNull DonutItemsHolder holder,
            int position) {

        spnAdapter = new ArrayAdapter<String>(holder.itemView.getContext(),android.R.layout.simple_spinner_item,quantity);
        spnAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.spn_quantity.setAdapter(spnAdapter);


//        holder.tv_name.setText(donutItems.get(position).getDonutName());
//        holder.tv_price.setText("$" + donutItems.get(position).getDonutPrice());
//        holder.im_item.setImageResource(donutItems.get(position).getImage());

        DonutItem currentItem = donutItems.get(position);

        // Set the name, image, and price of the DonutItem
        holder.tv_name.setText(currentItem.getDonutName());
        holder.im_item.setImageResource(currentItem.getImage());
        holder.tv_price.setText(String.format(Locale.getDefault(), "$%.2f", currentItem.getDonutPrice()));

        // Set the quantity of the DonutItem
        //holder.spn_quantity.setSelection(currentItem.getQuantity() - 1);

        // Update the total price of the DonutItem
        //double totalPrice = currentItem.getDonutPrice() * currentItem.getQuantity();
        //holder.tv_price.setText(String.format(Locale.getDefault(), "$%.2f", totalPrice));
    }



    @Override
    public int getItemCount() {
        return donutItems.size();
    }

    public ArrayAdapter<String> getSpnAdapter() {
        return spnAdapter;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        DonutItem selectedItem = donutItems.get(position); // aren't I selecting the spinner
//        selectedItem.setQuantity(selectedItem.getQuantity() + 1);
//        double price = selectedItem.getDonutPrice() * selectedItem.getQuantity();
//        selectedItem.setDonutPrice(price);
//        notifyItemChanged(position);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public static class DonutItemsHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_price;
        private ImageView im_item;
        private Button btn_add;
        private Spinner spn_quantity;

        private List<DonutItem> itemList;
        private DonutItemClickListener listener;
        //private TextView textView;
        //private String [] donutQuantity = {"1","2","3","4","5"};
        //private ArrayAdapter<String> spnAdapter;
        private ConstraintLayout parentLayout; //this is the row layout

        public DonutItemsHolder(@NonNull View itemView, List<DonutItem> itemList, DonutItemClickListener listener) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_flavor);
            tv_price = itemView.findViewById(R.id.tv_price);
            im_item = itemView.findViewById(R.id.im_item);
            btn_add = itemView.findViewById(R.id.btn_add);
            spn_quantity = itemView.findViewById(R.id.spn_quantity);
            parentLayout = itemView.findViewById(R.id.rowLayout);
            this.itemList = itemList;
            this.listener = listener;

            setAddButtonOnClick(itemView); //register the onClicklistener for the button on each row.


            /* set onClickListener for the row layout,
             * clicking on a row will navigate to another Activity
             */

            parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    listener.onItemClick(itemList.get(position));
                }
            });

            spn_quantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String quantityStr = parent.getItemAtPosition(position).toString();
                    int quantity = Integer.parseInt(quantityStr);


//                    String priceStr = tv_price.getText().toString().replaceAll("[^\\d.]", "");
//                    double price = Double.parseDouble(priceStr); // needs to be donutPrice instead of price

                    DonutItem item = itemList.get(getAdapterPosition());
                    if (item.getDonutType().equals(Donut.YEAST_DONUT)) {
                        item.setDonutPrice(Donut.YEAST_DONUT_PRICE*quantity);
                    } else if (item.getDonutType().equals(Donut.CAKE_DONUT)) {
                        item.setDonutPrice(Donut.CAKE_DONUT_PRICE*quantity);
                    } else if (item.getDonutType().equals(Donut.DONUT_HOLES)) {
                        item.setDonutPrice(Donut.DONUT_HOLES_PRICE*quantity);
                    }
                    tv_price.setText("$" + String.format("%.2f",item.getDonutPrice()));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }

            });


        }

        /**
         * Set the onClickListener for the button on each row.
         * Clicking on the button will create an AlertDialog with the options of YES/NO.
         *
         * @param itemView
         */
        private void setAddButtonOnClick(@NonNull View itemView) {

            spn_quantity = itemView.findViewById(R.id.spn_quantity);
            btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(itemView.getContext());
                    alert.setTitle("Add to order");
                    alert.setMessage(spn_quantity.getSelectedItem().toString() + " " + tv_name.getText().toString() + "(s)");
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

