//  Refael Avrahami 206482655
package Default;
import Interface.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.*;

/**
 * pause screen.
 */
public class PauseScreen implements Animation {
    private final KeyboardSensor keyboard;
    private final boolean stop;
    /**
     * Constructor.
     * @param k sensor of the keyboard.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.red.darker());
        d.fillRectangle(370, 150, 20, 80);
        d.fillRectangle(410, 150, 20, 80);
        int j = 450;
        for (int i = 0; i < 40; i++) {
            d.drawLine(450, 150, j, 190);
            j+=1;
        }
        j = 450;
        for (int i = 0; i < 40; i++) {
            d.drawLine(450, 230, j, 190);
            j+=1;
        }
        d.fillRectangle(450,175, 15,40);
        d.drawText(160, d.getHeight() / 2, "paused -- press space to continue", 32);
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
