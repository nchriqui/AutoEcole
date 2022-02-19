package engine.process;

import java.util.ArrayList;
import java.util.List;

import config.GameConfiguration;
import engine.mobile.Light;
import engine.map.Block;

public class LightBuilder {

	private Block[][] blocks;
	private List<Light> lightsList = new ArrayList<Light>();

	private int lineCount;
	private int columnCount;

	public LightBuilder(int lineCount, int columnCount) {
		this.lineCount = lineCount;
		this.columnCount = columnCount;

		blocks = new Block[lineCount][columnCount];

		for (int lineIndex = 0; lineIndex < lineCount; lineIndex++) {
			for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
				blocks[lineIndex][columnIndex] = new Block(lineIndex, columnIndex);
			}
		}
		initLights();
	}

	public Block[][] getBlocks() {
		return blocks;
	}

	public int getLineCount() {
		return lineCount;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public List<Light> getLightsList() {
		return lightsList;
	}

	public void setLightsList(List<Light> lightsList) {
		this.lightsList = lightsList;
	}

	public void initLights() {
		int lineCount = GameConfiguration.LINE_COUNT;
		int columCount = GameConfiguration.COLUMN_COUNT;
		int diffPositionLight = GameConfiguration.DIFF_LIGHT_POSITION;

		for (int lineIndex = 0; lineIndex < lineCount; lineIndex++) {
			for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {

				// FEU ROUGE DROIT
				if (lineIndex == lineCount / 2 - 1 && columnIndex == diffPositionLight - 1) {
					Light lightX = new Light(new Block(lineIndex, columnIndex), false);
					lightsList.add(lightX);
				}

				// FEU ROUGE GAUCHE
				if (lineIndex == lineCount / 2 - 1 && columnIndex == columCount - (diffPositionLight)) {
					Light lightX = new Light(new Block(lineIndex, columnIndex), false);
					lightsList.add(lightX);
				}
			}
		}

	}

}
