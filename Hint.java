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


public class Hint extends JPanel{
	Font arial = new Font("Arial", Font.BOLD, 30);
	String toPrint;
	int width;
	int height;
	Graphics g;

	public Hint(int initWidth, int initHeight, String input){
	toPrint = input;
	width = initWidth;
	height = initHeight;
	}

public void drawHint(Graphics g){
		g.setFont(arial);
		FontMetrics metrics = g.getFontMetrics(arial);
		g.setColor(Color.BLACK);
		g.fillRect(width/2 -metrics.stringWidth(toPrint)/2,height/9-height/23,metrics.stringWidth(toPrint),height/19);
		g.setColor(Color.WHITE);
		g.drawString(toPrint, width/2-metrics.stringWidth(toPrint)/2, height/9);
	}



}
