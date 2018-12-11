class Pair{ //taken from keyboard spheres
    public int x; 
    public int y; 
    
    public Pair(int initX, int initY){  
	x = initX;  
	y = initY;  
    }

    public Pair add(Pair toAdd){ 
	return new Pair(x + toAdd.x, y + toAdd.y);
    }

    public Pair divide(int denom){  
	return new Pair((int)(x / denom), (int)(y / denom));
    }

    public Pair times(int val){  
	return new Pair(x * val, y * val);
    }

    public void flipX(){  
	x = -x;
    }
    
    public void flipY(){  
	y = -y;
    }
}