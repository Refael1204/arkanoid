//  Refael Avrahami 206482655
package Interface;
import biuoop.DrawSurface;
/**
 * sprite interface.
 */
public interface Sprite {
    /**
     * this function draws the sprite on the given surface.
     * @param d - given surface
     */
    void drawOn(DrawSurface d);

    /**
     * this function is notify the sprite that time has passed.
     */
    void timePassed();
}
