// Refael Avrahami, 206482655
package Listeners;

import Default.Counter;
import Default.GameLevel;
import Interface.HitListener;
import ShownObject.Ball;
import ShownObject.Block;
/**
 * class SpecialBlock is in charge of add ball when ball hits in a special block, and updating the ball counter.
 */
public class SpecialBlock implements HitListener {
    private final GameLevel game;
    private final Counter countBall;

    /**
     * constructor.
     * @param game - the current game being played.
     * @param countBall - counter.
     */
    public SpecialBlock(GameLevel game, Counter countBall) {
        this.game = game;
        this.countBall = countBall;
    }

    /**
     * @param beingHit block being hit.
     * @param hitter the hitting ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.game.addBall(beingHit.getCollisionRectangle().getUpperLeft());
        this.countBall.increase(1);
        System.out.println("add");
    }

}
