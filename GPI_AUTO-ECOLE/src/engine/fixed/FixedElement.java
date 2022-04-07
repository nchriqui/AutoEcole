package engine.fixed;

import engine.map.Block;
/**
 * 
 * This abstract class serves to parameter the fixed class 
 * 
 */
public abstract class FixedElement {
	private Block position;

	public FixedElement(Block position) {
		this.position = position;
	}

	public Block getPosition() {
		return position;
	}

	public void setPosition(Block position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "FixedElement [position=" + position + "]";
	}

}
