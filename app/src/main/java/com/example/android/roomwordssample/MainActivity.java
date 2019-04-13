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

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
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


public class MainActivity extends AppCompatActivity implements WordListAdapter.OnWordListener {

    public static final String SELECTION_REPLY = "com.example.android.wordlistsql.SELECTION";
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    private WordViewModel mWordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        } //Else if play
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

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewWordActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            //Word word = new Word(data.getStringExtra(NewWordActivity.WORD_REPLY));
            //Word word = new Word(data.getStringExtra(NewWordActivity.WORD_REPLY),data.getStringExtra(NewWordActivity.CAT_REPLY));
            Word word = new Word(data.getStringExtra(NewWordActivity.WORD_REPLY),data.getStringExtra(NewWordActivity.CAT_REPLY), data.getStringExtra(NewWordActivity.PIC_REPLY));

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
        Toast.makeText(
                getApplicationContext(),
                clicked.getWord(),
                Toast.LENGTH_LONG).show();

        Intent replyIntent = new Intent();
        replyIntent.putExtra(SELECTION_REPLY, clicked.getPicture());
        setResult(RESULT_OK, replyIntent);
        finish();


    }
}
