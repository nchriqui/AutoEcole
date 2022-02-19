package engine.mobile;

import engine.map.Block;

public class Light extends MobileElement {

	private Boolean go;

	/**
	 * @param position
	 * @param go
	 */
	public Light(Block position, Boolean go) {
		super(position);
		this.go = go;
	}

	public Boolean getGo() {
		return go;
	}

	public void setGo(Boolean go) {
		this.go = go;
	}

}
