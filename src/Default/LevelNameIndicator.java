//  Refael Avrahami 206482655
package Default;

import Interface.Sprite;
import biuoop.DrawSurface;
import geometryShape.Rectangle;

import java.awt.Color;
/**
 * Displaying the current level name.
 */
public class LevelNameIndicator implements Sprite {
    private final String s;
    private final Rectangle rectangle;
    /**
     * @param rect of idicator
     * @param name of indicator
     */
    public LevelNameIndicator(Rectangle rect, String name) {
        this.s = name;
        this.rectangle = rect;
    }
    /**
     * @param d - surface to draw.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.drawText((int) this.rectangle.getUpperLeft().getX() + (int) this.rectangle.getWidth() * 3 / 4,
                (int) this.rectangle.getUpperLeft().getY() + 20, "Level Name: " + this.s, 15);
    }

    /**
     * @param game our game
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
    /**
     * Every level we need to print only in the beginning (once).
     * So no need timePassed for this object.
     */
    @Override
    public void timePassed() {
    }
}
