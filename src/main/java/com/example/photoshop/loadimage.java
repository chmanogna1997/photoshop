package com.example.photoshop;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;


public class loadimage {

    private AnchorPane anchorBasePane;
    private  File image_loaded_file;
    private  Image image;
    private  ImageView loadedImage_view;
    private  Label current_format;
    private Label error_label;
    public static String file_path = null;

//   constructor
    loadimage(AnchorPane anchorpane, ImageView loadedImage_view, Label current_format, Label error_label){
        this.anchorBasePane = anchorpane;
        this.loadedImage_view = loadedImage_view;
        this.current_format = current_format;
        this.error_label = error_label;
    }

    //  loading the image

    public void loadImage(){
        this.error_label.setText(null);
        openFileExplorer();
        check_ifImage_getPath();
    }


    // opening the file explorer
    public  void openFileExplorer(){
        FileChooser file_chooser = new FileChooser();
        file_chooser.setTitle("choose only an image");

        Stage stage_window = (Stage)anchorBasePane.getScene().getWindow();

        // saving the file
        image_loaded_file =  file_chooser.showSaveDialog(stage_window);
    }
// checking if any file is selected
    public String  check_filePath(){
        if(image_loaded_file != null){return  image_loaded_file.getAbsolutePath();}
        else{return  null;
        }
    };


//  get the file path if the loaded file is image and load the image to the pane
    public void check_ifImage_getPath(){
       if(check_filePath() == null){
          System.out.println("E1");
           loadedImage_view.setImage(null);
           new Error(error_label,"file not choosen").showError();
//           ***************** error message :: E1 === Please select a file
       }
       else{
           int index = check_filePath().lastIndexOf(".");
           if(index == -1){
               System.out.println("E2");
               loadedImage_view.setImage(null);
               new Error(error_label, "Please choose image file").showError();
//              ************** error message :: E2  "Please choose an image";
           }else{
               try{
                   String current_fileExtension = check_filePath().substring(index+1).toUpperCase();
                   fileExtensions.valueOf(current_fileExtension);
                   current_format.setText(current_fileExtension);
//                       // calling load image to pane method
                         loadImage_to_pane();
//                       // setting up the path
                       file_path = check_filePath();

               }
               catch  (IllegalArgumentException ex){
                   new Error(error_label,"Choosen file is not an valid image format").showError();
               }
           }
       }
    }

    // loading image to pane
    public void loadImage_to_pane(){
        //              Adding the input image to image view
        image = new Image(check_filePath());
        loadedImage_view.setImage(image);
    }




}
