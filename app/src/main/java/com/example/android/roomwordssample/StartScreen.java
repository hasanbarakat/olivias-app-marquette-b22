package com.example.android.roomwordssample;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class StartScreen extends AppCompatActivity {

    public static final int WORD_1_REQUEST_CODE = 2;
    public static final int WORD_2_REQUEST_CODE = 3;

    ImageButton option1Button;
    ImageButton option2Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //Option 1
        option1Button = (ImageButton) findViewById(R.id.option1_button);
        option1Button.setBackground(null);
        option1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(StartScreen.this, Categories.class);
                startActivityForResult(intent1, WORD_1_REQUEST_CODE);
            }
        }); //option1Button Listener

        //Option 2
        option2Button = findViewById(R.id.option2_button);
        option2Button.setBackground(null);
        option2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(StartScreen.this, Categories.class);
                startActivityForResult(intent2, WORD_2_REQUEST_CODE);
            }
        }); //option2Button Listener


        //Labels
        final Button Option1Label = findViewById(R.id.option1_label);
        final Button Option2Label = findViewById(R.id.option2_label);

        //Option 1 Label
        Option1Label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    option2Button.setVisibility(View.INVISIBLE);
                    Option2Label.setVisibility(View.INVISIBLE);
            }
        }); //Option1Label Listener

        //Option 2 Label
        Option2Label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                option1Button.setVisibility(View.INVISIBLE);
                Option1Label.setVisibility(View.INVISIBLE);
            }
        }); //Option1Label Listener
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == WORD_1_REQUEST_CODE && resultCode == RESULT_OK) {

            //Code to Get Picture, convert, and place goes here.
            String imageDIR = data.getStringExtra(Categories.PIC_REPLY);//Get Image DIR From Intent
            BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
            Bitmap imageBitmap = BitmapFactory.decodeFile(imageDIR,bitmapOptions);


            //Set Image
           option1Button.setImageBitmap(imageBitmap);
        }
        else if(requestCode == WORD_2_REQUEST_CODE && resultCode == RESULT_OK) {
            //Code to Get Picture, convert, and place goes here.
            String imageDIR = data.getStringExtra(Categories.PIC_REPLY);//Get Image DIR From Intent
            BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
            Bitmap imageBitmap = BitmapFactory.decodeFile(imageDIR,bitmapOptions);


            //Set Image
            option2Button.setImageBitmap(imageBitmap);
        }
        else {
            Toast.makeText(
                    getApplicationContext(),
                    "Canceled",
                    Toast.LENGTH_LONG).show();
        }
    } //onActivityResult

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.start_screen_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clear_button:
                // User chooses clear button
                this.recreate(); //Restart current activity
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

}
