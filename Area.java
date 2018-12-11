
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

public class Area extends JPanel{
	public static final int BOX_WIDTH = 960;
	public static final int BOX_HEIGHT = 640;
    public Image image;
    public Image image2;
    public static Graphics g;
    public int num;
    public Area leftNeighbor;
    public Area rightNeighbor;
    public Area forwardNeighbor;
    public Area backwardNeighbor;
    public Area puzzle;
    public Area myArea;
    public boolean isSolved;
    public boolean hClicked = false;
    public Hint myHint = new Hint(960,640,"no hint");
    public static Inventory myInventory = new Inventory(BOX_WIDTH,BOX_HEIGHT, "pick.png", "lock.png");
    public ArrayList<Rectangle> rect;

    public Area(Graphics g, String filename, int index, String filename2) {
        this(g, filename, index);
        image2 = loadArea(g, filename2);


    }

    public Area(Graphics g, String filename, int index){ //learned about downloading an image and imageIO and image class on oracle and on https://docs.oracle.com/javase/tutorial/2d/images/loadimage.html
    	this.setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));  //and https://stackoverflow.com/questions/13038411/how-to-fit-image-size-to-jframe-size
    	image = loadArea(g, filename);
    	rect = new ArrayList<Rectangle>(2);
    	rect.add(0, new Rectangle(0,0, BOX_WIDTH/9, BOX_HEIGHT));//creates left hitbox
    	rect.add(1, new Rectangle(BOX_WIDTH-BOX_WIDTH/9,0,BOX_WIDTH/9,BOX_HEIGHT));//creates right hitbox
    	num = index;
    }

    public Image loadArea(Graphics g, String filename){
    	Image image;
    	try{
        	image = ImageIO.read(new File(filename));
    	} 
    	catch (IOException e) { 
    		image = createImage(100, 100);
    	}
    	return image;
    }

    public void drawArea(Graphics g){
    	g.drawImage(image,0,0,BOX_WIDTH,BOX_HEIGHT,null);
    	myInventory.drawInventory(g);
        if(hClicked){
            myHint.drawHint(g);
        }
    }

    @Override    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  
        drawArea(g);
    }

    public static void main(String args[]){
    	JFrame frame = new JFrame("DrawToScreen");
       Area area1 = new Area(g,"computer.jpg",0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(area1);
        frame.pack();
        frame.setVisible(true);
    }

    public void setRightNeighbor(Area neighbor){
    	this.rightNeighbor = neighbor;
    }

    public void setLeftNeighbor(Area neighbor){
    	this.leftNeighbor = neighbor;
    }

    public void setForwardNeighbor(Area neighbor){
    	forwardNeighbor = neighbor;
    }

    public void setBackwardNeighbor(Area neighbor){
    	backwardNeighbor = neighbor;
    }

    public void createHitBox(int x, int y, int width, int height){
    	rect.add(new Rectangle(x, y, width, height));
    }

    public void setPuzzle(Area inPuzzle){
    	puzzle = inPuzzle;
    }

    public void puzzleSolved(){
        image = image2;
        isSolved = true;
    }
    public boolean getSolved(){
        return true;
    }

    public void setBottomRect(){
        createHitBox(0,(int)(540*(BOX_HEIGHT/640.0)),BOX_WIDTH, (int)(100* (BOX_HEIGHT/640.0)));
    }



}

