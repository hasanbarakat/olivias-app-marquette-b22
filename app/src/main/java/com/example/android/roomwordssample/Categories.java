package com.example.android.roomwordssample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//Create Intent
//Create a bundle
//Send bundle

public class Categories extends AppCompatActivity {
    //Flags for Category
    public static final int CAT_KEY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        final Button allButton = findViewById(R.id.button_all);
        allButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categories.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("CAT_KEY","ALL");
                intent.putExtras(bundle);
                startActivity(intent);
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
                startActivity(intent);
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
                startActivity(intent);
            }
        }); //PlayButton Listener
    }//OnCreate
}
