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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InventoryManagerController implements Initializable {

    ObservableList<InventoryItem> items = FXCollections.observableArrayList();

    @FXML
    public TextField serialNumberAddField;

    @FXML
    public TextField valueAddField;

    @FXML
    public TextField nameAddField;

    @FXML
    public MenuItem addItemButton;

    @FXML
    public MenuItem editItemButton;

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
    public MenuItem saveInventoryButton;

    @FXML
    public MenuItem saveNewInventoryButton;

    @FXML
    public Menu editMenu;

    @FXML
    public MenuItem deleteItemButton;

    @FXML
    public TableView<InventoryItem> itemsTable;

    @FXML
    public TableColumn<InventoryItem, String> serialNumColumn;

    @FXML
    public TableColumn<InventoryItem, BigDecimal> valueColumn;

    @FXML
    public TableColumn<InventoryItem, String> nameColumn;

    Pattern serialNumPattern = Pattern.compile("[a-zA-Z]-\\w\\w\\w-\\w\\w\\w-\\w\\w\\w");

    public void addItem() {
        String serialNumber = serialNumberAddField.getText().trim();
        float value;

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

        item.editSerialNumber(serialNumber);
        item.editValue(value);
        item.editName(name);

        items.add(item);

        serialNumberAddField.clear();
        valueAddField.clear();
        nameAddField.clear();

        serialNumberAddField.requestFocus();
    }

    /**
     * Shows an error inside a popup using the provided errorMessage.
     * @param errorMessage The message to show inside the popup.
     */
    public void showError(String errorMessage) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Error");

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 250, 100);

        Label label = new Label(errorMessage);
        label.setAlignment(Pos.CENTER);

        Button button = new Button("Close");
        button.setOnAction(event -> window.close());
        button.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(label, button);
        window.setScene(scene);
        window.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        serialNumColumn.setCellValueFactory(new PropertyValueFactory<InventoryItem, String>("serialNumber"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<InventoryItem, BigDecimal>("value"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<InventoryItem, String>("name"));

        InventoryItem item = new InventoryItem();

        item.editName("Test Item 1");
        item.editValue((float)500);
        item.editSerialNumber("A-123-456-789");

        items.add(item);

        itemsTable.setItems(items);

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
    }
}
