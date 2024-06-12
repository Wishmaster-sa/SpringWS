/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ega.SpringWS.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import lombok.Data;
import org.json.JSONObject;

/**
 * Цей клас налаштувань. 
 * Налаштування йдуть з файла-налаштувань який називається webservice.settings, дані в ньому зберігаються в форматі JSON
 * @author sa
 */
@Data
public class AppSettings {
    private String logFileName;
    private int logLevel;
    
    //конструктор класу замовчки
    public AppSettings() {
        this.logFileName ="springWS.log";
        this.logLevel = 0;
        LoadSettings();
    }

    private void SaveSettings() {
        try {
          FileWriter settingsFile = new FileWriter("webservice.settings");
          settingsFile.write(this.toJSON().toString());
          settingsFile.close();
          System.out.println("Successfully create settings file.");
        } catch (IOException e) {
          System.out.println("An error occurred while setting file created\n"+e.getMessage());
        }
    
    }
    
    private void LoadSettings(){
        String text = "";
        
        try {
            File settingsFile = new File("webservice.settings");
            Scanner myReader = new Scanner(settingsFile);
            while(myReader.hasNext()){
                text+=myReader.nextLine();
            }
            myReader.close();
           } catch (FileNotFoundException e) {
             SaveSettings();
             System.out.println("An error occurred while load settings file: "+e.getMessage());
           }
        
        try{
            JSONObject json = new JSONObject(text);
            this.fromJSON(json);
        }catch(Exception ex){
            SaveSettings();
             System.out.println("Text:\n"+text+"\n is not valid JSON!\nError:"+ex.getMessage());
        }
        
    }
    
    //метод який перетворює об'єкт в JSON
    public JSONObject toJSON(){
        JSONObject ret = new JSONObject();
        ret.put("logFileName", logFileName);
        ret.put("logLevel", logLevel);
        
        return ret;
    }

    //метод який перетворює JSON в об'єкт класу
    public AppSettings fromJSON(JSONObject json){
       
        this.setLogFileName(json.optString("logFileName", ""));
        this.setLogLevel(json.optInt("logLevel", 0));
        
        
        return this;
    }
 
    
}
