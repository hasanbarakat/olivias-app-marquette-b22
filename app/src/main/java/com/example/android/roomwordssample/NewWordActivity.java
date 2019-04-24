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

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Activity for entering a word.
 */

public class NewWordActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String WORD_REPLY = "com.example.android.wordlistsql.WORD";
    public static final String CAT_REPLY = "com.example.android.wordlistsql.CAT";
    public static final String PIC_REPLY = "com.example.android.wordlistsql.PIC";
    public static final String THUMB_REPLY = "com.example.android.wordlistsql.THUMB";

    static final int REQUEST_IMAGE_CAPTURE = 2;
    static final int REQUEST_IMAGE_CHOOSE = 3;

    //DIR for Pictures
    //final String picsDIR = getDir("Pictures", 0).toString();

    private EditText mEditWordView;
    String wordCategory;
    String wordPhotoDIR;
    String ThumbnailDIR;

    final int thumbSize = 480;


    // Create the File where the photo should go
    File photoFile = null;
    File thumbnailFile = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
        mEditWordView = findViewById(R.id.edit_word);

        //Category Spinner
        Spinner categories = findViewById(R.id.category_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.word_categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categories.setAdapter(adapter);
        categories.setOnItemSelectedListener(this);

        //Take Image From Camera Button
        final Button TakePicButton = findViewById(R.id.button_getPic_from_camera);
        TakePicButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TakePictureIntent();
            }
        });

        //Choose Image From Gallery Button
        //final Button ChoosePicButton = findViewById(R.id.button_getPic_from_gallery);
        /*ChoosePicButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ChoosePictureIntent();
            }
        });
*/
        //Save Button
        final Button saveButton = findViewById(R.id.button_save);

        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(wordPhotoDIR != null) {
                    Intent replyIntent = new Intent();
                    if (TextUtils.isEmpty(mEditWordView.getText())) {
                        setResult(RESULT_CANCELED, replyIntent);
                    } else {
                        String word = mEditWordView.getText().toString();

                        //Send Info Back to Main Activity for Addition to Database
                        replyIntent.putExtra(WORD_REPLY, word);
                        replyIntent.putExtra(CAT_REPLY, wordCategory);
                        replyIntent.putExtra(PIC_REPLY, wordPhotoDIR);
                        replyIntent.putExtra(THUMB_REPLY, ThumbnailDIR);
                        setResult(RESULT_OK, replyIntent);
                    }
                    finish();
                }
                else {
                    Toast.makeText(
                            getApplicationContext(),
                            "Need to Include Photo Before Saving!",
                            Toast.LENGTH_LONG).show();
                }
                }
        });

    }

    //Methods for Category Spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        wordCategory = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //Methods for Camera Intent
    private void TakePictureIntent(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //MediaStore.ACTION_IMAGE_CAPTURE
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

            try {
                photoFile = createImageFile();
                //Save path
                wordPhotoDIR = photoFile.getAbsolutePath();

                thumbnailFile = createImageFile();
                //Save path
                ThumbnailDIR = thumbnailFile.getAbsolutePath();

            } catch (IOException ex) {
                // Error occurred while creating the File
                Toast.makeText(
                        getApplicationContext(),
                        R.string.error_photo_file,
                        Toast.LENGTH_LONG).show();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {


                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.roomwordsample.fileprovider",
                        photoFile);

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }

        }
    }
    //Method for Choosing picture from file
    private void ChoosePictureIntent(){
        try {
            photoFile = createImageFile();
            //Save path
            wordPhotoDIR = photoFile.getAbsolutePath();

            thumbnailFile = createImageFile();
            //Save path
            ThumbnailDIR = thumbnailFile.getAbsolutePath();
        } catch (IOException ex) {
            // Error occurred while creating the File
            Toast.makeText(
                    getApplicationContext(),
                    R.string.error_photo_file,
                    Toast.LENGTH_LONG).show();
        }
        // Continue only if the File was successfully created
        if (photoFile != null) {

            Uri photoURI = FileProvider.getUriForFile(this,
                    "com.example.android.roomwordsample.fileprovider",
                    photoFile);

            Intent ChoosePictureIntent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(ChoosePictureIntent, REQUEST_IMAGE_CHOOSE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //Preview Image
        ImageView picPreview = findViewById(R.id.picPreview);
        Bitmap ThumbnailBitmap;

        //Full Size
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
            Bitmap imageBitmap = BitmapFactory.decodeFile(wordPhotoDIR,bitmapOptions);
            bitmapOptions.inJustDecodeBounds = true;

            //Compress
            ByteArrayOutputStream photoStream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 20,photoStream);
            //Write Compressed Photo to file
            try {

                FileOutputStream fileOutputStream = new FileOutputStream(photoFile);
                fileOutputStream.write(photoStream.toByteArray());
                fileOutputStream.flush();
                fileOutputStream.close();

            } catch (IOException ex) {
                // Error occurred while creating the File
                Toast.makeText(
                        getApplicationContext(),
                        R.string.error_photo_file,
                        Toast.LENGTH_LONG).show();
            }


            //Thumbnail
            ThumbnailBitmap= ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(wordPhotoDIR),
                    thumbSize, thumbSize);

            try {
                bitmapOptions.inJustDecodeBounds = true;

                //Compress
                ByteArrayOutputStream ThumbnailStream = new ByteArrayOutputStream();
                ThumbnailBitmap.compress(Bitmap.CompressFormat.JPEG, 50,ThumbnailStream);

                //Save to DB

                FileOutputStream fileOutputStream = new FileOutputStream(thumbnailFile);
                fileOutputStream.write(ThumbnailStream.toByteArray());
                fileOutputStream.flush();
                fileOutputStream.close();

                //picPreview.setImageBitmap(ThumbnailBitmap);

            }catch (IOException ex) {
                // Error occurred while creating the File
                Toast.makeText(
                        getApplicationContext(),
                        R.string.error_photo_file,
                        Toast.LENGTH_LONG).show();
            }


            picPreview.setImageBitmap(imageBitmap);
        }

        else if (requestCode == REQUEST_IMAGE_CHOOSE && resultCode == RESULT_OK) {

            Uri photoURI = data.getData();
            //File imageFile = new File(photoURI);


            BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
            try {
                Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                bitmapOptions.inJustDecodeBounds = true;

                //Compress
                ByteArrayOutputStream photoStream = new ByteArrayOutputStream();
                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 20,photoStream);
                picPreview.setImageBitmap(imageBitmap);

                //Save to DB

                FileOutputStream fileOutputStream = new FileOutputStream(photoFile);
                fileOutputStream.write(photoStream.toByteArray());
                fileOutputStream.flush();
                fileOutputStream.close();

            }catch (IOException ex) {
                // Error occurred while creating the File
                Toast.makeText(
                        getApplicationContext(),
                        R.string.error_photo_file,
                        Toast.LENGTH_LONG).show();
            }
            /*
            Uri photoURI = data.getData();
            Bitmap imageBitmap;
            //File imageFile = new File(photoURI);

            //Full Size
            BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
            try {
                imageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                bitmapOptions.inJustDecodeBounds = true;

                //Compress
                ByteArrayOutputStream photoStream = new ByteArrayOutputStream();
                //ByteArrayOutputStream ThumbnailStream = new ByteArrayOutputStream();

                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 50,photoStream);

                //ThumbnailBitmap= ThumbnailUtils.extractThumbnail(imageBitmap,thumbSize, thumbSize);

                picPreview.setImageBitmap(imageBitmap);
                //picPreview.setImageBitmap(ThumbnailBitmap);

                //Save to DB

                FileOutputStream fileOutputStream = new FileOutputStream(photoFile);
                fileOutputStream.write(photoStream.toByteArray());
                fileOutputStream.flush();
                fileOutputStream.close();

                /*
                FileOutputStream fileOutputStreamT = new FileOutputStream(thumbnailFile);
                fileOutputStreamT.write(ThumbnailStream.toByteArray());
                fileOutputStreamT.flush();
                fileOutputStreamT.close();


            }catch (IOException ex) {
                // Error occurred while creating the File
                Toast.makeText(
                        getApplicationContext(),
                        R.string.error_photo_file,
                        Toast.LENGTH_LONG).show();
            }*/


        }
    }

    private File createImageFile() throws IOException{

        //Create Name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "word_" + timeStamp;
        File storageDIR = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
            imageFileName, //Prefix
            ".jpg",  //Suffix
            storageDIR    //Directory
        );


        return image;

    }

}

