package gui;

import java.awt.Graphics;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Config.GameConfiguration;
import engine.Map.Block;
import engine.mobile.Car;

public class PaintStrategy extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;

	private BufferedImage Image;
	private BufferedImage ImageUp;
	private BufferedImage ImageRight;
	private BufferedImage ImageLeft;
	private BufferedImage ImageDown;

	public PaintStrategy() {
		super();
		try {
			Image = ImageIO.read(new File("src/images/mycarr.png"));
            ImageUp = ImageIO.read(new File("src/images/mycarr.png"));
            ImageRight = ImageIO.read(new File("src/images/mycarr3.png"));
            ImageLeft = ImageIO.read(new File("src/images/mycarr2.png"));
            ImageDown = ImageIO.read(new File("src/images/mycarr4.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

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

	public void paint(Car car, Graphics graphics) {

		Block position = car.getPosition();

		int blockSize = GameConfiguration.BLOCK_SIZE;

		int y = position.getLine();
		int x = position.getColumn();
        
		graphics.drawImage(Image, x * blockSize , y * blockSize, null);
		

	}

}
