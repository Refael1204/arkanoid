//  Refael Avrahami 206482655
package Default;

import Interface.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Decorator class that will wrap an existing animation and add a "waiting-for-key" behavior to it.
 */
public class KeyPressStoppableAnimation implements Animation {
    private final KeyboardSensor sensor;
    private final String key;
    private final Animation animation;
    private boolean done;
    private boolean isAlreadyPressed;

    /**
     * constructor.
     * @param sensor the keyboard sensor.
     * @param key to be pressed.
     * @param animation to be displayed.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.sensor = sensor;
        this.key = key;
        this.isAlreadyPressed = true;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
         if (this.sensor.isPressed(this.key) && !this.isAlreadyPressed) {
            this.done = true;
        }

        if (!this.sensor.isPressed(this.key)) {
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.done;
    }
}
