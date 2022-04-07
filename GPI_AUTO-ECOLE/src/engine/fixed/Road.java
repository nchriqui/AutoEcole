package engine.fixed;

import engine.map.Block;

public class Road extends FixedElement {
	/*
	 * 
	 * The class to define the object road of our map.
	 * 
	 */
	int direction = 0;
	int speed = 0;
	/**
	 * @author Mehdi ABERKANE
	 * @param position  - a Block position in the map of the road.
	 * @param direction - an integer varying from 0 to 4, to know the direction of
	 *                  the road.
	 * @param speed - a variable use to define the speed of the car.
	 */
	public Road(Block position, int direction, int speed) {
		super(position);
		this.direction = direction;
		this.speed = speed;
	}
	
	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	@Override
	public String toString() {
		return "Road [direction=" + direction + ", getPosition()=" + getPosition() + "]";
	}
}
