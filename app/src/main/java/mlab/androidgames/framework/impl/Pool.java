package mlab.androidgames.framework.impl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Takahiro on 2015/12/06.
 */
public class Pool <T> {
    private final PoolObjectFactory<T> factory;
    private final List<T> freeObjects;
    private final int maxSize;

    public Pool(PoolObjectFactory<T> factory, int maxSize) {
        this.factory = factory;
        this.freeObjects = new ArrayList<T>(maxSize);
        this.maxSize = maxSize;
    }


    public T newObject(){
        T object = null;

        if ( freeObjects.size() == 0){
            object = factory.createObject();
        } else {
            object = freeObjects.remove(freeObjects.size() -1 );
        }
        return object;

    }

    public  void free(T object){
        if ( freeObjects.size() < maxSize){
            freeObjects.add(object);
        }
    }

    public interface PoolObjectFactory<T>{
        T createObject();
    }

}



