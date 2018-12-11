import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;


public class Inventory extends JPanel{
	Font arial = new Font("Arial", Font.BOLD, 30);
	int x;
	int y;
	int width;
	int height;
	Image pick;
	Image lock;
	Graphics g;
	boolean pickSolved = false;
	boolean lockSolved = false;

	public Inventory(int initWidth, int initHeight, String filename, String filename2){
		pick = loadInventory(g, filename);
		lock = loadInventory(g, filename2);
		width = initWidth;
		height = initHeight;
	}

    public Image loadInventory(Graphics g, String filename){
    	Image image; //see Area code, but learned about how to import an image and image class from https://docs.oracle.com/javase/tutorial/2d/images/loadimage.html
    	try{
        	image = ImageIO.read(new File(filename));
    	} 
    	catch (IOException e) { 
    		image = createImage(100, 100);
    	}
    	return image;
    }



	public void drawInventory(Graphics g){
		g.setFont(arial);
		FontMetrics metrics = g.getFontMetrics(arial);
		g.setColor(Color.WHITE);
		g.fillRect(width/9,height-height/6,metrics.stringWidth("Inventory"),height/20);
		g.setColor(Color.BLACK);
		g.drawString("Inventory", width/9, height-height/8);
		if(pickSolved){
			g.drawImage(pick, 100, height-62, 112, 57, null);
		}
		if(lockSolved){
			g.drawImage(lock, 200, height - 65, 90, 48, null);
		}
	}



}
