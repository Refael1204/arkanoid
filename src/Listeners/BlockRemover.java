// Refael Avrahami, 206482655
package Listeners;
import Default.Counter;
import Default.GameLevel;
import Interface.HitListener;
import ShownObject.Ball;
import ShownObject.Block;


/**
 * BlockRemover class is in charge of removing blocks and update the counter.
 */
public class BlockRemover implements HitListener {
    private final GameLevel game;
    private final Counter remainingBlocks;
    /**
     * a constructor.
     * @param game - the current game.
     * @param removedBlocks a counter.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Update the numbers of blocks after remove a block.
     * @param beingHit being hit block.
     * @param hitter hitting ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(this.game);
            this.remainingBlocks.decrease(1);
          //  System.out.println(this.remainingBlocks.getValue());
    }
}
