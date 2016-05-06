package mlab.androidgames.framework;


/**
 * Created by Takahiro on 2015/11/17.
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface FileIO {
    InputStream readAsset(String fileName) throws  IOException;

    InputStream readFile(String fileName) throws  IOException;

    OutputStream writeFile(String fileName) throws  IOException;
}
