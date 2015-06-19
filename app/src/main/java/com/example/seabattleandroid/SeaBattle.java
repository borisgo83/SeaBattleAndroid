package com.example.seabattleandroid;

import java.util.*;

import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class SeaBattle{
	

	static int deskSize = 5;
	public ImageButton[][] table;
	public boolean vertical;
	Random rand = new Random();
    int placeX;
    int placeY;
    public static boolean [][] desk = new boolean [deskSize][deskSize];
    ArrayList<Ship> shipList = new ArrayList<Ship>();
    int[] cells;
    int score = 0;
    TextView displayScore, endText;
    
    
    //CONDTRUCTOR
    public SeaBattle(ImageButton[][] b, TextView result, TextView end, int[] cells) {
    	this.table = b;
    	this.displayScore = result;
    	this.endText = end;
    	this.cells = cells;
    }
    
    public void setUpShipsOnDesk(ArrayList<Ship> list) {
		for(int shipCounter=0;shipCounter<list.size();shipCounter++) {
		  while(list.get(shipCounter).shipPlaced == false) {
			  
		  
			vertical = rand.nextBoolean();
			if(vertical) {
				placeX = rand.nextInt(deskSize);
				placeY = rand.nextInt(deskSize-(list.get(shipCounter).getSize()-1));
				for(int _size=0; _size<list.get(shipCounter).getSize(); _size++) {
					if(checkCell(placeX, placeY+_size)==false) {
						break;
					}else if((checkCell(placeX, placeY+_size)) & (_size==(list.get(shipCounter).getSize()-1))) {
						for(int assignCell=0; assignCell<list.get(shipCounter).getSize(); assignCell++) {
							list.get(shipCounter).setCoords(placeX, (placeY+assignCell));
							useCell(placeX, (placeY+assignCell));
							//new stuff for ANDROID
							//table[placeX][placeY+assignCell].setBackgroundColor(Color.RED);
							//--------------------------
							list.get(shipCounter).shipPlaced = true;
						}
					}
				}
			}else{
				placeX = rand.nextInt(deskSize-(list.get(shipCounter).getSize()-1));
				placeY = rand.nextInt(deskSize);
				for(int _size=0; _size<list.get(shipCounter).getSize(); _size++) {
					if(checkCell(placeX+_size, placeY)==false) {
						break;
					}else if((checkCell(placeX+_size, placeY)) & (_size==(list.get(shipCounter).getSize()-1))) {
						for(int assignCell=0; assignCell<list.get(shipCounter).getSize(); assignCell++) {
							list.get(shipCounter).setCoords((placeX+assignCell), placeY);
							useCell((assignCell+placeX) , placeY);
							//new stuff for ANDROID
							//table[placeX][placeY+assignCell].setBackgroundColor(Color.RED);
							//--------------------------
							list.get(shipCounter).shipPlaced = true;
						}
					}
				}
			}
		  }
		}
	}
	
	public boolean checkCell(int x, int y) {
		if(desk[y][x]) {
			if(y==0 & x==0) {
				if(desk[y+1][x] & desk[y+1][x+1] & desk[y][x+1]) {
					return true;
				}else{
					return false;
				}
			}else if(y==0 & x==(deskSize-1)) {
				if(desk[y][x-1] & desk[y+1][x-1] & desk[y+1][x]) {
					return true;
				}else{
					return false;
				}
			}else if(y==(deskSize-1) & x==0) {
				if(desk[y-1][x] & desk[y-1][x+1] & desk[y][x+1]) {
					return true;
				}else{
					return false;
				}
			}else if(y==(deskSize-1) & x==(deskSize-1)) {
				if(desk[y][x-1] & desk[y-1][x-1] & desk[y-1][x]) {
					return true;
				}else{
					return false;
				}
			}else if(y!=0 & x!=0 & y!=(deskSize-1) & x!=(deskSize-1)) {
				if(desk[y-1][x-1] & desk[y-1][x] & desk[y-1][x+1] & desk[y][x+1] 
						& desk[y+1][x+1] & desk[y+1][x] & desk[y+1][x-1] & desk[y][x-1]) {
					return true;
				}else{
					return false;
				}
			}else if(y==0) {
				if(desk[y][x-1] & desk[y+1][x-1] & desk[y+1][x] & desk[y+1][x+1] & desk[y][x+1]) {
					return true;
				}else{
					return false;
				}
			}else if(x==(deskSize-1)) {
				if(desk[y-1][x] & desk[y-1][x-1] & desk[y][x-1] & desk[y+1][x-1] & desk[y+1][x]) {
				//if(desk[y][x-1] & desk[y-1][x-1] & desk[y-1][x] & desk[y-1][x+1] & desk[y][x+1]) {
					return true;
				}else{
					return false;
				}
			}else if(x==0) {
				if(desk[y-1][x] & desk[y-1][x+1] & desk[y][x+1] & desk[y+1][x+1] & desk[y+1][x]) {
					return true;
				}else{
					return false;
				}
			}else if(y==(deskSize-1)) {
				if(desk[y][x-1] & desk[y-1][x-1] & desk[y-1][x] & desk[y-1][x+1] & desk[y][x+1]) {
					return true;
				}else{
					return false;
				}
			}else{                      /////////////
				return false;            /////////////
			}
		}else{
			return false;
		}
	}
	
	public void useCell(int x, int y) {
		desk[y][x]=false;
		table[y][x].setBackgroundColor(Color.RED);
	}
	
	public void setUpGame() {
		
		
		for(int y=0;y<SeaBattle.deskSize;y++) {
			for(int x=0;x<SeaBattle.deskSize;x++) {
				//SeaBattle.table[y][x].;
			}
		}
		
		for(int y=0;y<SeaBattle.deskSize;y++) {
			for(int x=0;x<SeaBattle.deskSize;x++) {
				SeaBattle.desk[y][x] = true;
				System.out.print(SeaBattle.desk[y][x]+" ");
			}
			System.out.println();
		}
		//Random r = new Random();
		MiddleShip m1 = new MiddleShip();
		MiddleShip m2 = new MiddleShip();
		SmallShip s1 = new SmallShip();
		SmallShip s2 = new SmallShip();
		SmallShip s3 = new SmallShip();
		shipList.add(m1);
		shipList.add(m2);
		shipList.add(s1);
		shipList.add(s2);
		shipList.add(s3);
		//System.out.println(shipList.size());
		this.setUpShipsOnDesk(shipList);
		for(int y=0;y<SeaBattle.deskSize;y++) {
			for(int x=0;x<SeaBattle.deskSize;x++) {
				//desk[y][x] = true;
				System.out.print(SeaBattle.desk[y][x]+" ");
			}
			System.out.println();
		}
		for(int i=0; i<shipList.size(); i++) {
			System.out.println(shipList.get(i).getCoords(0));
		}
	}
	
   /* public void playGame() {
    	for(int i = 0; i < 5; i++) {
    		for(int j = 0; j < 0; j++) {
    			table[i][j].setOnClickListener(this);
    			//System.out.println(this);
    		}
    	}
    	for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				final int x = i;
				final int y = j;
				final ImageButton tempB =  table[i][j];
				tempB.setOnClickListener(new View.OnClickListener() {
	    			
	    			@Override
	    			public void onClick(View v) {
	    				// TODO Auto-generated method stub
	    				
	    				if(desk[x][y] == false) {
	    					tempB.setBackgroundResource(R.drawable.hit);
	    					tempB.setClickable(false);
	    					score++;
	    					displayScore.setText("" + score);
	    					if(shipList.isEmpty() == true) {
	    			    		for (int i = 0; i < 5; i++) {
	    			    			for (int j = 0; j < 5; j++) {
	    			    				final int x = i;
	    			    				final int y = j;
	    			    				final ImageButton tempB =  table[i][j];
	    			    				tempB.setClickable(false);
	    			    			}
	    			    		}
	    			        	endText.setVisibility(View.VISIBLE);
	    			    	}
	    				}else{
	    					tempB.setBackgroundResource(R.drawable.nohit);
	    					tempB.setClickable(false);
	    					score--;
	    					displayScore.setText("" + score);
	    				}
	    			}
	    		});
			}
		}
    	
    	
    	
    		//System.out.print("Make your guess : ");
    	//}
    }*/

	/* public static void main(String [] args) {
		SeaBattle sea = new SeaBattle();
		sea.setUpGame();
		
		

	} */
    /*
    @Override
	public void onClick(View v) {

        switch (v.getId()) {
		case R.id.b1: displayScore.setText("WTF");
		v.setBackgroundResource(R.drawable.hit);
			break;

		default:
			break;
		}


    	if(desk[temp][temp] == false) {
			v.setBackgroundResource(R.drawable.hit);
			v.setClickable(false);
			score++;
			displayScore.setText("" + temp);
			if(shipList.isEmpty() == true) {
	    		for (int i = 0; i < 5; i++) {
	    			for (int j = 0; j < 5; j++) {
	    				final int x = i;
	    				final int y = j;
	    				final ImageButton tempB =  table[i][j];
	    				tempB.setClickable(false);
	    			}
	    		}
	        	endText.setVisibility(View.VISIBLE);
	    	}
		}else{
			v.setBackgroundResource(R.drawable.nohit);
			v.setClickable(false);
			score--;
			displayScore.setText("" + score);
		}
		
	}*/
}
	
	


