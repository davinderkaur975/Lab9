/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab9;

import java.io.File;

/**
 *
 * @author Davinder Kaur
 */
public class Car {
    private String make, model;
    private int year, mileage;
    private File imageFile; 
   

    public Car(String make, String model, int year, int mileage) {
        setMake(make);
        setModel(model);
        setYear(year);
        setMileage(mileage);
    }

    public Car(String make, String model, int year, int mileage, File imageFile) {
         this(make, model, year, mileage);
        setImageFile(imageFile);
    }

    public File getImageFile() {
        return imageFile;
    }

    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }
    
    

    

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }
    
      @Override
    public String toString(){
        return this.make+this.model+this.year+this.mileage;
    }
    
}

