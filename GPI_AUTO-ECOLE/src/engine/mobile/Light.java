package engine.mobile;

import engine.map.Block;

public class Light {
	
	private Block position;
	private Boolean go= false;

	/**
	 * @param position
	 * @param go
	 */
	public Light(Block position, Boolean go) {
		this.position = position;
		this.go = go;
	}

	public Block getPosition() {
		return position;
	}

	public void setPosition(Block position) {
		this.position = position;
	}

	public Boolean getGo() {
		return go;
	}

	public void setGo(Boolean go) {
		this.go = go;
	}
	
	
	
	
	
}
