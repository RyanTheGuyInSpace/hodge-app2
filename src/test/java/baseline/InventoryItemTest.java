/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Ryan Hodge
 */

package baseline;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryItemTest {

    @Test
    void getName() {
        InventoryItem item = new InventoryItem();

        item.setName("Item Name One");

        assertEquals("Item Name One", item.getName());
    }

    @Test
    void setName() {
        InventoryItem item = new InventoryItem();

        item.setName("Set Item Name");

        assertNotNull(item.getName());
    }

    @Test
    void getValue() {
        InventoryItem item = new InventoryItem();

        item.setValue((float)5.55);

        assertEquals((float)5.55, item.getValue());
    }

    @Test
    void setValue() {
        InventoryItem item = new InventoryItem();

        item.setValue((float)15.55);

        assertEquals((float)15.55, item.getValue());
    }

    @Test
    void getSerialNumber() {
        InventoryItem item = new InventoryItem();

        item.setSerialNumber("A-123-321-456");

        assertEquals("A-123-321-456", item.getSerialNumber());
    }

    @Test
    void setSerialNumber() {
        InventoryItem item = new InventoryItem();

        item.setSerialNumber("A-123-321-ASD");

        assertEquals("A-123-321-ASD", item.getSerialNumber());
    }
}