package com.example.mytraningapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class Drawer extends View {
    /**
     *
     * @param context shall be canvas
     */
    public Drawer(Context context) {
        super(context);
    }

    //counter to keep information where drawer stay for Y
    static int numberOfPixelH = 0;

    //counter to keep information where we stay for X
    static int numberOfPixelW = 0;

    //counter to keep information of max height of figures on line
    static int maxHeiOnWid = 0;

    //canvas where drawer draws
    Canvas canvas;
    public void draw(FiguresHandler drawer){

        //draw all figures from drawer
        drawer.implementAll(canvas, "Fill");
    }

    /**
     * nullify values of position
     */
    public static void fromStart(){
        numberOfPixelW = 0;
        numberOfPixelH = 0;
        maxHeiOnWid = 0;
    }

    @Override
    protected void onDraw(Canvas canvas){
        this.canvas = canvas;
        super.onDraw(canvas);
    }

    public static class Spirals{

        public static void drawDoubleSpiral(int height, Canvas canvas, Paint paintTool)
                throws IndexOutOfBoundsException{
            Field arr = new Field(height);
            Figures.spiral(1,2,arr);
            Drawer.finallyDraw(canvas,paintTool,arr);
        }

        public static void drawTripleFatSpiral(int height, Canvas canvas, Paint paintTool)
                throws IndexOutOfBoundsException{
            Field arr = new Field(height);
            Figures.spiral(4,3,arr);
            Drawer.finallyDraw(canvas,paintTool,arr);
        }

        public static void drawSpiral(int height, Canvas canvas, Paint paintTool)
                throws IndexOutOfBoundsException{

            Field arr = new Field(height);
            Figures.spiral(1,1,arr);
            Drawer.finallyDraw(canvas,paintTool,arr);
        }

        public static void drawDoubleFatSpiral(int height, Canvas canvas, Paint paintTool)
                throws IndexOutOfBoundsException{

            Field arr = new Field(height);
            Figures.spiral(4,2,arr);
            Drawer.finallyDraw(canvas,paintTool,arr);
        }

        public static void drawCustomSpiral(int height, int width, int countOfCircles, Canvas canvas, Paint paintTool)throws IndexOutOfBoundsException{
            Field arr = new Field(height);
            Figures.spiral(width,countOfCircles,arr);
            Drawer.finallyDraw(canvas,paintTool,arr);
        }
    }

    public static class Circles{                                                                    //draw circles

        public static void drawDoubleFatCircle(int height, Canvas canvas, Paint paintTool){
            Field arr = new Field(height);
            Figures.circle(4,height / 2.2,arr);
            Figures.circle(4,height / 3,arr);
            Drawer.finallyDraw(canvas,paintTool,arr);
        }
        public static void drawDoubleCircle(int height, Canvas canvas, Paint paintTool){
            Field arr = new Field(height);
            Figures.circle(1,height / 2,arr);
            Figures.circle(1,height / 3,arr);
            Drawer.finallyDraw(canvas,paintTool,arr);
        }
    }

    /**
     *
     * @param canvas is - where drawer draws
     * @param paintTool is - how drawer draws
     * @param arr is - what drawer draws
     */
    protected static void finallyDraw(Canvas canvas, Paint paintTool, Field arr){
        //set elements
        int arrHeight = arr.getHeight();
        int arrWidth = arr.getWidth();

        //if line is over, go to next line
        if (numberOfPixelW + arrWidth >= canvas.getWidth()){
            numberOfPixelH += maxHeiOnWid + 5;
            numberOfPixelW = 0;
            maxHeiOnWid = 0;
        }

        //draw array on canvas
        int[][] outArray = arr.getValue();
        for(int y = 0; y < arrHeight; y++){
            for(int x = 0; x < arrWidth; x++){
                if(outArray[y][x] == 1)
                    canvas.drawRect(new Rect(
                            numberOfPixelW + x + 0,
                            numberOfPixelH + y,
                            numberOfPixelW + x + 1,
                            numberOfPixelH + y + 1), paintTool);
            }
        }

        //go to next column
        numberOfPixelW += arrWidth;

        //set max height on line
        if(maxHeiOnWid < arrHeight){
            maxHeiOnWid = arrHeight;
        }
    }
}
