package mlab.androidgames.framework.impl;

import android.graphics.Bitmap;

import mlab.androidgames.framework.Graphics;
import mlab.androidgames.framework.Pixmap;

/**
 * Created by Takahiro on 2015/12/15.
 */
public class AndroidPixmap implements Pixmap {
    Bitmap bitmap;
    Graphics.PixmapFormat format;

    public AndroidPixmap(Bitmap bitmap, Graphics.PixmapFormat format){
        this.bitmap = bitmap;
        this.format = format;
    }

    @Override
    public int getWidth() {
        return bitmap.getWidth();
    }

    @Override
    public int getHeight() {
        return bitmap.getHeight();
    }

    @Override
    public Graphics.PixmapFormat getFormat() {
        return this.format;
    }

    @Override
    public void dispose() {
        bitmap.recycle();

    }
}
