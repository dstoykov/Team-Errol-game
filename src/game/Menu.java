package game;

import graphics.ImageLoader;

import java.awt.*;

public class Menu {

    public void render(Graphics g, byte level) {


        if (Game.State == Game.STATE.MENU) {

            if (Game.currentLevel > 1) {
                g.drawImage(ImageLoader.loadImage("/button_resume-game.png"), 300, 100, 200, 50, null);
            }
            g.drawImage(ImageLoader.loadImage("/button_start-game.png"), 300, 200, 200, 50, null);
            g.drawImage(ImageLoader.loadImage("/button_highscores.png"), 300, 300, 200, 50, null);
            g.drawImage(ImageLoader.loadImage("/button_exit.png"), 300, 400, 200, 50, null);

        } else if (Game.State == Game.STATE.PAUSE) {

            g.setColor(Color.WHITE);
            Font f1 = new Font("arial", Font.BOLD, 30);
            g.setFont(f1);
            g.drawString(String.format("Level %d completed!", --level), 250, 200);
            g.drawImage(ImageLoader.loadImage("/button_next-level.png"), 300, 300, 200, 50, null);
            g.drawImage(ImageLoader.loadImage("/button_back-to-menu.png"), 300, 400, 200, 50, null);

        } else if (Game.State == Game.STATE.WIN) {

            g.drawImage(ImageLoader.loadImage("/button_good-job.png"), 300, 100, 200, 50, null);
            g.drawImage(ImageLoader.loadImage("/button_add-your-score.png"), 300, 200, 200, 50, null);
            g.drawImage(ImageLoader.loadImage("/button_back-to-menu.png"), 300, 300, 200, 50, null);


        } else if (Game.State == Game.STATE.PLAYER_INIT) {

            g.drawImage(ImageLoader.loadImage("/inputBgr.png"), 300, 200, 200, 50, null);
            g.setColor(Color.WHITE);
            Font f1 = new Font("arial", Font.ITALIC, 30);
            g.setFont(f1);
            g.drawString(Game.playerName.toString(), 315, 235);
            g.drawImage(ImageLoader.loadImage("/button_add-your-score.png"), 300, 400, 200, 50, null);
            g.drawImage(ImageLoader.loadImage("/button_back-to-menu.png"), 300, 500, 200, 50, null);

        } else if (Game.State == Game.STATE.HIGHSCORES) {

            //TODO: Display highscores!
            g.drawImage(ImageLoader.loadImage("/button_back-to-menu.png"), 300, 500, 200, 50, null);
        }
    }
}