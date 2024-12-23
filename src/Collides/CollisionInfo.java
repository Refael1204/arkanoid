//  Refael Avrahami 206482655
package Collides;
import Interface.Collidable;
import geometryShape.Point;

/**
 * this class Consists of collisionObject and collisionPoint.
 */
public class CollisionInfo {
    private final Collidable collisionObject;
    private Point collisionPoint;
    /**
     * @param collisionObject -  collision object
     * @param collisionPoint - collision point
     */
    public CollisionInfo(Collidable collisionObject, Point collisionPoint) {
        this.collisionObject = collisionObject;
        this.collisionPoint = collisionPoint;
    }
    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return collisionPoint;
    }
    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return collisionObject;
    }
}
