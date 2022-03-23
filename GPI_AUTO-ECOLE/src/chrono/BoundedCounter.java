/**
 * 
 */
package chrono;

/**
 * The bounded counter has a min value and a max value.
 * 
 * We can set freely these two values when creating the bounded counter.
 * 
 * @author afatc
 *
 */
public class BoundedCounter extends Counter {
	private int max;
	private int min;

	public BoundedCounter(int value, int max, int min) {
		super(value);
		this.max = max;
		this.min = min;
	}

	@Override
	public void decrement() {
		if (getValue() > min) {
			super.decrement();
		}
	}

	@Override
	public void increment() {
		if (getValue() < max) {
			super.increment();
		}
	}

	public int getMax() {
		return max;
	}

	public int getMin() {
		return min;
	}
}
