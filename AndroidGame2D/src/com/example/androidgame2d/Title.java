package com.example.androidgame2d;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

public class Title {
	private int x;
	private int y;
	private int type;
	private Bitmap imagetuong, imagesan, image;
	private RectF r;
	private Paint blue;
	private int tam;
	private ChibiCharacter chibiCharacter;

	public Title(int x, int y, int type, Bitmap[] bitmap, int tam, ChibiCharacter chibicharacter) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.chibiCharacter = chibicharacter;
		if (type == 1) {
			image = bitmap[0];
		}
		if (type == 2) {
			image = bitmap[1];
		}
		if (type == 3) {
			image = bitmap[2];
		}
		if (type == 4) {
			image = bitmap[1];
		}

		this.tam = tam;
		r = new RectF();
		blue = new Paint();
		blue.setColor(Color.WHITE);
		blue.setStyle(Paint.Style.STROKE);

	}

	public void update() {
	}

	public int getType() {
		return type;
	}

	public RectF getRect() {
		return r;
	}

	public Bitmap getImage() {
		return image;
	}

	public void draw(Canvas canvas) {
		r.set(x, y, x + tam, y + tam);
		blue = new Paint();
		blue.setColor(Color.BLUE);
		blue.setStyle(Paint.Style.STROKE);
		canvas.drawBitmap(image, x, y, null);
		// canvas.drawRoundRect(r, x, y, null);
//		canvas.drawRect(r, blue);
		// canvas.drawRoundRect(r, 100, 100, blue);
		// canvas.drawRect(r, blue);
	}
}
