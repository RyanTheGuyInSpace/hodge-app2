/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Ryan Hodge
 */

package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.FloatStringConverter;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InventoryManagerController implements Initializable {

    ObservableList<InventoryItem> items = FXCollections.observableArrayList();

    private static float oldValue = 0;

    @FXML
    public MenuItem exportButton;

    @FXML
    public TextField serialNumberAddField;

    @FXML
    public TextField valueAddField;

    @FXML
    public TextField nameAddField;

    @FXML
    public TextField searchTextField;

    @FXML
    public MenuItem deleteAllItemsButton;

    @FXML
    public AnchorPane mainPane;

    @FXML
    public MenuBar mainMenuBar;

    @FXML
    public Menu fileMenu;

    @FXML
    public MenuItem newInventoryButton;

    @FXML
    public MenuItem openInventoryButton;

    @FXML
    public Menu editMenu;

    @FXML
    public MenuItem deleteItemButton;

    @FXML
    public TableView<InventoryItem> itemsTable;

    @FXML
    public TableColumn<InventoryItem, String> serialNumColumn;

    @FXML
    public TableColumn<InventoryItem, Float> valueColumn;

    @FXML
    public TableColumn<InventoryItem, String> nameColumn;

    Pattern serialNumPattern = Pattern.compile("[a-zA-Z]-\\w\\w\\w-\\w\\w\\w-\\w\\w\\w");

    /**
     * Checks serialNumberAddField, valueAddField and nameAddField for information and checks it.
     * If the information passes, a new Inventory item is created and added to the items list.
     *
     * -----
     * Get the text entered into the add fields
     * Check the inputs to make sure they're valid inputs
     * Create the InventoryItem using the information gathered from those fields
     * Add the InventoryItem to the items list
     * Reselect the Serial Number field
     * -----
     */
    public void addItem() {
        String serialNumber = serialNumberAddField.getText().trim();
        float value;

        if (serialNumber.length() > 13) {
            showError("Not a valid serial number. Please try again.");

            serialNumberAddField.requestFocus();

            return;
        }

        // Validate serialNumber with regex and make sure it doesn't already exist inside the items list
        if (serialNumPattern.matcher(serialNumber).find()) {
            // Check if this value is already inside the items list
            for (InventoryItem item : items) {
                if (item.getSerialNumber().equals(serialNumber)) {
                    showError("Serial Number already exists. Please try again.");

                    serialNumberAddField.requestFocus();

                    return;
                }
            }
        } else {
            showError("Not a valid serial number. Please try again.");

            serialNumberAddField.requestFocus();

            return;
        }

        try {
            value = Float.parseFloat(valueAddField.getText());

            if (value < 0) {
                // Show an error and exit
                showError("Input for \"Value\" is invalid.");

                valueAddField.requestFocus();

                return;
            }

        } catch (Exception e) {
            // Show an error and exit
            showError("Input for \"Value\" is invalid.");

            valueAddField.requestFocus();

            return;
        }

        String name = nameAddField.getText();

        if (name.length() < 2 || name.length() > 256) {
            showError("Name must be between 2 and 256 characters long.");

            nameAddField.requestFocus();

            return;
        }

        // All checks have passed. Create the inventory item and add it to the list
        InventoryItem item = new InventoryItem();

        item.setSerialNumber(serialNumber);
        item.setValue(value);
        item.setName(name);

        items.add(item);

        serialNumberAddField.clear();
        valueAddField.clear();
        nameAddField.clear();

        serialNumberAddField.requestFocus();
    }

    /**
     * Shows an error inside a popup using the provided errorMessage.
     * @param errorMessage The message to show inside the popup.
     *
     * -----
     * Create a stage for the window
     * Add a scene, Label for the error message and button for dismissal
     * Show the new stage
     * -----
     */
    public static void showError(String errorMessage) {
        Stage window = new Stage();

        // APPLICATION_MODAL will ensure that the popup must be dealt with before continuing
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Error");

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 350, 100);

        Label label = new Label(errorMessage);
        label.setAlignment(Pos.CENTER);

        // "Close" button for easy dismissal
        Button button = new Button("Close");
        button.setOnAction(event -> window.close());
        button.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(label, button);
        window.setScene(scene);
        window.showAndWait();
    }

    /**
     * Clears all items from the items list.
     */
    public void deleteAllItems() {
        items.clear();
    }

    /**
     * Finds the currently selected inventory item from the items list and deletes it.
     * If there is no item currently selected, nothing happens
     *
     * -----
     * Find the currently selected InventoryItem from the itemsTable
     * If no item is selected, show and error and return
     * If and item is selected, remove it from the items list
     * -----
     */
    public void deleteItem() {

        InventoryItem item = itemsTable.getSelectionModel().getSelectedItem();

        if (item == null) {
            showError("No item selected. Please select an item and try again.");

            return;
        }

        items.remove(item);
    }

    /**
     * Opens a FileChooser to select the JSON file containing an inventory and
     * loads it into the GUI.
     *
     * -----
     * Open a FileChooser to select a JSON file
     * Try to use Gson to read the JSON file into a list of InventoryItems
     * If it fails, show an error message and return
     * Otherwise, set the items list to contain all the items that were read in
     * -----
     */
    public void openInventory() {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Select Inventory");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Json Files", "*.json"));

        File loadFile = fileChooser.showOpenDialog(serialNumberAddField.getScene().getWindow());

        InventoryManager.loadInventory(loadFile.getPath(), items);
    }

    /**
     * Exports the currently listed inventory to a JSON file at a location specified with
     * the FileChooser.
     *
     * -----
     * Use the FileChooser to pick where the file should be exported to
     * Once the destination is selected, call the export based on the filetype chosen
     * -----
     */
    public void exportInventory() {

        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Select Export Location");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JSON File", "*.json"),
                new FileChooser.ExtensionFilter("HTML File", "*.html"),
                new FileChooser.ExtensionFilter("TSV File", "*.txt"));

        File exportFile = fileChooser.showSaveDialog(serialNumberAddField.getScene().getWindow());

        try {
            exportFile.createNewFile();
        } catch (IOException e) {
            showError("Error exporting file. Please try again.");

            return;
        }

        if (exportFile.getName().endsWith(".json")) {
            // Export as JSON
            InventoryManager.exportToJSON(items, exportFile);
        } else if (exportFile.getName().endsWith(".html")) {
            // Export as HTML
            InventoryManager.exportToHTML(items, exportFile);
        } else if (exportFile.getName().endsWith(".txt")) {
            // Export as TSV
            InventoryManager.exportToTSV(items, exportFile);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        serialNumColumn.setCellValueFactory(new PropertyValueFactory<InventoryItem, String>("serialNumber"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<InventoryItem, Float>("value"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<InventoryItem, String>("name"));

        InventoryItem item = new InventoryItem();

        item.setName("Test Item 1");
        item.setValue((float)500);
        item.setSerialNumber("A-123-456-789");

        items.add(item);

        itemsTable.setItems(items);

        serialNumColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        valueColumn.setCellFactory(TextFieldTableCell.forTableColumn(new CustomFloatStringConverter()));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        serialNumberAddField.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)) {
                addItem();
            }
        });

        valueAddField.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)) {
                addItem();
            }
        });

        nameAddField.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)) {
                addItem();
            }
        });

        searchTextField.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)) {
                searchItem();
            }
        });
    }

    /**
     * Searches for an InventoryItem by serial number or name.
     *
     * -----
     * Get the text inside the search box
     * Find any InventoryItems whose serial number contains the query and select it
     * Find any InventoryItems whose name contains the query and select it
     * If no items are found, show an error indicating that no items were found
     * -----
     */
    public void searchItem() {
        String query = searchTextField.getText();

        // If the box is empty, do nothing and just return
        if (query.isEmpty() || query.isBlank()) {
            return;
        }

        itemsTable.getItems().stream().filter(a -> a.getSerialNumber().contains(query)).findAny().ifPresent(
                a -> {
                    itemsTable.getSelectionModel().select(a);
                    itemsTable.scrollTo(a);
                }
        );

        itemsTable.getItems().stream().filter(a -> a.getName().contains(query)).findAny().ifPresent(
                a -> {
                    itemsTable.getSelectionModel().select(a);
                    itemsTable.scrollTo(a);
                }
        );

        // If no items are found by the given query, show an error
        if (itemsTable.getItems().stream().filter(a -> a.getSerialNumber().contains(query)).findAny().isEmpty()
            && itemsTable.getItems().stream().filter(a -> a.getName().contains(query)).findAny().isEmpty()) {
            showError("No items found");
        }
    }

    /**
     * Edits the serialNumber of the currently selected InventoryItem
     * @param inventoryItemStringCellEditEvent The event being fired upon edit commit
     *
     * -----
     * Get the item being edited
     * Get the new serial number that's trying to be used in the item
     * Verify the serial number is valid and not a duplicate and set it as the selected item's serial number if it passes
     * If the serial number exists already or isn't value, show an error and return
     * -----
     */
    public void editSerialNumber(TableColumn.CellEditEvent<InventoryItem, String> inventoryItemStringCellEditEvent) {
        InventoryItem selectedItem = itemsTable.getSelectionModel().getSelectedItem();

        String serialNumber = inventoryItemStringCellEditEvent.getNewValue();

        if (serialNumber.length() > 13) {
            showError("Not a valid serial number. Please try again.");

            itemsTable.refresh();

            return;
        }

        // Validate serialNumber with regex and make sure it doesn't already exist inside the items list
        if (serialNumPattern.matcher(serialNumber).find()) {
            // Check if this value is already inside the items list
            for (InventoryItem item : items) {
                if (item.getSerialNumber().equals(serialNumber)) {
                    showError("Serial Number already exists. Please try again.");

                    itemsTable.refresh();

                    return;
                }
            }

            selectedItem.setSerialNumber(serialNumber);

            itemsTable.refresh();
        } else {
            showError("Not a valid serial number. Please try again.");

            itemsTable.refresh();

            return;
        }
    }

    /**
     * Edits the value of the currently selected InventoryItem
     * @param inventoryItemFloatCellEditEvent The event being fired upon edit commit
     *
     * -----
     * Get the item being edited
     * Get the new value that's trying to be used in the item
     * If the value isn't valid or is less than 0, show an error and return
     * -----
     */
    public void editValue(TableColumn.CellEditEvent<InventoryItem, Float> inventoryItemFloatCellEditEvent) {
        InventoryItem item = itemsTable.getSelectionModel().getSelectedItem();

        float value;

        try {
            value = inventoryItemFloatCellEditEvent.getNewValue();

            item.setValue(value);
        } catch (Exception e) {
            // Show an error and exit
            showError("Input for \"Value\" is invalid.");

            itemsTable.refresh();

            return;
        }

        itemsTable.refresh();
    }

    /**
     * Edits the name of the currently selected InventoryItem
     * @param inventoryItemStringCellEditEvent The event being fired upon edit commit
     *
     * -----
     * Get the item being edited
     * Get the new name that's trying to be used in the item
     * If the new name length is less than 2 characters or greater than 256 characters, show an error and return
     * -----
     */
    public void editName(TableColumn.CellEditEvent<InventoryItem, String> inventoryItemStringCellEditEvent) {
        InventoryItem item = itemsTable.getSelectionModel().getSelectedItem();

        String name = inventoryItemStringCellEditEvent.getNewValue();

        if (name.length() < 2 || name.length() > 256) {
            showError("Name must be between 2 and 256 characters long.");

            itemsTable.refresh();

            return;
        }

        item.setName(name);
    }

    /**
     * Sets the static oldValue to whatever value was inside the selected InventoryItem before editing began.
     * We do this so the value can be pulled when a non-numerical value is used and fails
     * @param inventoryItemFloatCellEditEvent The event fired when a value starts to be edited
     */
    public void beginValueEdit(TableColumn.CellEditEvent<InventoryItem, Float> inventoryItemFloatCellEditEvent) {
        oldValue = inventoryItemFloatCellEditEvent.getOldValue();
    }

    /**
     * Custom version of FloatStringConverter where we override the fromString method to not throw an exception when
     * it fails
     */
    public static class CustomFloatStringConverter extends FloatStringConverter {

        private final FloatStringConverter converter = new FloatStringConverter();

        @Override
        public Float fromString(String string) {
            try {
                return converter.fromString(string);
            } catch (Exception e) {
                showError("Input for \"Value\" is invalid.");
            }

            return InventoryManagerController.oldValue;
        }
    }
}
