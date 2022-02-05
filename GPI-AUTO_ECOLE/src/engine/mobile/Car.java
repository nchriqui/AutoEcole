package engine.mobile;

import engine.Map.Block;

public class Car { 
	private Block Position ;

	public Car(Block position) {
		super();
		Position = position;
	}

	public Block getPosition() {
		return Position;
	}

	public void setPosition(Block position) {
		Position = position;
	}

	@Override
	public String toString() {
		return "Car [Position=" + Position + "]";
	} 
}
	
	
	