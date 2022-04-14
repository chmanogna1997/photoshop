package com.example.photoshop;

public class Changeformat {
    // using encapsulation concept to hide the data
    private String file_path;
    private String new_format;

    void changeformat(String file_path, String new_format){
        System.out.println("in changeformat variables");
        String new_file_format = file_path.substring(0, file_path.lastIndexOf(".")) + " " + new_format;
        new javaxt.io.Image(file_path).saveAs(new_file_format + "." + new_format);
    }
}
