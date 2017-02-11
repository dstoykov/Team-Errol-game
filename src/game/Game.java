package game;

import display.Display;
import graphics.ImageLoader;
import units.Ball;
import units.Brick;
import units.Platform;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

//By far the most complex component of our project. This is the game itself.

public class Game extends JFrame implements Runnable {

    private String name;
    private int width, height;

    private Display display;
    private Platform platform;
    private Ball ball;
    private Brick[] bricks;
    private int bricksRemaining;
    public BufferStrategy bs;

    public Graphics graphics;
    private Thread thread;
    private boolean isRunning;


    private Menu menu;

    public static enum STATE {
        MENU,
        GAME,
        HIGHSCORES
    }

    public static STATE State = STATE.MENU;

    //private InputHandler ih;


    public Game(String name, int width, int height) {

        this.name = name;
        this.width = width;
        this.height = height;

    }

    public void initialization() {
        //Initial of brick composition, to be done with load of file for every Level
        Brick bricks[];
        bricks = new Brick[30];
        int bricksRemaining = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                bricks[bricksRemaining++] = new Brick(40 + j * 40 * 3, 48 + i * 12 * 3);
            }
        }
        this.display = new Display(name, width, height);
        this.addKeyListener(new InputHandler(this.display.getCanvas()));
        this.platform = new Platform(350, 550, 100, 10, 30);
        this.ball = new Ball(350, 550, 15, 30, 30, 5, 5, platform, bricks);
        this.bricks = bricks;
        this.bricksRemaining = bricksRemaining;  //if brickRemaining=0 then level ends
        this.menu = new Menu();
        this.addMouseListener(new MouseInput(this.display.getCanvas()));

    }

    public void thick() {

        if (State == STATE.GAME) {
            this.platform.thick();
        }

    }


    public void render() {

        int scores = 0;

        //Display Game Menu
//        displayMenu();


        //This is the buffered strategy. We get it from the canvas. If it is null, we set it with 2 buffers.
        //We can change it later.
        this.bs = this.display.getCanvas().getBufferStrategy();


        if (this.bs == null) {

            this.display.getCanvas().createBufferStrategy(2);
            this.bs = this.display.getCanvas().getBufferStrategy();
        }
        this.graphics = this.bs.getDrawGraphics();

        //Here we draw the background on the canvas.
        this.graphics.drawImage(ImageLoader.loadImage("/backgroundPic.png"), 0, 0, 800, 600, null);

        //This is the place for rendering graphics.

        //By default the menu mode is true. As we render, if the ENTER key is pressed, menu mode becomes false and then the game starts.

        if (State == STATE.GAME) {
            //Creating the platform

            this.platform.render(graphics);
            this.graphics.drawImage(ImageLoader.loadImage("/platform.png"),
                    platform.getPlatformX(),
                    platform.getPlatformY(),
                    platform.getPlatformWidth(),
                    platform.getPlatformHeight(), null);

            this.ball.render(graphics);
            this.graphics.setColor(Color.WHITE);
            this.graphics.fillOval((int) ball.getCenterX(), (int) ball.getCenterY(), ball.getH(), ball.getW());
            // Draw the bricks
            this.bricksRemaining = 30;
            for (Brick brick : bricks) {
                // If brick is destroyed, continue to next brick.
                if (brick.destroyed) {
                    // Increment player scores
                    scores += 5;
                    this.bricksRemaining--;
                } else {
                    // Else, draw the brick.
                    if (this.bricksRemaining != 0)
                        this.graphics.drawImage(brick.getImage(), brick.getX(), brick.getY(),
                                brick.getWidth(), brick.getHeight(), this);
                }
            }
            // Show player scores
            this.graphics.setFont(new Font("serif", Font.BOLD, 27));
            this.graphics.drawString("" + scores, 740, 30);

        } else if (State == STATE.MENU) {
            this.menu.render(graphics);
        }


        //Take a careful look at these two operations. This is the cornerstone of visualizing our graphics.
        // Whatever we draw, it finally goes through dispose and the it is shown.
        this.graphics.dispose();
        this.bs.show();
    }

    @Override
    public void run() {

        // Here we initialize the game loop.
        this.initialization();

        int fps = 60;
        double timePerTick = 1_000_000_000 / fps;

        double delta = 0;
        long now;
        long lasTimeTicked = System.nanoTime();

        while (isRunning) {
            now = System.nanoTime();

            delta += (now - lasTimeTicked) / timePerTick;

            if (delta >= 1) {
                thick();
                render();
                delta--;
                ball.move();
            }

            if (this.bricksRemaining == 0 && State == STATE.GAME) {

                State = STATE.MENU;
            }


            // Stop the game when the ball exits game field
            if (this.ball.getCenterY() >= 570) {
                State = STATE.MENU;
                this.bricksRemaining = -1;
            }
        }

        this.stop();
    }

    public synchronized void start() {

        this.isRunning = true;
        //this.menuMode = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {

        try {
            this.isRunning = false;
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}