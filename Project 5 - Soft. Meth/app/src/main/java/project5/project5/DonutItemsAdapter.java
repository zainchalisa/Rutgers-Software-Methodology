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

/**
 * This class is used to load data in the donut recyclerview
 *
 * @author nanaafriyie
 * @author zainchalisa
 */
class DonutItemsAdapter
        extends RecyclerView.Adapter<DonutItemsAdapter.DonutItemsHolder>
        implements AdapterView.OnItemSelectedListener {
    private Context context;
    private static ArrayList<DonutItem> donutItems;
    private ArrayAdapter<String> spnAdapter;
    String[] quantity = {"1", "2", "3", "4", "5"};

    /**
     * Constructor for the adapter
     * @param context the current state of the activity
     * @param donutItems list of all donutItems in the recyclerview
     */
    public DonutItemsAdapter(Context context,
                             ArrayList<DonutItem> donutItems) {
        this.context = context;
        this.donutItems = donutItems;

    }

    /**
     *
     * Creates and initializes new ViewHolder objects
     * @param parent   The ViewGroup into which the new View will be added
     *                 after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return new ViewHolder for items in each row in recyclerview
     */
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

    /**
     * Updates the contents of ViewHolder object for data in each position
     * @param holder   The ViewHolder which should be updated to represent
     *                 the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data
     *                 set.
     */
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
        holder.tv_name.setText(currentItem.getDonutName());
        holder.im_item.setImageResource(currentItem.getImage());
        holder.tv_price.setText(String.format(Locale.getDefault(), "$%.2f",
                currentItem.itemPrice()));
    }


    /**
     * Returns the amount of items in the list
     * @return an integer representing the size of the list
     */
    @Override
    public int getItemCount() {
        return donutItems.size();
    }

    /**
     * Performs an action when item is selected
     * @param parent The AdapterView where the selection happened
     * @param view The view within the AdapterView that was clicked
     * @param position The position of the view in the adapter
     * @param id The row id of the item that is selected
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int position, long id) {
    }

    /**
     * Performs an action when no item is selected
     * @param parent The AdapterView that now contains no selected item.
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    /**
     * This class provides functionality for items in the recyclerview
     *
     * @author nanaafriyie
     * @author zainchalisa
     */
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


        /**
         * Constructor for the ViewHolder for items in the recyclerview
         * @param itemView view object representing UI layout for a
         *                 single row in the RecyclerView
         * @param itemList  a List of DonutItem objects that represents the
         *                  data to be displayed in the UI
         * @param listener  a DonutItemClickListener object that represents
         *                  the click listener for the UI item.
         */
        public DonutItemsHolder(@NonNull View itemView,
                                List<DonutItem> itemList,
                                DonutItemClickListener listener) {
            super(itemView);
            createViews();
            this.itemList = itemList;
            this.listener = listener;
            setAddButtonOnClick(itemView);
            parentLayout.setOnClickListener(new View.OnClickListener() {
                /**
                 * Gets the position of item in list on click
                 * @param v The view that was clicked.
                 */
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    listener.onItemClick(itemList.get(position));
                }
            });
            spn_quantity.setOnItemSelectedListener(
                    new AdapterView.OnItemSelectedListener() {
                        /**
                         * Recalculates itemPrice after quantity is
                         * selected
                         * @param parent The AdapterView where the
                         *               selection happened
                         * @param view The view within the AdapterView that
                         *            was clicked
                         * @param position The position of the view in the
                         *                 adapter
                         * @param id The row id of the item that is
                         *           selected
                         */
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
                            setItemPrice(item, quantity);

                            tv_price.setText("$" + String.format("%.2f",
                                    item.itemPrice()));
                        }
                        /**
                         * Performs no action if no quantity value is
                         * selected
                         * @param parent The AdapterView that now contains
                         *               no selected item.
                         */
                        @Override
                        public void onNothingSelected(
                                AdapterView<?> parent) {}
                    });
        }

        /**
         * This method create the different views in the activity
         */
        public void createViews(){
            tv_name = itemView.findViewById(R.id.tv_flavor);
            tv_price = itemView.findViewById(R.id.tv_price);
            im_item = itemView.findViewById(R.id.im_item);
            btn_add = itemView.findViewById(R.id.btn_add);
            spn_quantity = itemView.findViewById(R.id.spn_quantity);
            parentLayout = itemView.findViewById(R.id.rowLayout);
        }

        /**
         * This method sets the donutItems price
         * @param item the donut item
         * @param quantity the quantity of donuts
         */
        public void setItemPrice(DonutItem item, int quantity){
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
        }

        /**
         * Set the onClickListener for the button on each row.
         * Clicking on the button will create an AlertDialog with the
         * options of YES/NO.
         *
         * @param itemView
         */
        private void setAddButtonOnClick(@NonNull View itemView) {
            spn_quantity = itemView.findViewById(R.id.spn_quantity);
            btn_add.setOnClickListener(new View.OnClickListener() {
                /**
                 * Creates confirmation message after clicking add button
                 * @param view The view that was clicked.
                 */
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
                                    onClickModule(item);
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

        /**
         * This method modularized the code for the addButton method
         * @param item the donutItem being added
         */
        public void onClickModule(DonutItem item){
            Toast.makeText(itemView.getContext(),
                    tv_name.getText().toString() +
                            " added.",
                    Toast.LENGTH_LONG).show();
            addDonutToCart(item);
            spn_quantity.setSelection(
                    STARTING_INDEX);
            item.setQuantity(STARTING_VALUE);
        }


        /**
         * Adds DonutItem to cart and checks for duplicates
         * @param item DonutItem to be inserted into shopping cart
         */
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
                        item.setQuantity(item.getQuantity() +
                                listItem.getQuantity()); //
                        DonutItem newItem =
                                new DonutItem(item.getDonutName(),
                                        item.itemPrice(),
                                        item.getQuantity(),
                                        item.getDonutName());
                        iterator.remove();
                        CurrentOrder.addToBasket(newItem);
                        break;
                    }
                }
            }
            if (!sameDonut) {
                DonutItem newItem = new DonutItem(item.getDonutName(),
                        item.itemPrice(), item.getQuantity(),
                        item.getDonutName());
                CurrentOrder.addToBasket(newItem);
            }
        }
    }
}

