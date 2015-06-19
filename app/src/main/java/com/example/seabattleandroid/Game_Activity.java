package com.example.seabattleandroid;

import java.util.ArrayList;
import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Game_Activity extends Activity implements OnClickListener{
	final ImageButton[][] buts = new ImageButton[5][5];
    int score;
	TextView displayScore,endText;
	int[] cells;
	ArrayList<Ship> shipList = new ArrayList<Ship>();
	public boolean vertical;
	Random rand = new Random();
	static int deskSize = 5;
	int placeX;
    int placeY;
    //ImageButton tempBu;
    public static boolean [][] desk = new boolean [deskSize][deskSize];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		int[] cells2 = {R.id.b1,R.id.b2,R.id.b3,R.id.b4,R.id.b5,R.id.b6,R.id.b7,
				R.id.b8,R.id.b9,R.id.b10,R.id.b11,R.id.b12,R.id.b13,R.id.b14,R.id.b15,
				R.id.b16,R.id.b17,R.id.b18,R.id.b19,R.id.b20,R.id.b21,R.id.b22,R.id.b23,
				R.id.b24,R.id.b25};
		cells = cells2;
        displayScore = (TextView)findViewById(R.id.score);
        endText = (TextView)findViewById(R.id.endGameText);





		buts[0][0] = (ImageButton)findViewById(R.id.b1);
		buts[0][1] = (ImageButton)findViewById(R.id.b2);
		buts[0][2] = (ImageButton)findViewById(R.id.b3);
		buts[0][3] = (ImageButton)findViewById(R.id.b4);
		buts[0][4] = (ImageButton)findViewById(R.id.b5);
		buts[1][0] = (ImageButton)findViewById(R.id.b6);
		buts[1][1] = (ImageButton)findViewById(R.id.b7);
		buts[1][2] = (ImageButton)findViewById(R.id.b8);
		buts[1][3] = (ImageButton)findViewById(R.id.b9);
		buts[1][4] = (ImageButton)findViewById(R.id.b10);
		buts[2][0] = (ImageButton)findViewById(R.id.b11);
		buts[2][1] = (ImageButton)findViewById(R.id.b12);
		buts[2][2] = (ImageButton)findViewById(R.id.b13);
		buts[2][3] = (ImageButton)findViewById(R.id.b14);
		buts[2][4] = (ImageButton)findViewById(R.id.b15);
		buts[3][0] = (ImageButton)findViewById(R.id.b16);
		buts[3][1] = (ImageButton)findViewById(R.id.b17);
		buts[3][2] = (ImageButton)findViewById(R.id.b18);
		buts[3][3] = (ImageButton)findViewById(R.id.b19);
		buts[3][4] = (ImageButton)findViewById(R.id.b20);
		buts[4][0] = (ImageButton)findViewById(R.id.b21);
		buts[4][1] = (ImageButton)findViewById(R.id.b22);
		buts[4][2] = (ImageButton)findViewById(R.id.b23);
		buts[4][3] = (ImageButton)findViewById(R.id.b24);
		buts[4][4] = (ImageButton)findViewById(R.id.b25);

		
		SeaBattle sea = new SeaBattle(buts, displayScore, endText, cells);
		setUpGame();
		playGame();

		
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
							//list.get(shipCounter).shipPlaced = true;
						}

					}
                    list.get(shipCounter).shipPlaced = true; // ???????????????? sign ship as "placed"
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
							//list.get(shipCounter).shipPlaced = true;
						}
					}
                    list.get(shipCounter).shipPlaced = true; // ???????????????? sign ship as "placed"
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
		buts[y][x].setBackgroundColor(Color.RED);
	}
	
	public void setUpGame() {
		
		
		
		
		for(int y=0;y<deskSize;y++) {
			for(int x=0;x<deskSize;x++) {
				desk[y][x] = true;

			}

		}

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

		this.setUpShipsOnDesk(shipList);

		
	}
	
    public void playGame() {
    	for(int i = 0; i < 5; i++) {
			//int count = 0;
			for(int j = 0; j < 5; j++) {
				//buts[i][j] = (ImageButton)findViewById(cells[(i*5)+j]);
				buts[i][j].setOnClickListener(this);

			}
			
		}
		


    }

    @Override
    public void onClick(View v) {
        int clickedButton = v.getId();
       // Toast.makeText(Game_Activity.this,String.valueOf(v.getId()),Toast.LENGTH_SHORT).show();

        for(int i = 0; i < deskSize; i++) {
            for(int j = 0; j < deskSize; j++) {
               // Toast.makeText(Game_Activity.this, String.valueOf(cells[i*5+j]),Toast.LENGTH_SHORT).show();
                if(clickedButton == cells[i*5+j]) {
                    //----------------------------------------------

                    if (desk[i][j] == false) {
                        v.setBackgroundResource(R.drawable.hit);
                        v.setClickable(false);
                        score++;
                        displayScore.setText("" + score);


                    } else {
                        v.setBackgroundResource(R.drawable.nohit);
                        v.setClickable(false);
                        score--;
                        displayScore.setText("" + score);
                    }

                    if (shipList.isEmpty()) {
                        for (int x = 0; x < 5; x++) {
                            for (int y = 0; y < 5; y++) {
                                //final int x = i;
                                //final int y = j;
                                //final ImageButton tempB =  buts[i][j];;
                                //tempB.setClickable(false);
                                buts[x][y].setClickable(false);
                            }
                        }
                        endText.setVisibility(View.VISIBLE);

                        //-------------------------------------------------------
                    }
                }
            }
        }
    }



	

}
