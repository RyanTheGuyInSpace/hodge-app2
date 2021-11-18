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
    private void setName(String name) {
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
    private void setValue(float value) {
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
    private void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * Edits the name of the InventoryItem and performs validation for the new name.
     * @param name The new name to set for the InventoryItem.
     * @return True if successful, false otherwise
     */
    public boolean editName(String name) {
        if (name.length() < 2 || name.length() > 256) {
            return false;
        }

        this.name = name;

        return true;
    }

    /**
     * Edits the value of the InventoryItem and performs validation for the new value.
     * @param value The new value to set for the InventoryItem.
     * @return True if successful, false otherwise
     */
    public boolean editValue(float value) {
        if (value < 0) {
            return false;
        }

        this.value = value;

        return true;
    }

    /**
     * Edits the serial number of the InventoryItem and performs validation for the new value.
     * @param serialNumber The new serial number to set for the InventoryItem.
     * @return True if successful, false otherwise
     */
    public boolean editSerialNumber(String serialNumber) {

        /*
         * TODO use regex to verify correct serial number format. This method shouldn't need to be moved
         * since we can cancel the operation and show a popup from the controller if this returns false
         */
        this.setSerialNumber(serialNumber);

        return false;
    }
}
