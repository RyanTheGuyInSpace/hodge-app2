/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Ryan Hodge
 */

package baseline;

import java.math.BigDecimal;

public class InventoryItem {
    private String name;
    private float value;
    private String serialNumber;

    /**
     * Gets the name of InventoryItem
     * @return The name of the InventoryItem
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the InventoryItem.
     * @param name The new name to set for the InventoryItem.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the value of the InventoryItem.
     * @return The value of the InventoryItem.
     */
    public float getValue() {
        return this.value;
    }

    /**
     * Sets the value of the InventoryItem.
     * @param value The new value to set for the InventoryItem.
     */
    public void setValue(float value) {
        this.value = value;
    }

    /**
     * Gets the serial number of the InventoryItem.
     * @return The serial number of the InventoryItem.
     */
    public String getSerialNumber() {
        return this.serialNumber;
    }

    /**
     * Sets the serial number of the InventoryItem.
     * @param serialNumber The new serial number to set the for InventoryItem.
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
