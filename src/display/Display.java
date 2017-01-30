package display;

import javax.swing.*;
import java.awt.*;

// Initialization of the Display class. Here we set all necessary
// parameters of the game display. We also create a canvas. The canvas is what we draw our graphics on.
// And then the canvas is visualized on the current display.

public class Display {

    private JFrame frame;
    private Canvas canvas;

    public Display(String name, int width, int height) {


        this.frame = new JFrame(name);
        this.frame.setPreferredSize(new Dimension(width, height));
        this.frame.setMinimumSize(new Dimension(width, height));
        this.frame.setMaximumSize(new Dimension(width, height));
        this.frame.setVisible(true);
        this.frame.setFocusable(true);
        this.frame.setResizable(false);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);

        this.canvas=new Canvas();
        this.canvas.setPreferredSize(new Dimension(width, height));
        this.canvas.setMinimumSize(new Dimension(width, height));
        this.canvas.setMaximumSize(new Dimension(width, height));
        this.canvas.setFocusable(true);

        this.frame.add(this.canvas);
        this.frame.pack();
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
