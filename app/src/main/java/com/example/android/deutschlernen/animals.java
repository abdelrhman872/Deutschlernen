package com.example.android.deutschlernen;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class animals extends AppCompatActivity {
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
        setContentView(R.layout.activity_phrases);

        // Create a list of words
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word(" ", "Das Pferd", R.drawable.hor, R.raw.pfe));
        words.add(new Word(" ", "Die Kuh ", R.drawable.kuh, R.raw.kuh));
        words.add(new Word(" ", "Der Hahn", R.drawable.hah, R.raw.hah));
        words.add(new Word(" ", "Das Schaf", R.drawable.sch, R.raw.scha));
        words.add(new Word(" ", "Der Löwe", R.drawable.low, R.raw.low));
        words.add(new Word(" ", "Der Tiger", R.drawable.tig, R.raw.tig));
        words.add(new Word(" ", "Der Hund", R.drawable.hun, R.raw.hun));
        words.add(new Word(" ", "Die Katze", R.drawable.kat, R.raw.kat));
        words.add(new Word(" ", "Der Fisch", R.drawable.fisch, R.raw.fis));
        words.add(new Word(" ", "Der Bär", R.drawable.bar, R.raw.bar));
        words.add(new Word(" ", "Der Pinguin", R.drawable.ping, R.raw.ping));
        words.add(new Word(" ", "Der Schmetterling", R.drawable.schm, R.raw.schm));
        words.add(new Word(" ", "Die Biene", R.drawable.bien, R.raw.bie));
        words.add(new Word(" ", "Die Ziege", R.drawable.zie, R.raw.zie));
        words.add(new Word(" ", "Der Elefant", R.drawable.elef, R.raw.elef));


        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_phrases);

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
                mMediaPlayer = MediaPlayer.create(animals.this, word.getAudioResourceId());
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
