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
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

/**
 * View Model to keep a reference to the word repository and
 * an up-to-date list of all words.
 */

public class WordViewModel extends AndroidViewModel {

    private WordRepository mRepository;
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
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

    public WordViewModel(Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
        mBasicCatWords = mRepository.getmBasicCatWords();
        mPlayCatWords = mRepository.getmPlayCatWords();
        mFoodCatWords = mRepository.getmFoodCatWords();
        mOtherCatWords = mRepository.getmOtherCatWords();
        mReadingCatWords = mRepository.getmReadingCatWords();
        mMathCatWords = mRepository.getmMathCatWords();
        mOPCatWords = mRepository.getmOPCatWords();
        mSpeechLangCatWords = mRepository.getmSpeechLangCatWords();
        mFineMotorCatWords = mRepository.getmFineMotorCatWords();
        mSensoryCatWords = mRepository.getmSensoryCatWords();
        mPeopleCatWords = mRepository.getmPeopleCatWords();
        mPlacesCatWords = mRepository.getmPlacesCatWords();
        mSocialEmotCatWords = mRepository.getmSocialEmotCatWords();
    }

    LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    LiveData<List<Word>> getBasicCatWords() {
        return mBasicCatWords;
    }

    LiveData<List<Word>> getPlayCatWords() {
        return mPlayCatWords;
    }
    LiveData<List<Word>> getFoodCatWords() {
        return mFoodCatWords;
    }
    LiveData<List<Word>> getReadingCatWords() {
        return mReadingCatWords;
    }
    LiveData<List<Word>> getMathCatWords() {
        return mMathCatWords;
    }
    LiveData<List<Word>> getOPCatWords() {
        return mOPCatWords;
    }
    LiveData<List<Word>> getSpeechLangCatWords() {
        return mSpeechLangCatWords;
    }
    LiveData<List<Word>> getFineMotorCatWords() {
        return mFineMotorCatWords;
    }
    LiveData<List<Word>> getSensoryCatWords() {
        return mSensoryCatWords;
    }
    LiveData<List<Word>> getPeopleCatWords() {
        return mPeopleCatWords;
    }
    LiveData<List<Word>> getPlacesCatWords() {
        return mPlacesCatWords;
    }
    LiveData<List<Word>> getSocialEmotCatWords() {
        return mSocialEmotCatWords;
    }
    LiveData<List<Word>> getOtherCatWords() {
        return mOtherCatWords;
    }
    void insert(Word word) {
        mRepository.insert(word);
    }
    void delete(Word word) {
        mRepository.delete(word);
    }
}