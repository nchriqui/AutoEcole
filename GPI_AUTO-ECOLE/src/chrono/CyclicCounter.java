package chrono;

/**
 * The cyclic counter is a bounded counter with cyclic value change.
 * 
 * @author Auto-Ecole
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
	public String toString() {
		return Chronometer.transform(getValue());
	}
}
