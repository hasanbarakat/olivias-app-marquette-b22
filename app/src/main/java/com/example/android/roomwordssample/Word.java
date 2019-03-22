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

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * A basic class representing an entity that is a row in a one-column database table.
 *
 * @ Entity - You must annotate the class as an entity and supply a table name if not class name.
 * @ PrimaryKey - You must identify the primary key.
 * @ ColumnInfo - You must supply the column name if it is different from the variable name.
 *
 * See the documentation for the full rich set of annotations.
 * https://developer.android.com/topic/libraries/architecture/room.html
 */

@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;

    @NonNull
    @ColumnInfo(name = "category")
    private String mCategory;

    @NonNull
    @ColumnInfo(name = "picture")
    private String mPicture;

    public Word(@NonNull String word, @NonNull String category, @NonNull String picture) {
    //public Word(@NonNull String word, @NonNull String category) {
    //public Word(@NonNull String word) {
        this.mWord = word;
        this.mCategory = category;
        this.mPicture = picture;
    }

    @NonNull
    public String getWord() {
        return this.mWord;
    }

    @NonNull
    public String getCategory() {
        return this.mCategory;
    }

    @NonNull
    public String getPicture() {

        return this.mPicture;
    }
/*

    @NonNull
    public String setWord() {
        return mWord;
    }

    @NonNull
    public String setCategory() {
        return mCategory;
    }

    @NonNull
    public String setPic() {

        return mPic;
    }
    */
}