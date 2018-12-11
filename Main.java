import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.JFrame;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.io.IOException;

public class Main extends JPanel implements KeyListener, MouseListener{
    public static int WIDTH = 960;
    public static int HEIGHT = 640;
    public Graphics g;
    public int FPS = 60;
    public static Room r;
    public boolean rightClicked;
    public boolean leftClicked;
    public boolean hitBoxClicked;
    public boolean inPuzzle;
    public boolean inMyArea;
    public boolean mouseInLeft;
    public boolean mouseInRight;
    public boolean mouseInBottom;
    public static Main hello;
    public Point p;
    public Pair mouseLocation = new Pair(0,0);
    public boolean correctPawn = false;

    public MyDS ds = new MyDS();
    public Main(){
    addMouseListener(this); //We got information and code about mouselistener from https://www.javatpoint.com/java-mouselistener.  This helped us know the methods and see an example.
    addKeyListener(this);
    setFocusable(true); //https://stackoverflow.com/questions/286727/unresponsive-keylistener-for-jframe
    r = new Room(WIDTH, HEIGHT);
    this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    Thread mainThread = new Thread(new Runner());
    mainThread.start();
    }

    public void keyPressed(KeyEvent e) { //we copied this code from keyboard spheres
        char c=e.getKeyChar();
            if(c == 'h'){
                if(r.currentArea.hClicked){
                    r.currentArea.hClicked = false;
                    if (r.currentArea.num == 12){
                        inPuzzle =false;
                    }
                    repaint();
                    if (r.currentArea.num == 12){
                        inPuzzle =true;
                    }
                }
                else{
                    if (r.currentArea.num == 12){
                        inPuzzle =false;
                    }
                    r.currentArea.hClicked = true;
                    repaint();
                    if (r.currentArea.num == 12){
                        inPuzzle =true;
                    }
                }
            }
                    if(r.currentArea.num == 13 && !r.currentArea.isSolved){
                        if(c == 'c'){
                            ds.append(1);
                        }
                        else if(c == 'e'){
                            ds.append(2);
                        }
                        else if(c == 'g'){
                            ds.append(3);
                        }
                        else if(c == 'b'){
                            ds.append(4);
                        }
                        else if(c == 'd'){
                            ds.append(5);
                        }
                        else if(c == 'f'){
                            ds.append(6);
                        }
                        else if(c == 'a'){
                            ds.append(7);
                        }
                        else{
                            ds.append(8);
                        }
                        if(ds.checkTrojan()){
                                r.currentArea.puzzleSolved();
                                r.supArea[2].puzzleSolved();
                                r.areas[9].puzzleSolved();
                                repaint();
                        }
                    }
                    else if(r.currentArea.num == 15 && !r.currentArea.isSolved){
                        if(c == 'c'){
                            ds.append(1);
                        }
                        else if(c == 'a'){
                            ds.append(2);
                        }
                        else if(c == 'n'){
                            ds.append(3);
                        }
                        else if(c == 'd'){
                            ds.append(4);
                        }
                        else if(c == 'l'){
                            ds.append(5);
                        }
                        else if(c == 'e'){
                            ds.append(6);
                        }
                        else{
                            ds.append(8);
                        }
                        if(ds.checkRiddle()){
                            r.currentArea.puzzleSolved();
                            repaint();
                        }
                    }

        

    }

    
    public void keyReleased(KeyEvent e) {
        char c=e.getKeyChar();
        System.out.println("\tYou let go of: " + c);
    
    }


    public void keyTyped(KeyEvent e) {

    }

    public void mouseClicked(MouseEvent e) { //learned about mouse events on the website mentioned above. 
        int x = e.getX();
        int y = e.getY();
        Pair toCheck = new Pair(x,y);
        if(checkPair(toCheck,r)){
            if(inPuzzle){
                System.out.println("im in the puzzle");
                if(checkBottom(toCheck,r)){
                    inMyArea=true;
                    inPuzzle=false;
                    repaint();
                }
                else if(r.currentArea.num == 13 && !r.currentArea.isSolved){
                        if(checkKeyboard(toCheck,r,2)){
                            ds.append(1);
                        }
                        else if(checkKeyboard(toCheck,r,4)){
                            ds.append(2);
                        }
                        else if(checkKeyboard(toCheck,r,6)){
                            ds.append(3);
                        }
                        else if(checkKeyboard(toCheck,r,8)){
                            ds.append(4);
                        }
                        else if(checkKeyboard(toCheck,r,3)){
                            ds.append(5);
                        }
                        else if(checkKeyboard(toCheck,r,5)){
                            ds.append(6);
                        }
                        else if(checkKeyboard(toCheck,r,7)){
                            ds.append(7);
                        }
                        else{
                            ds.append(8);
                        }
                        if(ds.checkTrojan()){
                                r.currentArea.puzzleSolved();
                                r.supArea[2].puzzleSolved();
                                r.areas[9].puzzleSolved();
                                repaint();
                        }


                }
                else if(checkHitBox(toCheck,r)){
                hitBoxClicked = true;
                inPuzzle = true;
                //r.currentArea = r.currentArea.puzzle;
                repaint();
                hitBoxClicked = false;
                }
            }
            else if(checkLeft(toCheck,r)){
                leftClicked = true;
                repaint();

            }
            else if(checkRight(toCheck,r)){
                rightClicked = true;
                repaint();
            }
            else if(checkHitBox(toCheck,r)){
                System.out.println("its in the hitbox");
                hitBoxClicked = true;
                inPuzzle = true;
                r.previousArea = r.currentArea;
                repaint();
                hitBoxClicked = false;
            }

        }

    }  
    public static void listenToMouse(){
        try{
            hello.p = MouseInfo.getPointerInfo().getLocation();
        }
        catch(NullPointerException e){
            hello.p = new Point(WIDTH/2,HEIGHT/2);
        }

        hello.mouseLocation =  new Pair(hello.p.x,hello.p.y -50);  //for some reason the initial mouse y location is 50 so we have to adjust for that
        if(!hello.inPuzzle && hello.r.currentArea.num!=16){
            if(hello.checkRight(hello.mouseLocation,r)){
                hello.mouseInRight = true;
                hello.repaint();
            }
            else if(hello.checkLeft(hello.mouseLocation,r)){
                hello.mouseInLeft = true;
                hello.repaint();
            }
            else{
                hello.repaint();
            }
        }
        /*else if(hello.inPuzzle){
            if(hello.checkBottom(hello.mouseLocation,r)){
                hello.mouseInBottom = true;
                hello.repaint();
            }
            else{
                hello.repaint();
            }
        }*/

    }
    public void mouseEntered(MouseEvent e) {  //taken from website above necessary to implement mouselistener

    }  
    public void mouseExited(MouseEvent e) {  

    }  
    public void mousePressed(MouseEvent e) { 
        int x = e.getX();
        int y = e.getY();
        Pair toCheck = new Pair(x,y); 
        if(r.currentArea.num == 16){
            if(!r.currentArea.getSolved() && checkChess(toCheck,r)){
                correctPawn = true;
            }
        }
    }  
    public void mouseReleased(MouseEvent e) {  
        int x = e.getX();
        int y = e.getY();
        Pair toCheck = new Pair(x,y);
        if(r.currentArea.num == 16){
            if(!r.currentArea.getSolved() && checkChess2(toCheck,r) && correctPawn){
                r.currentArea.puzzleSolved();
                r.areas[7].puzzleSolved();
                r.areas[8].puzzleSolved();
                Area.myInventory.pickSolved = true;
                repaint();
            }

        } 
       
    }  

    @Override    
    public void paintComponent(Graphics g) { //main idea of paint component taken from keyboard spheres
        super.paintComponent(g);
        if(rightClicked){
            r.currentArea = r.currentArea.rightNeighbor;
            rightClicked = false;
        }
        if(leftClicked){
            r.currentArea = r.currentArea.leftNeighbor;
            leftClicked = false;
        }
        if(inPuzzle && r.currentArea.num !=16 && r.currentArea.num !=15 && r.currentArea.num != 13 && r.currentArea.num!=14){
            r.currentArea =r.currentArea.puzzle;
            if(r.currentArea.num == 14 && r.currentArea.getSolved()){
                Area.myInventory.lockSolved = true;
            }
        }
        if(inMyArea){
            r.currentArea = r.previousArea;
            inMyArea=false;
        }
        if(Area.myInventory.lockSolved && Area.myInventory.pickSolved && r.currentArea.num == 0){
            r.currentArea.puzzleSolved();
        }
        r.drawRoom(g);
        if(mouseInLeft){
            r.drawLeftArrow(g);
            hello.mouseInLeft = false;
        }
        if(mouseInRight){
            r.drawRightArrow(g);
            hello.mouseInRight = false;
        }
        if(mouseInBottom){
            r.drawBottomArrow(g);
            hello.mouseInBottom = false;
        }

    }

    public static void main(String[] args) {  //main idea taken from keyboard spheres/draw to screen
        hello = new Main();  
        JFrame frame = new JFrame("Escape Room");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(hello);
        frame.pack();
        frame.setVisible(true);
    }  

    public boolean checkPair(Pair p, Room r){
        return r.isIn(p);
    }

    public boolean checkLeft(Pair p, Room r){
        if(!inPuzzle){
          return r.currentArea.rect.get(0).isIn(p);
        }
        return false;
    }

    public boolean checkRight(Pair p, Room r){
        if(!inPuzzle){
          return r.currentArea.rect.get(1).isIn(p);
        }
        return false;
    }

    public boolean checkHitBox(Pair p, Room r){
        if(r.currentArea.rect.size()>2){    
            return r.currentArea.rect.get(2).isIn(p);
        }
        return false;
    }

    public boolean checkBottom(Pair p, Room r){
        return r.currentArea.rect.get(r.currentArea.rect.size()-1).isIn(p);
    }

    public boolean checkChess(Pair p, Room r){
        return r.currentArea.rect.get(2).isIn(p);
    }

    public boolean checkChess2(Pair p, Room r){
        return r.currentArea.rect.get(3).isIn(p);
    }

    public boolean checkKeyboard(Pair p, Room r, int i){
        if(r.currentArea.rect.size()>i){    
            return r.currentArea.rect.get(i).isIn(p);
        }
        return false;
    }




}
