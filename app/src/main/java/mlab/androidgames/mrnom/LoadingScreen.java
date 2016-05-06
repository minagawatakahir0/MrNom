package mlab.androidgames.mrnom;

import mlab.androidgames.framework.Game;
import mlab.androidgames.framework.Graphics;
import mlab.androidgames.framework.Screen;

/**
 * Created by Takahiro on 2015/12/27.
 */
public class LoadingScreen extends Screen {
    public LoadingScreen(Game game){
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.background = g.newPixmap("background.png", Graphics.PixmapFormat.RGB565);
        Assets.logo = g.newPixmap("logo.png", Graphics.PixmapFormat.ARGB4444);
        Assets.mainmenu = g.newPixmap("mainmenu.png", Graphics.PixmapFormat.ARGB4444);
        Assets.buttons = g.newPixmap("buttons.png", Graphics.PixmapFormat.ARGB4444);
        Assets.help1 = g.newPixmap("help1.png", Graphics.PixmapFormat.ARGB4444);
        Assets.help2 = g.newPixmap("help2.png", Graphics.PixmapFormat.ARGB4444);
        Assets.help3 = g.newPixmap("help3.png", Graphics.PixmapFormat.ARGB4444);
        Assets.numbers = g.newPixmap("numbers.png", Graphics.PixmapFormat.ARGB4444);
        Assets.ready = g.newPixmap("ready.png", Graphics.PixmapFormat.ARGB4444);
        Assets.pause = g.newPixmap("pausemenu.png", Graphics.PixmapFormat.ARGB4444);
        Assets.gameover = g.newPixmap("gameover.png", Graphics.PixmapFormat.ARGB4444);
        Assets.headup = g.newPixmap("headup.png", Graphics.PixmapFormat.ARGB4444);
        Assets.headleft = g.newPixmap("headleft.png", Graphics.PixmapFormat.ARGB4444);
        Assets.headright = g.newPixmap("headright.png", Graphics.PixmapFormat.ARGB4444);
        Assets.headdown = g.newPixmap("headdown.png", Graphics.PixmapFormat.ARGB4444);
        Assets.tail = g.newPixmap("tail.png", Graphics.PixmapFormat.ARGB4444);
        Assets.stain1 = g.newPixmap("stain1.png", Graphics.PixmapFormat.ARGB4444);
        Assets.stain2 = g.newPixmap("stain2.png", Graphics.PixmapFormat.ARGB4444);
        Assets.stain3 = g.newPixmap("stain3.png", Graphics.PixmapFormat.ARGB4444);
        Assets.click =  game.getAudio().newSound("click.ogg");
        Assets.eat =  game.getAudio().newSound("eat.ogg");
        Assets.bitten =  game.getAudio().newSound("bitten.ogg");
        Settings.load(game.getFileIO());
        game.setScreen(new MainMenuScreen(game));
    }

    @Override
    public void present(float deltaTime) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {

    }
}
