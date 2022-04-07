package testunit;



import org.junit.jupiter.api.Test;

import engine.map.Block;
import engine.map.Map;
import engine.mobile.Car;
import engine.mobile.Light;
import engine.process.MobileElementManager;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import static org.junit.Assert.*;

import javax.imageio.ImageIO;

import gui.PaintStrategy;

class JUnitTests {
	
	@Test
	void TestLosePointTurnBack() {
		
		Map testMap = new Map(5, 5);
		short scoreTest = 2;
		
		//test of initMobile() about car initialization
		MobileElementManager manager1 = new MobileElementManager(testMap);
		
		
		//test of initializeCar()
		Block blockStart = new Block(3,3);
		Car carTest = new Car(blockStart);
		manager1.setCar(carTest);
		
		Block position = carTest.getPosition();
		Block newPosition = testMap.getBlock(position.getLine(), position.getColumn() - 1);
		
		manager1.setLastMoveRight(true);//On retient que la voiture est vers la droite
		manager1.setLastMoveUp(false);
		
		if (manager1.isLastMoveUp() || manager1.isLastMoveDown() || manager1.isLastMoveLeft()) {
			carTest.setPosition(newPosition);
		}
		else {
			scoreTest --;
		}
		
		assertTrue("Probl me de d cr mentation de score lors des demi-tours",scoreTest == 1 );
	}
	

	@Test
	void TestImageCarUP() {
		
		PaintStrategy testCarPaint = new PaintStrategy(1);
		BufferedImage generatedImg = (BufferedImage) testCarPaint.getImageUp();
		
		try {
			BufferedImage comparedImg = ImageIO.read(new File("src/images/mycarr.png"));
			byte[] byteArrayExpected = ((DataBufferByte) comparedImg.getData().getDataBuffer()).getData();
			
			byte[] InitailizedByteCar = ((DataBufferByte) generatedImg.getData().getDataBuffer()).getData();
			assertArrayEquals("erreur image différente ", byteArrayExpected, InitailizedByteCar);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	void TestLosePointRedLight() {
		Map testMap = new Map(10, 10);
		int scoreTest = 3;
		PaintStrategy pstrategy = new PaintStrategy(1);
		MobileElementManager manager = new MobileElementManager(testMap);
		manager.setLastMoveRight(true);//On retient que la voiture est vers la droite

		Light lightX = new Light(new Block(4, 4), false);
		
		// set the red light
		 Block position = lightX.getPosition();
         int xlight = position.getLine();
         int ylight = position.getColumn();
         
         Block blockPedestianH = new Block(xlight,ylight + 1);
		
		//test of initializeCar()
		Block blockStart = new Block(3,3);
		Car carTest = new Car(blockStart);
		manager.setCar(carTest);
       
	
		Block newPosition = testMap.getBlock(position.getLine(), position.getColumn() + 1);

	            if (newPosition.compareBlock(blockPedestianH)) {
	                if (pstrategy.isLightRed()) {
	                    scoreTest = scoreTest -2;
	                }
	    
	    assertTrue("Pas de perte de points lors du passage du feu rouge",scoreTest == 1 );
}
		
	}
	
	
	
	
}


