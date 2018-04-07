package com.example.androidgame2d;

import android.R.bool;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.os.Message;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
	public GameThread gameThread;
	public GameStateManager gsm;
	private int tam;
	public static ChibiCharacter chibi1;
	private boolean pause = false;
	public static MainActivity context;
	private int d;
//	private LuuDiem luudiem;

	public GamePanel(MainActivity context, int heightpx, int widhtpx) {
		super(context);
		this.setFocusable(true);
		this.getHolder().addCallback(this);
		this.context = context;
		int width = (int) (widhtpx / 15);
		int height = (int) (heightpx / 10);
		if (width >= height) {
			tam = height;
		} else {
			tam = width;
		}
		Bitmap nhanvat = Bitmap.createScaledBitmap(LoadImage.getBmpFromAsset(getContext(), "nhan_vat_chinh.png"),
				tam * 9, tam * 8, false);
		chibi1 = new ChibiCharacter(this, nhanvat, 2 * tam, 2 * tam, tam);
		gsm = new GameStateManager(this, chibi1, tam);
//		luudiem = new LuuDiem();
		// TODO Auto-generated constructor stub
	}

	public void update() {
		if (!pause) {
			gsm.update();
			if (chibi1.getLive() == 0) {
//				String level = Integer.toString(gsm.getLevel());
//				luudiem.setDiem(level);
				lose();
				
			}
			context.onPlay();
		}
	

	}

	public void draw(Canvas canvas) {
		gsm.Draw(canvas);
	}

	// public void setScore(int score){
	// context.setScore(chibi1.getScore());
	// }

	public int getTam() {
		return tam;
	}

	public ChibiCharacter getChibi() {
		return gsm.getChibi();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// TODO Auto-generated method stub
		// gameThread = new GameThread(this, getHolder());
		// gameThread.setRunning(true);
		// gameThread.start();

	}

	public void lose() {
		Message gsm = context.handle.obtainMessage();
		gsm.what = 3;
		context.handle.sendMessage(gsm);
		gameThread.setRunning(false);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		if (gameThread == null) {
			gameThread = new GameThread(this, getHolder());
			gameThread.setRunning(true);
			gameThread.start();
		}

	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}

	public boolean getPause() {
		return pause;
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}

}