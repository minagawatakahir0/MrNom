package mlab.androidgames.framework.impl;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;

import mlab.androidgames.framework.Audio;
import mlab.androidgames.framework.FileIO;
import mlab.androidgames.framework.Game;
import mlab.androidgames.framework.Graphics;
import mlab.androidgames.framework.Input;
import mlab.androidgames.framework.Screen;
import android.os.PowerManager.WakeLock;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Takahiro on 2015/12/19.
 */
public abstract  class AndroidGame extends Activity implements Game {

    AndroidFastRenderView renderView;
    Graphics graphics;
    Audio audio;
    Input input;
    FileIO fileIO;
    Screen screen;
    WakeLock wakeLock;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);
        boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        int frameBufferWidth = isLandscape ? 480 : 320;
        int frameBufferHeight = isLandscape ? 320 : 480;
        Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth,frameBufferHeight, Bitmap.Config.RGB_565);

        float scaleX ;
        float scaleY ;

        Display display = getWindowManager().getDefaultDisplay();

        if((Integer.valueOf(Build.VERSION.SDK_INT)) < 13 ){
            scaleX = (float) frameBufferWidth / display.getWidth();
            scaleY = (float) frameBufferHeight / display.getHeight();
        } else {
            Point size = new Point();
            display.getSize(size);

            scaleX =(float) frameBufferWidth / size.x;
            scaleY =(float) frameBufferHeight / size.y;
        }

        renderView = new AndroidFastRenderView(this, frameBuffer);
        graphics = new AndroidGraphic(getAssets(),frameBuffer);
        fileIO = new AndroidFileIO(getAssets());
        audio = new AndroidAudio(this);
        input = new AndroidInput(this,renderView,scaleX,scaleY);
        screen = getStartScreen();
        setContentView(renderView);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    public  void onResume(){
        super.onResume();
        //wakeLock.acquire();
        screen.resume();
        renderView.resume();
    }

    @Override
    public void onPause(){
        super.onPause();
        //wakeLock.release();
        renderView.pause();
        screen.pause();

        if(isFinishing())
            screen.dispose();
    }


    @Override
    public Input getInput() {
        return input;
    }

    @Override
    public FileIO getFileIO() {
        return fileIO;
    }

    @Override
    public Graphics getGraphics() {
        return graphics;
    }

    @Override
    public Audio getAudio() {
        return audio;
    }

    @Override
    public void setScreen(Screen screen) {
        if(screen == null)
            throw new IllegalArgumentException("Screen must not be null");

        this.screen.pause();
        this.screen.dispose();
        screen.resume();
        screen.update(0);
        this.screen = screen;
    }

    @Override
    public Screen getCurrentScreen() {
        return screen;
    }

    @Override
    public Screen getStartScreen() {
        return null;
    }
}


