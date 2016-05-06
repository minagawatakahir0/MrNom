package mlab.androidgames.framework.impl;

import android.view.View;

import java.util.List;

import mlab.androidgames.framework.Input;

/**
 * Created by Takahiro on 2015/12/07.
 */
public interface TouchHandler extends View.OnTouchListener{
    boolean isTouchDown(int pointer);

    int getTouchX(int pointer);
    int getTouchY(int pointer);
    List<Input.TouchEvent> getTouchEvents();
}
