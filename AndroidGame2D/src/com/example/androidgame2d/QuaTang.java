package com.example.androidgame2d;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.media.MediaPlayer;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

public class QuaTang {
	private Bitmap image;
	private int x, y, tam;
	private RectF r;
	private long time, timenhan;
	private int colUsing = 0;
	Paint p;
	MediaPlayer music;
	private boolean xuathien, onetime;
	private ChibiCharacter chibi1;
	public QuaTang(ChibiCharacter chibi1,Bitmap image, int x, int y, int tam) {
		this.image = image;
		this.x = x;
		this.y = y;
		this.tam = tam;
		r = new RectF();
		time = SystemClock.elapsedRealtime();
		p = new Paint();
		p.setColor(Color.RED);
		p.setStyle(Paint.Style.STROKE);
		this.chibi1 = chibi1;
	}

	public void update() {

		if ((SystemClock.elapsedRealtime() - time) >= 10000) {
			xuathien = true;
			// onetime = true;
		}
		if ((SystemClock.elapsedRealtime() - time) >= 15000) {
			xuathien = false;
			colUsing = random(3, 0);
			time = SystemClock.elapsedRealtime();
			switch (colUsing) {
			case 0:
				x = 6 * tam;
				y = 3 * tam;
				break;
			case 1:
				x = 9 * tam;
				y = 3 * tam;
				break;
			case 2:
				x = 6 * tam;
				y = 6 * tam;
				break;
			case 3:
				x = 3 * tam;
				y = 7 * tam;
				break;
			default:
				break;
			}
		}
		if (xuathien) {
			if(chibi1.getRect().intersect(r)){
				xuathien = false;
				time = SystemClock.elapsedRealtime();
				music = MediaPlayer.create(chibi1.gamePanel.context, R.raw.thuong);
				music.setVolume(0.3f, 0.3f);
				music.start();
				int i = random(1, 0);
				if(i == 1){
					int live = chibi1.getLive();
					live += 1;
					chibi1.setLive(live);
					Message  msg =  chibi1.gamePanel.context.handle.obtainMessage();
					msg.what = 0;
					chibi1.gamePanel.context.handle.sendMessage(msg);
				}else {
					int bom = chibi1.getQuaBoom();
					bom+= 1;
					chibi1.setDatBom(bom);
					Message  msg =  chibi1.gamePanel.context.handle.obtainMessage();
					msg.what = 2;
					chibi1.gamePanel.context.handle.sendMessage(msg);
				}
			}
		
		}

	}

	public void draw(Canvas canvas) {
		if (xuathien) {
			r.set(x, y, x + (int) (tam / 2), y + (int) (tam / 2));
			canvas.drawBitmap(image, x, y, null);
		
		}

	}

	public Bitmap getImage() {
		return image;
	}

	public int random(int max, int min) {
		Random r = new Random();
		int i1 = r.nextInt(max - min + 1) + min;
		return i1;
	}

	public void setImage(Bitmap image) {
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

	public int getTam() {
		return tam;
	}

	public void setTam(int tam) {
		this.tam = tam;
	}
}
