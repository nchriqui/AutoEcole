package engine.map;

public class Block {
	private int line;
	private int column;

	public Block(int line, int column) {
		this.line = line;
		this.column = column;
	}

	public int getLine() {
		return line;
	}

	public int getColumn() {
		return column;
	}

	public boolean compareBlock(Block block) {
		if (this.getLine() == block.getLine() && this.getColumn() == block.getColumn()) {
			return true;
		} else {
			return false;
		}
	}

	public int recupDirection(Block lastPosition) {
		int direction = 0;
		/*
		 * direction => 1 : gauche | 2 : haut | 3 : droite | 4 : bas
		 */
		int line = this.getLine() - lastPosition.getLine();
		int column = this.getColumn() - lastPosition.getColumn();

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

	@Override
	public String toString() {
		return "Block [line=" + line + ", column=" + column + "]";
	}
}
