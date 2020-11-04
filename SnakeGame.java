package com.javarush.games.snake;

import com.javarush.engine.cell.*;
import com.javarush.engine.cell.Game;

public class SnakeGame extends Game{

    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;

    @Override
    public void initialize(){
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    private void drawScene(){
        for(int width=0; width<WIDTH; width++){
            for(int height=0;height<HEIGHT;height++){
                setCellColor(width, height, Color.ANTIQUEWHITE);
            }
        }
    }

    private void createGame(){
        drawScene();

    }

}
