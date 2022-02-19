/**
 * 
 */
package config;

/**
 * This is the basic counter implementation. The counter has a integer value that can be incremented or decremented. 
 * 
 * We can only increment or decrement the value by 1 each time.
 * 
 * @author afatc
 *
 */
public class Counter {
	private int value;

	public Counter(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void increment() {
		value++;
	}

	public void decrement() {
		value--;
	}

	protected void setValue(int value) {
		this.value = value;
	}
}
