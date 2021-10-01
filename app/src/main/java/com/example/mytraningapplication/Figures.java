package com.example.mytraningapplication;

import java.util.ArrayList;

public class Figures {
    /**
     * Draws spiral.
     * Circle is, when the spiral does one turnover
     */
    public static void spiral(int width, int countOfCircles, Field array)throws IndexOutOfBoundsException{
        double distanceForCircle = (array.getHeight() / 2 / countOfCircles);

        //every circle
        for(int numberOfCircle = 1; numberOfCircle <= countOfCircles; numberOfCircle++){
            //every degree
            for(int deg = 360; deg >= 0; deg--){

                int radius = (int)(distanceForCircle
                        * (countOfCircles - numberOfCircle) + (distanceForCircle / 360 * deg));
                try{
                    array.draw((int)(Math.cos(Math.toRadians(deg)) * radius) + array.getHeight() / 2,
                            (int)(Math.sin(Math.toRadians(deg)) * radius) + array.getHeight() / 2, width);
                }catch(IndexOutOfBoundsException e){

                }
            }
        }
    }
    public static void circle(int width, double radius, Field array){
        //every degree
        for(int deg = 360; deg >= 0; deg--){
            try{
                array.draw((int)(Math.cos(Math.toRadians(deg)) * radius) + array.getHeight() / 2,
                        (int)(Math.sin(Math.toRadians(deg)) * radius) + array.getHeight() / 2, width);
            }catch(IndexOutOfBoundsException e){

            }
        }

    }
}
