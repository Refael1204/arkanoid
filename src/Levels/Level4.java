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
 * Level3 - forth level.
 */
public class Level4 implements LevelInformation {
    private static final int LENGTH = 600, WIDTH = 800;
    private final String levelName;
    private final int paddleSpeed;
    private final int numberOfBalls;
    private final int paddleWidth;
    private final int numberOfBlocksToRemove;
    private final Color color;
    private final Block background;

    /**
     * Constructor.
     */
    public Level4() {
        this.levelName = "Hard Level";
        this.paddleSpeed = 12;
        this.numberOfBalls = 3;
        this.paddleWidth = 85;
        this.numberOfBlocksToRemove = 75;
        this.color = new Color(34, 150, 255);
        this.background = new Block(new Rectangle(new Point(0, 0), WIDTH, LENGTH), this.color);

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
        int angel = 0;
        velocityBalls.add(Velocity.fromAngleAndSpeed(angel + 70, 7));
        velocityBalls.add(Velocity.fromAngleAndSpeed(angel, 7));
        velocityBalls.add(Velocity.fromAngleAndSpeed(angel - 70, 7));

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
        int blockWidth = 52;
        int blockHeight = 20;
        int borderSize = 10;
        Point uppLeft = new Point(WIDTH - borderSize, 125);
        Color color2 = new Color(0xFF1D5B33, true);
        Color color4 = new Color(0xFF396447, true);
        Color color6 = new Color(0xFF28834D, true);
        Color color3 = new Color(0xFF2BA155, true);
        Color color7 = new Color(0xFF57916F, true);
        Color color1 = new Color(0xFF6FA883, true);
        Color color5 = new Color(0xFF9AE3B7, true);

        Color[] arrCol = {color2, color4, color6, color3, color7, color1, color5};
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 15; j++) {
                Point tempPoint = new Point(uppLeft.getX() - (j + 1) * blockWidth, uppLeft.getY());
                Block tempBlock = new Block(new Rectangle(tempPoint, blockWidth, blockHeight), arrCol[i]);
                blocksList.add(tempBlock);
            }
            uppLeft = new Point(WIDTH - borderSize, uppLeft.getY() + blockHeight);
        }
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
