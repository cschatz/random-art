package randomart;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;


public class Picture {
	Picture(int width, int height, String title) {
		this.pixels = new int[width+1][height+1]; 
		this.width = width;
		this.height = height;
		this.title = title;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	

	void set(int pixelX, int pixelY, int r, int g, int b) throws Error {
		if (pixelX < 0 || pixelX > width || pixelY < 0 || pixelY > height)
			throw new Error("set() called with (x,y) position out of bounds");
		pixels[pixelX][pixelY] = (new Color(r, g, b).getRGB());
	}
	
	void show() {
		if (frame == null) {
			frame = new JFrame(title);
			display = new PictureDisplay(pixels, width, height);
			frame.add(display);
			frame.pack();
			frame.setVisible(true);
			display.update();
		} else {
			display.update();
		}
	}
	
	void close() {
		if (frame == null)
			return;
		frame.setVisible(false);
		frame.dispose();
		frame = null;
	}
	
	private class PictureDisplay extends Canvas {
		PictureDisplay(int[][] data, int width, int height) {
			this.width = width;
			this.height = height;
			this.data = data;
			this.image = new BufferedImage(width+1, height+1, BufferedImage.TYPE_INT_RGB);
			this.setSize(width, height);
		}

		@Override
	    public void paint(Graphics g) {
			g.drawImage(image, 0, 0, null);
	    }
		
		public void update() {
			for (int x = 0; x <= width; x++) {
				for (int y = 0; y <= height; y++) {
					image.setRGB(x, y, data[x][y]);
				}
			}
			repaint();
		}
	    
		int width;
		int height;
	    private int[][] data;
	    private BufferedImage image;
	}
	
	private int width;
	private int height;
	private String title;
	private int[][] pixels;
	private JFrame frame;
	private PictureDisplay display;
}
