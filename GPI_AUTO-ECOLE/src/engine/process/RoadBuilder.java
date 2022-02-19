package engine.process;

import java.util.ArrayList;
import java.util.List;

import config.GameConfiguration;
import engine.fixed.Road;
import engine.map.Block;

public class RoadBuilder {

	private Block[][] blocks;
	private List<Road> roads = new ArrayList<Road>();

	private int lineCount;
	private int columnCount;

	public RoadBuilder(int lineCount, int columnCount) {
		this.lineCount = lineCount;
		this.columnCount = columnCount;

		blocks = new Block[lineCount][columnCount];

		for (int lineIndex = 0; lineIndex < lineCount; lineIndex++) {
			for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
				blocks[lineIndex][columnIndex] = new Block(lineIndex, columnIndex);
			}
		}
		initRoads();
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

	public List<Road> getRoads() {
		return roads;
	}

	public void setRoads(List<Road> roads) {
		this.roads = roads;
	}

	public void initRoads() {
		int lineCount = GameConfiguration.LINE_COUNT;
		int columCount = GameConfiguration.COLUMN_COUNT;
		int diffPositionRoad = GameConfiguration.DIFF_ROAD_POSITION;

		// STOCKAGE ROUTE VERTICAL GAUCHE
		for (int lineIndex = diffPositionRoad; lineIndex < lineCount - (diffPositionRoad); lineIndex++) {
			for (int columnCount = diffPositionRoad - 1; columnCount < (diffPositionRoad + 1); columnCount++) {
				Road road = new Road(blocks[lineIndex][columnCount]);
				roads.add(road);
			}
		}

		// STOCKAGE ROUTE VERTICALE DROITE
		for (int lineIndex = diffPositionRoad; lineIndex < lineCount - (diffPositionRoad); lineIndex++) {
			for (int columnIndex = columCount - (diffPositionRoad + 1); columnIndex < columCount - 1; columnIndex++) {
				Road road = new Road(blocks[lineIndex][columnIndex]);
				roads.add(road);
			}
		}

		// STOCKAGE ROUTE HORIZONTALE HAUTE
		for (int lineIndex = diffPositionRoad - 1; lineIndex < (diffPositionRoad + 1); lineIndex++) {
			for (int columnIndex = diffPositionRoad - 1; columnIndex < columCount - 1; columnIndex++) {
				Road road = new Road(blocks[lineIndex][columnIndex]);
				roads.add(road);
			}
		}

		// STOCKAGE ROUTE HORIZONTALE BASSE
		for (int lineIndex = lineCount - (diffPositionRoad + 1); lineIndex < lineCount - 1; lineIndex++) {
			for (int columnIndex = diffPositionRoad - 1; columnIndex < columCount - 1; columnIndex++) {
				Road road = new Road(blocks[lineIndex][columnIndex]);
				roads.add(road);
			}
		}

	}

}
