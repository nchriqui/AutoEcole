package engine.mobile;

import engine.Map.Block;

public class Light {

	private Block position;

	public Block getPosition() {
		return position;
	}

	public void setPosition(Block position) {
		this.position = position;
	}

	public Light(Block position) {
		this.position = position;
	}
	
	
}
