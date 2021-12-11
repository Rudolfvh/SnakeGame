package entity;

import iwwp.SnakeGameMain;

public class Apple {

    public int posY;
    public int posX;

    public Apple(int x, int y){
        posX = x;
        posY = y;
    }

    public void setRandomPosition(){
        posX = Math.abs((int) (Math.random()* SnakeGameMain.Width - 1));
        posY = Math.abs((int) (Math.random()* SnakeGameMain.Height - 1));
    }
}
