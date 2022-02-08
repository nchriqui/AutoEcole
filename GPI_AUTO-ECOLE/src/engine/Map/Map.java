package engine.Map;

import java.util.ArrayList;
import java.util.List;

import engine.mobile.Light;
import engine.mobile.Road;

public class Map {

		private Block[][] blocks;
		private List<Road> roads = new ArrayList<Road>();
		private List<Road> roads2 = new ArrayList<Road>();
		private List<Road> roads3 = new ArrayList<Road>();
		private List<Road> roads4 = new ArrayList<Road>();
		private List<Light> lights = new ArrayList<Light>();

		public List<Road> getRoads2() {
			return roads2;
		}

		public List<Road> getRoads3() {
			return roads3;
		}

		public void setRoads3(List<Road> roads3) {
			this.roads3 = roads3;
		}

		public List<Road> getRoads4() {
			return roads4;
		}

		public void setRoads4(List<Road> roads4) {
			this.roads4 = roads4;
		}

		public void setRoads2(List<Road> roads2) {
			this.roads2 = roads2;
		}

		private int lineCount;
		private int columnCount;

		public Map(int lineCount, int columnCount) {
			this.lineCount = lineCount;
			this.columnCount = columnCount;

			blocks = new Block[lineCount][columnCount];

			for (int lineIndex = 0; lineIndex < lineCount; lineIndex++) {
				for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
					blocks[lineIndex][columnIndex] = new Block(lineIndex, columnIndex);
				}
			}
			initHorizontalRoads();
			initHorizontalRoads2();
			initVerticalRoads();
			initVerticalRoads2();
			//initLight();
		}

		public List<Light> getLights() {
			return lights;
		}

		public void setLights(List<Light> lights) {
			this.lights = lights;
		}

		public List<Road> getRoads() {
			return roads;
		}

		public void setRoads(List<Road> roads) {
			this.roads = roads;
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

		public Block getBlock(int line, int column) {
			return blocks[line][column];
		}
		
		public void initHorizontalRoads() {
			for (int lineIndex = 0; lineIndex < lineCount; lineIndex++) {
				Road road = new Road(blocks[lineIndex][0]);
				roads.add(road);
			}
		}
		
		public void initHorizontalRoads2() {
			for (int lineIndex = 0; lineIndex < lineCount; lineIndex++) {
				Road road2 = new Road(blocks[lineIndex][lineCount-1]);
				roads2.add(road2);
			}
		}
		
		public void initVerticalRoads() {
			for (int columnIndex = 1; columnIndex < columnCount; columnIndex++) {
				Road road3 = new Road(blocks[0][columnIndex]);
				roads3.add(road3);
			}
		}
		
		public void initVerticalRoads2() {
			for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
				Road road4 = new Road(blocks[columnCount-1][columnIndex]);
				roads4.add(road4);
			}
		}
		
		/*public void initLight() {
			Light light = new Light(blocks[1][1]);
			lights.add(light);
		}*/
}
