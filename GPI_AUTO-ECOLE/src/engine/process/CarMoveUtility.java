package engine.process;

public class CarMoveUtility {

	public boolean checkMoveLeft(MobileElementManager manager) {
		// si le dernier deplacement était en haut , en bas ou à gauche
		if (manager.isLastMoveUp() == true || manager.isLastMoveDown() == true || manager.isLastMoveLeft() == true) {
			return true;

		} else {
			System.out.println("DEPLACEMENT IMPOSSIBLE");
			return false;
		}
	}

	public boolean checkMoveUp(MobileElementManager manager) {
		// si le dernier deplacement était a gauche , a droite ou en haut
		if (manager.isLastMoveLeft() == true || manager.isLastMoveRight() == true || manager.isLastMoveUp() == true) {
			return true;
		} else {
			System.out.println("DEPLACEMENT IMPOSSIBLE");
			return false;
		}
	}

	public boolean checkMoveRight(MobileElementManager manager) {
		// si le dernier deplacement était en haut , en bas ou a droite
		if (manager.isLastMoveUp() == true || manager.isLastMoveDown() == true || manager.isLastMoveRight() == true) {
			return true;
		} else {
			System.out.println("DEPLACEMENT IMPOSSIBLE");
			return false;
		}
	}

	public boolean checkMoveBottom(MobileElementManager manager) {
		// si le dernier deplacement était a gauche , a droite ou en bas
		if (manager.isLastMoveLeft() == true || manager.isLastMoveRight() == true || manager.isLastMoveDown() == true) {
			return true;
		} else {
			System.out.println("DEPLACEMENT IMPOSSIBLE");
			return false;
		}
	}

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