package bfst.controllers;

import bfst.OSMReader.OSMReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

/**
 * MainController
 */
public class MainController {
    Stage stage;
    @FXML MenuItem loadClick;

    public MainController(Stage primaryStage){
        this.stage = primaryStage;
    }

    @FXML
    public void initialize() {
        loadClick.setOnAction(this::loadFileOnClick);
    }

    public void loadFileOnClick(ActionEvent e){
        loadFile();
    }

    public void loadFile() {
        File file = new FileChooser().showOpenDialog(stage);

        try {
            String fileName = file.getName();
            String fileExtension = fileName.substring(fileName.lastIndexOf("."));
            switch (fileExtension) {
                case ".bin":
                    //loadBinary(file);
                    break;
                case ".txt":
                    //loadTxt(file);
                    break;
                case ".osm":
                    OSMReader reader = new OSMReader(new FileInputStream(file));
                    System.out.println("It did it!!!!");
                    break;
                default:
                    throw new FileTypeNotSupportedException(fileExtension);
            }
        }
        catch(FileTypeNotSupportedException exception){
            Alert alert = new Alert((Alert.AlertType.ERROR));
            alert.setHeaderText("File type not supported: " +  exception.getFileType());
            alert.showAndWait();
        }
        catch (Exception exception) {
            Alert alert = new Alert((Alert.AlertType.ERROR));
            alert.setHeaderText("Something unexpected happened, please try again");
            alert.showAndWait();
        }
    }
}