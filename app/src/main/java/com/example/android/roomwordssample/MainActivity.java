package com.example.android.roomwordssample;

/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View.OnClickListener;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.GridLayout;

import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;


public class MainActivity extends AppCompatActivity implements WordListAdapter.OnWordListener, DeleteOptionDialog.DeleteOptionDialogListener {

    public static final String SELECTION_REPLY = "com.example.android.wordlistsql.SELECTION";
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    private WordViewModel mWordViewModel;

    public boolean deleteCONFIRM = FALSE;

    Word deleteWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       if(getIntent().getAction().contains("DELETE"))
        {
            toolbar.setBackgroundColor(getResources().getColor(R.color.deleteColor));
        }
        //RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true); //Line to improve performance
        final WordListAdapter adapter = new WordListAdapter(this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,6)); //Change this to change # of columns

        //Selection Tracker
        //SelectionTracker tracker

        // Get a new or existing ViewModel from the ViewModelProvider.
        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);

        //Unload Intent to get Category
        String chosenCat = getIntent().getExtras().getString("CAT_KEY");

        if(chosenCat.equals("BASIC")) {
            mWordViewModel.getBasicCatWords().observe(this, new Observer<List<Word>>() {
                @Override
                public void onChanged(@Nullable final List<Word> words) {
                    // Update the cached copy of the words in the adapter.
                    adapter.setWords(words);
                    setTitle("Basic");
                    //Toast.makeText(
                      //      getApplicationContext(),
                        //    "Category 1",
                          //  Toast.LENGTH_LONG).show();
                }
            });
        } //If Basic
        else if(chosenCat.equals("ALL")) {
            mWordViewModel.getAllWords().observe(this, new Observer<List<Word>>() {
                @Override
                public void onChanged(@Nullable final List<Word> words) {
                    // Update the cached copy of the words in the adapter.
                    adapter.setWords(words);
                    setTitle("All");
                   // Toast.makeText(
                     //       getApplicationContext(),
                       //     DIR,
                         //   Toast.LENGTH_LONG).show();
                }
            });
        } //Else if all
        else if(chosenCat.equals("PLAY")) {
            mWordViewModel.getPlayCatWords().observe(this, new Observer<List<Word>>() {
                @Override
                public void onChanged(@Nullable final List<Word> words) {
                    // Update the cached copy of the words in the adapter.
                    adapter.setWords(words);
                    setTitle("Play");
                   // Toast.makeText(
                     //       getApplicationContext(),
                       //     "Category 2",
                         //   Toast.LENGTH_LONG).show();
                }
            });
        } //Else if play
        else if(chosenCat.equals("FOOD")) {
            mWordViewModel.getFoodCatWords().observe(this, new Observer<List<Word>>() {
                @Override
                public void onChanged(@Nullable final List<Word> words) {
                    // Update the cached copy of the words in the adapter.
                    adapter.setWords(words);
                    setTitle("Food");
                    // Toast.makeText(
                    //       getApplicationContext(),
                    //     "Category 2",
                    //   Toast.LENGTH_LONG).show();
                }
            });
        } //Else if food
        else if(chosenCat.equals("READING")) {
            mWordViewModel.getReadingCatWords().observe(this, new Observer<List<Word>>() {
                @Override
                public void onChanged(@Nullable final List<Word> words) {
                    // Update the cached copy of the words in the adapter.
                    adapter.setWords(words);
                    setTitle("Reading");
                    // Toast.makeText(
                    //       getApplicationContext(),
                    //     "Category 2",
                    //   Toast.LENGTH_LONG).show();
                }
            });
        } //Else if reading
        else if(chosenCat.equals("MATH")) {
            mWordViewModel.getMathCatWords().observe(this, new Observer<List<Word>>() {
                @Override
                public void onChanged(@Nullable final List<Word> words) {
                    // Update the cached copy of the words in the adapter.
                    adapter.setWords(words);
                    setTitle("Math");
                    // Toast.makeText(
                    //       getApplicationContext(),
                    //     "Category 2",
                    //   Toast.LENGTH_LONG).show();
                }
            });
        } //Else if math
        else if(chosenCat.equals("OLIVIAPRIV")) {
            mWordViewModel.getOPCatWords().observe(this, new Observer<List<Word>>() {
                @Override
                public void onChanged(@Nullable final List<Word> words) {
                    // Update the cached copy of the words in the adapter.
                    adapter.setWords(words);
                    setTitle("Privileges");
                    // Toast.makeText(
                    //       getApplicationContext(),
                    //     "Category 2",
                    //   Toast.LENGTH_LONG).show();
                }
            });
        } //Else if Olivia's Privileges
        else if(chosenCat.equals("SPEECHLANG")) {
            mWordViewModel.getSpeechLangCatWords().observe(this, new Observer<List<Word>>() {
                @Override
                public void onChanged(@Nullable final List<Word> words) {
                    // Update the cached copy of the words in the adapter.
                    adapter.setWords(words);
                    setTitle("Speech and Language");
                    // Toast.makeText(
                    //       getApplicationContext(),
                    //     "Category 2",
                    //   Toast.LENGTH_LONG).show();
                }
            });
        } //Else if Speech and Language
        else if(chosenCat.equals("SENSORY")) {
            mWordViewModel.getSensoryCatWords().observe(this, new Observer<List<Word>>() {
                @Override
                public void onChanged(@Nullable final List<Word> words) {
                    // Update the cached copy of the words in the adapter.
                    adapter.setWords(words);
                    setTitle("Sensory");
                    // Toast.makeText(
                    //       getApplicationContext(),
                    //     "Category 2",
                    //   Toast.LENGTH_LONG).show();
                }
            });
        } //Else if Sensory
        else if(chosenCat.equals("FINEMOTOR")) {
            mWordViewModel.getFineMotorCatWords().observe(this, new Observer<List<Word>>() {
                @Override
                public void onChanged(@Nullable final List<Word> words) {
                    // Update the cached copy of the words in the adapter.
                    adapter.setWords(words);
                    setTitle("Fine Motor");
                    // Toast.makeText(
                    //       getApplicationContext(),
                    //     "Category 2",
                    //   Toast.LENGTH_LONG).show();
                }
            });
        } //Else if Fine Motor
        else if(chosenCat.equals("PEOPLE")) {
            mWordViewModel.getPeopleCatWords().observe(this, new Observer<List<Word>>() {
                @Override
                public void onChanged(@Nullable final List<Word> words) {
                    // Update the cached copy of the words in the adapter.
                    adapter.setWords(words);
                    setTitle("People");
                    // Toast.makeText(
                    //       getApplicationContext(),
                    //     "Category 2",
                    //   Toast.LENGTH_LONG).show();
                }
            });
        } //Else if People
        else if(chosenCat.equals("PLACES")) {
            mWordViewModel.getPlacesCatWords().observe(this, new Observer<List<Word>>() {
                @Override
                public void onChanged(@Nullable final List<Word> words) {
                    // Update the cached copy of the words in the adapter.
                    adapter.setWords(words);
                    setTitle("Places");
                    // Toast.makeText(
                    //       getApplicationContext(),
                    //     "Category 2",
                    //   Toast.LENGTH_LONG).show();
                }
            });
        } //Else if Places
        else if(chosenCat.equals("SOCIALEMOT")) {
            mWordViewModel.getSocialEmotCatWords().observe(this, new Observer<List<Word>>() {
                @Override
                public void onChanged(@Nullable final List<Word> words) {
                    // Update the cached copy of the words in the adapter.
                    adapter.setWords(words);
                    setTitle("Social/Emotional");
                    // Toast.makeText(
                    //       getApplicationContext(),
                    //     "Category 2",
                    //   Toast.LENGTH_LONG).show();
                }
            });
        } //Else if Social/Emotional
        else if(chosenCat.equals("OTHER")) {
            mWordViewModel.getOtherCatWords().observe(this, new Observer<List<Word>>() {
                @Override
                public void onChanged(@Nullable final List<Word> words) {
                    // Update the cached copy of the words in the adapter.
                    adapter.setWords(words);
                    setTitle("Other");
                    // Toast.makeText(
                    //       getApplicationContext(),
                    //     "Category 2",
                    //   Toast.LENGTH_LONG).show();
                }
            });
        } //Else if play

       /* FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewWordActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });*/
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            //Word word = new Word(data.getStringExtra(NewWordActivity.WORD_REPLY));
            //Word word = new Word(data.getStringExtra(NewWordActivity.WORD_REPLY),data.getStringExtra(NewWordActivity.CAT_REPLY));
            Word word = new Word(data.getStringExtra(NewWordActivity.WORD_REPLY),data.getStringExtra(NewWordActivity.CAT_REPLY), data.getStringExtra(NewWordActivity.PIC_REPLY),data.getStringExtra(NewWordActivity.THUMB_REPLY));

            mWordViewModel.insert(word);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    } //onActivityResult

    @Override
    public void onWordClick(Word clicked) {
        //Navigate to new activity
        //Use intent


        if(getIntent().getAction().contains("CHOOSE")) {
            Toast.makeText(
                    getApplicationContext(),
                    clicked.getWord(),
                    Toast.LENGTH_LONG).show();

            Intent replyIntent = new Intent();
            replyIntent.putExtra(SELECTION_REPLY, clicked.getPicture());
            setResult(RESULT_OK, replyIntent);
            finish();
        }
        else if(getIntent().getAction().contains("DELETE"))
        {
            deleteWord = clicked;

            showNoticeDialog(clicked.getWord());
            /*
            if(deleteCONFIRM == TRUE) {
                mWordViewModel.delete(clicked);
                Toast.makeText(
                        getApplicationContext(),
                        clicked.getWord() + " Deleted",
                        Toast.LENGTH_LONG).show();
                mWordViewModel.delete(clicked);
                */

                //finish();
            }

        }


    public void showNoticeDialog(String name) {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new DeleteOptionDialog();

        Bundle args = new Bundle();
        args.putString("name",name);
        dialog.setArguments(args);

        dialog.show(getFragmentManager(), "DeleteOptionDialog");
    }

    // The dialog fragment receives a reference to this Activity through the
    // Fragment.onAttach() callback, which it uses to call the following methods
    // defined by the NoticeDialogFragment.NoticeDialogListener interface
    @Override
    public void onDialogPositiveClick() {
        mWordViewModel.delete(deleteWord);
        Toast.makeText(
                getApplicationContext(),
              "Deleted " + deleteWord.getWord(),
                Toast.LENGTH_LONG).show();

        Intent replyIntent = new Intent();
        setResult(RESULT_OK, replyIntent);
        finish();
    }

    @Override
    public void onDialogNegativeClick() {
        Toast.makeText(
                getApplicationContext(),
                "Canceled Deleting Option",
                Toast.LENGTH_LONG).show();
    }

}
