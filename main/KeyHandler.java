package SpaceScroller.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    // To assign the key bindings
    private static int leftKeyBinding = KeyEvent.VK_A;
    private static int rightKeyBinding = KeyEvent.VK_D;
    private static int upKeyBinding = KeyEvent.VK_W;
    private static int downKeyBinding = KeyEvent.VK_S;
    private static int shootKeyBinding = KeyEvent.VK_SPACE;

    // To keep track of which side we're moving
    public boolean upPressed = false, downPressed = false, leftPressed = false, rightPressed = false;
    public boolean shootPressed = false;

    @Override
    public void keyTyped(KeyEvent e) {
        /*
         * It'll barely be used
         */
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Fetch the key code and perform necessary actions
        // As the user has pressed some key
        int code = e.getKeyCode();

        if(code == leftKeyBinding) leftPressed = true;
        if(code == rightKeyBinding) rightPressed = true;
        if(code == upKeyBinding) upPressed = true;
        if(code == downKeyBinding) downPressed = true;

        // To shoot a bullet
        if(code == shootKeyBinding) shootPressed = true;
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Fetch the key code and perform necessary actions
        // As the user has released some key
        int code = e.getKeyCode();

        if(code == leftKeyBinding) leftPressed = false;
        if(code == rightKeyBinding) rightPressed = false;
        if(code == upKeyBinding) upPressed = false;
        if(code == downKeyBinding) downPressed = false;
        
        if(code == shootKeyBinding) shootPressed = false;
    }
    
}
