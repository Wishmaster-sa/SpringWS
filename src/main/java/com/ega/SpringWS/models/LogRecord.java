/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ega.SpringWS.models;

import java.time.LocalDateTime;
import lombok.Data;
import org.json.JSONObject;

/**
 * Це модель запису в лог-файл. 
 * Він містить інформацію стосовно часу коли відбулася подія, IP, HTTP метод, ресурс який запрошував запит та все інше.
 * @author E-Rigi Academy
 */
@Data
public class LogRecord {
    private LocalDateTime dateTime;
    private String ip;
    private String httpMethod;
    private String resource;
    private String body;
    private boolean isError;
    private String descr;
    private Answer result;

    //Конструктор класу замовчки
    public LogRecord(){
        this.dateTime = LocalDateTime.now();
        this.ip = "localhost";
        this.httpMethod = "";
        this.resource = "";
        this.body = "";
        this.isError = true;
        this.descr = "log record is not init!";
                
    }
    
    //перетворює об'єкт класу в JSON
    public JSONObject toJSON(){
        JSONObject jsData = new JSONObject();
        jsData.put("dateTime",getDateTime());
        jsData.put("ip",getIp());
        jsData.put("httpMethod",getHttpMethod());
        jsData.put("resource",getResource());
        jsData.put("isError",isError());
        jsData.put("descr",getDescr());
        jsData.put("result",getResult());
        jsData.put("body",body);
        
        return jsData;
    }
}
