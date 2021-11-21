/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Ryan Hodge
 */

package baseline;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class InventoryManager {

    /**
     * Loads an existing inventory from a file.
     * @param path The path to the pre-existing Inventory.
     * @param items The ObservableList of InventoryItems to put the read InventoryItem into.
     *
     * -----
     * Use Gson to read the provided path for InventoryItems
     * Set the items list to the new InventoryItems that get read in
     * -----
     */
    public static void loadInventory(String path, ObservableList<InventoryItem> items) {
        Gson gson = new Gson();

        try {
            Reader reader = Files.newBufferedReader(Paths.get(path));

            InventoryItem[] newItems = gson.fromJson(reader, InventoryItem[].class);

            items.setAll(newItems);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the provided list of items into the specified destinationFile as JSON.
     * @param items The list of InventoryItems to write to JSON.
     * @param destinationFile The file to write the JSON into.
     */
    public static File exportToJSON(List<InventoryItem> items, File destinationFile) {
        Gson gson = new Gson();

        try {
            FileWriter writer = new FileWriter(destinationFile.getPath());

            writer.write(gson.toJson(items));
            writer.flush();
            writer.close();
        } catch (IOException e) {

        }

        return destinationFile;
    }

    /**
     * Writes the provided list of items into the specified destinationFile as an HTML file
     * where the items are contained inside a table element.
     * @param items The list of InventoryItems to write to HTML.
     * @param destinationFile The file to write the HTML into.
     */
    public static File exportToHTML(List<InventoryItem> items, File destinationFile) {
        try {
            FileWriter writer = new FileWriter(destinationFile.getPath());

            writer.write("<!DOCTYPE html>");
            writer.write("<HTML>");
            writer.write("<head>");
            writer.write(String.format("<title>%s</title>", destinationFile.getName()));
            writer.write("</head>");
            writer.write("<body>");
            writer.write("<table>");
            writer.write("<tr>");
            writer.write("<th>Serial Number</th>\n" +
                    "<th>Name</th>\n" +
                    "<th>Value</th>\n");
            writer.write("</tr>");

            for (InventoryItem item : items) {
                writer.write("<tr>");
                writer.write(String.format("<td>%s</td>", item.getSerialNumber()));
                writer.write(String.format("<td>%s</td>", item.getName()));
                writer.write(String.format("<td>%s</td>", item.getValue()));
                writer.write("</tr>");
            }

            writer.write("</table>");
            writer.write("</body>");
            writer.write("</HTML");

            writer.flush();
            writer.close();
        } catch (IOException e) {

        }

        return destinationFile;
    }

    /**
     * Writes the provided list of items into the specified destinationFile as a TSV file.
     * @param items The list of InventoryItems to write to TSV.
     * @param destinationFile The file to write the TSV into.
     */
    public static File exportToTSV(List<InventoryItem> items, File destinationFile) {
        try {
            FileWriter writer = new FileWriter(destinationFile.getPath());

            writer.write(String.format("Serial Number\tName\tValue\n"));

            for (InventoryItem item : items) {
                writer.write(String.format("%s\t%s\t%.2f\n", item.getSerialNumber(), item.getName(), item.getValue()));
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {

        }

        return destinationFile;
    }

}
