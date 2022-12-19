package SpaceScroller.constants;

/*
 * The constants for the projects lie here!
 */

public class Constants {

    public static final String TITLE = "Space Shooter";
    public static final boolean RESIZABLE = false;

    /*
     * The Tile-size and Tile-map specifications
     */
    public static final int ORIGINAL_TILESIZE = 32;
    public static final int GLOBAL_SCALE = 1;

    public static final int SCALED_TILESIZE = GLOBAL_SCALE * ORIGINAL_TILESIZE;

    // The size of the screen is determined here
    public static final int MAX_SCREEN_COLS = 20; // The width of the screen associated here
    public static final int MAX_SCREEN_ROWS = 15; // The height of the screen associated here

}
