//  Refael Avrahami 206482655
package ShownObject;
import Default.GameLevel;
import Default.Velocity;
import Interface.Collidable;
import Interface.HitListener;
import Interface.HitNotifier;
import Interface.Sprite;
import biuoop.DrawSurface;
import geometryShape.Point;
import geometryShape.Rectangle;

import java.awt.Color;
import java.util.ArrayList;

/**
 * block Defined by rectangle and color, and it can be collided.
 */
public class Block implements Sprite, Collidable, HitNotifier {
    private final Rectangle rec;
    private Color color;
    private final ArrayList<HitListener> hitListeners;


    /**
     * @param rec - rectangle.
     * @param color - color of the block.
     */
    public Block(Rectangle rec, Color color) {
        this.rec = rec;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * @return the collision rectangle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }

    /**
     * @param collisionPoint - collision point where there was a hit of block or paddle.
     * @param currentVelocity - velocity before the collision.
     * @return the new velocity after the collision.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // Where there is a collision
        Rectangle.LinePosition side = this.rec.getIntersectionLinePosition(collisionPoint);
        this.notifyHit(hitter);

        // If the collision from below then need to change direction vertically
        if (side == Rectangle.LinePosition.Down && currentVelocity.getDy() < 0) {
           // System.out.println("down");
            currentVelocity.changeDyDirection();
            return currentVelocity;
        }
        // If the collision from above then need to change direction vertically
        if (side == Rectangle.LinePosition.Up && currentVelocity.getDy() > 0) {
           // System.out.println("up");
            currentVelocity.changeDyDirection();
            return currentVelocity;
        }
        //If the collision from the left side then you need to change direction horizontally
        if (side == Rectangle.LinePosition.Left && currentVelocity.getDx() > 0) {
           // System.out.println("left");
            currentVelocity.changeDxDirection();
            return currentVelocity;
        }
        //If the collision from the right side then you need to change direction horizontally
        if (side == Rectangle.LinePosition.Right && currentVelocity.getDx() < 0) {
           // System.out.println("right");
            currentVelocity.changeDxDirection();
            return currentVelocity;
        }
        return currentVelocity;
    }

    /**
     * Draws the block on the surface.
     * @param d - surface on which to draw.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
                (int) this.rec.getWidth(), (int) this.rec.getHeight());
        d.setColor(color);
        d.fillRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
                (int) this.rec.getWidth(), (int) this.rec.getHeight());
    }

    /**
     * If we wanted to have animated blocks we could use the timePassed method to implement this behavior.
     */
    @Override
    public void timePassed() {

    }
    /**
     * the function add a block to the game.
     * @param game - our game.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    /**
     * the function remove block from the game.
     * @param game - our game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }
    /**
     * the function add hl as a listener to hit events.
     * @param hl is notify listener.
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }
    /**
     * the function remove hl from the list of listeners to hit events.
     * @param hl is notify listener.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
    /**
     * @param hitter hitting ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        ArrayList<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * @param c - a color.
     */
    public void setColor(Color c) {
        this.color = c;
    }

    /**
     * @return color of the block
     */
    public Color getColor() {
        return this.color;
    }
}
