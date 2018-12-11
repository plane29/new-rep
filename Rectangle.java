import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;


public class Rectangle extends JPanel{
	public int x;
	public int y;
	public int width;
	public int height;
	public int centerX;
	public int centerY;

	public Rectangle(int inX, int inY, int inWidth, int inHeight){
		x=inX;
		y=inY;
		width=inWidth;
		height=inHeight;
	}

	public void rectangleDraw(Graphics g){
		g.fillRect(x,y,width,height);
	}

	public boolean isIn(Pair point){ //main inspiration came from MonteCarlo
		boolean in;
		if((point.x<=x+width && point.x>=x) && (point.y<=y+height && point.y>=y)){
			in = true;
		}
		else{
			in = false;
		}
		return in;
	}
}