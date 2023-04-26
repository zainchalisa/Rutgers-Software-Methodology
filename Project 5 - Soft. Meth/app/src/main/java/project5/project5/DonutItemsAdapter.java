package project5.project5;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

class DonutItemsAdapter
        extends RecyclerView.Adapter<DonutItemsAdapter.DonutItemsHolder>
        implements AdapterView.OnItemSelectedListener {
    private Context context;
    private static ArrayList<DonutItem> donutItems;
    private ArrayAdapter<String> spnAdapter;
    String[] quantity = {"1", "2", "3", "4", "5"};

    public DonutItemsAdapter(Context context,
                             ArrayList<DonutItem> donutItems) {
        this.context = context;
        this.donutItems = donutItems;

    }

    @NonNull
    @Override
    public DonutItemsHolder onCreateViewHolder(
            // creates base layout of row on screen
            @NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_view, parent, false);
        DonutItemClickListener listener = new DonutItemClickListener() {
            @Override
            public void onItemClick(DonutItem item) {
                // Do something with the clicked item
            }
        };

        return new DonutItemsHolder(view, donutItems,
                listener); // returns the next row-view
    }

    @Override
    public void onBindViewHolder( // populates row with specific data
                                  @NonNull DonutItemsHolder holder,
                                  int position) {

        spnAdapter = new ArrayAdapter<String>(holder.itemView.getContext(),
                android.R.layout.simple_spinner_item, quantity);
        spnAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        holder.spn_quantity.setAdapter(spnAdapter);
        DonutItem currentItem = donutItems.get(position);
        // Set the name, image, and price of the DonutItem
        holder.tv_name.setText(currentItem.getDonutName());
        holder.im_item.setImageResource(currentItem.getImage());
        holder.tv_price.setText(String.format(Locale.getDefault(), "$%.2f",
                currentItem.itemPrice()));
    }


    @Override
    public int getItemCount() {
        return donutItems.size();
    }

    public ArrayAdapter<String> getSpnAdapter() {
        return spnAdapter;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int position, long id) {
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
        private ConstraintLayout parentLayout; //this is the row layout

        private static final int STARTING_INDEX = 0;
        private static final int STARTING_VALUE = 1;


        public DonutItemsHolder(@NonNull View itemView,
                                List<DonutItem> itemList,
                                DonutItemClickListener listener) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_flavor);
            tv_price = itemView.findViewById(R.id.tv_price);
            im_item = itemView.findViewById(R.id.im_item);
            btn_add = itemView.findViewById(R.id.btn_add);
            spn_quantity = itemView.findViewById(R.id.spn_quantity);
            parentLayout = itemView.findViewById(R.id.rowLayout);
            this.itemList = itemList;
            this.listener = listener;

            setAddButtonOnClick(itemView);
            parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    listener.onItemClick(itemList.get(position));
                }
            });
            spn_quantity.setOnItemSelectedListener(
                    new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent,
                                                   View view, int position,
                                                   long id) {
                            String quantityStr =
                                    parent.getItemAtPosition(position)
                                            .toString();
                            int quantity = Integer.parseInt(quantityStr);
                            DonutItem item =
                                    itemList.get(getAdapterPosition());
                            item.setQuantity(quantity);
                            if (item.getDonutType()
                                    .equals(Donut.YEAST_DONUT)) {
                                item.setItemPrice(Donut.YEAST_DONUT_PRICE *
                                        quantity);
                            } else if (item.getDonutType()
                                    .equals(Donut.CAKE_DONUT)) {
                                item.setItemPrice(
                                        Donut.CAKE_DONUT_PRICE * quantity);
                            } else if (item.getDonutType()
                                    .equals(Donut.DONUT_HOLES)) {
                                item.setItemPrice(Donut.DONUT_HOLES_PRICE *
                                        quantity);
                            }
                            tv_price.setText("$" + String.format("%.2f",
                                    item.itemPrice()));
                        }
                        @Override
                        public void onNothingSelected(
                                AdapterView<?> parent) {}
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
                    DonutItem item = itemList.get(getAdapterPosition());
                    AlertDialog.Builder alert =
                            new AlertDialog.Builder(itemView.getContext());
                    alert.setTitle("Add to order");
                    alert.setMessage(
                            spn_quantity.getSelectedItem().toString() +
                                    " " + tv_name.getText().toString() +
                                    "(s)");
                    alert.setPositiveButton("yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    Toast.makeText(itemView.getContext(),
                                            tv_name.getText().toString() +
                                                    " added.",
                                            Toast.LENGTH_LONG).show();
                                    addDonutToCart(item);
                                    spn_quantity.setSelection(
                                            STARTING_INDEX);
                                    item.setQuantity(STARTING_VALUE);
                                }
                            }).setNegativeButton("no",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    Toast.makeText(itemView.getContext(),
                                            tv_name.getText().toString() +
                                                    " not added.",
                                            Toast.LENGTH_LONG).show();
                                }
                            });
                    AlertDialog dialog = alert.create();
                    dialog.show();
                }
            });
        }

        private void addDonutToCart(DonutItem item) {
            boolean sameDonut = false;
            Iterator<MenuItem> iterator =
                    CurrentOrder.getOrder().iterator();
            while (iterator.hasNext()) {
                MenuItem listItem = iterator.next();
                if (listItem instanceof DonutItem) {
                    if (((DonutItem) listItem).getDonutName()
                            .equals(item.getDonutName())) {
                        sameDonut = true;
                        System.out.println(item.getQuantity());
                        System.out.println(listItem.getQuantity());
                        item.setQuantity(item.getQuantity() +
                                listItem.getQuantity()); //
                        DonutItem newItem =
                                new DonutItem(item.getDonutName(),
                                        item.itemPrice(),
                                        item.getQuantity(),
                                        item.getDonutName());
                        iterator.remove(); // Remove the old item using the iterator
                        CurrentOrder.addToBasket(newItem);
                        break; // We found a matching item, so we can exit the loop
                    }
                }
            }
            if (!sameDonut) {
                DonutItem newItem = new DonutItem(item.getDonutName(),
                        item.itemPrice(), item.getQuantity(),
                        item.getDonutName());
                System.out.println("Price: " + newItem.itemPrice());
                System.out.println("Quantity: " + newItem.getQuantity());
                System.out.println("Type: " + newItem.getDonutType());
                CurrentOrder.addToBasket(newItem);
                System.out.println(item.getQuantity());
            }

        }

    }
}

