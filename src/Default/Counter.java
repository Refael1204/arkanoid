// Refael Avrahami, 206482655

package Default;
/**
 * a counting class.
 */
public class Counter {
    private int counter;
    /**
     * constructor.
     * @param counter is the count of something.
     */
    public Counter(int counter) {
        this.counter = counter;
    }
    /**
     * function to increase value.
     * @param number to add.
     */
    public void increase(int number) {
        this.counter = number + this.counter;
    }
    /**
     * function to decrease value.
     * @param number to subtract.
     */
    public void decrease(int number) {
        this.counter =  this.counter - number;
    }
    /**
     * get value function.
     * @return current value.
     */
    public int getValue() {
        return this.counter;

    }
}