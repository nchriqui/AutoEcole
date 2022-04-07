package engine.fixed;

import engine.map.Block;

public class SpeedLimit extends FixedElement {
	
	/*
	 * 
	 * The class to define the object SpeedLimit of our map.
	 * 
	 */


	int vitesse = 0;

	public SpeedLimit(Block position, int vitesse) {
		super(position);
		this.vitesse = vitesse;
	}

	public int getVitesse() {
		return vitesse;
	}

	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	@Override
	public String toString() {
		return "SpeedLimit [vitesse=" + vitesse + "]";
	}

}
