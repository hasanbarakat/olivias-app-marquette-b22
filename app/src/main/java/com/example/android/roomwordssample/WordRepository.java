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

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

/**
 * Abstracted Repository as promoted by the Architecture Guide.
 * https://developer.android.com/topic/libraries/architecture/guide.html
 */

class WordRepository {

    //Strings for Categories
    private String CAT_BASIC = "Basic";
    private String CAT_PLAY = "Play";
    private String CAT_FOOD = "Food";
    private String CAT_READING = "Reading";
    private String CAT_MATH = "Math";
    private String CAT_O_P = "Privileges";
    private String CAT_SPEECH_LANG = "Speech and Language";
    private String CAT_FINE_MOTOR = "Fine Motor";
    private String CAT_SENSORY = "Sensory";
    private String CAT_PEOPLE = "People";
    private String CAT_PLACES = "Places";
    private String CAT_SOCIAL_EMOT = "Social/Emotional";
    private String CAT_OTHER = "Other";

    private WordDao mWordDao;
    private Word mWord;
    private LiveData<List<Word>> mAllWords;
    private LiveData<List<Word>> mBasicCatWords;
    private LiveData<List<Word>> mPlayCatWords;
    private LiveData<List<Word>> mFoodCatWords;
    private LiveData<List<Word>> mReadingCatWords;
    private LiveData<List<Word>> mMathCatWords;
    private LiveData<List<Word>> mOPCatWords;
    private LiveData<List<Word>> mSpeechLangCatWords;
    private LiveData<List<Word>> mFineMotorCatWords;
    private LiveData<List<Word>> mSensoryCatWords;
    private LiveData<List<Word>> mPeopleCatWords;
    private LiveData<List<Word>> mPlacesCatWords;
    private LiveData<List<Word>> mSocialEmotCatWords;
    private LiveData<List<Word>> mOtherCatWords;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        //mWord = mWordDao.findWord(String search)
        mAllWords = mWordDao.getAlphabetizedWords();
        mBasicCatWords = mWordDao.findWordInCategory(CAT_BASIC);
        mPlayCatWords = mWordDao.findWordInCategory(CAT_PLAY);
        mFoodCatWords = mWordDao.findWordInCategory(CAT_FOOD);
        mOtherCatWords = mWordDao.findWordInCategory(CAT_OTHER);
        mReadingCatWords = mWordDao.findWordInCategory(CAT_READING);
        mMathCatWords = mWordDao.findWordInCategory(CAT_MATH);
        mOPCatWords = mWordDao.findWordInCategory(CAT_O_P);
        mSpeechLangCatWords = mWordDao.findWordInCategory(CAT_SPEECH_LANG);
        mFineMotorCatWords = mWordDao.findWordInCategory(CAT_FINE_MOTOR);
        mSensoryCatWords = mWordDao.findWordInCategory(CAT_SENSORY);
        mPeopleCatWords = mWordDao.findWordInCategory(CAT_PEOPLE);
        mPlacesCatWords = mWordDao.findWordInCategory(CAT_PLACES);
        mSocialEmotCatWords = mWordDao.findWordInCategory(CAT_SOCIAL_EMOT);
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    LiveData<List<Word>> getmBasicCatWords() {
        return mBasicCatWords;
    }
    LiveData<List<Word>> getmPlayCatWords() {
        return mPlayCatWords;
    }
    LiveData<List<Word>> getmFoodCatWords() {
        return mFoodCatWords;
    }
    LiveData<List<Word>> getmReadingCatWords() {
        return mReadingCatWords;
    }
    LiveData<List<Word>> getmMathCatWords() {
        return mMathCatWords;
    }
    LiveData<List<Word>> getmOPCatWords() {
        return mOPCatWords;
    }
    LiveData<List<Word>> getmSpeechLangCatWords() {
        return mSpeechLangCatWords;
    }
    LiveData<List<Word>> getmFineMotorCatWords() {
        return mFineMotorCatWords;
    }
    LiveData<List<Word>> getmSensoryCatWords() {
        return mSensoryCatWords;
    }
    LiveData<List<Word>> getmPeopleCatWords() {
        return mPeopleCatWords;
    }
    LiveData<List<Word>> getmPlacesCatWords() {
        return mPlacesCatWords;
    }
    LiveData<List<Word>> getmSocialEmotCatWords() {
        return mSocialEmotCatWords;
    }
    LiveData<List<Word>> getmOtherCatWords() {
        return mOtherCatWords;
    }


    // You must call this on a non-UI thread or your app will crash.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    void insert(Word word) {
        new insertAsyncTask(mWordDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    void delete(Word word) {
        new deleteAsyncTask(mWordDao).execute(word);
    }

    private static class deleteAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        deleteAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.deleteWord(params[0]);
            return null;
        }
    }
}