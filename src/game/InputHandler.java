package game;
import units.Platform;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//This is the input handler class. We will extend it later with movement commands for the platform.
public class InputHandler implements KeyListener {

    private boolean menuMode;

    public InputHandler(Canvas canvas, boolean menuMode) {
        canvas.addKeyListener(this);
        this.menuMode=menuMode;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code=e.getKeyCode();

        if(code==KeyEvent.VK_ENTER){

         this.menuMode=false;
        }

        //Implementing platform's moving
        if (code == KeyEvent.VK_RIGHT){
            Platform.isMovingRight = true;
            Platform.isMovingLeft = false;
        } else if (code == KeyEvent.VK_LEFT){
            Platform.isMovingRight = false;
            Platform.isMovingLeft = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code=e.getKeyCode();

        if (code == KeyEvent.VK_RIGHT){
            Platform.isMovingRight = false;
            Platform.isMovingLeft = false;
        } else if (code == KeyEvent.VK_LEFT){
            Platform.isMovingRight = false;
            Platform.isMovingLeft = false;
        }
    }

    public boolean isMenuModeOn(){


       return this.menuMode;
    }

    public void goBackToMenu(){

        this.menuMode=true;
    }
}