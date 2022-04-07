package chrono;

import config.GameConfiguration;

/**
 * This is the basic counter implementation. The counter has a integer value
 * that can be incremented or decremented.
 * 
 * We can only increment or decrement the value by 1 each time.
 * 
 * @author Auto-Ecole
 *
 */
public class Counter {
	private int value;
	private boolean run;

	public Counter(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	protected void setValue(int value) {
		this.value = value;
	}

	public boolean isRun() {
		return run;
	}

	public void setRun(boolean run) {
		this.run = run;
	}

	public void init() {
		setValue(GameConfiguration.STOP_DURATION);
		setRun(false);
	}

	public void decrement() {
		value--;
	}

	public boolean endCounter() {
		boolean result = false;
		if (getValue() == 0 || getValue() == GameConfiguration.STOP_DURATION) {
			result = true;
		}
		return result;
	}

	@Override
	public String toString() {
		return value + " secondes";
	}
}
