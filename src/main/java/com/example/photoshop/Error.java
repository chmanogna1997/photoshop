package com.example.photoshop;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class Error {

    private Label error_label;
    public String error;

    // constructor
    Error( Label error_label, String error ){
        System.out.println("in the error constructor");
      this.error_label = error_label;
      this.error = error;
    }

    public void showError(){
        System.out.println("the error is " + error);
        error_label.setText(error);
    }
}
