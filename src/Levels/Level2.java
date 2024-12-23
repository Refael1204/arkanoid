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
 * Level2 - second level.
 */
public class Level2 implements LevelInformation {
    private static final int LENGTH = 600, WIDTH = 800, BLOCKWIDTH = 52, LOCATIONX = 738, LOCATIONY = 245;
    private final int numberOfBalls;
    private final int paddleSpeed;
    private final int paddleWidth;
    private final String levelName;
    private final int numberOfBlocksToRemove;
    private final Sprite background;
    /**
     * Constructor.
     */
    public Level2() {
        Color color = new Color(0x6CD5F1);
        this.background = new Block(new Rectangle(new Point(0, 0), WIDTH, LENGTH), color);
        this.numberOfBlocksToRemove = 15;
        this.levelName = "Easy Level";
        this.numberOfBalls = 10;
        this.paddleSpeed = 4;
        this.paddleWidth = 600;
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
        int angel = 72;
        for (int i = 0; i < this.numberOfBalls; i++) {
            velocityBalls.add(Velocity.fromAngleAndSpeed(angel, 5));
            angel -= 16;
        }
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
        double axisX = LOCATIONX;
        double axisY = LOCATIONY;
        List<Block> blocksList = new ArrayList<>();
        blocksList.add(new Block(new Rectangle(new Point(axisX, axisY),
                BLOCKWIDTH, 20), Color.cyan));
        axisX -= BLOCKWIDTH;
        blocksList.add(new Block(new Rectangle(new Point(axisX, axisY),
                BLOCKWIDTH, 20), Color.cyan));
        axisX -= BLOCKWIDTH;
        blocksList.add(new Block(new Rectangle(new Point(axisX, axisY),
                BLOCKWIDTH, 20), Color.pink));
        axisX -= BLOCKWIDTH;
        blocksList.add(new Block(new Rectangle(new Point(axisX, axisY),
                BLOCKWIDTH, 20), Color.pink));
        axisX -= BLOCKWIDTH;
        blocksList.add(new Block(new Rectangle(new Point(axisX, axisY),
                BLOCKWIDTH, 20), Color.blue));
        axisX -= BLOCKWIDTH;
        blocksList.add(new Block(new Rectangle(new Point(axisX, axisY),
                BLOCKWIDTH, 20), Color.blue));
        axisX -= BLOCKWIDTH;
        blocksList.add(new Block(new Rectangle(new Point(axisX, axisY),
                BLOCKWIDTH, 20), Color.GREEN));
        axisX -= BLOCKWIDTH;
        blocksList.add(new Block(new Rectangle(new Point(axisX, axisY),
                BLOCKWIDTH, 20), Color.GREEN));
        axisX -= BLOCKWIDTH;
        blocksList.add(new Block(new Rectangle(new Point(axisX, axisY),
                BLOCKWIDTH, 20), Color.GREEN));
        axisX -= BLOCKWIDTH;
        blocksList.add(new Block(new Rectangle(new Point(axisX, axisY),
                BLOCKWIDTH, 20), Color.yellow));
        axisX -= BLOCKWIDTH;
        blocksList.add(new Block(new Rectangle(new Point(axisX, axisY),
                BLOCKWIDTH, 20), Color.yellow));
        axisX -= BLOCKWIDTH;
        blocksList.add(new Block(new Rectangle(new Point(axisX, axisY),
                BLOCKWIDTH, 20), Color.ORANGE));
        axisX -= BLOCKWIDTH;
        blocksList.add(new Block(new Rectangle(new Point(axisX, axisY),
                BLOCKWIDTH, 20), Color.ORANGE));
        axisX -= BLOCKWIDTH;
        blocksList.add(new Block(new Rectangle(new Point(axisX, axisY),
                BLOCKWIDTH, 20), Color.red));
        axisX -= BLOCKWIDTH;
        blocksList.add(new Block(new Rectangle(new Point(axisX, axisY),
                BLOCKWIDTH, 20), Color.red));

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

