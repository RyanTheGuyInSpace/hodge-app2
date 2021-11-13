/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Ryan Hodge
 */

package baseline;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class InventoryManagerController implements Initializable {

    @FXML
    public Label noInventoryLabel;

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
    public TableView itemsTable;

    @FXML
    public TableColumn serialNumColumn;

    @FXML
    public TableColumn valueColumn;

    @FXML
    public TableColumn nameColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
