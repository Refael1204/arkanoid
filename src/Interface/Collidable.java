//  Refael Avrahami 206482655
package Interface;
import Default.Velocity;
import ShownObject.Ball;
import geometryShape.Point;
import geometryShape.Rectangle;

/**
 * collidable interface.
 */
public interface Collidable {
    /**
     * this function is returning the "collision shape" of an object.
     * @return rectangle, the collidable in rectangle shape.
     */
    Rectangle getCollisionRectangle();

    /**
     * this function notify the object that we collided with it at collisionPoint with a given velocity.
     * the return is the new velocity expected after the hit (based on the force the object inflicted on us).
     * @param hitter - the hitting ball.
     * @param collisionPoint - the point where the collision occured
     * @param currentVelocity - the current velocity
     * @return the new velocity expected after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
