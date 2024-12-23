//  Refael Avrahami 206482655
package Default;
import Collides.GameEnvironment;
import Collides.SpriteCollection;
import Interface.Animation;
import Interface.Collidable;
import Interface.LevelInformation;
import Interface.Sprite;
import Listeners.BallRemover;
import Listeners.BlockRemover;
import Listeners.ScoreTrackingListener;
import Listeners.SpecialBlock;
import java.awt.Color;
import java.util.Iterator;
import java.util.List;

import ShownObject.Ball;
import ShownObject.Block;
import ShownObject.Paddle;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import geometryShape.Point;
import geometryShape.Rectangle;


/**
 * GameLevel class that will hold the sprites and the collidables, and will be in charge of the animation.
 */
public class GameLevel implements Animation {
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    public static final int BOARD_WIDTH = 800;
    public static final int BOARD_HEIGHT = 600;
    private final Sleeper sleeper;
    private Counter blockCount;
    private Counter ballCount;
    private final Counter score;
    private final AnimationRunner runner;
    private boolean running;
    private final KeyboardSensor keyboard;
    private final LevelInformation info;
    private List<Block> blocks;
    private List<Velocity> velocities;
    /**
     * constructor.
     * @param info our level info
     * @param animationRunner animation runner to run animation.
     * @param scores the score.
     * @param countBlock number of blocks.
     */
    public GameLevel(LevelInformation info, AnimationRunner animationRunner, Counter scores, Counter countBlock) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        //this.gui = new GUI("refael the king", BOARD_WIDTH, BOARD_HEIGHT);
        this.sleeper = new Sleeper();
        this.blockCount = countBlock;
        this.ballCount = new Counter(0);
        this.score = scores;
        this.runner = animationRunner;
        this.keyboard = animationRunner.getGui().getKeyboardSensor();
        this.info = info;
    }

    /**
     * this function is adding the given collidable to the environment.
     * @param c - given collidable object
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * this function is returning the gui of the game.
     * @return gui
     */
    public GUI getGui() {
        return this.runner.getGui();
    }

    /**
     * this function is adding the given sprite to sprites list.
     * @param s - given sprite
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * this function initializing a new game: create the block, ball and paddle,
     * and add them to the game.
     */
    public void initialize() {
        this.blockCount = new Counter(info.numberOfBlocksToRemove());
        this.ballCount = new Counter(info.numberOfBalls());
        Block[] borders = borders();
        for (Block border : borders) {
            border.addToGame(this);
        }
        ScoreTrackingListener scoreTracking = new ScoreTrackingListener(this.score);
        BallRemover ballRemove = new BallRemover(this, this.ballCount);
        Block deathBlock = new Block(new Rectangle(new Point(0, BOARD_HEIGHT), BOARD_WIDTH, 5), Color.white);
        deathBlock.addHitListener(ballRemove);
        deathBlock.addToGame(this);
        SpecialBlock specialBlock = new SpecialBlock(this, ballCount);
        ScoreIndicator scoreInd = new ScoreIndicator(this.score, new Rectangle(new Point(0, 0), BOARD_WIDTH, 25));
        scoreInd.addToGame(this);
        LevelNameIndicator levelName = new LevelNameIndicator(new Rectangle(new Point(0, 0), BOARD_WIDTH,
                25), this.info.levelName());
        levelName.addToGame(this);
        BlockRemover blockRem = new BlockRemover(this, this.blockCount);
        blocks(blockRem, scoreTracking, specialBlock);
        Paddle paddle = new Paddle(new Point((double) (BOARD_WIDTH - info.paddleWidth()) / 2,
                BOARD_HEIGHT - 20 - 25), info.paddleWidth(), 20,
                Color.ORANGE, this.keyboard);
        this.velocities = this.info.initialBallVelocities();
        Iterator<Velocity> v = velocities.iterator();
        balls(v);
        paddle.setSpeed(info.paddleSpeed());
        paddle.addToGame(this);
    }

    /**
     * this function is generating array of borders and returning it.
     * @return - array of borders
     */
    public Block[] borders() {
        Block[] borders = new Block[3];
        borders[0] = new Block(new Rectangle(new Point(5, 25), BOARD_WIDTH, 5), Color.gray); // up
        // borders[1] = new Block(new Rectangle(new Point(0, BOARD_HEIGHT - 5), BOARD_WIDTH, 5), Color.gray); // down
        borders[1] = new Block(new Rectangle(new Point(0, 0), 10, BOARD_HEIGHT), Color.gray); // left
        borders[2] = new Block(new Rectangle(new Point(BOARD_WIDTH - 10, 0), 10, BOARD_HEIGHT), Color.gray); // right
        return borders;
    }

    /**
     * this function is generating the balls and adding them to environment.
     * @param v - the list of the velocities.
     */
    public void balls(Iterator<Velocity> v) {
        Ball[] ball = new Ball[info.numberOfBalls()];

        for (Ball b1 : ball) {
            b1 = new Ball(new Point((double) ((BOARD_WIDTH - info.paddleWidth()) / 2)
                    + (double) (info.paddleWidth() / 2), BOARD_HEIGHT - 20 - 25 - 50), 4, Color.white);
            b1.setVelocity(v.next());
            b1.setGameEnv(environment);
            b1.addToGame(this);
        }
    }

    /**
     * this function is generating the blocks and adding them to environment.
     *
     * @param blockRem      - charge of removing balls, and updating the ball counter.
     * @param scoreTracking - charge of updating the score after removing a block.
     * @param specialBlock  - charge of create a special block.
     */
    public void blocks(BlockRemover blockRem, ScoreTrackingListener scoreTracking, SpecialBlock specialBlock) {

        this.blocks = this.info.blocks();
        for (Block block : blocks) {
            if (this.info.levelName().equals("Medium Level") && block.getColor() == Color.black) {
                block.addHitListener(specialBlock);
            }
            block.addHitListener(blockRem);
            block.addHitListener(scoreTracking);
            block.addToGame(this);

        }
    }

    /**
     * this function is starting the animation loop and running the game.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, this.sprites, info));
        this.running = true;
        this.runner.run(this);
        if (blockCount.getValue() == 0) {
            score.increase(100);
            runner.runOneFrame(this);
        }
    }

    /**
     * @param c - collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * @param s - sprite to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * this function create a new ball and add it to the game.
     *
     * @param start - the start position to the new ball.
     */
    public void addBall(Point start) {
        Ball ball1 = new Ball(start, 4, Color.RED.darker());
        ball1.setVelocity(2, -5);
        ball1.setGameEnv(this.environment);
        ball1.addToGame(this);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.drawBackground(d);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.blockCount.getValue() == 0) {
            this.running = false;
        }
        if (this.ballCount.getValue() == 0) {
          //  running = false;
            this.runner.run(new KeyPressStoppableAnimation(keyboard,
                    KeyboardSensor.SPACE_KEY, new EndScreen(keyboard, score, false)));
            this.getGui().close();
        }
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(keyboard,
                    KeyboardSensor.SPACE_KEY, new PauseScreen(this.keyboard)));
           // this.runner.run(new PauseScreen(this.keyboard));
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * drawing he bacckground.
     * @param d - the draw surface.
     */
    public void drawBackground(DrawSurface d) {
        if (this.info.levelName().equals("Direct Hit")) {
            Point sun = new Point(400, 170);
            this.info.getBackground().drawOn(d);
            int radius = 40;
            d.drawLine((int) sun.getX(), (int) sun.getY() - radius, (int) sun.getX(), (int) sun.getY() + radius);
            d.drawLine((int) sun.getX() - radius, (int) sun.getY(), (int) sun.getX() + radius, (int) sun.getY());
            d.setColor(Color.BLUE);
            d.drawCircle((int) sun.getX(), (int) sun.getY(), radius);
            d.setColor(Color.BLUE);
            d.drawCircle((int) sun.getX(), (int) sun.getY(), radius + 33);
            d.setColor(Color.BLUE);
            d.drawCircle((int) sun.getX(), (int) sun.getY(), radius + 66);
            d.setColor(Color.BLUE);
            d.drawLine(295, 170, 400, 170);
            d.drawLine(400, 170, 505, 170);
            d.drawLine(400, 170, 400, 275);
            d.drawLine(400, 170, 400, 65);
        }
        if (this.info.levelName().equals("Easy Level")) {
            this.info.getBackground().drawOn(d);
            int j = 0;
            Color color = new Color(255, 255, 180);
            d.setColor(color);
            for (int i = 0; i < 75; i++) {
                d.drawLine(100, 120, j, 245);
                j += 12;
            }
            d.fillCircle(100, 120, 55);
            color = new Color(255, 255, 120);
            d.setColor(color);
            d.fillCircle(100, 120, 45);
            color = new Color(255, 255, 60);
            d.setColor(color);
            d.fillCircle(100, 120, 40);
        }
        if (this.info.levelName().equals("Medium Level")) {
            this.info.getBackground().drawOn(d);
            // building
            d.setColor(Color.black);
            d.fillRectangle(70, 487, 90, 120);
            Color color;
            d.setColor(Color.red.darker().darker());
            for (int i = 78; i < 144; i += 16) {
                for (int j = 495; j < 600; j += 22) {
                    if (i == 78 && j == 495) {
                        color = new Color(180, 50, 50);
                        d.setColor(color);
                        d.fillRectangle(i, j, 10, 15);
                    } else if ((i == 78 && j == 517) || (i == 94 && j == 495)) {
                        color = new Color(180, 120, 50);
                        d.setColor(color);
                        d.fillRectangle(i, j, 10, 15);
                    } else if ((i == 78 && j == 539) || (i == 94 && j == 517) || (i == 110 && j == 495)) {
                        color = new Color(255, 255, 120);
                        d.setColor(color);
                        d.fillRectangle(i, j, 10, 15);
                    } else if ((i == 78 && j == 561) || (i == 94 && j == 539) || (i == 110 && j == 517)
                            || (i == 126 && j == 495)) {
                        color = new Color(160, 255, 120);
                        d.setColor(color);
                        d.fillRectangle(i, j, 10, 15);
                    } else if ((i == 78 && j == 583) || (i == 94 && j == 561) || (i == 110 && j == 539)
                            || (i == 126 && j == 517) || (i == 142 && j == 495)) {
                        color = new Color(100, 150, 100);
                        d.setColor(color);
                        d.fillRectangle(i, j, 10, 15);
                    } else if ((i == 94 && j == 583) || (i == 110 && j == 561) || (i == 126 && j == 539)
                            || (i == 142 && j == 517))  {
                        color = new Color(183, 236, 242);
                        d.setColor(color);
                        d.fillRectangle(i, j, 10, 15);
                    } else if ((i == 110 && j == 583) || (i == 126 && j == 561) || (i == 142 && j == 539)) {
                        color = new Color(200, 150, 200);
                        d.setColor(color);
                        d.fillRectangle(i, j, 10, 15);
                    } else if ((i == 126 && j == 583) || (i == 142 && j == 561)) {
                        color = new Color(180, 160, 255);
                        d.setColor(color);
                        d.fillRectangle(i, j, 10, 15);
                    } else if (i == 142 && j == 583) {
                        color = new Color(90, 60, 130);
                        d.setColor(color);
                        d.fillRectangle(i, j, 10, 15);
                    }
                }
            }
            d.setColor(Color.black);
            d.fillRectangle(111, 200, 8, 250);
            d.fillRectangle(105, 450, 20, 37);
            d.setColor(Color.yellow);
            d.fillCircle(115, 200, 10);
            d.setColor(Color.BLACK);
        }

        if (this.info.levelName().equals("Hard Level")) {
            this.info.getBackground().drawOn(d);
            Color color = new Color(211, 244, 248);
            d.setColor(color);
            d.fillCircle(690, 295, 35);
            d.drawLine(690, 295, 650, 600);
            d.drawLine(660, 315, 620, 620);
            d.drawLine(610, 335, 570, 640);
            d.drawLine(630, 355, 590, 660);
            color = new Color(183, 236, 242);
            d.setColor(color);
            d.fillCircle(600, 300, 35);
            d.fillCircle(635, 320, 40);
            color = new Color(211, 244, 248);
            d.setColor(color);
            d.fillCircle(650, 260, 35);


           // d.fillCircle(450, 360, 35);
            d.drawLine(400, 400, 370, 600);
            d.drawLine(425, 425, 395, 625);
            d.drawLine(450, 450, 420, 650);
            d.drawLine(485, 400, 455, 600);
            d.fillCircle(490, 395, 35);
            color = new Color(183, 236, 242);
            d.setColor(color);
            d.fillCircle(400, 400, 35);
            d.fillCircle(435, 420, 40);
            color = new Color(211, 244, 248);
            d.setColor(color);
            d.fillCircle(450, 360, 35);

            color = new Color(169, 232, 239);
            d.setColor(color);
            d.fillCircle(660, 195, 35);
            color = new Color(211, 244, 248);
            d.setColor(color);
            d.fillCircle(200, 300, 35);
            d.drawLine(270, 300, 240, 600);
            d.drawLine(245, 300, 215, 600);
            d.drawLine(220, 300, 190, 600);
            d.drawLine(195, 300, 165, 600);

            color = new Color(169, 232, 239);
            d.setColor(color);
            d.fillCircle(230, 350, 35);
            d.fillCircle(255, 300, 35);
            color = new Color(211, 244, 248);
            d.setColor(color);
            d.fillCircle(230, 260, 35);
        }
        //Color color = new Color(0x4C1D1D);
    }
}