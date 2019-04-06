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

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;


public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private OnWordListener mOnWordListener;

    //Constructor
    WordListAdapter(Context context, OnWordListener onWordListener) {
        mInflater = LayoutInflater.from(context);
        this.mOnWordListener = onWordListener;
    }

    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView wordItemView;
        private final ImageView wordImage;
        OnWordListener onWordListener;

        private WordViewHolder(View itemView, OnWordListener onWordListener) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
            wordImage = itemView.findViewById(R.id.image);
            this.onWordListener = onWordListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Word clicked = mWords.get(getAdapterPosition());
            onWordListener.onWordClick(clicked);
            //onWordListener.onWordClick(getAdapterPosition());

        }
    }

    private final LayoutInflater mInflater;
    private List<Word> mWords = Collections.emptyList(); // Cached copy of words


    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView, mOnWordListener);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {

        //Word Title
        final Word current = mWords.get(position);

        //Picture
        //Code to Get Picture, convert, and place goes here.
        String imageDIR = current.getPicture();
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        Bitmap imageBitmap = BitmapFactory.decodeFile(imageDIR, bitmapOptions);

        //Set Image and Word

        holder.wordImage.setImageBitmap(imageBitmap);
        holder.wordItemView.setText(current.getWord());
    }

    void setWords(List<Word> words) {
        mWords = words;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mWords.size();
    }

    public interface OnWordListener {
        void onWordClick(Word clicked);
    }
/*
    private static class loadPicAsyncTask extends AsyncTask<Word, Void, Void> {

        private Word mWord;

        loadPicAsyncTask(Word word) {
            mWord = word;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            String imageDIR = mWord.getPicture();
            BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
            Bitmap imageBitmap = BitmapFactory.decodeFile(imageDIR, bitmapOptions);
            return null;
        }
    }
*/
}


