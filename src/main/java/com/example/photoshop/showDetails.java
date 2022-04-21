package com.example.photoshop;

public class showDetails {

    public  javaxt.io.Image javaxt_image;
    public   java.util.HashMap<Integer, Object> exif;

    public void load_image_using_library(){
        if(loadimage.file_path != null){
            javaxt_image = new javaxt.io.Image(loadimage.file_path);
            exif = javaxt_image.getExifTags();
        }
    }

    public   String show_thumb_nails(){
        String output;
        if(javaxt_image.getHeight()!=0  && javaxt_image.getWidth()!=0) {
            output = (javaxt_image.getHeight() + " X " + javaxt_image.getWidth());
        }else{
            output = "value is not available";
        }
        return  output;
    }

    public String show_image_date(){
        String output;
        if (exif.get(0x0132)!= null) {
            output = (String) exif.get(0x0132);
        }else{
            output = "value is not available";
        }
        return output;
    }

    public String show_camera_details(){
        String output;
        if (exif.get(0x0110)!= null) {
            output = (String) exif.get(0x0110);
        }else{
            output = ("value is not available");
        }
        return output;
    }

    public String show_coordinates(){
        String output;
        double[] coord = javaxt_image.getGPSCoordinate();
        if (coord != null) {
            output = (String.format("%.4g%n", coord[0]) + ", " + String.format("%.4g%n", coord[1]));
        }else{
            output = ("value is not available");
        }
        return  output;
    }

}
