package com.example.androidgame2d;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class GameThread extends Thread {
	private boolean running;
//	private GameSurface gameSurface;
	private GamePanel gamePanel;
	private SurfaceHolder surfaceHolder;

	public GameThread(GamePanel gamePanel, SurfaceHolder surfaceHolder) {
		this.gamePanel = gamePanel;
		this.surfaceHolder = surfaceHolder;
	}
	public void run(){
//		long startTime = System.nanoTime();
		while(running){
		
			Canvas canvas = null;
					try{
						canvas = this.surfaceHolder.lockCanvas();
						// Dong bo hoa
						synchronized (canvas) {
							
						this.gamePanel.update();
						this.gamePanel.draw(canvas);
						
						}
					}catch(Exception e){
						// khong lam gi
					}finally{
						if(canvas != null){
							// mo khoa cho canvas.
							this.surfaceHolder.unlockCanvasAndPost(canvas);
						}
					}
					try{
						// Ngung chuong trinh  mot chut.
						this.sleep(40);
					}catch(InterruptedException e){
						
					}
//					startTime = System.nanoTime();
			
		}
	}
	public void setRunning(boolean running){
		this.running = running;
	}
}
