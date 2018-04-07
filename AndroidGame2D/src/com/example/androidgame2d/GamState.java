package com.example.androidgame2d;

import android.graphics.Canvas;

public abstract class GamState {
	public  GamState(GamePanel gamePanel){
		
	}
public abstract void update();
public abstract void init();
public abstract ChibiCharacter getChibi();
public abstract void draw(Canvas canvas);
}
