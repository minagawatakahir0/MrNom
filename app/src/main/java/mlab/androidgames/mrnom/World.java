package mlab.androidgames.mrnom;

import java.util.Random;

/**
 * Created by Takahiro on 2015/12/30.
 */
public class World {
    public static final int WORLD_WIDTH = 10;
    public static final int WORLD_HEIGHT = 13;
    public static final int SCORE_INCREMENT = 10;
    public static final float TICK_INITIAL  = 0.5f ;
    public static final float TICK_DECREMENT  = 0.05f ;


    public Snake snake;
    public Stain stain ;
    public boolean gameOver = false;
    public int score;

    boolean fields[][] = new boolean[WORLD_WIDTH][WORLD_HEIGHT];
    Random random = new Random();
    float tickTime = 0;
    static float tick = TICK_INITIAL;

    public World(){
        snake = new Snake();
        placeStain();
    }

    private void placeStain() {
        for (int x = 0 ;x < WORLD_WIDTH; x++){
            for (int y = 0; y <WORLD_HEIGHT; y ++){
                fields[x][y] = false;
            }
        }

        int len = snake.parts.size();
        for ( int i = 0; i < len ; i++){
            SnakePart part =snake.parts.get(i);
            fields[part.x][part.y] = true;
        }


        int stainX = random.nextInt(WORLD_WIDTH);
        int stainY = random.nextInt(WORLD_HEIGHT);

        while (true){
            if (fields[stainX][stainY] == false)
                break;
            stainX += 1;
            if (stainX >= WORLD_WIDTH){
                stainX = 0;
                stainY += 1;
                if (stainY >= WORLD_HEIGHT)
                    stainY = 0;
            }
        }
        stain = new Stain(stainX, stainY, random.nextInt(3) );
    }

    public void update(float deltaTime){
        if (gameOver)
            return;

        tickTime += deltaTime;

        while(tickTime > tick){
            tickTime -= tick;
            snake.advanced();
            if (snake.checkBitten()){
                gameOver = true;
                return;
            }

            SnakePart head = snake.parts.get(0);
            if (head.x == stain.x && head.y == stain.y) {
                score += SCORE_INCREMENT;
                snake.eat();
                if (snake.parts.size() == WORLD_WIDTH * WORLD_HEIGHT){
                    gameOver = true;
                    return;
                } else {
                    placeStain();
                }

                if (score % 100 == 0 && tick - TICK_DECREMENT > 0 ){
                    tick -= TICK_DECREMENT;
                }
            }


        }

    }
}

