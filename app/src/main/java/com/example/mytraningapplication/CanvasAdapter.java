package com.example.mytraningapplication;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class CanvasAdapter extends ArrayAdapter<FiguresHandler> {

    //bitmaps for images
    Bitmap[] outBit;

    //height of every figure
    int height;

    /**
     *
     * @param context ListView for array
     * @param arr from this object it gets array
     * @param height height of every object
     */
    public CanvasAdapter(Context context, HandlersHandler arr, int height) {
        super(context, R.layout.activity_canv, arr.getTasks());
        outBit = new Bitmap[arr.getTasks().size()];
        this.height = height;
    }

    /**
     * repit for every element
     * @param position index of element
     * @param convertView element
     * @param parent activity
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //get handler with information from inner list in "position"
        FiguresHandler handler = getItem(position);

        //get layout
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.activity_canv, null);
        }

        //nullify stack
        Drawer.fromStart();

        //create bitmap for output
        outBit[position] = Bitmap.createBitmap(
                //magic: get how much space will figures take  in 700

                //get maximal count of figures in 700
                (int)((int)(700 / (height * 1.1)) *
                        //get height of figure
                        Math.ceil(height * 1.1)),

                //magic: get how much pixels will be in height of bitmap

                //get count of figures
                (int)(Math.ceil((double)(handler.getTasks().size() /

                        //divide by maximal count of figures in 700(do not try to change it)
                        (Math.ceil((int)((int)(700 / (height * 1.1)) * Math.ceil(height * 1.1)) / (height * 1.1))))) *

                        //multiply by height of figure
                        (double)(height * 1.1)),
                Bitmap.Config.ARGB_8888);

        //create output
        Canvas canvas = new Canvas(outBit[position]);

        //draw all figures from "handler with information" on "output"
        handler.implementAll(canvas);

        //draw "output"
        ((ImageView) convertView.findViewById(R.id.imv)).setImageBitmap(outBit[position]);

        return convertView;
    }

}
