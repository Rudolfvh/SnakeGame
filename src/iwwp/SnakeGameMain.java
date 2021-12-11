package iwwp;

import entity.Apple;
import entity.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakeGameMain  extends JPanel implements ActionListener {

    public static JFrame jFrame;

    public static final int Scare = 32;
    public static final int Width = 20;
    public static final int Height = 20;
    public static int speed = 10;
    public static int score = 0;

    Snake snake = new Snake(5,6,5,5);
    Apple apple = new Apple(Math.abs((int) (Math.random()* SnakeGameMain.Width - 1)),Math.abs((int) (Math.random()* SnakeGameMain.Height - 1)));
    Timer timer = new Timer(1000/speed,this);

    public SnakeGameMain(){
        timer.start();
        addKeyListener(new KeyBoard());
        setFocusable(true);
    }

    public void paint(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0,0,Width*Scare,Height*Scare);

       // for(int x=0; x<Width*Scare; x+=Scare){
            //g.setColor(Color.BLACK);
      //      g.drawLine(x,0,x,Height*Scare);
      // }

       // for(int y=0; y<Height*Scare; y+=Scare){
       //     g.setColor(Color.BLACK);
       //     g.drawLine(0,y,Width*Scare,y);
       // }

        g.setColor(Color.RED);
        g.fillOval(apple.posX*Scare+1,apple.posY*Scare+1,Scare-1, Scare-1);

        for(int l =0; l < snake.leight; l++){
            g.setColor(Color.green);
            g.fillRect(snake.snakeX[l]*Scare+3, snake.snakeY[l]*Scare+3, Scare-6,Scare-6);
        }


    }

    public static void main(String[] args) {

        jFrame = new JFrame("Title");
        jFrame.setSize(Width*Scare+15,Height*Scare+9);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);

        jFrame.add(new SnakeGameMain());

        jFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        snake.move();
        if((snake.snakeX[0] == apple.posX)&&(snake.snakeY[0] == apple.posY)){
            apple.setRandomPosition();
            speed+=2;
            score++;
            snake.leight++;
        }
        for(int l =1; l < snake.leight; l++){
            if((snake.snakeX[l] == apple.posX)&&(snake.snakeY[l] == apple.posY)){
                apple.setRandomPosition();
            }
            if((snake.snakeX[0]== snake.snakeX[l])&&(snake.snakeY[0] == snake.snakeY[l])){
                timer.stop();
                JOptionPane.showMessageDialog(null,"Лох,Проебал. Начинай заново\n" + "Счёт: " + score);
                jFrame.setVisible(false);
                snake.leight = 2;
                snake.direction = 0;
                score = 0;
                apple.setRandomPosition();
                jFrame.setVisible(true);
                timer.start();
            }
        }
        repaint();
    }

    public class KeyBoard extends KeyAdapter {
        public void keyPressed (KeyEvent event){
             int key = event.getKeyCode();

             if(key == KeyEvent.VK_UP && snake.direction!=2)snake.direction = 0;

             if(key == KeyEvent.VK_DOWN && snake.direction!=0)snake.direction = 2;

             if(key == KeyEvent.VK_RIGHT && snake.direction!=3)snake.direction = 1;

             if(key == KeyEvent.VK_LEFT && snake.direction!=1)snake.direction = 3;
        }
    }
}
