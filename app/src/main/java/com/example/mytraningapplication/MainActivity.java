package com.example.mytraningapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    //button to start draw new figures
    Button btnNextLine;

    //button to go to encrypting
    Button btnEnc;

    //button to save
    Button btnSave;

    //button to load
    Button btnLoad;

    //button to delete last figure
    Button btnDelSymbol;

    //button to delete last line
    Button btnDelLine;

    //List of buttons to change what it shall been drawn
    ListView tasksList;

    //counter of canvases to draw in list
    Integer counter = 0;

    //height of figures
    static final int height = 60;

    //handler to handle canvases to draw on list
    HandlersHandler gettersHandler;

    //list to handle, what user wrote in last line
    LinkedList<FiguresHandler> getter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setting
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.change_actions_text_title);

        //initialise variables

        //get from activity: button to go to next line
        btnNextLine = (Button)findViewById(R.id.btnNextLine);

        //get from activity: button to save
        btnSave = (Button)findViewById(R.id.btnSave);

        //get from activity: button to load
        btnLoad = (Button)findViewById(R.id.btnLoad);

        ////get from activity: button to go to encrypting
        //btnEnc = (Button)findViewById(R.id.btnToEncrypt);

        //get from activity: List of buttons to change what it shall been drawn
        tasksList = (ListView)findViewById(R.id.enter);

        //get from activity: button to delete last figure
        btnDelSymbol = (Button)findViewById(R.id.btnDelSymbol);

        //get from activity: button to delete last line
        btnDelLine = (Button)findViewById(R.id.btnDelLine);

        //initialise list to handle, what user wrote in last line (it`s just buffer)
        getter = new LinkedList<>();

        //initialise handler to handle canvases to draw on list
        gettersHandler = new HandlersHandler();

        //add first line
        firstLine();

        //initialise events

        //listener of click on: List of buttons to change what it shall been drawn
        tasksList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                //get name of item, that was be clicked
                Object o = tasksList.getItemAtPosition(position);
                String str=(String)o;

                //add figure to: list to handle, what user wrote in last line
                getter.get(counter).add(FiguresTasks.valueOf(str), height + ",1,4");

                //make a toast
                Toast.makeText(getApplicationContext(),
                        str.replace('_', ' ').toLowerCase(),
                        Toast.LENGTH_SHORT).show();

                //add: list to handle, what user wrote in last line to: handler to handle canvases to draw on list
                gettersHandler.set(counter, getter.get(counter),"Fill");

                //set values from main handler to list on activity
                setValues(gettersHandler);
            }
        });

        //listener of click on: button to go to next line
        btnNextLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //create new line

                counter++;
                getter.add(new FiguresHandler());

                //add new line

                //clear current line buffer
                getter.get(counter).clear();

                //make first object in new line
                getter.get(counter).add(FiguresTasks.NULL,null);

                //add: list to handle, what user wrote in last line to: handler to handle canvases to draw on list
                gettersHandler.add(getter.get(counter),"Fill");

                //set values from main handler to list on activity
                setValues(gettersHandler);
            }
        });

        //listener to click on: button to save current handler to handle canvases to draw on list
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFile();
            }
        });

        //listener to click on: button to load last saved handler to handle canvases to draw on list
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFile();
            }
        });

        //btnEnc.setOnClickListener(new View.OnClickListener() {                                      //listener of click on: "button to go to encrypting"
        //    @Override
        //    public void onClick(View v) {
        //        Intent encryptingIntent = new Intent(MainActivity.this,
        //                EncryptingActivity.class);
        //        startActivity(encryptingIntent);
        //    }
        //});
        btnDelLine.setOnClickListener(new View.OnClickListener() {                                  //listener of click on: "button to go to encrypting"
            @Override
            public void onClick(View v) {
                try {
                    delLine();
                }catch (IndexOutOfBoundsException ex){
                    Toast.makeText(getApplicationContext(),"what are trying to do???",Toast.LENGTH_LONG);
                }
            }
        });
        btnDelSymbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    getter.get(counter).removeLast();
                    gettersHandler.set(counter, getter.get(counter), "Fill");
                    setValues(gettersHandler);
                }catch (IndexOutOfBoundsException ex){
                    delLine();
                }
            }
        });
    }



    protected void fillGetter(int length){
        getter.clear();
        for (int i = 0; i <= length; i++){
            getter.add(new FiguresHandler());
        }
    }

    /**
     * save current handler to handle canvases to draw on list
     */
    public void saveFile(){
        gettersHandler.save(getFilesDir() + String.valueOf(R.string.path_to_save));
    }


    /**
     * load last saved handler to handle canvases to draw on list
     */
    protected void loadFile(){

        gettersHandler.load(getFilesDir() + String.valueOf(R.string.path_to_save));
        counter = gettersHandler.getTasks().size() -1;
        fillGetter(counter);
        getter.set(counter, gettersHandler.getTasks().get(counter));

        setValues(gettersHandler);
    }

    /**
     * set values from main handler to list on activity
     * @param gettersHandler main handler
     */
    protected void setValues(HandlersHandler gettersHandler){

        //with
        CanvasAdapter cAdapter = new CanvasAdapter(this, gettersHandler, height);

        //on
        ListView lv = (ListView) findViewById(R.id.outer);
        lv.setAdapter(cAdapter);

        //it`s all
        System.out.println("its all");
    }

    /**
     * delete last line from outer list
     */
    protected void delLine(){
        try {
            //delete line
            gettersHandler.removeLast();
            getter.remove();

            //reintialise variables
            counter--;
            getter.set(counter, gettersHandler.getTask(counter));

            //final
            setValues(gettersHandler);
        }catch (IndexOutOfBoundsException ex){
            //if if was single line on list, clear handler and make single clear line
            getter.clear();
            firstLine();
        }
    }

    /**
     * void to draw first line
     */
    protected void firstLine(){
        //create buffer variables
        FiguresHandler starterHandler = new FiguresHandler();
        starterHandler.add(FiguresTasks.NULL, null);

        //add new line with null object
        getter.add(starterHandler);
        gettersHandler.add(starterHandler,"Fill");

        //final
        setValues(gettersHandler);
    }
}