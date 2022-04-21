package com.example.photoshop;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

public class ShowImageDetails {

    private  javaxt.io.Image javaxt_image;
    private   java.util.HashMap<Integer, Object> exif;

    private RadioButton image_thumbnails;
    private RadioButton image_date;
    private RadioButton image_camera;
    private RadioButton image_GPS;

    private Label image_details;
    private Label error_label;

    ShowImageDetails(RadioButton image_thumbnails,
                     RadioButton image_date, RadioButton image_camera,
                     RadioButton image_GPS, Label image_details, Label error_label ){
        this.image_thumbnails = image_thumbnails;
        this.image_date = image_date;
        this.image_camera = image_camera;
        this.image_GPS = image_GPS;
        this.image_details = image_details;
        this.error_label = error_label;
    }

    public void showdetails(){
        if(loadimage.file_path != null) {
            this.error_label.setText(null);
            System.out.println("in herere showing details");
            load_image_using_library();
            if (image_thumbnails.isSelected()) {
                image_details.setText(show_thumb_nails());
            } else if (image_date.isSelected()) {
                image_details.setText(show_image_date());
            } else if (image_camera.isSelected()) {
                image_details.setText(show_camera_details());
            } else {
                image_details.setText(show_coordinates());
            }
        }else{
            new Error(error_label,"Please load an image").showError();
        }
    }

    private void load_image_using_library(){
        String output = null;
        if(loadimage.file_path != null){
            javaxt_image = new javaxt.io.Image(loadimage.file_path);
            exif = javaxt_image.getExifTags();
        }else{
            new Error(error_label,"Please choose an image");
        }
    }

    private   String show_thumb_nails(){
        String output = null;
        if(javaxt_image.getHeight()!=0  && javaxt_image.getWidth()!=0) {
       output = (String.valueOf(javaxt_image.getHeight()) + " X " + String.valueOf(javaxt_image.getWidth()));
        }else{
            output = "value is not available";
        }
        return  output;
    }

    private String show_image_date(){
        String output;
        if (exif.get(0x0132)!= null) {
           output = (String) exif.get(0x0132);
        }else{
           output = "value is not available";
        }
        return output;
    }

    private String show_camera_details(){
        String output;
        if (exif.get(0x0110)!= null) {
            output = (String) exif.get(0x0110);
        }else{
            output = ("value is not available");
        }
        return output;
    }

    private String show_coordinates(){
        String output;
        double[] coord = javaxt_image.getGPSCoordinate();
        if (coord != null) {
            String location =  String.format("%.4g%n", coord[0]) + ", " + String.format("%.4g%n", coord[1]);
            output = (location);
        }else{
            output = ("value is not available");
        }
        return  output;
    }




}
