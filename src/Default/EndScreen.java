//  Refael Avrahami 206482655
package Default;

import Interface.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * end screen of winning or losing.
 */
public class EndScreen implements Animation {
        private final KeyboardSensor keyboard;
        private final boolean stop;
        private final Counter score;
        private final boolean isWinner;
    /**
     * @param k keyboard sensor.
     * @param score our score.
     * @param isWinner if we win.
     */
    public EndScreen(KeyboardSensor k, Counter score, boolean isWinner) {
        this.keyboard = k;
        this.stop = false;
        this.score = score;
        this.isWinner = isWinner;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        if (isWinner) {
            d.drawText(d.getWidth() / 4, d.getHeight() / 2, "You Win! Your score is " + score.getValue(),
                    32);
            Color color = new Color(100, 150, 100);
            d.setColor(color);
            d.drawLine(200 + 80, 150, 240 + 80, 100);
            d.drawLine(200 + 80, 150, 400 + 80, 150);
            d.drawLine(240 + 80, 100, 260 + 80, 130);
            d.drawLine(260 + 80, 130, 280 + 80, 100);
            d.drawLine(280 + 80, 100, 300 + 80, 130);
            d.drawLine(300 + 80, 130, 320 + 80, 100);
            d.drawLine(320 + 80, 100, 340 + 80, 130);
            d.drawLine(340 + 80, 130, 360 + 80, 100);
            d.drawLine(360 + 80, 100, 400 + 80, 150);
            color = new Color(255, 220, 60);
            d.setColor(color);
            d.fillCircle(240 + 80, 80, 7);
            d.fillCircle(280 + 80, 80, 7);
            d.fillCircle(320 + 80, 80, 7);
            d.fillCircle(360 + 80, 80, 7);
        } else {
            d.drawText(d.getWidth() / 4, d.getHeight() / 2, "Game Over. Your score is " + score.getValue(),
                    32);
            Color color = Color.red;
            d.setColor(color);
            d.fillRectangle(370, 150, 3, 20);
            d.fillRectangle(410, 150, 3, 20);
            d.drawCircle(392, 200, 20);
            d.drawCircle(390, 175, 50);
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
