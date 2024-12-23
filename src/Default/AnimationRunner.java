//  Refael Avrahami 206482655
package Default;
import Interface.Animation;
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

/**
 * runner of the animation.
 */
public class AnimationRunner {
    private final GUI gui;
    private final int framesPerSecond;
    private final Sleeper sleeper;
    /**
     * @param framesPerSecond - will be this number of frames every second.
     * @param g - our gui of animation.
     */
    public AnimationRunner(GUI g, int framesPerSecond)  {
        this.gui = g;
        this.framesPerSecond = framesPerSecond;
        this.sleeper = new Sleeper();
    }
    /**
     * @param animation to run.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);

            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
    /**
     * @param animation to run but only one frame.
     */
    public void runOneFrame(Animation animation) {
        DrawSurface d = gui.getDrawSurface();
        animation.doOneFrame(d);
        gui.show(d);
    }
    /**
     * @return the gui.
     */
    public GUI getGui() {
        return this.gui;
    }
}
