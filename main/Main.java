package SpaceScroller.main;

import javax.swing.JFrame;
import SpaceScroller.constants.Constants;

/*
 * Run this file to run the project
 */

public class Main {

    public static void main(String[] args) {
        
        JFrame window = new JFrame();

        // Set the window properties
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle(Constants.TITLE);
        window.setResizable(Constants.RESIZABLE);

        // Make a GamePanel
        GamePanel gp = new GamePanel();

        window.add(gp);
        window.pack();

        // Make the window visible
        window.setVisible(true);
        
        window.setLocationRelativeTo(null);

        // Run the game
        gp.startGameThread();

    }
    
}
