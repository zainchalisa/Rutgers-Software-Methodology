package project5.project5;

/**
 * This interface is used to select an item from the recyclerview in the
 * donuts activity
 *
 * @author nanaafriyie
 * @author zainchalisa
 */
public interface DonutItemClickListener {
    /**
     * Used to receive input when item is clicked in
     * @param item seleted from recyclerview
     */
    void onItemClick(DonutItem item);
}
