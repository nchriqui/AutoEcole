package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import config.GameConfiguration;
import engine.map.Block;
import engine.map.Map;
import engine.mobile.Car;
import engine.process.GameUtility;

public class PaintStrategy extends JPanel {

	private static final long serialVersionUID = 2L;

	private  Image Image;
	private  Image ImageUp;
	private  Image ImageRight;
	private  Image ImageLeft;
	private  Image ImageDown;

	public PaintStrategy() {
		//RECUPERATION DES DIFFERENTES IMAGES DE NOTRE VOITURE ( UNE DIRECTION = UNE IMAGE )
		Image = GameUtility.readImage("src/images/mycarr33.png");
        ImageUp = GameUtility.readImage("src/images/mycarr.png");
        ImageRight = GameUtility.readImage("src/images/mycarr3.png");
        ImageLeft = GameUtility.readImage("src/images/mycarr2.png");
        ImageDown = GameUtility.readImage("src/images/mycarr4.png");
	}
	
	// 4 Méthodes pour recuperer l'image associe au deplacement
	public Image getImageUp() {
		return ImageUp;
	}

	public Image getImageRight() {
		return ImageRight;
	}

	public Image getImageLeft() {
		return ImageLeft;
	}

	public Image getImageDown() {
		return ImageDown;
	}

	public void setImage(Image image) {
		this.Image = image;
	}
	
	//DESSIN DE LA CARTE DE JEU (DASHBOARD)
	public void paint(Map map, Graphics graphics) {
		int blockSize = GameConfiguration.BLOCK_SIZE;
		Block[][] blocks = map.getBlocks();

		//PARCOURS DE LA MAP
		for (int lineIndex =0; lineIndex < map.getLineCount(); lineIndex++) {
			for (int columnIndex = 0; columnIndex < map.getColumnCount(); columnIndex++) {
				Block block = blocks[lineIndex][columnIndex];
				
				//DESSIN DU FOND DE LA CARTE 
				graphics.setColor(Color.decode("#3aaf08"));
				graphics.fillRect(block.getColumn() * blockSize, block.getLine() * blockSize, blockSize, blockSize);
					
				
				//DESSIN DES FEU ROUGE (V1)
				if ((lineIndex==5 && columnIndex==3) || (lineIndex==5 && columnIndex==21)) {
					graphics.setColor(Color.RED);
					graphics.fillRoundRect(block.getColumn() * blockSize, block.getLine() * blockSize, blockSize, blockSize, blockSize, blockSize);
				}
			
			
			}
		}
		
		//DESSIN ROUTE VERTICALE GAUCHE
		for (int lineIndex = 1; lineIndex <= 10; lineIndex++) {
			for (int columnIndex =1 ; columnIndex <=2; columnIndex++) {
				Block block = blocks[lineIndex][columnIndex];

				graphics.setColor(Color.BLACK);
				graphics.fillRect(block.getColumn() * blockSize, block.getLine() * blockSize, blockSize, blockSize);
				
			}
		}
		
		//DESSIN ROUTE VERTICALE DROITE
		for (int lineIndex = 1; lineIndex <= 10; lineIndex++) {
			for (int columnIndex =22 ; columnIndex <=23; columnIndex++) {
				Block block = blocks[lineIndex][columnIndex];

				graphics.setColor(Color.BLACK);
				graphics.fillRect(block.getColumn() * blockSize, block.getLine() * blockSize, blockSize, blockSize);
				
			}
		}
		
		//DESSIN ROUTE HORIZONTALE HAUTE
		for (int lineIndex = 1; lineIndex <= 2; lineIndex++) {
			for (int columnIndex =1 ; columnIndex <= 23; columnIndex++) {
				Block block = blocks[lineIndex][columnIndex];

				graphics.setColor(Color.BLACK);
				graphics.fillRect(block.getColumn() * blockSize, block.getLine() * blockSize, blockSize, blockSize);
				

			}
		}
		
		//DESSIN ROUTE HORIZONTALE BASSE
		for (int lineIndex = 9; lineIndex <= 10; lineIndex++) {
			for (int columnIndex =1 ; columnIndex <= 23; columnIndex++) {
				Block block = blocks[lineIndex][columnIndex];

				graphics.setColor(Color.BLACK);
				graphics.fillRect(block.getColumn() * blockSize, block.getLine() * blockSize, blockSize, blockSize);
			
				
			}
		}
	}
	
	//DESSIN DE LA VOITURE (IMAGE)
	public void paint(Car car, Graphics graphics) {

		Block position = car.getPosition();

		int blockSize = GameConfiguration.BLOCK_SIZE;

		int y = position.getLine();
		int x = position.getColumn();
        
		graphics.drawImage(Image, x * blockSize , y * blockSize, null);
		

	}

}
