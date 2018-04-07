package com.example.androidgame2d;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.ImageButton;

@SuppressLint("NewApi")
public class GameSurface extends GamState {
	private ChibiCharacter chibi1;
	private Quaivat quaivat1, quaivat2, quaivat3, quaivat4, quaivat5;
	private ArrayList<Title> tilearray;
	private Bitmap[] image;
	private int Move;
	private Bitmap  hopqua;
	private LoadMap loadMap;
	private Bitmap[] Imboom;
	private int tam;
	private GameObject ob, ob2, ob3, ob4;
	private GamePanel gamePanel;
	private ArrayList<Boom> arrayBoom;
	private QuaTang quatang;

	public GameSurface(GamePanel gamePanel,ChibiCharacter chibi1, int tam) {
		super(gamePanel);
		this.gamePanel = gamePanel;
		this.tam = tam;
		this.chibi1 = chibi1;
		 arrayBoom = new ArrayList<Boom>();
	}

	public void update() {
		// TODO Auto-generated method stub
		this.chibi1.update();
		this.quaivat5.update(0);
		this.quaivat1.update(0);
		this.quaivat2.update(0);
		this.quaivat3.update(0);
		this.quaivat4.update(0);
		
		
		this.quatang.update();
		RectF r = chibi1.getRect();
		if ((r.intersect(quaivat1.getRectBound())) || (r.intersect(quaivat2.getRectBound()))
				|| (r.intersect(quaivat3.getRectBound())) || (r.intersect(quaivat4.getRectBound()))
				|| r.intersect(quaivat5.getRectBound())) {
			int live = chibi1.getLive();
			live -= 1;
			chibi1.setLive(live);
			Message  msg =  gamePanel.context.handle.obtainMessage();
			msg.what = 0;
			gamePanel.context.handle.sendMessage(msg);
			chibi1.setX(2*tam);
			chibi1.setY(2*tam);
		}
//		if(chibi1.getScore() >= 80){
//			chibi1.gamePanel.gsm.setState(1);
//			chibi1.setX(tam*2);
//			chibi1.setY(tam*2);
//		}
	}

	public void draw(Canvas canvas) {
		// TODO Auto-generated method s

		for (int i = 0; i < tilearray.size(); i++) {
			Title t = tilearray.get(i);
			t.draw(canvas);
		}
		for (int i = 0; i < arrayBoom.size(); i++) {
			Boom b = arrayBoom.get(i);
			b.Draw(canvas);
		}
		canvas.drawBitmap(image[3], (int) (tam * 1.5), tam, null);
		canvas.drawBitmap(image[4], 13 * tam, 4 * tam, null);
		this.quaivat1.Draw(canvas);
		this.quaivat2.Draw(canvas);
		this.quaivat3.Draw(canvas);
		this.quaivat4.Draw(canvas);
		this.quaivat5.Draw(canvas);
		this.chibi1.draw(canvas);
		this.quatang.draw(canvas);
	
	}

	public void init() {
		ob = new GameObject(LoadImage.getBmpFromAsset(gamePanel.getContext(), "map_tiles_2_1.png"), 8, 16, 0, 0);
		ob2 = new GameObject(LoadImage.getBmpFromAsset(gamePanel.getContext(), "map_tiles_2_6.png"), 8, 16, 0, 0);
		ob3 = new GameObject(LoadImage.getBmpFromAsset(gamePanel.getContext(), "map_tiles_1_7.png"), 4, 16, 0, 0);
		ob4 = new GameObject(LoadImage.getBmpFromAsset(gamePanel.getContext(), "map_tiles_1_7.png"), 4, 8, 0, 0);
		
		hopqua = Bitmap.createScaledBitmap(LoadImage.getBmpFromAsset(gamePanel.getContext(), "hopqua.png"), (int)(tam/2), (int)(tam/2), false);
		
		this.image = new Bitmap[5];
		Bitmap quaivat = Bitmap.createScaledBitmap(LoadImage.getBmpFromAsset(gamePanel.getContext(), "quai_vat.png"),
				tam * 9, tam * 7, false);
		this.image[1] = Bitmap.createScaledBitmap(ob.createSubImageAT(5, 0), tam, tam, false);
		this.image[0] = Bitmap.createScaledBitmap(ob.createSubImageAT(3, 4), tam, tam, false);
		this.image[2] = Bitmap.createScaledBitmap(ob2.createSubImageAT(0, 4), tam, tam, false);
		this.image[3] = Bitmap.createScaledBitmap(ob4.createSubImageAT(3, 7), tam * 2, tam * 2, false);
		this.image[4] = Bitmap.createScaledBitmap(ob3.createSubImageAT(2, 15), tam, tam * 2, false);
		
		loadMap = new LoadMap(gamePanel, tam, chibi1);
		tilearray = loadMap.loadMap("map.txt", image);

		this.quaivat1 = new Quaivat(chibi1,tilearray, quaivat, 4, 9, 6 * tam, tam, tam, tam);

		this.quaivat2 = new Quaivat(chibi1, tilearray,quaivat, 0, 9, 8 * tam, tam * 2, tam, tam);
		this.quaivat3 = new Quaivat(chibi1, tilearray,quaivat, 0, 6, 9 * tam, tam * 7, tam, tam);
		this.quaivat4 = new Quaivat(chibi1, tilearray,quaivat, 4, 3, 12 * tam, tam * 6, tam, tam);
		this.quaivat5 = new Quaivat(chibi1, tilearray,quaivat, 4, 0, 10 * tam, tam * 8, tam, tam);
		this.chibi1.setArrayTitle(tilearray);
		this.quatang = new QuaTang(chibi1,hopqua, 6*tam, 3*tam, tam);

	}

	public Bitmap[] getBitmap() {
		return image;
	}

	public ArrayList<Title> getArray() {
		return tilearray;
	}
	public void datBoom() {
		Boom b = new Boom(gamePanel, Imboom, chibi1.getX(), chibi1.getY(), tam);
		arrayBoom.add(b);
	}
	public ChibiCharacter getChibit() {
		return this.chibi1;
	}

	public void setMOVE(int move) {
		this.Move = move;
		chibi1.setMove(Move);
	}

	public int getTam() {
		return tam;
	}

	@Override
	public ChibiCharacter getChibi() {
		// TODO Auto-generated method stub
		return chibi1;
	}

}
