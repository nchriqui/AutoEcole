package gui;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import config.GameConfiguration;
import engine.fixed.Road;
import engine.fixed.SpeedLimit;
import engine.map.Block;
import engine.map.Map;
import engine.mobile.Car;
import engine.mobile.Light;
import engine.process.MapBuilder;
import engine.process.MobileElementManager;

/*
 * This class contain the methods for the draw of the map 
 */

public class GameDisplay extends JPanel {
    private static final long serialVersionUID = 2L;

    private Map map;
    private MobileElementManager manager;

    private PaintStrategy paintStrategy;

    private MapBuilder mapBuilder = new MapBuilder(GameConfiguration.LINE_COUNT, GameConfiguration.COLUMN_COUNT);

    public GameDisplay(Map map, MobileElementManager manager, PaintStrategy paintStrategy) {
        this.map = map;
        this.manager = manager;
        this.paintStrategy = paintStrategy;
    }

    public PaintStrategy getPaintStrategy() {
        return paintStrategy;
    }

    public void setPaintStrategy(PaintStrategy paintStrategy) {
        this.paintStrategy = paintStrategy;
    }

    @Override

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        paintStrategy.paint(map, g);

        for (Road road : mapBuilder.getRoads()) {
            paintStrategy.paintRoad(road, g);
        }

        for (Light lightX : mapBuilder.getLights()) {
            paintStrategy.paintRedLight(lightX, g);
            paintStrategy.paintOrangeLight(lightX, g);
            paintStrategy.paintGreenLight(lightX, g);
        }

        for (Block pedestianV : mapBuilder.getpVCrossings()) {
            paintStrategy.paintVerticalPedestrian(pedestianV, g);
        }

        for (Block pedestianH : mapBuilder.getpHCrossings()) {
            paintStrategy.paintHorizontalPedestian(pedestianH, g);
        }

        for (Block stopX : mapBuilder.getStops()) {
            paintStrategy.paintStop(stopX, g);
        }
        
        for (Block waitStopV : mapBuilder.getWaitStopV()) {
			paintStrategy.paintWaitStopV(waitStopV, g);
		}

		for (Block waitStopH : mapBuilder.getWaitStopH()) {
			paintStrategy.paintWaitStopH(waitStopH, g);
		}

        for (Block prohibitedDirX : mapBuilder.getProhibitedDirs()) {
            paintStrategy.paintProhibitedDir(prohibitedDirX, g);
        }
        
        for (SpeedLimit speedLimitX : mapBuilder.getSpeedLimits()) {
			paintStrategy.paintSpeedLimit(speedLimitX, g);
		}
		
		for (Block treeX : mapBuilder.getTrees()) {
			paintStrategy.paintTree(treeX, g);
		}
		
        Car car = manager.getCar();
        paintStrategy.paint(car, g);

    }

    public void checkLight(Car car) {
        Block carPosition = car.getPosition();
        List<Block> allPedetrian = new ArrayList<Block>();
		allPedetrian.addAll(mapBuilder.getpHCrossings());
		allPedetrian.addAll(mapBuilder.getpVCrossings());

		for (Block pedestianX : allPedetrian) {
			if (carPosition.compareBlock(pedestianX)) {
				if (paintStrategy.isLightRed()) {
					GameConfiguration.SCORE = GameConfiguration.SCORE - 2;

					System.out.println("VOUS ETES PASSE ALORS QUE LE FEU ETAIT ROUGE !!!!!!");
					System.out.println("SCORE-2");
				}
				if (paintStrategy.isLightOrange()) {
					GameConfiguration.SCORE = GameConfiguration.SCORE - 1;

					System.out.println("VOUS ETES PASSE ALORS QUE LE FEU ETAIT JAUNE !!!!!!");
					System.out.println("SCORE-1");
				}
				/* We move the car for leave out the condition */
				manager.moveAuto();
			}
		}
    }

}
