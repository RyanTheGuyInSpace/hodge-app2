/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Ryan Hodge
 */

package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class InventoryManagerTest {

    @Test
    void loadInventory() {
        ObservableList<InventoryItem> items = FXCollections.observableArrayList();

        InventoryManager.loadInventory("inventory1.json", items);

        assertNotEquals(0, items.size());
    }

    @Test
    void exportToJSON() {
        ObservableList<InventoryItem> items = FXCollections.observableArrayList();

        InventoryItem item = new InventoryItem();

        item.setSerialNumber("A-123-123-123");
        item.setValue((float)10.99);
        item.setName("This is an item");

        items.add(item);

        File file = new File("testInventory.json");

        InventoryManager.exportToJSON(items, file);

        File file2 = new File("testInventory.json");

        assertNotEquals(0, file2.length());
    }

    @Test
    void exportToHTML() {
        ObservableList<InventoryItem> items = FXCollections.observableArrayList();

        InventoryItem item = new InventoryItem();

        item.setSerialNumber("A-123-123-123");
        item.setValue((float)10.99);
        item.setName("This is an item");

        items.add(item);

        File file = new File("testInventory.html");

        InventoryManager.exportToJSON(items, file);

        File file2 = new File("testInventory.html");

        assertNotEquals(0, file2.length());
    }

    @Test
    void exportToTSV() {
        ObservableList<InventoryItem> items = FXCollections.observableArrayList();

        InventoryItem item = new InventoryItem();

        item.setSerialNumber("A-123-123-123");
        item.setValue((float)10.99);
        item.setName("This is an item");

        items.add(item);

        File file = new File("testInventory.txt");

        InventoryManager.exportToJSON(items, file);

        File file2 = new File("testInventory.txt");

        assertNotEquals(0, file2.length());
    }
}