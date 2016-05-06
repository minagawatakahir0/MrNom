package mlab.androidgames.framework;

/**
 * Created by Takahiro on 2015/11/17.
 */
public interface Game {
    Input getInput();
    FileIO getFileIO();
    Graphics getGraphics();
    Audio getAudio();
    void setScreen(Screen screen);
    Screen getCurrentScreen();
    Screen getStartScreen();

}
