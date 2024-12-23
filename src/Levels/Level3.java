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
 * Level3 - third level.
 */
public class Level3 implements LevelInformation {
    private static final int LENGTH = 600, WIDTH = 800, BLOCKWIDTH = 50, LINES = 5;
    private final int numberOfBalls;
    private final int paddleSpeed;
    private final int paddleWidth;
    private final String levelName;
    private final int numberOfBlocksToRemove;
    private final Sprite background;
    /**
     * Constructor.
     */
    public Level3() {
        this.background = new Block(new Rectangle(new Point(0, 0), WIDTH, LENGTH), Color.white);
        this.numberOfBlocksToRemove = 40;
        this.levelName = "Medium Level";
        this.numberOfBalls = 2;
        this.paddleSpeed = 8;
        this.paddleWidth = 120;
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
        List<Velocity> velocities = new ArrayList<>();
        int initialV = 38;
        velocities.add(Velocity.fromAngleAndSpeed(initialV, 6));
        initialV -= 76;
        velocities.add(Velocity.fromAngleAndSpeed(initialV, 6));
        return velocities;

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
        Block[][] block = new Block[LINES][];
        Color color;
        color = new Color(90, 60, 130);
        block[0] = createBlock(10, 740, 125, color, blocksList);
        color = new Color(180, 160, 255);
        block[1] = createBlock(9, 740, 145, color, blocksList);
        color = new Color(183, 236, 242);
        block[2] = createBlock(8, 740, 165, color, blocksList);
        color = new Color(100, 150, 100);
        block[3] = createBlock(7, 740, 185, color, blocksList);
        color = new Color(255, 255, 120);
        block[4] = createBlock(6, 740, 205, color, blocksList);
        return blocksList;
    }
    /**
     * @param size - number of blocks in each row.
     * @param axisX - axis x start of the blocks in each row.
     * @param axisY - axis y start of the blocks in each row.
     * @param blockColor - the color of the block in each row.
     * @param blocks - the list of the block.
     * @return array of blocks - the blocks in each row.
     */
    private Block[] createBlock(int size, int axisX, int axisY, Color blockColor, List<Block> blocks) {
        Block[] someBlock = new Block[size];
        for (int i = 0; i < size; i++) {
            someBlock[i] = new Block(new Rectangle(new Point(axisX, axisY), BLOCKWIDTH, 20), blockColor);
            if (size == 8 && i == 4) {
                someBlock[i].setColor(Color.black);
            }
            axisX -= BLOCKWIDTH;
            blocks.add(someBlock[i]);
        }
        return someBlock;
    }
    /**
     * @return number of blocks that should be removed.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }
}
