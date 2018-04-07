package com.example.androidgame2d;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.media.MediaPlayer;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

public class Boom {
	private Bitmap[] image;
	private int x;
	private int y;
	private int colUsing;
	private long time;
	private RectF r,r1;
	private Paint p;
	private MediaPlayer bum;
	private int tam;
	private GamePanel gamePanel;
	public Boom(GamePanel gamePanel,Bitmap[] image, int x, int y, int tam) {
		this.image = image;
		this.x = x;
		this.y = y;
		this.gamePanel = gamePanel;
		time = SystemClock.elapsedRealtime();
//		r = new RectF(x-30, y+10, x+100, y+50);
//		r1 = new RectF(x + 10,y-30,x+60,y+100);
		r = new RectF();
		r1 = new RectF(); 
		p = new Paint();
		p.setColor(Color.BLACK);
		p.setStyle(Style.STROKE);
		this.tam = tam;
	}

	public RectF getR() {
		return r;
	}

	public void setR(RectF r) {
		this.r = r;
	}

	public RectF getR1() {
		return r1;
	}

	public void setR1(RectF r1) {
		this.r1 = r1;
	}

	public void update(int dt) {
		// if (SystemClock.elapsedRealtime() - time >= 500) {
		colUsing++;
		// time = SystemClock.elapsedRealtime();
		Log.v("so colUsing"+colUsing, "so colUsing");
		// }
		if (SystemClock.elapsedRealtime() - time <= 3000) {
			if (colUsing == 4) {
				colUsing = 0;
			}
		}

	}

	public Bitmap getCurrentImage() {
		return image[colUsing];
	}

	public void Draw(Canvas canvas) {
	
		canvas.drawBitmap(getCurrentImage(), x, y, null);
//		canvas.drawRect(r, p);
//		canvas.drawRect(r1, p);
		if (colUsing > 4) {
			r.set(x - (int)(tam*0.65), (int)(y +tam*0.25), x+(int)(tam*1.5), y+(int)(tam*0.8));
			r1.set(x ,(int)(y-tam*0.5),(int)(x+tam*0.75),y+(int)(tam*1.6));
			canvas.drawBitmap(getCurrentImage(), x + (int)(tam*0.75), y, null);
			canvas.drawBitmap(getCurrentImage(), x - (int)(tam*0.75), y, null);
			canvas.drawBitmap(getCurrentImage(), x, y + (int)(tam*0.75), null);
			canvas.drawBitmap(getCurrentImage(), x, y - (int)(tam*0.75), null);
//			canvas.drawRect(r, p);
//			canvas.drawRect(r1, p);
			
			if(colUsing == 5 && gamePanel.context.getMute()== true){
				bum = MediaPlayer.create(gamePanel.context, R.raw.no1);
				bum.setVolume(0.3f, 0.3f);
				bum.start();
			}
			if(colUsing == 8){
				int datboom = gamePanel.getChibi().getQuaBoom();
				datboom ++;
				gamePanel.getChibi().setDatBoom(datboom);
				Message msg = gamePanel.context.handle.obtainMessage();
				msg.what = 2;
				gamePanel.context.handle.sendMessage(msg);
			}
		}
	}

	// public int getX(){
	// return x;
	// }

	public Bitmap[] getImage() {
		return image;
	}

	public int getColUsing() {
		return colUsing;
	}

	public void setImage(Bitmap[] image) {
		this.image = image;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
