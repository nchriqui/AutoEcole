package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JPanel;

import Config.GameConfiguration;
import engine.Map.Block;
import engine.mobile.Car;
import engine.mobile.Light;
import engine.mobile.Road;
import engine.process.GameUtility;

public class PaintStrategy extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;

	private Image Image;
	private Image ImageUp;
	private Image ImageRight;
	private Image ImageLeft;
	private Image ImageDown;

	public PaintStrategy(){
		super();
		Image = GameUtility.readImage("src/Images/mycarr.png");
		ImageUp = GameUtility.readImage("src/Images/mycarr.png");
		ImageRight = GameUtility.readImage("src/Images/mycarr3.png");
		ImageLeft = GameUtility.readImage("src/Images/mycarr2.png");
		ImageDown = GameUtility.readImage("src/Images/mycarr4.png");

	}

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

	public void paint(Car car, Graphics2D graphics) {

		Block position = car.getPosition();

		int blockSize = GameConfiguration.BLOCK_SIZE;

		int y = position.getLine();
		int x = position.getColumn();
        
		graphics.drawImage(Image, x * blockSize , y * blockSize,blockSize,blockSize,null);
		
	}
	
	public void paintHorizontalRoad(Graphics2D g , Road road) {
		Block position = road.getPosition();
		g.drawImage(GameUtility.readImage("src/Images/routeHorizontal.png"),position.getLine()*GameConfiguration.BLOCK_SIZE,position.getColumn(),GameConfiguration.BLOCK_SIZE,GameConfiguration.BLOCK_SIZE,null);
	}
	
	public void paintRoad(Graphics2D g , Road road) {
		Block position = road.getPosition();	
		g.drawImage(GameUtility.readImage("src/Images/routeHorizontal.png"),position.getLine()*GameConfiguration.BLOCK_SIZE,GameConfiguration.BLOCK_SIZE*GameConfiguration.COLUMN_COUNT,GameConfiguration.BLOCK_SIZE,GameConfiguration.BLOCK_SIZE,null);
	}
	
	public void paintVerticalRoad(Graphics2D g , Road road) {
		Block position = road.getPosition();	
		g.drawImage(GameUtility.readImage("src/Images/routeVertical.png"),position.getLine(),position.getColumn()*GameConfiguration.BLOCK_SIZE,GameConfiguration.BLOCK_SIZE,GameConfiguration.BLOCK_SIZE,null);
	}
	public void paintRoad2(Graphics2D g, Road road) {
		Block position = road.getPosition();
		g.drawImage(GameUtility.readImage("src/Images/routeVertical.png"),GameConfiguration.BLOCK_SIZE*(GameConfiguration.LINE_COUNT-1),position.getColumn()*GameConfiguration.BLOCK_SIZE,GameConfiguration.BLOCK_SIZE,GameConfiguration.BLOCK_SIZE,null);
	}
	
	public void paintLights(Graphics2D g2) {
		//Block position = light.getPosition();
		g2.drawImage(GameUtility.readImage("src/Images/redLightUp.png"),30+5,30+5,GameConfiguration.BLOCK_SIZE-15,GameConfiguration.BLOCK_SIZE-15,null);
		g2.drawImage(GameUtility.readImage("src/Images/redLightLeft.png"),30+5,570+5,GameConfiguration.BLOCK_SIZE-15,GameConfiguration.BLOCK_SIZE-15,null);
	}

}
