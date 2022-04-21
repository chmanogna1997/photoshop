package com.example.photoshop;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

//introducing concept of inheritance
public class ShowImageDetailsInterface extends showDetails {

    private RadioButton image_thumbnails;
    private RadioButton image_date;
    private RadioButton image_camera;
    private RadioButton image_GPS;

    public Label image_details;
    public Label error_label;

    ShowImageDetailsInterface(RadioButton image_thumbnails,
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
}
