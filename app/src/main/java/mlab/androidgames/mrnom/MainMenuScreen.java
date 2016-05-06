package mlab.androidgames.mrnom;

import java.util.List;

import mlab.androidgames.framework.Game;
import mlab.androidgames.framework.Graphics;
import mlab.androidgames.framework.Input.TouchEvent;
import mlab.androidgames.framework.Screen;


/**
 * Created by Takahiro on 2015/12/27.
 */
public class MainMenuScreen extends Screen {
    public MainMenuScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();

        int len = touchEvents.size();

        for (int i = 0; i < len; i ++){
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP){
                // This is Sound-Enabled Area
                // !! AndroidGraphics.getHeight(Width) returns "frameBuffer's height(width)!!"
                if(inBounds(event, 0 , g.getHeight() - 64 , 64 , 64 ) ){
                    Settings.soundEnabled = !Settings.soundEnabled;
                    if (Settings.soundEnabled)
                        Assets.click.play(1);
                    return;
                }
                // This is Start-Game Area
                if(inBounds(event,64,220,192,42)){
                    game.setScreen(new GameScreen(game));
                    if (Settings.soundEnabled)
                        Assets.click.play(1);
                    return;
                }

                // This is High-Score Area
                if(inBounds(event,64,220 + 42,192,42)){
                    game.setScreen(new HighScoreScreen(game));
                    if (Settings.soundEnabled)
                        Assets.click.play(1);
                    return;
                }
                // This is HelpScreen Area
                if(inBounds(event,64,220 + 84,192,42)){
                    game.setScreen(new HelpScreen(game));
                    if (Settings.soundEnabled)
                        Assets.click.play(1);
                    return;
                }
            }
        }
    }

    private boolean inBounds(TouchEvent event, int x, int y, int width, int height) {
        if(event.x > x && event.x < x + width -1 &&
           event.y > y && event.y < y + height -1) {
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.background,0,0);
        g.drawPixmap(Assets.logo,32,20);
        g.drawPixmap(Assets.mainmenu,64,220);
        if(Settings.soundEnabled)
            g.drawPixmap(Assets.buttons,0,416,0,0,64,64);
        else
            g.drawPixmap(Assets.buttons,0,416,64,0,64,64);
    }

    @Override
    public void pause() {
        Settings.save(game.getFileIO());

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
