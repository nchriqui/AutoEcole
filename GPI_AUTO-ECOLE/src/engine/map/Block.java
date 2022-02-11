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
	
	public boolean compareBlock(Block block1 , Block block2) {
		if (block1.getLine() == block2.getLine() && block1.getColumn() == block2.getColumn()) {
			return true;
		}else {
			return false;
		}	
	}
	
	
	@Override
	public String toString() {
		return "Block [line=" + line + ", column=" + column + "]";
	}
}
