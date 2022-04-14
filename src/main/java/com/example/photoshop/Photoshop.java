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
public class Photoshop extends Changeformat implements Initializable  {

    // using encapsulation concept to hide the data
    private String file_path;
    private Image image;
    private File image_loaded_file;
    private String file_extension;

    @FXML
    private AnchorPane anchorBasePane;

    @FXML
    private Label error_label;

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

        if(image_loaded_file != null) {

            javaxt.io.Image javaxt_image = new javaxt.io.Image(file_path);
            java.util.HashMap<Integer, Object> exif = javaxt_image.getExifTags();

             System.out.println(" the details are "+ image_thumbnails.isSelected());
            if (image_thumbnails.isSelected()) {
                if(image.getHeight()!=0  && image.getWidth()!=0) {
                    image_details.setText(String.valueOf(image.getHeight()) + " X " + String.valueOf(image.getWidth()));
                }else{
                    image_details.setText("value is not available");
                }
            } else if (image_date.isSelected()) {
                if (exif.get(0x0132)!= null) {
                    image_details.setText((String) exif.get(0x0132));
                }else{
                    image_details.setText("value is not available");
                }
            } else if (image_camera.isSelected()) {
                if (exif.get(0x0110)!= null) {
                    image_details.setText((String) exif.get(0x0110));
                }else{
                    image_details.setText("value is not available");
                }
            } else {
                double[] coord = javaxt_image.getGPSCoordinate();
                if (coord != null) {
                    String location =  coord[0] + ", " + coord[1];
                    image_details.setText(location);
                }
            }
        }else{
            image_details.setText(" please select the image");
        }
    }

    @FXML
    void loadImage(ActionEvent event) throws FileNotFoundException {

//        <******************* Check the formats the library takes *******************>
//        String[] inputFormats = javaxt.io.Image.InputFormats;
//        String[] outputFormats = javaxt.io.Image.OutputFormats;
//
//        for(int i = 0; i < inputFormats.length ; i++){ System.out.println("in " + inputFormats[i]);}
//        for(int i = 0; i < outputFormats.length ; i++){ System.out.println("out " + outputFormats[i]);}

//        creating file chooser instance. :: File chooser is a dialog that enables the user to select one or
//        more files via File Explorer from user's local computer

        image_details.setText("");
        error_label.setText(" ");

        FileChooser file_chooser = new FileChooser();
        file_chooser.setTitle("Choose an Image");

        Stage stage_window = (Stage)anchorBasePane.getScene().getWindow();

//        saving the file now
         image_loaded_file =  file_chooser.showSaveDialog(stage_window);

        if(image_loaded_file != null){
//            Getting absolute path of the image
              System.out.println("file path is :::: " + image_loaded_file.getAbsolutePath());
              file_path = image_loaded_file.getAbsolutePath();

            //              Getting extension of the file
            int index = file_path.lastIndexOf(".");

            if(index != -1){
                System.out.println("check if contains " + format_list.contains(file_extension) + file_extension);
                file_extension = file_path.substring(index).toUpperCase();
                current_format.setText(file_extension);

//                checking if given input is image or not
                if(format_list.contains(file_extension)) {
                    //              Adding the input image to image view
                    image = new Image(file_path);
                    loadedImage_view.setImage(image);

                    // making thumbnails active on image load => to get its details
                    image_thumbnails.setSelected(true);
                }
                else{
                    error_label.setText("invalid file type. Please select only Image formats" + "\n" + "need to be either formats" + format_list);
                    loadedImage_view.setImage(null);
                }
            }
     }
        else{
            error_label.setText("Please choose the file");
            loadedImage_view.setImage(null);
        }
    }


    @FXML
    void change_format(ActionEvent event) throws IOException {
        if(image_loaded_file != null) {
            System.out.println("in change format func");
            System.out.println("In " + choose_Format.getValue());
//    calling the change format method from parent class
            changeformat(file_path,choose_Format.getValue());

//            String new_file_format = file_path.substring(0, file_path.lastIndexOf(".")) + " " + choose_Format.getValue();
//            new javaxt.io.Image(file_path).saveAs(new_file_format + "." + choose_Format.getValue());

        }else{
            error_label.setText("Please choose the file");
            loadedImage_view.setImage(null);
        }

// ************************************** Other way to change the format  ***********************
//        FileInputStream inputStream = new FileInputStream(file_path);
//        FileOutputStream outputStream = new FileOutputStream(new_file_format);
//
//        // reads input image from file
//        BufferedImage inputImage = ImageIO.read(inputStream);
//
//        // writes to the output image in specified format
//        boolean result = ImageIO.write(inputImage, "." +choose_Format.getValue(), outputStream);
//
//        // needs to close the streams
//        outputStream.close();
//        inputStream.close();

//        System.out.println("the value of result is " + result);

    }

    /// to set the value of the dropdown on page load ==> using interface concept
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        ObservableList<String> output_format_list = FXCollections.observableArrayList("GIF", "JPG", "JPEG", "PNG", "TIF", "TIFF");
        ObservableList<String> output_format_list = FXCollections.observableArrayList(format_list);
        choose_Format.setItems(output_format_list);

    }
}


