package mlab.androidgames.framework.impl;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

import java.io.IOException;

import mlab.androidgames.framework.Music;

/**
 * Created by Takahiro on 2015/12/06.
 */
public class AndroidMusic implements Music, OnCompletionListener {
    MediaPlayer mediaPlayer;
    boolean isPrepared = false;

    public AndroidMusic (AssetFileDescriptor assetDescriptor){
        mediaPlayer = new MediaPlayer();

        try {
            mediaPlayer.setDataSource(assetDescriptor.getFileDescriptor(),
                                      assetDescriptor.getStartOffset(),
                                      assetDescriptor.getLength());

            mediaPlayer.prepare();
            isPrepared = true;
            mediaPlayer.setOnCompletionListener(this);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load music");
        }

    }
    @Override
    public void play() {
        if ( mediaPlayer.isPlaying()){
            return;
        }
        try {
            mediaPlayer.prepare();

            synchronized (this) {
                if (!isPrepared)
                    mediaPlayer.start();
                mediaPlayer.start();
            }

        } catch (IllegalStateException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
    }

    @Override
    public void pause() {
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }

    }

    @Override
    public void setLooping(boolean Looping) {
        mediaPlayer.setLooping(isLooping());
    }

    @Override
    public void setVolume(float volume) {
        mediaPlayer.setVolume(volume,volume);
    }

    @Override
    public boolean isPlaying() {
        return  mediaPlayer.isPlaying();
    }

    @Override
    public boolean isStopped() {
        return !isPrepared;
    }

    @Override
    public boolean isLooping() {
        return mediaPlayer.isLooping();
    }

    @Override
    public void dispose() {
        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        synchronized (this){
            isPrepared = false;
        }
    }
}
