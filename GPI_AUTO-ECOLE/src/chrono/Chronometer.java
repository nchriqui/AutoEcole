package chrono;

import config.GameConfiguration;

/**
 * The chronometer class is composed of the three cyclic counters. We can count
 * until 59 hours 59 minutes and 59 seconds.
 * 
 * @author Auto-Ecole
 *
 */
public class Chronometer {
	private CyclicCounter minute = new CyclicCounter(0, GameConfiguration.GAME_MINUTE_DURATION, 0);
	private CyclicCounter second = new CyclicCounter(0, GameConfiguration.GAME_SECONDE_DURATION, 0);
	private boolean run = true;

	public CyclicCounter getMinute() {
		return minute;
	}

	public CyclicCounter getSecond() {
		return second;
	}

	public boolean isRun() {
		return run;
	}

	public void setRun(boolean run) {
		this.run = run;
	}

	public void init() {
		minute.setValue(GameConfiguration.GAME_MINUTE_DURATION);
		second.setValue(GameConfiguration.GAME_SECONDE_DURATION);
	}

	public void decrement() {
		second.decrement();
		if (second.getValue() == 59) {
			minute.decrement();
		}
	}

	public boolean endChrono() {
		boolean result = false;
		if (this.getMinute().getValue() == 0 && this.getSecond().getValue() == 1) {
			result = true;
		}
		return result;
	}

	public static String transform(int value) {
		String result = "";
		if (value < 10) {
			result = "0" + value;
		} else {
			result = String.valueOf(value);
		}
		return result;
	}

	public String toString() {
		return "" + minute.toString() + ":" + second.toString();
	}

}
