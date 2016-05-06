package mlab.androidgames.framework;

/**
 * Created by Takahiro on 2015/11/17.
 */
public interface Audio {

    Music newMusic(String filename);

    Sound newSound(String filename);
}
