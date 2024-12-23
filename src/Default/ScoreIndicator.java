//  Refael Avrahami 206482655
package Default;

import Interface.Sprite;
import biuoop.DrawSurface;
import geometryShape.Rectangle;

import java.awt.Color;

/**
 * Displaying the current score. Holds a reference to the score counter,
 * and will be added to the game as a sprite positioned at the top of the screen.
 */
public class ScoreIndicator implements Sprite {
    private final Counter count;
    private final Rectangle rectangle;
    private static final int MAX_COUNT = 385;
    /**
     * Constructor.
     * @param rec - rectangle that inside of it written the score.
     * @param score the score.
     */
    public ScoreIndicator(Counter score, Rectangle rec) {
        this.count = score;
        this.rectangle = rec;
    }
    /**
     * The function draws the score on the surface.
     * @param d the surface to draw on it.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.gray);
        d.fillRectangle(0, 0, 800, 25);
        d.setColor(Color.white);
        if (this.count.getValue() != MAX_COUNT) {
            d.drawText((int) this.rectangle.getUpperLeft().getX() + (int) this.rectangle.getWidth() / 2 - 40,
                    (int) this.rectangle.getUpperLeft().getY() + 20, "Score: " + this.count.getValue(), 15);
        }
    }

    @Override
    public void timePassed() {

    }
    /**
     * Add this indicator to our game.
     * @param game our game.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
