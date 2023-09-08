package com.example.android.deutschlernen;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Arbeit extends AppCompatActivity {
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
        setContentView(R.layout.activity_arbeit);
        // Create a list of words
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word(" ", "Der Koch", R.drawable.koch,R.raw.koch));
        words.add(new Word(" ", "Der Entwickler", R.drawable.entwickler,R.raw.entwickler));
        words.add(new Word(" ", "Der Artz",R.drawable.arzt,R.raw.arzt));
        words.add(new Word(" ", "Der Lehrer", R.drawable.lehrer,R.raw.lehrer));
        words.add(new Word(" ", "Der Richter", R.drawable.richter,R.raw.richter));
        words.add(new Word(" ", "Der Zeichner", R.drawable.zeichner,R.raw.zeichner));
        words.add(new Word(" ", "Der Bauer", R.drawable.bauer,R.raw.bauer));
        words.add(new Word(" ", "Der Militär", R.drawable.militar,R.raw.militar));
        words.add(new Word(" ", "Der Ingenieur", R.drawable.ingenieur,R.raw.ingenieur));
        words.add(new Word(" ", "Der Astronaut", R.drawable.astronaut,R.raw.astronaut));
        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_weekdayes);

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
                mMediaPlayer = MediaPlayer.create(Arbeit.this, word.getAudioResourceId());
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