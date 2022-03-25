package engine.mobile;

import engine.map.Block;

public class Car extends MobileElement {

	private Block lastPosition;
	int vitesse;

	private boolean ClignotantGauche = false;
	private boolean ClignotantDroit = false;

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
	
	public void shifter(int vitesse) {
		switch (vitesse) {
		case 1:
			System.out.println("1ere vitesse");
			setVitesse(1);
			break;
		case 2:
			System.out.println("2eme vitesse");
			setVitesse(2);
			break;
		case 3:
			System.out.println("3eme vitesse");
			setVitesse(3);
			break;
		case 4:
			System.out.println("4eme vitesse");
			setVitesse(4);
			break;
		case 5:
			System.out.println("5eme vitesse");
			setVitesse(5);
			break;
		}
	}

	@Override
	public String toString() {
		return "Car [lastPosition=" + lastPosition + ", vitesse=" + vitesse + ", ClignotantGauche=" + ClignotantGauche
				+ ", ClignotantDroit=" + ClignotantDroit + "]";
	}

}
