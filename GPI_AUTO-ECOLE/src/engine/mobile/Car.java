package engine.mobile;

import engine.map.Block;

/**
 * The class to define the object Car of our map.
 * 
 * @author Auto-Ecole
 *
 */
public class Car extends MobileElement {

	private Block lastPosition; // Block use to define the last position of the our car.
	
	int vitesse; // Integer use to define the speed of the car. 

	private boolean ClignotantGauche = false; // Boolean to know if the left flasher is on.
	private boolean ClignotantDroit = false; // Boolean to know if the right flasher is on.

	/**
	 * @param position - Block use to define the position of our car on the map.
	 */
	public Car(Block position) {
		super(position);
	}

	public Block getLastPosition() {
		return lastPosition;
	}

	public void setLastPosition(Block lastPosition) {
		this.lastPosition = lastPosition;
	}

	public boolean isClignotantGauche() {
		return ClignotantGauche;
	}

	public void setClignotantGauche(boolean clignotantGauche) {
		ClignotantGauche = clignotantGauche;
	}

	public boolean isClignotantDroit() {
		return ClignotantDroit;
	}

	public void setClignotantDroit(boolean clignotantDroit) {
		ClignotantDroit = clignotantDroit;
	}

	public int getVitesse() {
		return vitesse;
	}

	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	/**
	 * Method use to recover the direction of the car thanks the comparaison of his actual postion and his last position.
	 * 
	 * 
	 * 
	 */
	public int recupDirection() {
		int direction = 0;
		/*
		 * direction => 1 : gauche | 2 : haut | 3 : droite | 4 : bas
		 */
		int line = getPosition().getLine() - getLastPosition().getLine();
		int column = getPosition().getColumn() - getLastPosition().getColumn();

		if (line > 0 && column == 0) {
			direction = 4; /* bas */
		} else if (line < 0 && column == 0) {
			direction = 2; /* haut */
		} else if (line == 0 && column < 0) {
			direction = 1; /* gauche */
		} else if (line == 0 && column > 0) {
			direction = 3; /* droite */
		} else {
			System.out.println("Votre déplacement est illogique.\n");
		}
		return direction;
	}
	
	/**
	 * Method use to set the speed of the car 
	 * 
	 * 
	 * @param speed : the speed of the car 
	 * 
	 */
	public void shifter(int vitesse) {///
		switch (vitesse) {
		case 1:
			if(getVitesse()==0 || getVitesse()==40) {
				System.out.println("1ere vitesse");
				setVitesse(20);
			}else {
				System.out.println("Vous ne pouvez pas sauter des vitesses");
			}
			break;
		case 2:
			if(getVitesse()==20 || getVitesse()==60) {
				System.out.println("2eme vitesse");
				setVitesse(40);
			}else {
				System.out.println("Vous ne pouvez pas sauter des vitesses");
			}
			break;
		case 3:
			if(getVitesse()==40 || getVitesse()==80) {
				System.out.println("3eme vitesse");
				setVitesse(60);
			}else {
				System.out.println("Vous ne pouvez pas sauter des vitesses");
			}
			break;
		case 4:
			if(getVitesse()==60 || getVitesse()==100) {
				System.out.println("4eme vitesse");
				setVitesse(80);
			}else {
				System.out.println("Vous ne pouvez pas sauter des vitesses");
			}
			break;
		case 5:
			if(getVitesse()==80 ) {
				System.out.println("5eme vitesse");
				setVitesse(100);
			}else {
				System.out.println("Vous ne pouvez pas sauter des vitesses");
			}
			break;
		}
	}

	@Override
	public String toString() {
		return "Car [lastPosition=" + lastPosition + ", vitesse=" + vitesse + ", ClignotantGauche=" + ClignotantGauche
				+ ", ClignotantDroit=" + ClignotantDroit + "]";
	}

}
