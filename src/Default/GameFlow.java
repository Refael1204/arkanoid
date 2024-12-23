//  Refael Avrahami 206482655
package Default;

import Interface.LevelInformation;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * GameFlow class charge of creating the different levels, and moving
 * from one level to the next.
 */
public class GameFlow {
    private final AnimationRunner animationRunner;
    private final KeyboardSensor keyboardSensor;
    private final Counter countBlocks = new Counter(0);
    private final Counter score = new Counter(0);
    private final boolean didWin = true;

    /**
     * @param anim is AnimationRunner of the game.
     * @param key is our KeyboardSensor of the game.
     */
    public GameFlow(AnimationRunner anim, KeyboardSensor key) {
        this.animationRunner = anim;
        this.keyboardSensor = key;
    }

    /**
     * Creates the wanted level by given level info array, run while number
     * and run the game until winning or losing the game.
     * @param levels List with level to play
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.animationRunner, this.score, this.countBlocks);
            level.initialize();
            level.run();
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                KeyboardSensor.SPACE_KEY, new EndScreen(this.animationRunner.getGui().getKeyboardSensor(), this.score,
                this.didWin)));
        this.animationRunner.getGui().close();
         }
}
