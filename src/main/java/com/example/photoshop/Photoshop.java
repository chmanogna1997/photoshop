package com.example.photoshop;

//combo box
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

// for initalizable interface
import javafx.fxml.Initializable;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.Image;

// used interface concept and inheritance concept
public class Photoshop<Static> implements Initializable  {

    // using encapsulation concept to hide the data

    @FXML
    private AnchorPane anchorBasePane;

    @FXML
    private  Label error_label;

    @FXML
    private RadioButton image_GPS;

    @FXML
    private ComboBox<String> choose_Format;

    @FXML
    private Label current_format;

    @FXML
    private RadioButton image_camera;

    @FXML
    private RadioButton image_date;

    @FXML
    private Label image_details;

    @FXML
    private ToggleGroup image_dtails;

    @FXML
    private ImageView loadedImage_view;

    @FXML
    private RadioButton image_thumbnails;


    ArrayList<String> format_list = new ArrayList<>(Arrays.asList(".GIF", ".JPG", ".JPEG", ".PNG", ".TIF", ".TIFF"));


    @FXML
    void radio_details(ActionEvent event) {
//              Getting camera details
        new ShowImageDetails(
                image_thumbnails,image_date, image_camera, image_GPS, image_details,  error_label
        ).showdetails();

    }

    @FXML
    void loadImage(ActionEvent event) throws FileNotFoundException {
//         loading the image to the pane
        new loadimage(anchorBasePane,loadedImage_view, current_format, error_label).loadImage();
    }


    @FXML
    void change_format(ActionEvent event) throws IOException {
        new Changeformat(error_label, this.choose_Format.getValue() , loadedImage_view).changeformat();
    }



    /// to set the value of the dropdown on page load ==> using interface concept
//    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        ObservableList<String> output_format_list = FXCollections.observableArrayList("GIF", "JPG", "JPEG", "PNG", "TIF", "TIFF");
        ObservableList<String> output_format_list = FXCollections.observableArrayList(format_list);
        choose_Format.setItems(output_format_list);

    }
}


