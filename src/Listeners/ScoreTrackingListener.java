// Refael Avrahami, 206482655
package Listeners;

import Default.Counter;
import Interface.HitListener;
import ShownObject.Ball;
import ShownObject.Block;
/**
 * A listener of the game score.
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;
    /**
     * @param scoreCounter current score counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /**
     *  Update current score.
     *  @param beingHit block that is being hit
     *  @param hitter ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
            this.currentScore.increase(5);
    }
}
