package com.example.androidgame2d;

import android.graphics.Canvas;


public class GameStateManager {
	private GamState gsm ;
	private GamePanel gamePanel;
	private int tam;
	public static int level;
	final int Level1 = 1,  Level2 = 0, Level3= 3;
	private ChibiCharacter chibi1;
	public GameStateManager(GamePanel gamePanel,ChibiCharacter chibi1, int tam) {
		this.gamePanel = gamePanel;
		this.tam = tam;
		this.chibi1 = chibi1;
//		gsm = new GameSurface2(gamePanel,chibi1, tam);
//		gsm.init();
		setState(level);
	}
	public void setState(int i){
//		case 1:
		level = i;
		setStatePrevious(i);
		switch (i) {
		case Level1:
			gsm = new GameSurface(gamePanel, chibi1, tam);
			gsm.init();
			break;
		case Level2:
			gsm  = new GameSurface2(gamePanel, chibi1, tam);
			gsm.init();
			break;
		case Level3:
			gsm = null;
			break;
		default:
			break;
		}
			
	}
	public int getLevel(){
		return level;
	}
	public void setStatePrevious(int a){
		gsm = null;
	}
	public void update(){
//		((GameSurface) gsm).update();
		gsm.update();
		
	}
	public ChibiCharacter getChibi(){
		return gsm.getChibi();
		
	}
	public void Draw(Canvas canvas){
//		gsm.draw(canvas);
		gsm.draw(canvas);
	
	}
}
 