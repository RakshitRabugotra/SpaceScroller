package SpaceScroller.constants;

/*
 * Defining the possible clusters of bullet
 */

public class BulletClusterProps {
    
    public static final int[][][] CLUSTERS = new int[][][] {
        {
            {0, 1, 0},
            {0, 0, 0},
            {0, 0, 0}
        },

        {
            {0, 1, 0},
            {1, 0, 1},
            {0, 0, 0}
        },

        {
            {1, 1, 1},
            {0, 0, 0},
            {0, 0, 0}
        },

        {
            {1, 1, 1},
            {0, 0, 0},
            {0, 1, 0}
        },

        {
            {1, 1, 1},
            {1, 0, 1},
            {0, 0, 0}
        },


        {
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
        },
    };

}
