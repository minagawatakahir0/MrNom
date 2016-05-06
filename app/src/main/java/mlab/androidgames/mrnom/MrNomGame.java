package mlab.androidgames.mrnom;
import mlab.androidgames.framework.Screen;
import mlab.androidgames.framework.impl.AndroidGame;

public class MrNomGame extends AndroidGame {

    @Override
    public Screen getStartScreen() {
        return new LoadingScreen(this);
    }
}
