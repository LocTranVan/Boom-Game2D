package com.example.androidgame2d;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.media.Image;
import android.os.Message;
import android.util.Log;

public class GameSurface2 extends GamState {
	private ChibiCharacter chibi1;
	private Quaivat quaivat1, quaivat2, quaivat3, quaivat4, quaivat5;
	private ArrayList<Title> tilearray;
	private Bitmap[] image, image2;
	private int Move;
	private Bitmap tuong;
	private Bitmap  hopqua;
	private LoadMap loadMap;
	private Boom boom;
	private Bitmap[] Imboom;
	private int tam;
	private GameObject ob, ob2, ob3, ob4, ob5, ob6;
	private GamePanel gamePanel;
	private ArrayList<Boom> arrayBoom;
	private QuaTang quatang;
	private LuuDiem luudiem;
	public GameSurface2(GamePanel gamePanel,ChibiCharacter chibi1, int tam) {
		super(gamePanel);
		this.gamePanel = gamePanel;
		this.tam = tam;
		this.chibi1 = chibi1;
		 arrayBoom = new ArrayList<Boom>();
		 luudiem = new LuuDiem();
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
		if(chibi1.getScore() >= 80){
		
			chibi1.gamePanel.gsm.setState(1);
			String level = Integer.toString(gamePanel.gsm.getLevel());
			luudiem.setDiem(level);
			chibi1.setX(tam*2);
			chibi1.setY(tam*2);
		}
	}

	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		for (int i = 0; i < tilearray.size(); i++) {
			Title t = tilearray.get(i);
			t.draw(canvas);
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
		for (int i = 0; i < arrayBoom.size(); i++) {
			Boom b = arrayBoom.get(i);
			b.Draw(canvas);
		}
	}

	public void init() {
		ob = new GameObject(LoadImage.getBmpFromAsset(gamePanel.getContext(), "map_tiles_2_5.png"), 8, 16, 0, 0);
		ob2 = new GameObject(LoadImage.getBmpFromAsset(gamePanel.getContext(), "map_tiles_2_6.png"), 8, 16, 0, 0);
		ob3 = new GameObject(LoadImage.getBmpFromAsset(gamePanel.getContext(), "map_tiles_1_7.png"), 4, 16, 0, 0);
		ob4 = new GameObject(LoadImage.getBmpFromAsset(gamePanel.getContext(), "map_tiles_1_7.png"), 4, 8, 0, 0);
//		ob5 = new GameObject(BitmapFactory.decodeResource(gamePanel.getResources(), R.drawable.bom), 1, 4, 0, 0);
//		ob6 = new GameObject(BitmapFactory.decodeResource(gamePanel.getResources(), R.drawable.no), 5, 5, 0, 0);
		this.image = new Bitmap[5];
		Bitmap quaivat = Bitmap.createScaledBitmap(LoadImage.getBmpFromAsset(gamePanel.getContext(), "quai_vat.png"),
				tam * 9, tam * 7, false);
		hopqua = Bitmap.createScaledBitmap(LoadImage.getBmpFromAsset(gamePanel.getContext(), "hopqua.png"), (int)(tam/2), (int)(tam/2), false);
//		this.Imboom = new Bitmap[29];
		Bitmap imagetam = image[1];
	
		this.image[1] = Bitmap.createScaledBitmap(ob.createSubImageAT(0, 5), tam, tam, false);
		this.image[0] = Bitmap.createScaledBitmap(ob.createSubImageAT(0, 7), tam, tam, false);
		this.image[2] = Bitmap.createScaledBitmap(ob2.createSubImageAT(0, 5), tam, tam, false);
		this.image[3] = Bitmap.createScaledBitmap(ob4.createSubImageAT(3, 7), tam * 2, tam * 2, false);
		this.image[4] = Bitmap.createScaledBitmap(ob3.createSubImageAT(2, 15), tam, tam * 2, false);
//		Imboom[0] = ob5.createSubImageAT(0, 0);
//		Imboom[1] = ob5.createSubImageAT(0, 1);
//		Imboom[2] = ob5.createSubImageAT(0, 2);
//		Imboom[3] = ob5.createSubImageAT(0, 3);
//		int k = 4;
//		for (int i = 0; i < 5; i++) {
//			for (int j = 0; j < 5; j++) {
//
//				Imboom[k] = ob6.createSubImageAT(i, j);
//				k++;
//			}
//
//		}
		
		loadMap = new LoadMap(gamePanel, tam, chibi1);
		tilearray = loadMap.loadMap("map2.txt", image);

		this.quaivat1 = new Quaivat(chibi1,tilearray, quaivat, 4, 9, 6 * tam, tam, tam*4, tam);

		this.quaivat2 = new Quaivat(chibi1, tilearray,quaivat, 0, 9, 8 * tam, tam * 2, 4*tam,tam);
		this.quaivat3 = new Quaivat(chibi1, tilearray,quaivat, 0, 6, 9 * tam, tam * 7, 4*tam, tam);
		this.quaivat4 = new Quaivat(chibi1, tilearray,quaivat, 4, 3, 12 * tam, tam * 6, 2*tam, tam);
		this.quaivat5 = new Quaivat(chibi1, tilearray,quaivat, 4, 0, 10 * tam, tam * 8, 2*tam,tam);
		Bitmap nhanvat = Bitmap.createScaledBitmap(
				LoadImage.getBmpFromAsset(gamePanel.getContext(), "nhan_vat_chinh.png"), tam * 9, tam * 8, false);
//		this.chibi1 = new ChibiCharacter(nhanvat, 2 * tam, 2 * tam, tam);
		this.chibi1.setArrayTitle(tilearray);
		this.quatang = new QuaTang(chibi1,hopqua, 6*tam, 3*tam, tam);
//		chibi1.setImageBoom(Imboom);

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