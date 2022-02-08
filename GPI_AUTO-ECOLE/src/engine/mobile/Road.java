package engine.mobile;

import engine.Map.Block;

public class Road {
	
	private Block position;

	public Road(Block position) {
		this.position = position;
	}
	
	public Block getPosition() {
		return position;
	}

	public void setPosition(Block position) {
		this.position = position;
	}

}
