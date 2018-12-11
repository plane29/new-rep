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

public class Puzzle extends Area{
	public boolean isSolved;
	public Image image2;
	public Puzzle(Graphics g, String filename, int index, String solvedFilename){
		super(g,filename, index);
    	isSolved = false;
    	image2 = loadArea(g, solvedFilename);

	}

	@Override
	public void puzzleSolved(){
		image = image2;
		isSolved = true;
		for(int i = 0; i<rect.size()-1; i++){
			rect.remove(i);
		}
	}

	public boolean getSolved(){
		return isSolved;
	}
}