package com.example.android.deutschlernen;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Veggie extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veggie);

        // Create a list of words
       final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word(" ", "Die Tomate",R.drawable.toma,R.raw.tomate));
        words.add(new Word(" ", "Der Pfeffer", R.drawable.pfe,R.raw.pfeffer));
        words.add(new Word(" ", "Die Möhren", R.drawable.mohr,R.raw.mohren));
        words.add(new Word(" ", "Die Zwiebel", R.drawable.zwie,R.raw.zwiebel));
        words.add(new Word(" ", "Der Mais", R.drawable.mais,R.raw.mais));
        words.add(new Word(" ", "Der Brokkoli", R.drawable.brokk,R.raw.brokkoli));
        words.add(new Word(" ", "Die Gurke", R.drawable.gurk,R.raw.durke));
        words.add(new Word(" ", "Die Aubergine", R.drawable.aube,R.raw.aubergine));
        words.add(new Word(" ", "Der Knoblauch", R.drawable.knob,R.raw.knoblauch));
        words.add(new Word(" ", "Der Kopfsalat", R.drawable.kopfsa,R.raw.kopfsalat));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_themonths);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // activity_numbers.xml layout file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();
                // Get the {@link Word} object at the given position the user clicked on
                Word word = words.get(position);
                // Create and setup the {@link MediaPlayer} for the audio resource associated
                // with the current word
                mMediaPlayer = MediaPlayer.create(Veggie.this, word.getAudioResourceId());
                mMediaPlayer.start();
                // Setup a listener on the media player, so that we can stop and release the
                // media player once the sound has finished playing.
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });
    }
    @Override
    protected void onStop() {
        super.onStop();
        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
    }
    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }
}