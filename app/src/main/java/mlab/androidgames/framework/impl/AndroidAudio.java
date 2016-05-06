package mlab.androidgames.framework.impl;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import java.io.IOException;

import mlab.androidgames.framework.Audio;
import mlab.androidgames.framework.Music;
import mlab.androidgames.framework.Sound;

/**
 * Created by Takahiro on 2015/12/06.
 */
public class AndroidAudio implements Audio {
    AssetManager assets;
    SoundPool soundPool;

    public AndroidAudio(Activity activity){
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        this.assets = activity.getAssets();
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
            soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
        } else {
            AudioAttributes attr =new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setAudioAttributes(attr)
                    .setMaxStreams(20)
                    .build();
        }



    }
    @Override
    public Music newMusic(String filename) {
        try {
            AssetFileDescriptor assetDescriptor  = assets.openFd(filename);
            return new AndroidMusic(assetDescriptor);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load music '" + filename + "'");
        }
    }

    @Override
    public Sound newSound(String filename) {
        try {
            AssetFileDescriptor assetDescriptor = assets.openFd(filename);
            int soundId = soundPool.load(assetDescriptor, 0);
            return new AndroidSound(soundPool,soundId);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load sound '" + filename + "'");

        }
    }

}
