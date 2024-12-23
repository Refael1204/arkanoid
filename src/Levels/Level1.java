// Refael Avrahami, 206482655
package Levels;
import Default.Velocity;
import Interface.LevelInformation;
import Interface.Sprite;
import ShownObject.Block;
import geometryShape.Point;
import geometryShape.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * Level1 - first level.
 */
public class Level1 implements LevelInformation {
    private static final int LENGTH = 600, WIDTH = 800, BLOCK_WIDTH = 40;
    private final int numberOfBalls;
    private final int paddleSpeed;
    private final int paddleWidth;
    private final String levelName;
    private final int numberOfBlocksToRemove;
    private final Sprite background;
    /**
     * Constructor.
     */
    public Level1() {
        this.background = new Block(new Rectangle(new Point(0, 0), WIDTH, LENGTH), Color.black);
        this.numberOfBlocksToRemove = 1;
        this.levelName = "Direct Hit";
        this.numberOfBalls = 1;
        this.paddleSpeed = 5;
        this.paddleWidth = 100;
    }
    /**
     * @return the number of balls left.
     */
    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }
    /**
     * @return velocity list of balls.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityBalls = new ArrayList<>();
        velocityBalls.add(Velocity.fromAngleAndSpeed(0, -4));
        return velocityBalls;
    }

    /**
     * @return paddle speed.
     */
    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }
    /**
     * @return paddle width.
     */
    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }
    /**
     * @return level name.
     */
    @Override
    public String levelName() {
        return this.levelName;
    }
    /**
     * @return background of level.
     */
    @Override
    public Sprite getBackground() {

        return this.background;
    }
    /**
     * @return list of the Blocks of this level.
     */
    @Override
    public List<Block> blocks() {
        List<Block> blocksList = new ArrayList<>();
        Block block = new Block(new Rectangle(new Point(380, 150), BLOCK_WIDTH, BLOCK_WIDTH), Color.RED);
        blocksList.add(block);
        return blocksList;
    }
    /**
     * @return number of blocks that should be removed.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }
}
