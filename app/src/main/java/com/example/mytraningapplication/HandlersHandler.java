package com.example.mytraningapplication;

import android.graphics.Canvas;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class HandlersHandler extends Handler<FiguresHandler>{




    public HandlersHandler(){
        super.tasks = new LinkedList<FiguresHandler>();
        super.values = new ArrayList<String>();
    }

    /**
     * Do all tasks of child FiguresHandlers
     * @param canvas
     * @param paintValue Fill or stroke
     */
    @Override
    public void implementAll(Canvas canvas, String paintValue) {
        //init tasks
        LinkedList<FiguresHandler> tasks = super.getTasks();
        for (FiguresHandler item:
                tasks) {
            item.implementAll(canvas, paintValue);
        }
    }

    /**
     * Do all tasks of child FiguresHandlers with fill stile
     * @param canvas
     */
    @Override
    public void implementAll(Canvas canvas){
        //init tasks
        LinkedList<FiguresHandler> tasks = super.getTasks();

        //init values
        ArrayList<String> values = super.getValues();
        for (FiguresHandler item:
             tasks) {
            item.implementAll(canvas, values.get(tasks.indexOf(item)));
        }
    }

    /**
     * save handler to single file
     * @param path
     */
    @Override
    public void save(String path){
        FileOutputStream fos = null;
        try {

            //init file
            File jsonData = new File(path);
            if (!jsonData.exists()) {
                jsonData.createNewFile();
            }

            //init writer
            fos = new FileOutputStream(path,false);

            //with Gson serialise handler to string
            Gson gson = new Gson();
            String text = gson.toJson(this);

            //write created string to file
            fos.write(text.getBytes());
            fos.flush();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        finally{
            try{
                if(fos!=null)
                    fos.close();
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }

    /**
     * load last saved handler
     * @param path
     */
    @Override
    public void load(String path){
        FileInputStream inputStream = null;
        try {

            //init file
            File jsonData = new File(path);
            if (!jsonData.exists()) {
                jsonData.createNewFile();
            }

            //init reader
            inputStream = new FileInputStream(jsonData);
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedStream = new BufferedReader(streamReader);

            //write string from file
            String text = bufferedStream.readLine();

            //with Gson deserialize read string to handler
            Gson gson = new Gson();
            HandlersHandler handler = gson.fromJson(text,HandlersHandler.class);

            //load deserialized string to this handler
            this.tasks = handler.getTasks();
            this.values = handler.getValues();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        finally{
            try{
                if(inputStream!=null)
                    inputStream.close();
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }
}
