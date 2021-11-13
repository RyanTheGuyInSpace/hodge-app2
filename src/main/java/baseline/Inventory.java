/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Ryan Hodge
 */

package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private String path;
    private ObservableList<InventoryItem> items = FXCollections.observableArrayList();

    /**
     * Adds the provided InventoryItem to the items list.
     * @param item The item to add to the items list.
     */
    public void addItem(InventoryItem item) {

    }

    /**
     * Deletes the provided InventoryItem from this inventory's items
     * @param item The item to delete from the items list.
     */
    public void deleteItem(InventoryItem item) {

    }

    /**
     * Deletes all items from the inventory's items list.
     */
    public void deleteAllItems() {
        this.items.clear();
    }

    /**
     * Rewrites this list to its file as json.
     */
    public void save() {

    }

    /**
     * Saves this Inventory as an HTML file at the provided path. The Inventory's items will be listed out
     * inside a table element.
     * @param path The path to save the HTML file to.
     */
    public void saveAsHtml(String path) {

    }

    /**
     * Saves this Inventory as a tab-separated value file at the provided path.
     * @param path The path to save the TSV file to.
     */
    public void saveAsTsv(String path) {

    }

    /**
     * Saves this Inventory as a JSON file at the provided path.
     * @param path The path to save the JSON file to.
     */
    public void saveAsJson(String path) {

    }
}
