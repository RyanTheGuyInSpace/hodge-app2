package baseline;

public class InventoryManager {

    /**
     * Creates a new Inventory.
     * @return The new Inventory.
     */
    public static Inventory createInventory() {
        return new Inventory();
    }

    /**
     * Loads an existing inventory from a file.
     * @param path The path to the pre-existing Inventory.
     * @return The loaded Inventory.
     */
    public static Inventory loadInventory(String path) {
        return new Inventory();
    }
}
