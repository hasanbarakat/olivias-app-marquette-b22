package com.example.android.roomwordssample;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static com.example.android.roomwordssample.MainActivity.NEW_WORD_ACTIVITY_REQUEST_CODE;
//Create Intent
//Create a bundle
//Send bundle

public class Categories extends AppCompatActivity {
    //Flags for Category
    public static final int CAT_KEY = 1;

    public static final int WORD_REQUEST_CODE = 11;
    public static final int DELETE_REQUEST_CODE = 12;
    public static final String PIC_REPLY = "com.example.android.wordlistsql.PICSELECTION";
    public static final String DELETE_REPLY = "com.example.android.wordlistsql.DELETESELECTION";
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 2;



    private WordViewModel mWordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        setTitle("Library");

        // Get a new or existing ViewModel from the ViewModelProvider.
        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);



        //Buttons for Categories

        final Button allButton = findViewById(R.id.button_all);
        allButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categories.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("CAT_KEY","ALL");
                intent.putExtras(bundle);

                if(getIntent().getAction().contains("CHOOSE"))
                {
                    intent.setAction("CHOOSE");
                    startActivityForResult(intent,WORD_REQUEST_CODE);
                }
                else if(getIntent().getAction().contains("DELETE"))
                {
                    intent.setAction("DELETE");
                    startActivityForResult(intent,DELETE_REQUEST_CODE);
                }

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

                if(getIntent().getAction().contains("CHOOSE"))
                {
                    intent.setAction("CHOOSE");
                    startActivityForResult(intent,WORD_REQUEST_CODE);
                }
                else if(getIntent().getAction().contains("DELETE"))
                {
                    intent.setAction("DELETE");
                    startActivityForResult(intent,DELETE_REQUEST_CODE);
                }

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

                if(getIntent().getAction().contains("CHOOSE"))
                {
                    intent.setAction("CHOOSE");
                    startActivityForResult(intent,WORD_REQUEST_CODE);
                }
                else if(getIntent().getAction().contains("DELETE"))
                {
                    intent.setAction("DELETE");
                    startActivityForResult(intent,DELETE_REQUEST_CODE);
                }

            }
        }); //PlayButton Listener

        final Button FoodButton = findViewById(R.id.button_food);
        FoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categories.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("CAT_KEY","FOOD");
                intent.putExtras(bundle);

                if(getIntent().getAction().contains("CHOOSE"))
                {
                    intent.setAction("CHOOSE");
                    startActivityForResult(intent,WORD_REQUEST_CODE);
                }
                else if(getIntent().getAction().contains("DELETE"))
                {
                    intent.setAction("DELETE");
                    startActivityForResult(intent,DELETE_REQUEST_CODE);
                }

            }
        }); //FoodButton Listener

        final Button ReadingButton = findViewById(R.id.button_reading);
        ReadingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categories.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("CAT_KEY","READING");
                intent.putExtras(bundle);

                if(getIntent().getAction().contains("CHOOSE"))
                {
                    intent.setAction("CHOOSE");
                    startActivityForResult(intent,WORD_REQUEST_CODE);
                }
                else if(getIntent().getAction().contains("DELETE"))
                {
                    intent.setAction("DELETE");
                    startActivityForResult(intent,DELETE_REQUEST_CODE);
                }

            }
        }); //ReadingButton Listener

        final Button MathButton = findViewById(R.id.button_math);
        MathButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categories.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("CAT_KEY","MATH");
                intent.putExtras(bundle);

                if(getIntent().getAction().contains("CHOOSE"))
                {
                    intent.setAction("CHOOSE");
                    startActivityForResult(intent,WORD_REQUEST_CODE);
                }
                else if(getIntent().getAction().contains("DELETE"))
                {
                    intent.setAction("DELETE");
                    startActivityForResult(intent,DELETE_REQUEST_CODE);
                }

            }
        }); //MathButton Listener

        final Button OliviaPButton = findViewById(R.id.button_olivias_privileges);
        OliviaPButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categories.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("CAT_KEY","OLIVIAPRIV");
                intent.putExtras(bundle);

                if(getIntent().getAction().contains("CHOOSE"))
                {
                    intent.setAction("CHOOSE");
                    startActivityForResult(intent,WORD_REQUEST_CODE);
                }
                else if(getIntent().getAction().contains("DELETE"))
                {
                    intent.setAction("DELETE");
                    startActivityForResult(intent,DELETE_REQUEST_CODE);
                }

            }
        }); //OliviaPButton Listener

        final Button SpeechLangButton = findViewById(R.id.button_speech_language);
        SpeechLangButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categories.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("CAT_KEY","SPEECHLANG");
                intent.putExtras(bundle);

                if(getIntent().getAction().contains("CHOOSE"))
                {
                    intent.setAction("CHOOSE");
                    startActivityForResult(intent,WORD_REQUEST_CODE);
                }
                else if(getIntent().getAction().contains("DELETE"))
                {
                    intent.setAction("DELETE");
                    startActivityForResult(intent,DELETE_REQUEST_CODE);
                }

            }
        }); //SpeechLangButton Listener

        final Button SensoryButton = findViewById(R.id.button_sensory);
        SensoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categories.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("CAT_KEY","SENSORY");
                intent.putExtras(bundle);

                if(getIntent().getAction().contains("CHOOSE"))
                {
                    intent.setAction("CHOOSE");
                    startActivityForResult(intent,WORD_REQUEST_CODE);
                }
                else if(getIntent().getAction().contains("DELETE"))
                {
                    intent.setAction("DELETE");
                    startActivityForResult(intent,DELETE_REQUEST_CODE);
                }

            }
        }); //SensoryButton Listener

        final Button FineMotorButton = findViewById(R.id.button_fine_motor);
        FineMotorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categories.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("CAT_KEY","FINEMOTOR");
                intent.putExtras(bundle);

                if(getIntent().getAction().contains("CHOOSE"))
                {
                    intent.setAction("CHOOSE");
                    startActivityForResult(intent,WORD_REQUEST_CODE);
                }
                else if(getIntent().getAction().contains("DELETE"))
                {
                    intent.setAction("DELETE");
                    startActivityForResult(intent,DELETE_REQUEST_CODE);
                }

            }
        }); //FineMotorButton Listener

        final Button PeopleButton = findViewById(R.id.button_people);
        PeopleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categories.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("CAT_KEY","PEOPLE");
                intent.putExtras(bundle);

                if(getIntent().getAction().contains("CHOOSE"))
                {
                    intent.setAction("CHOOSE");
                    startActivityForResult(intent,WORD_REQUEST_CODE);
                }
                else if(getIntent().getAction().contains("DELETE"))
                {
                    intent.setAction("DELETE");
                    startActivityForResult(intent,DELETE_REQUEST_CODE);
                }

            }
        }); //PeopleButton Listener

        final Button SocialEmotButton = findViewById(R.id.button_social_emotional);
        SocialEmotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categories.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("CAT_KEY","SOCIALEMOT");
                intent.putExtras(bundle);

                if(getIntent().getAction().contains("CHOOSE"))
                {
                    intent.setAction("CHOOSE");
                    startActivityForResult(intent,WORD_REQUEST_CODE);
                }
                else if(getIntent().getAction().contains("DELETE"))
                {
                    intent.setAction("DELETE");
                    startActivityForResult(intent,DELETE_REQUEST_CODE);
                }

            }
        }); //SocialEmotButton Listener

        final Button PlacesButton = findViewById(R.id.button_places);
        PlacesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categories.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("CAT_KEY","PLACES");
                intent.putExtras(bundle);

                if(getIntent().getAction().contains("CHOOSE"))
                {
                    intent.setAction("CHOOSE");
                    startActivityForResult(intent,WORD_REQUEST_CODE);
                }
                else if(getIntent().getAction().contains("DELETE"))
                {
                    intent.setAction("DELETE");
                    startActivityForResult(intent,DELETE_REQUEST_CODE);
                }

            }
        }); //PlacesButton Listener

        final Button OtherButton = findViewById(R.id.button_other);
        OtherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categories.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("CAT_KEY","OTHER");
                intent.putExtras(bundle);

                if(getIntent().getAction().contains("CHOOSE"))
                {
                    intent.setAction("CHOOSE");
                    startActivityForResult(intent,WORD_REQUEST_CODE);
                }
                else if(getIntent().getAction().contains("DELETE"))
                {
                    intent.setAction("DELETE");
                    startActivityForResult(intent,DELETE_REQUEST_CODE);
                }

            }
        }); //OtherButton Listener


        FloatingActionButton fab = findViewById(R.id.fab);
        if(getIntent().getAction().contains("DELETE"))
        {
            fab.setVisibility(View.INVISIBLE);
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Categories.this, NewWordActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });
    }//OnCreate

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Intent replyIntent = new Intent();

        if (requestCode == WORD_REQUEST_CODE && resultCode == RESULT_OK) {

            replyIntent.putExtra(PIC_REPLY, data.getStringExtra(MainActivity.SELECTION_REPLY));
            setResult(RESULT_OK, replyIntent);
            finish();
        }
        if (requestCode == DELETE_REQUEST_CODE && resultCode == RESULT_OK) {
            setResult(RESULT_OK, replyIntent);
            finish();
        }
        else if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            //Word word = new Word(data.getStringExtra(NewWordActivity.WORD_REPLY));
            //Word word = new Word(data.getStringExtra(NewWordActivity.WORD_REPLY),data.getStringExtra(NewWordActivity.CAT_REPLY));
            Word word = new Word(data.getStringExtra(NewWordActivity.WORD_REPLY),data.getStringExtra(NewWordActivity.CAT_REPLY), data.getStringExtra(NewWordActivity.PIC_REPLY),data.getStringExtra(NewWordActivity.THUMB_REPLY));

            mWordViewModel.insert(word);
        }

        else {
            /*Toast.makeText(
                    getApplicationContext(),
                    "Canceled",
                    Toast.LENGTH_LONG).show();*/
        }
    } //onActivityResult
}
