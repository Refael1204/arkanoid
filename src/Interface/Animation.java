// Refael Avrahami, 206482655
package Interface;
import biuoop.DrawSurface;
/**
 * animation interface.
 */
public interface Animation {
    /**
     * @param d drawing surface to draw the animation.
     */
    void doOneFrame(DrawSurface d);
    /**
     * @return boolean if we should stop the animation.
     */
    boolean shouldStop();
}
