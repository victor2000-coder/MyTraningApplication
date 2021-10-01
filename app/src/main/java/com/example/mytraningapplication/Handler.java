package com.example.mytraningapplication;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Handler<T> {
    //tasks, that handles this handler
    protected LinkedList<T> tasks;

    //arguments for "tasks, that handle this handler"
    protected ArrayList<String> values;

    public Handler(){
        tasks = new LinkedList<>();
        values = new ArrayList<>();
    }

    public Handler(int length){
        tasks = new LinkedList<T>();
        values = new ArrayList<>();
        fill(length);
    }

    protected void fill(int length){
        for (int i = 0; i < length; i++){
            this.tasks.add(null);
            this.values.add(null);
        }
    }

    public Handler(T[] tasks, String[] values){
        tasks = tasks;
        values = values;
    }

    /**
     * add task and value to handler
     * @param index where we add
     * @param task
     * @param value
     */
    public void add(int index, T task, String value){
        tasks.add(index,task);
        values.add(index,value);
    }

    public void add( T task, String value){
        tasks.add(task);
        values.add(value);
    }

    public void remove(int index){
        tasks.remove(index);
        values.remove(index);
    }

    public void removeLast(){
        tasks.remove(tasks.size() - 1);
        values.remove(tasks.size() - 1);
    }

    /**
     * @param moveTo: handler, where we move
     * @param index: index of element in handler, that we move
     */
    public void move(Handler moveTo, int index){
        moveTo.add(tasks.get(index),values.get(index));
        remove(index);
    }

    /**
     * remove all tasks and values from handler
     */
    public void clear(){
        tasks.clear();
        values.clear();
    }

    public abstract void implementAll(Canvas canvas, String paintValue);

    public abstract void implementAll(Canvas canvas);

    public LinkedList<T> getTasks(){
        return tasks;
    }
    public T getTask(int index){
        return tasks.get(index);
    }

    public ArrayList<String> getValues(){
        return values;
    }

    public void load(String path) {}
    public void save(String path) {}
    public void set(int index, T task, String value){
        this.tasks.set(index,task);
        this.values.set(index,value);
    }
}
