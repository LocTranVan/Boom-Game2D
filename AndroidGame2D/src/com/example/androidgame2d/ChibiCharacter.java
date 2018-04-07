package com.example.androidgame2d;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

public class ChibiCharacter extends GameObject {

	// DOng anh dang duoc su dung.
	private int rowUsing = 2;
	private int colUsing;
	private Bitmap[] LeftoRight;
	private Bitmap[] RighttoLeft;
	private Bitmap[] BottomtoTop;
	private Bitmap[] ToptoBottom;
	// Van toc di chuyen cua nhan van (pixel/milisecon).
	public static final float VELOCITY = 0.1f;
	private RectF r,rbot, rtop, rright, rleft;
	private Paint blue;
	// private int row, col;
	private int x, y;
	private int speedx, speedy;
	// private GameSurface gameSurface;
	private long time;
	private int UP = 1, DOWN = 2, LEFT = 3, RIGHT = 4;
	private int MOVE;
	private Bitmap[] Imboom;
	private Boolean dead = false;
	private ArrayList<Boom> arrayBoom;
	private ArrayList<Title> arrayTitle;
	private int speed;
	public GamePanel gamePanel;
	private int score = 20, live = 3;
	private int quaBom = 1;
	private GameObject ob, ob1;
	private int tam;
	public ChibiCharacter(GamePanel gamePanel, Bitmap image, int x, int y, int speed) {
		super(image, 8, 9, x, y);

		this.ToptoBottom = new Bitmap[3]; // 3
		this.RighttoLeft = new Bitmap[3]; // 3
		this.LeftoRight = new Bitmap[3]; // 3
		this.BottomtoTop = new Bitmap[3]; // 3
		this.gamePanel = gamePanel;
		this.tam = speed;
		this.x = x;
		this.y = y;
		this.speed = speed / 4;
		this.LeftoRight[0] = this.createSubImageAT(0, 4);
		this.LeftoRight[1] = this.createSubImageAT(1, 4);
		this.LeftoRight[2] = this.createSubImageAT(2, 4);
		this.RighttoLeft[0] = this.createSubImageAT(2, 3);
		this.RighttoLeft[2] = this.createSubImageAT(3, 3);
		this.RighttoLeft[1] = this.createSubImageAT(1, 3);
		this.BottomtoTop[0] = this.createSubImageAT(0, 3);
		this.BottomtoTop[1] = this.createSubImageAT(0, 5);
		this.BottomtoTop[2] = this.createSubImageAT(3, 4);
		this.ToptoBottom[0] = this.createSubImageAT(1, 5);
		this.ToptoBottom[1] = this.createSubImageAT(2, 5);
		this.ToptoBottom[2] = this.createSubImageAT(3, 5);
		r = new RectF();
		blue = new Paint();
		blue.setColor(Color.WHITE);
		blue.setStyle(Paint.Style.FILL);
		arrayBoom = new ArrayList<Boom>();
		this.Imboom = new Bitmap[29];
		ob = new GameObject(BitmapFactory.decodeResource(gamePanel.getResources(), R.drawable.bom), 1, 4, 0, 0);
		ob1 = new GameObject(BitmapFactory.decodeResource(gamePanel.getResources(), R.drawable.no), 5, 5, 0, 0);
		Imboom[0] = ob.createSubImageAT(0, 0);
		Imboom[1] = ob.createSubImageAT(0, 1);
		Imboom[2] = ob.createSubImageAT(0, 2);
		Imboom[3] = ob.createSubImageAT(0, 3);
		int k = 4;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {

				Imboom[k] = ob1.createSubImageAT(i, j);
				k++;
			}

		}
	}

	public Bitmap[] getMoveBitmaps() {
		switch (rowUsing) {
		case 0:
			return this.BottomtoTop;
		case 3:
			return this.LeftoRight;
		case 2:
			return this.ToptoBottom;
		case 1:
			return this.RighttoLeft;
		default:
			return null;
		}
	}

	public Bitmap getCurrentMoveBitmap() {
		Bitmap[] bitmaps = this.getMoveBitmaps();
		return bitmaps[this.colUsing];
	}

	public void update() {
		if (live != 0) {
			if (!(MOVE == 0)) {
				this.colUsing++;
				if (colUsing >= 3) {
					this.colUsing = 0;
				}
			}

			x += speedx;
			y += speedy;
			r.set(x + speed, y , x + 3* speed, y + 4 * speed);
			for (int i = 0; i < arrayTitle.size(); i++) {
				Title t = arrayTitle.get(i);
				if ((t.getType() != 2) && r.intersect(t.getRect())) {
					if (rowUsing == 1) {
						 x = x + speed;
						 

					} else if (rowUsing == 2) {
						 y = y - speed;

					} else  if (rowUsing == 0 ) {
						 y = y + speed ;

					}  else  if (rowUsing == 3 ) {
						 x = x - speed;
					}
				}
			}
			
		}
		
		for (int i = 0; i < arrayBoom.size(); i++) {
			Boom b = arrayBoom.get(i);
			b.update(0);
			if (b.getR().intersect(r) || b.getR1().intersect(r)) {
				if (b.getColUsing() == 27) {
					live--;
					x = 8 * speed;
					y = 8 * speed;
					Message msg = gamePanel.context.handle.obtainMessage();
					msg.what = 0;
					gamePanel.context.handle.sendMessage(msg);
					if (live == 0) {
						Message msg1 = gamePanel.context.handle.obtainMessage();
						msg.what = 3;
						gamePanel.context.handle.sendMessage(msg);
					}
				}

			}
			if (b.getColUsing() == 28) {
				arrayBoom.remove(i);
			}
		}
	}

	public void datBoom() {
		Boom b = new Boom(gamePanel, Imboom, x, y, speed * 4);
		arrayBoom.add(b);
	}

	public void setImageBoom(Bitmap[] Imboom) {
		this.Imboom = Imboom;
	}

	public int getRowUsing() {
		return rowUsing;
	}

	public int random(int min, int max) {
		Random r = new Random();
		int i1 = r.nextInt(max - min + 1) + min;
		return i1;
	}

	public void setLive(int live) {
		this.live = live;
	}

	public void setDatBom(int bom) {
		this.quaBom = bom;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLive() {
		return live;
	}

	public int getScore() {
		return score;
	}

	public ArrayList<Boom> getarrayBoom() {
		return arrayBoom;
	}

	public void setArrayTitle(ArrayList<Title> arrayTitle) {
		this.arrayTitle = arrayTitle;
	}

	public RectF getRect() {
		return r;
	}

	public void setDead(boolean Dead) {
		this.dead = Dead;
	}

	public boolean getDead() {
		return dead;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public  void setMove(int x) {
		this.MOVE = x;
		switch (MOVE) {
		case 1:
			speedx = 0;
			speedy = -speed;
			rowUsing = 0;
			break;
		case 2:
			speedx = 0;
			speedy = speed;
			rowUsing = 2;
			break;
		case 3:
			speedx = -speed;
			speedy = 0;
			rowUsing = 1;
			break;
		case 4:
			speedx = speed;
			speedy = 0;
			rowUsing = 3;
			break;
		case 0:
			speedx = 0;
			speedy = 0;
			colUsing = 0;
			break;
		default:

			break;
		}
		
		
	
				
	}

	public int getMove() {
		return MOVE;
	}

	public void draw(Canvas canvas) {
		if (gamePanel.context.dlive != 0) {
			canvas.drawBitmap(this.getCurrentMoveBitmap(), x, y, null);
//			 canvas.drawRect(r, blue);
//			 canvas.drawRect(rbot, blue);
//			 canvas.drawRect(rleft, blue);
//			 canvas.drawRect(rright, blue);
//			 canvas.drawRect(rtop, blue);

		}
		for (int i = 0; i < arrayBoom.size(); i++) {
			Boom b = arrayBoom.get(i);
			b.Draw(canvas);
		}
	}

	public int getQuaBoom() {
		return quaBom;
	}
	public int getTam(){
		return speed*4;
	}
	public void setDatBoom(int datboom) {
		this.quaBom = datboom;
	}
}
