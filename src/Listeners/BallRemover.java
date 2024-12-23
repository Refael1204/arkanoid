// Refael Avrahami, 206482655
package Listeners;

import Default.Counter;
import Default.GameLevel;
import Interface.HitListener;
import ShownObject.Ball;
import ShownObject.Block;

/**
 * class BallRemover is in charge of removing balls, and updating the ball counter.
 */
public class BallRemover implements HitListener {
    private final GameLevel game;
    private final Counter remainingBall;
    /**
     * @param game - the current game being played.
     * @param removedBall counter.
     */
    public BallRemover(GameLevel game, Counter removedBall) {
        this.game = game;
        this.remainingBall = removedBall;
    }

    /**
     * Remove ball and update counter.
     * @param beingHit - block to determine whether a ball went off the screen.
     * @param hitter hitting ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBall.decrease(1);
    }

}
