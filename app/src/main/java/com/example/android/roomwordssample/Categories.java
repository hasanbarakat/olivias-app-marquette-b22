package com.example.android.roomwordssample;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
//Create Intent
//Create a bundle
//Send bundle

public class Categories extends AppCompatActivity {
    //Flags for Category
    public static final int CAT_KEY = 1;

    public static final int WORD_REQUEST_CODE = 11;
    public static final String PIC_REPLY = "com.example.android.wordlistsql.PICSELECTION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        setTitle("Library");

        //Buttons for Categories

        final Button allButton = findViewById(R.id.button_all);
        allButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categories.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("CAT_KEY","ALL");
                intent.putExtras(bundle);
                startActivityForResult(intent,WORD_REQUEST_CODE);
            }
        }); //allButton Listener

        final Button BasicButton = findViewById(R.id.button_basic);
        BasicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categories.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("CAT_KEY","BASIC");
                intent.putExtras(bundle);
                startActivityForResult(intent,WORD_REQUEST_CODE);
            }
        }); //BasicButton Listener
        final Button PlayButton = findViewById(R.id.button_play);
        PlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categories.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("CAT_KEY","PLAY");
                intent.putExtras(bundle);
                startActivityForResult(intent,WORD_REQUEST_CODE);
            }
        }); //PlayButton Listener
    }//OnCreate

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Intent replyIntent = new Intent();

        if (requestCode == WORD_REQUEST_CODE && resultCode == RESULT_OK) {

            replyIntent.putExtra(PIC_REPLY, data.getStringExtra(MainActivity.SELECTION_REPLY));
            setResult(RESULT_OK, replyIntent);
            finish();
        }

        else {
            Toast.makeText(
                    getApplicationContext(),
                    "Canceled",
                    Toast.LENGTH_LONG).show();
        }
    } //onActivityResult
}
