//  Refael Avrahami 206482655
package Collides;
import java.util.ArrayList;

import Interface.Sprite;
import biuoop.DrawSurface;
/**
 * SpriteCollection will hold a collection of sprites.
 */
public class SpriteCollection {
    private final ArrayList<Sprite> sprites;
    /**
     * Constructor.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }
    /**
     * add sprites to collection.
     * @param s - a sprite to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }
    /**
     * Call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < sprites.size(); i++) {
            sprites.get(i).timePassed();
        }
    }
    /**
     * Call drawOn(d) on all sprites.
     * @param d where we draw.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }
    /**
     * Method to remove sprites to collection.
     * @param s a sprite to remove.
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }
}
