package com.example.seabattleandroid;

import java.util.*;
import android.graphics.Point;

public abstract class Ship {
	private String name;
	private int size;                                  //size of ship
	private int hits = 0;                              //num of hits on this ship
	private boolean isAlive = true;                    //ship dead = false or alive = true
	boolean shipPlaced = false;
	ArrayList<Point> coords = new ArrayList<Point>();  //ArrayList of Point coordinates of the ship 

	public String getName() {
		return this.name;
	}
	
	public void setName(String _name) {
		this.name = _name;
	}
	public void setSize(int _size) {
		this.size = _size;
	}
	public int getSize() {
		return this.size;
	}
	
	public int getHits() {
		return this.hits;
	}
	
	public void setHits(int num) {
		this.hits = num;
	}
	
	public boolean getIsAlive() {
		return this.isAlive;
	}
	
	public void setIsAlive(boolean isAliveOrDead) {
		this.isAlive = isAliveOrDead; //DEAD=FALSE,ALIVE=TRUE
	}
	
	public boolean checkIsAlive() {
		if(this.coords.size()>0) {
			return true;
		}else{
			return false;
		}
	}
	
	public Point getCoords(int indexOfCoords) {
		return this.coords.get(indexOfCoords);
	}
	
	public void setCoords(Point p) {
		this.coords.add(p);
	}
	public void setCoords(int x, int y) {
		Point p =  new Point();
		p.x = x;
		p.y = y;
		this.coords.add(p);
	}
	
	public void checkHits(Point p) {
		for(int indexOfCoords=0;indexOfCoords<this.coords.size();indexOfCoords++) {
			if(this.coords.get(indexOfCoords).equals(p)) {
				this.coords.remove(indexOfCoords);
				if(this.getIsAlive()) {
					System.out.println("You HIT him");
				}else{
					System.out.println("You KILLED "+this.name);
				}
				break;
			}
		}
	}

}


