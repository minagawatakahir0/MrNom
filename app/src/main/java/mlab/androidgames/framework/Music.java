package mlab.androidgames.framework;



/**
 * Created by Takahiro on 2015/11/17.
 */
public interface Music {
    void play();
    void stop();
    void pause();
    void setLooping(boolean Looping);
    void setVolume(float volume);
    boolean isPlaying();
    boolean isStopped();
    boolean isLooping();
    void dispose();
}
