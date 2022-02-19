/**
 * 
 */
package config;

/**
 * The cyclic counter is a bounded counter with cyclic value change.
 * @author afatc
 *
 */
public class CyclicCounter extends BoundedCounter {

	public CyclicCounter(int value, int max, int min) {
		super(value, max, min); 
	}

	@Override
	public void decrement() {
		if (getValue() > getMin()) {
			super.decrement();
		} else {
			setValue(getMax());
		}
	}

	@Override
	public void increment() {
		if (getValue() < getMax()) {
			super.increment();
		} else {
			setValue(getMin());
		}
	}

	@Override
	public String toString() {
		return Chronometer.transform(getValue());
	}
}
