package engine.map;

/**
 * The class to define the block object, essential to create our map.
 * 
 * @author Auto-Ecole
 */

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

	/**
	 * A method to check if two blocks are the same.
	 *
	 * 
	 * @param Block block - The block we want to compare.
	 * 
	 * @return a boolean indicating if the two blocks are identical.
	 */
	public boolean compareBlock(Block block) {
		if (this.getLine() == block.getLine() && this.getColumn() == block.getColumn()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "Block [line=" + line + ", column=" + column + "]";
	}
}
