// Refael Avrahami, 206482655
package Interface;

import Default.Velocity;
import ShownObject.Block;
import java.util.List;
/**
 * interface LevelInformation - is an interface that specifies
 * the information required to fully describe a level.
 */
public interface LevelInformation {
    /**
     * @return the number of balls that was left.
     */
    int numberOfBalls();
    /**
     * @return a velocity list of the balls.
     */
    List<Velocity> initialBallVelocities();
    /**
     * @return the paddle's speed.
     */
    int paddleSpeed();
    /**
     * @return the paddle's width.
     */
    int paddleWidth();
    /**
     * @return level name.
     */
    String levelName();
    /**
     * @return a sprite with the background of the level.
     */
    Sprite getBackground();
    /**
     * @return a list of blocks that make up this level.
     */
    List<Block> blocks();
    /**
     * @return number of blocks that should be removed.
     */
    int numberOfBlocksToRemove();
}
