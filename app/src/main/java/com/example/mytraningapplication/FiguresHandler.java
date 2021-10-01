package com.example.mytraningapplication;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class FiguresHandler extends Handler<FiguresTasks>{

    public FiguresHandler(){
        super.tasks = new LinkedList<FiguresTasks>();
        super.values = new ArrayList<String>();
    }

    public FiguresHandler(int length){
        super.tasks = new LinkedList<>();
        super.values = new ArrayList<>();
        fill(length);
    }
    public FiguresHandler(LinkedList<FiguresTasks> tasks, ArrayList<String> values){
        super.tasks = tasks;
        super.values = values;
    }
    /**
     * draw all figures on canvas
     * @param canvas
     * @param paintValue fill or stroke
     */
    @Override
    public void implementAll(Canvas canvas, String paintValue){
        //get tasks from handler
        LinkedList<FiguresTasks> tasks = super.getTasks();

        //get values from handler
        ArrayList<String> values = super.getValues();

        //stile of drawing
        Paint paintTool = new Paint();
        paintTool.setStyle(paintValue == "Fill" ? Paint.Style.FILL : Paint.Style.STROKE);
        paintTool.setColor(Color.BLUE);

        //params from "values"
        int param1 = 0;
        int param2 = 0;
        int param3 = 0;

        //task from tasks
        FiguresTasks task;
        for(int i = 0; i < tasks.size();i++){
            //init task
            task = tasks.get(i);
            try {
                //init params
                param1 = Integer.parseInt(values.get(i).split(",")[0]);
                param2 = Integer.parseInt(values.get(i).split(",")[1]);
                param3 = Integer.parseInt(values.get(i).split(",")[2]);
            }catch(Exception ex){

            }

            switch (task){

                case DRAW_DOUBLE_FAT_SPIRAL:
                    Drawer.Spirals.drawDoubleFatSpiral(param1,canvas,paintTool);
                    break;
                case DRAW_TRIPLE_FAT_SPIRAL:
                    Drawer.Spirals.drawTripleFatSpiral(param1,canvas,paintTool);
                    break;
                case DRAW_CUSTOM_SPIRAL:
                    Drawer.Spirals.drawCustomSpiral(param1,param2,param3,canvas,paintTool);
                    break;
                case DRAW_DOUBLE_SPIRAL:
                    Drawer.Spirals.drawDoubleSpiral(param1,canvas,paintTool);
                    break;
                case DRAW_SPIRAL:
                    Drawer.Spirals.drawSpiral(param1,canvas,paintTool);
                    break;
                case DRAW_DOUBLE_CIRCLE:
                    Drawer.Circles.drawDoubleCircle(param1,canvas,paintTool);
                    break;
                case DRAW_DOUBLE_FAT_CIRCLE:
                    Drawer.Circles.drawDoubleFatCircle(param1,canvas,paintTool);
                    break;
                case NULL:
                    break;
            }
        }
    }

    /**
     * draw all figures on canvas with fill style
     * @param canvas
     */
    @Override
    public void implementAll(Canvas canvas) {
        LinkedList<FiguresTasks> tasks = super.getTasks();                                          //get tasks from handler
        ArrayList<String> values = super.getValues();                                               //get values from handler

        //how we draw
        Paint paintTool = new Paint();
        paintTool.setStyle(Paint.Style.FILL);

        //params from "values"
        int param1 = 0;
        int param2 = 0;
        int param3 = 0;
        FiguresTasks task;                                                                          //task from tasks
        for(int i = 0; i < tasks.size();i++){
            task = tasks.get(i);                                                                    //init tasks
            try {
                //init params
                param1 = Integer.parseInt(values.get(i).split(",")[0]);
                param2 = Integer.parseInt(values.get(i).split(",")[1]);
                param3 = Integer.parseInt(values.get(i).split(",")[2]);
            }catch(Exception ex){

            }

            switch (task){

                case DRAW_DOUBLE_FAT_SPIRAL:
                    Drawer.Spirals.drawDoubleFatSpiral(param1,canvas,paintTool);
                    break;
                case DRAW_TRIPLE_FAT_SPIRAL:
                    Drawer.Spirals.drawTripleFatSpiral(param1,canvas,paintTool);
                    break;
                case DRAW_CUSTOM_SPIRAL:
                    Drawer.Spirals.drawCustomSpiral(param1,param2,param3,canvas,paintTool);
                    break;
                case DRAW_DOUBLE_SPIRAL:
                    Drawer.Spirals.drawDoubleSpiral(param1,canvas,paintTool);
                    break;
                case DRAW_SPIRAL:
                    Drawer.Spirals.drawSpiral(param1,canvas,paintTool);
                    break;
                //case DRAW_FIBONACCI_SPIRAL:
                //    Drawer.Spirals.drawFibonacciSpiral(param1,canvas,paintTool);
                //    break;
                case DRAW_DOUBLE_CIRCLE:
                    Drawer.Circles.drawDoubleCircle(param1,canvas,paintTool);
                    break;
                case DRAW_DOUBLE_FAT_CIRCLE:
                    Drawer.Circles.drawDoubleFatCircle(param1,canvas,paintTool);
                    break;
            }
        }
    }

    /**
     *
     * @param count
     * @param maxHeight
     * generate random figures from Drawer.Spirals and Drawer.Circles
     * minimal height of figures = 25
     */
    public void generateRandomValues(int count, int maxHeight){
        Random r = new Random();
        String tempValues;
        for(int i = 0; i < count; i++){
            int operation = r.nextInt(FiguresTasks.values().length - 1);
            if(operation == 2) {
                tempValues = (r.nextInt(maxHeight - 25) + 26) + "," + (r.nextInt(4) + 1) + "," + (r.nextInt(10) + 1);
            }else{
                tempValues = ((r.nextInt(maxHeight - 25) + 26) + "");
            }
            add(FiguresTasks.values()[operation],tempValues);
        }
    }

    /**
     *
     * @param count
     * @param maxHeight
     * @param minHeight
     * generate random figures from Drawer.Spirals and Drawer.Circles
     */
    public void generateRandomValues(int count, int maxHeight, int minHeight){
        Random r = new Random();
        String tempValues;
        for(int i = 0; i < count; i++){
            int operation = r.nextInt(FiguresTasks.values().length - 1);
            if(operation == 2) {
                tempValues = (r.nextInt(maxHeight - minHeight) + minHeight + 1) + "," + (r.nextInt(4) + 1) + "," + (r.nextInt(10) + 1);
            }else{
                tempValues = ((r.nextInt(maxHeight - minHeight) + minHeight + 1) + "");
            }
            add(FiguresTasks.values()[operation],tempValues);
        }
    }

    /**
     *
     * @param count
     * generate random figures from Drawer.Spirals and Drawer.Circles
     * minimal height of figures = 25
     * maximal height of figures = 100
     */
    public void generateRandomValues(int count){
        Random r = new Random();
        String tempValues;
        for(int i = 0; i < count; i++){
            int operation = r.nextInt(FiguresTasks.values().length);
            if(operation == 2) {
                tempValues = (r.nextInt(75) + 26) + "," + (r.nextInt(4) + 1) + "," + (r.nextInt(10) + 1);
            }else{
                tempValues = ((r.nextInt(75) + 26) + "");
            }
            add(FiguresTasks.values()[operation],tempValues);
        }
    }
}
