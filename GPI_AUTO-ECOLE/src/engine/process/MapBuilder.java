/**
 * @author ABERKANE Mehdi
 *
 * GPI_AUTO-ECOLE
 *
 * 23 mars 2022
 */
package engine.process;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import config.GameConfiguration;
import engine.fixed.Road;
import engine.map.Block;
import engine.mobile.Light;

public class MapBuilder {
	private List<Road> roads = new ArrayList<Road>();
	private List<Light> lights = new ArrayList<Light>();
	private List<Block> pVCrossings = new ArrayList<Block>();
	private List<Block> pHCrossings = new ArrayList<Block>();
	private List<Block> stops = new ArrayList<Block>();
	private List<Block> prohibitedDirs = new ArrayList<Block>();
	private List<Block> waitStopV = new ArrayList<Block>();
	private List<Block> waitStopH = new ArrayList<Block>();

	private int lineCount;
	private int columnCount;

	public MapBuilder(int lineCount, int columnCount) {
		this.lineCount = lineCount;
		this.columnCount = columnCount;

		try {
			initMapCSV(lineCount, columnCount);
		} catch (FileNotFoundException e) {
			System.out.println("Erreur : " + e);
		}
	}

	public int getLineCount() {
		return lineCount;
	}

	public void setLineCount(int lineCount) {
		this.lineCount = lineCount;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public void setColumnCount(int columnCount) {
		this.columnCount = columnCount;
	}

	public List<Road> getRoads() {
		return roads;
	}

	public List<Light> getLights() {
		return lights;
	}

	public List<Block> getpVCrossings() {
		return pVCrossings;
	}

	public List<Block> getpHCrossings() {
		return pHCrossings;
	}
	
	public List<Block> getStops() {
		return stops;
	}

	public List<Block> getProhibitedDirs() {
		return prohibitedDirs;
	}
	
	public List<Block> getWaitStopV() {
		return waitStopV;
	}

	public List<Block> getWaitStopH() {
		return waitStopH;
	}

	public void initMapCSV(int lineCount, int columnCount) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(GameConfiguration.mapCSV1));
		sc.useDelimiter(";");
		Integer row = 0;
		Integer column = 0;
		String current;
		while (sc.hasNext()) {
			if (column == columnCount) {
				current = sc.nextLine();
				column = -1;
				row++;

			} else {
				current = sc.next();
			}
			switch (current) {
				case "r" -> {// Road
					Road roadX = new Road(new Block(row, column),0);
					roads.add(roadX);
					break;
				}
				case "a" -> {// Road with direction left
					Road roadX = new Road(new Block(row, column), 1);
					roads.add(roadX);
					break;
				}
				case "b" -> {// Road with direction up
					Road roadX = new Road(new Block(row, column), 2);
					roads.add(roadX);
					break;
				}
				case "c" -> {// Road with direction right
					Road roadX = new Road(new Block(row, column), 3);
					roads.add(roadX);
					break;
				}
				case "d" -> {// Road with direction down
					Road roadX = new Road(new Block(row, column), 4);
					roads.add(roadX);
					break;
				}
				case "l" -> {// Light
					Light lightX = new Light(new Block(row, column), false);
					lights.add(lightX);
					break;
				}
				case "v" -> {// Vertical Pedestian
					Road roadX = new Road(new Block(row, column),0);
					roads.add(roadX);
					pVCrossings.add(new Block(row, column));
					break;
				}
				case "h" -> {// Horizontal Pedestian
					Road roadX = new Road(new Block(row, column),0);
					roads.add(roadX);
					pHCrossings.add(new Block(row, column));
					break;
				}
				case "s" -> {// Stops
					stops.add(new Block(row, column));
					break;
				}
				case "t" -> {// Wait Stops vertical
					Road roadX = new Road(new Block(row, column), 0);
					roads.add(roadX);
					waitStopV.add(new Block(row, column));
					break;
				}
				case "u" -> {// Wait Stops Horizontal
					Road roadX = new Road(new Block(row, column), 0);
					roads.add(roadX);
					waitStopH.add(new Block(row, column));
					break;
				}
				case "p" -> {// Prohibited Directions
					prohibitedDirs.add(new Block(row, column));
					break;
				}
				default -> { 
					break;
				}
			}
			column++;
		}
	}
}
