package engine.fixed;

import engine.map.Block;
import engine.mobile.MobileElement;

public class Road extends MobileElement {
	
	int direction = 0;

	public Road(Block position, int direction) {
		super(position);
		this.direction = direction;
	}
	
	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	@Override
	public String toString() {
		return "Road [direction=" + direction + ", getPosition()=" + getPosition() + "]";
	}
}
