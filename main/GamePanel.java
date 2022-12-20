package SpaceScroller.main;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import SpaceScroller.constants.Constants;
import SpaceScroller.entity.Enemy;
import SpaceScroller.entity.Player;
import SpaceScroller.entity.TileMap;

public class GamePanel extends JPanel implements Runnable {
    
    // Setup our screen
    public final int screenWidth = Constants.MAX_SCREEN_COLS * Constants.SCALED_TILESIZE;
    public final int screenHeight = Constants.MAX_SCREEN_ROWS * Constants.SCALED_TILESIZE;

    // Create a game thread to run the game-loop
    private Thread gameThread;

    // Add a key handler to the game
    public KeyHandler keyH = new KeyHandler();

    /*
     * Declare all the GameObjects here!
     */
    public TileMap tileMap = new TileMap(this, keyH);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Constants.BACKGROUND_COLOR);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        /*
         * Set a player to the TileMap
         */
        tileMap.setPlayer(Constants.MAX_SCREEN_COLS/2, Constants.MAX_SCREEN_ROWS/2);

        // Add a single enemy to the game
        tileMap.addEnemy(new Enemy(Constants.MAX_SCREEN_COLS/2, 0));
    }

    // Function to initialize the game-thread and to call the run method
    public void startGameThread() {
        /*
         * We will initialize and run the game thread
         */
        gameThread = new Thread(this);
        // Start the game
        gameThread.start();
    }

    public void update(double dt) {
        /*
         * Update all the game entities here!
         */
        // Update the tilemap
        tileMap.update(dt);
    }

    public void paintComponent(Graphics g) {
        /*
         * Call the superclass implementation of this function
         */
        super.paintComponent(g);

        // Cast the Graphics to Graphics2D for better control
        Graphics2D g2 = (Graphics2D) g;

        /*
         * Draw all the game entities here!
         */
        tileMap.draw(g2);

        // Dispose the graphics for better memory management
        g2.dispose();
    }

    @Override
    public void run() {
        /*
         * The Game loop resides here!
         */

    
        // The time by which we wanna delay the Frame,
        // to get the desired FPS
        long frameDelay = 0;
        // Container to store the time taken to execute this frame
        long frameTime = 0;

        /*
         * The main game-loop
         */
        while(gameThread != null) {
            // Calculate the frame delay in milliseconds
            frameDelay = 1000/Constants.FPS;
            // Capture the time at the start of the frame
            frameTime = System.currentTimeMillis();
            /*
             * We will update and paint the objects
             */
            update(1);
            repaint();

            // What was the time taken to execute this?
            frameTime = System.currentTimeMillis() - frameTime;

            // If we have some time on our hand, then delay the game
            if(frameDelay - frameTime > 0) {
                try {
                    Thread.sleep(frameDelay - frameTime);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }
        
    }



}
