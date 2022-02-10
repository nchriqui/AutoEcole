package gui;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import config.GameConfiguration;
import engine.map.Block;
import engine.map.Map;
import engine.mobile.Car;

public class PaintStrategy extends JPanel {

	private static final long serialVersionUID = 2L;

	private BufferedImage Image;
	private BufferedImage ImageUp;
	private BufferedImage ImageRight;
	private BufferedImage ImageLeft;
	private BufferedImage ImageDown;

	public PaintStrategy() {
		super();
		try {
			//RECUPERATION DES DIFFERENTES IMAGES DE NOTRE VOITURE ( UNE DIRECTION = UNE IMAGE )
			Image = ImageIO.read(new File("src/images/mycarr3.png"));
            ImageUp = ImageIO.read(new File("src/images/mycarr.png"));
            ImageRight = ImageIO.read(new File("src/images/mycarr3.png"));
            ImageLeft = ImageIO.read(new File("src/images/mycarr2.png"));
            ImageDown = ImageIO.read(new File("src/images/mycarr4.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	// 4 M�thodes pour recuperer l'image associe au deplacement
	public BufferedImage getImageUp() {
		return ImageUp;
	}

	public BufferedImage getImageRight() {
		return ImageRight;
	}

	public BufferedImage getImageLeft() {
		return ImageLeft;
	}

	public BufferedImage getImageDown() {
		return ImageDown;
	}

	public void setImage(BufferedImage image) {
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
