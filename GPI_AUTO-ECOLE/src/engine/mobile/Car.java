package engine.mobile;

import engine.map.Block;

public class Car extends MobileElement {

	private Block lastPosition;

	public Car(Block position) {
		super(position);
	}

	public Block getLastPosition() {
		return lastPosition;
	}

	public void setLastPosition(Block lastPosition) {
		this.lastPosition = lastPosition;
	}

	@Override
	public String toString() {
		return "Car [lastPosition=" + lastPosition + ", getLastPosition()=" + getLastPosition() + ", getPosition()="
				+ getPosition() + ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + "]";
	}

}
