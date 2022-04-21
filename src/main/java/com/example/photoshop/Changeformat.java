package com.example.photoshop;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class Changeformat {
    // using encapsulation concept to hide the data
    private Label error_label;
    private String new_format;
    private ImageView loadedImage_view;


    public Changeformat(String new_format,  Label error_label){
        this.new_format = new_format;
        this.error_label = error_label;
    }

    public Changeformat(Label error_label, String new_format, ImageView loadedImage_view) {
        this.loadedImage_view =loadedImage_view;
        this.new_format = new_format;
        this.error_label = error_label;
    }


    void changeformat(){
        if(loadimage.file_path != null) {
            this.error_label.setText(null);
            System.out.println("the new format is " + new_format);
            if(new_format != null) {
                String file_path = loadimage.file_path;
                String new_file_format = file_path.substring(0, file_path.lastIndexOf(".")) + " " + new_format;
                new javaxt.io.Image(file_path).saveAs(new_file_format );
                error_label.setText("Image is downloaded Sucessfully");
            }else{
                new Error(error_label,"Please choose select the format you wanna convert to.." ).showError();
            }
        }else{
             new Error(error_label,"Please choose the file" ).showError();
             loadedImage_view.setImage(null);
        }
    }
}
