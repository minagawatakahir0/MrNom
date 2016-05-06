package mlab.androidgames.framework;

/**
 * Created by Takahiro on 2015/11/17.
 */

import mlab.androidgames.framework.Graphics.PixmapFormat;


public interface Pixmap {
    int getWidth();

    int getHeight();

    PixmapFormat getFormat();

    void dispose();

}
