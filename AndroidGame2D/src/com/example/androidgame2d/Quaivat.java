package com.example.androidgame2d;

import java.util.ArrayList;
import java.util.Random;

import javax.crypto.Cipher;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

public class Quaivat extends GameObject {
//	private Bitmap image;
//	private int huong = 0;
	private int x;
	private int y;
	private int speedx, speedy;
	private long time, time1;
	private int rowUsing, colUsing;
	private boolean vacham, nghi = false;
	private Bitmap[] LeftoRight;
	private Bitmap[] RighttoLeft;
	private Bitmap[] ToptoBottom;
	private Bitmap[] BottomtoTop;
	private RectF rectTop, rectBot, rectLeft, rectRight, rectBound;
	private Paint blue;
	private int row, col;
	private boolean dead = false;
	private int speed;
	private int height;
	private ChibiCharacter chibi1;
//	private long time;
	private int dai, rong, tam;
	private ArrayList<Title>arrayTitle;
	public Quaivat(ChibiCharacter chibi1,ArrayList<Title>arrayTitle, Bitmap image, int row, int col, int x, int y, int speed, int tam) {
		super(image, 8, 12, x, y);
		this.image = image;
		this.x = x;
		this.y = y;
		this.dai = x;
		this.rong = y;
		this.arrayTitle = arrayTitle;
		this.chibi1 = chibi1;
		this.speed = (int) speed / 8;
		this.height =(int) tam / 4;
		LeftoRight = new Bitmap[3];
		RighttoLeft = new Bitmap[3];
		ToptoBottom = new Bitmap[3];
		BottomtoTop = new Bitmap[3];
		for (int i = 0; i < 3; i++) {
			this.LeftoRight[i] =  createSubImageAT(row + 1, col + i);
			this.RighttoLeft[i] = createSubImageAT(row + 3, col + i);
			this.BottomtoTop[i] = createSubImageAT(row, col + i);
			this.ToptoBottom[i] = createSubImageAT(row + 2, col + i);
		}
		rectTop = new RectF();
		rectBot = new RectF();
		rectRight = new RectF();
		rectLeft = new RectF();
		rectBound = new RectF();
		blue = new Paint();
		blue.setColor(Color.RED);
		blue.setStyle(Paint.Style.FILL);

	}

	public Bitmap getCurrentMoveBitmap() {
		Bitmap[] bitmaps = this.getMoveBitmap();
		return bitmaps[this.colUsing];

	}

	public int random(int min, int max) {
		Random r = new Random();
		int i1 = r.nextInt(max - min + 1) + min;
		return i1;
	}

	public RectF getRectTop() {
		return rectTop;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setSpeedX(int speedX) {
		this.speedx = speedX;
	}

	public void setSpeedY(int speedY) {
		this.speedy = speedY;
	}

	public void moveRandom() {
		if (SystemClock.elapsedRealtime() - time > 3000) {
			rowUsing = random(0, 3);
			time = SystemClock.elapsedRealtime();
		}
	}

	public void Draw(Canvas canvas) {
		if (!dead) {
			Bitmap bitmap = this.getCurrentMoveBitmap();
			canvas.drawBitmap(bitmap, x, y, null);
//			 canvas.drawRect(rectTop, blue);
//			 canvas.drawRect(rectBot, blue);
//			 canvas.drawRect(rectRight, blue);
//			 canvas.drawRect(rectLeft, blue);
//			canvas.drawRect(rectBound, blue);
		}

	}

	public Bitmap[] getMoveBitmap() {
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

	public void update(int dt) {
		if (!dead) {
			colUsing++;
			if (colUsing >= 3) {
				colUsing = 0;
			}
			rectTop.set(x + height, y + (int) (height * 0.7), x + height * 2, y + height);
			rectBot.set(x + height, y + height * 2, x + height * 2, y + (int) (height * 3.5));
			rectRight.set(x + height * 2, y + height * 2, x + (int) (height * 2.6), y + (int) (height* 2.6));
			rectLeft.set(x, y + height * 2, x + height, y + (int) (height * 2.6));
			rectBound.set(x, y + (int) (height * 0.7), x + (int) (height * 2.6), y + (int) (height * 3.5));
			for (int i = 0; i < arrayTitle.size(); i++) {
				Title t = arrayTitle.get(i);
				if ((t.getType() != 2) && t.getRect().intersect(rectBound)) {
					if ((rowUsing == 0) && t.getRect().intersect(rectTop) ) {
					y = y + speed;
					speed = 0;

					}
					else if ( (rowUsing == 2) && t.getRect().intersect(rectBot)) {
						y = y - speed;
						speed = 0;
					}
					else if ((rowUsing == 3) && t.getRect().intersect(rectRight)) {
						x = x - speed;
						speed = 0;

					}
					else if ((rowUsing == 1) && t.getRect().intersect(rectLeft)) {
						x = x + speed;
						speed = 0;

					}
				}
			}
			switch (rowUsing) {
			case 0:
				speedx = 0;
				speedy = -speed;
				break;
			case 1:
				speedx = -speed;
				speedy = 0;
				break;
			case 2:
				speedx = 0;
				speedy = speed;	
				break;
			case 3:
				speedx = speed;
				speedy = 0;
				break;

			default:
				break;
			}

			ArrayList<Boom> array = chibi1.getarrayBoom();
			for (int i = 0; i < array.size(); i++) {
				Boom b = array.get(i);
				if ((b.getR().intersect(rectBound)) || (b.getR1().intersect(rectBound))) {
					dead = true;
					int score = chibi1.getScore();
					score += 10;
					chibi1.setScore(score);
//					if(score == 5){
//						chibi1.gamePanel.gsm.setState(1);
//						chibi1.setX(height*8);
//						chibi1.setY(height*8);
//					}
					Message msg = chibi1.gamePanel.context.handle.obtainMessage();
					msg.what = 1;
					chibi1.gamePanel.context.handle.sendMessage(msg);
					x = -200;
					y = -200;
					rectTop.set(x + height, y + (int) (height * 0.7), x + height * 2, y + height);
					rectBot.set(x + height, y + height * 2, x + height * 2, y + (int) (height * 3.5));
					rectRight.set(x + height * 2, y + height * 2, x + (int) (height * 2.6), y + (int) (height* 2.6));
					rectLeft.set(x, y + height * 2, x + height, y + (int) (height * 2.6));
					rectBound.set(x, y + (int) (height * 0.7), x + (int) (height * 2.6), y + (int) (height * 3.5));
					time1 = SystemClock.elapsedRealtime();
				}
			}
			x += speedx;
			y += speedy;

			if (SystemClock.elapsedRealtime() - time > 3000) {
				rowUsing = random(0, 3);
				speed= (int )(height/2);
				
				time = SystemClock.elapsedRealtime();
			}
		}
		if(dead){
			if((SystemClock.elapsedRealtime() - time1) >= 10000){
				dead = false;
				x = dai;
				y = rong;
			}
		}
		
		
	}

	public RectF getRectBound() {
		return rectBound;
	}
}
