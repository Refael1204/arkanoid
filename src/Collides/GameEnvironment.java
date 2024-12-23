//  Refael Avrahami 206482655
package Collides;
import Interface.Collidable;
import geometryShape.Line;
import geometryShape.Point;
import geometryShape.Rectangle;

import java.util.ArrayList;
/**
 * GameEnvironment - the GameEnvironment holds a list of Collidable objects.
 */
public class GameEnvironment {
    private final ArrayList<Collidable> collidables = new ArrayList<>();

    /**
     * @param c - add the given collidable to the environment.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * @return the collidibales
     */
    public ArrayList<Collidable> getCollidables() {
        return this.collidables;
    }



    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.

    /**
     * @param trajectory - the trajectory of the ball.
     * @return If this object will not collide with any of the collidables in this collection, return null.
     * Else, return the information about the closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        int indexOfCollidable = -1; // The index of the array of the list of conflictable
        Point closestPoint = null; // The closest point
        Point collisionPoint; // The collision point
        Rectangle currentReC;
        for (int i = 0; i < collidables.size(); i++) {
            currentReC = collidables.get(i).getCollisionRectangle();
            collisionPoint = trajectory.closestIntersectionToStartOfLine(currentReC);
            if (collisionPoint != null) {
                if (closestPoint == null) {
                    indexOfCollidable = i;
                    closestPoint = collisionPoint;
                } else {
                    if (trajectory.start().distance(collisionPoint) < trajectory.start().distance(closestPoint)) {
                        indexOfCollidable = i;
                        closestPoint = collisionPoint;
                    }
                }
            }
        }
        if (closestPoint != null) {
            return new CollisionInfo(collidables.get(indexOfCollidable), closestPoint);
        }
        return null;
    }
    /**
     * @param c remove collide from environment
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }
}