package engine.process;

import config.GameConfiguration;

/**
 * The class use to check the move of the car.
 * 
 * @author Auto-Ecole
 *
 */
public class CarMoveUtility {
	/**
	 * Method use to check if the move is possible.
	 * 
	 * @param MobileElementManager manager - Object MobileElementManager to recover
	 *                             all the boolean of the car move.
	 * 
	 * @return a Boolean showing if the move is possible.
	 */
	public boolean checkMoveLeft(MobileElementManager manager) {///
		int vitesse=manager.getCar().getVitesse();
		//Si la voiture est au point mort
		if (vitesse != 0 ) {
		// si le dernier deplacement était en haut , en bas ou à gauche
			if (manager.isLastMoveUp() == true || manager.isLastMoveDown() == true || manager.isLastMoveLeft() == true) {
				return true;
			}else {
				System.out.println("DEPLACEMENT IMPOSSIBLE, SCORE-1");
				GameConfiguration.SCORE--;
				return false;
			}
		}else {
			System.out.println("Passez la première vitesse pour commencer à jouer");
			System.out.println("Touche MAJ+1 ou Touche 1 du clavier numerique");
			return false;
		}
	}
	
	/**
	 * Method use to check if the move is possible.
	 * 
	 * 
	 * @param MobileElementManager manager - Object MobileElementManager to recover
	 *                             all the boolean of the car move.
	 * 
	 * @return a Boolean showing if the move is possible.
	 */
	public boolean checkMoveUp(MobileElementManager manager) {
		int vitesse=manager.getCar().getVitesse();
		//Si la voiture est au point mort
		if (vitesse != 0 ) {
		// si le dernier deplacement était a gauche , a droite ou en haut
			if (manager.isLastMoveLeft() == true || manager.isLastMoveRight() == true || manager.isLastMoveUp() == true) {
				return true;
			}else {
				System.out.println("DEPLACEMENT IMPOSSIBLE, SCORE-1");
				GameConfiguration.SCORE--;
				return false;
			}
		}else {
			System.out.println("Passez la première vitesse pour commencer à jouer");
			System.out.println("Touche MAJ+1 ou Touche 1 du clavier numerique");
			return false;
		}
	}

	/**
	 * Method use to check if the move is possible.
	 * 
	 * 
	 * @param MobileElementManager manager - Object MobileElementManager to recover
	 *                             all the boolean of the car move.
	 * 
	 * @return a Boolean showing if the move is possible.
	 */
	public boolean checkMoveRight(MobileElementManager manager) {
		int vitesse=manager.getCar().getVitesse();
		//Si la voiture est au point mort
		if (vitesse != 0 ) {
		// si le dernier deplacement était en haut , en bas ou a droite
			if (manager.isLastMoveUp() == true || manager.isLastMoveDown() == true || manager.isLastMoveRight() == true) {
				return true;
			}else {
				System.out.println("DEPLACEMENT IMPOSSIBLE, SCORE-1");
				GameConfiguration.SCORE--;
				return false;
			}
		}else {
			System.out.println("Passez la première vitesse pour commencer à jouer");
			System.out.println("Touche MAJ+1 ou Touche 1 du clavier numerique");
			return false;
		}
	}
	
	/**
	 * Method use to check if the move is possible.
	 * 
	 * 
	 * @param MobileElementManager manager - Object MobileElementManager to recover
	 *                             all the boolean of the car move.
	 * 
	 * @return a Boolean showing if the move is possible.
	 */
	public boolean checkMoveBottom(MobileElementManager manager) {
		int vitesse=manager.getCar().getVitesse();
		//Si la voiture est au point mort
		if (vitesse != 0 ) {
		// si le dernier deplacement était a gauche , a droite ou en bas
			if (manager.isLastMoveLeft() == true || manager.isLastMoveRight() == true || manager.isLastMoveDown() == true) {
				return true;
			}else {
				System.out.println("DEPLACEMENT IMPOSSIBLE, SCORE-1");
				GameConfiguration.SCORE--;
				return false;
			}
		}else {
			System.out.println("Passez la première vitesse pour commencer à jouer");
			System.out.println("Touche MAJ+1 ou Touche 1 du clavier numerique");
			return false;
		}
	}
	
	/**
	 * 
	 * Method use to set the variable of the car move.
	 * 
	 * 
	 * @param int moveKeyCode - Integer represent the key code of the keyboard.
	 * @param MobileElementManager manager - Object MobileElementManager to recover
	 *                             all the boolean of the car move.
	 * 
	 */
	public void saveLastMove(int moveKeyCode, MobileElementManager manager) {
		switch (moveKeyCode) {
		case 37: // FLECHE GAUCHE
			manager.setLastMoveLeft(true);
			manager.setLastMoveDown(false);
			manager.setLastMoveRight(false);
			manager.setLastMoveUp(false);
			break;
		case 38: // FLECHE DU HAUT
			manager.setLastMoveLeft(false);
			manager.setLastMoveDown(false);
			manager.setLastMoveRight(false);
			manager.setLastMoveUp(true);
			break;

		case 39: // FLECHE DE DROITE
			manager.setLastMoveLeft(false);
			manager.setLastMoveDown(false);
			manager.setLastMoveRight(true);
			manager.setLastMoveUp(false);
			break;

		case 40: // FLECHE DU BAS
			manager.setLastMoveLeft(false);
			manager.setLastMoveDown(true);
			manager.setLastMoveRight(false);
			manager.setLastMoveUp(false);
			break;

		default:
			break;
		}
	}

}