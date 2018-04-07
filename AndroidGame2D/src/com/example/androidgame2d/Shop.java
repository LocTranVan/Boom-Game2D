package com.example.androidgame2d;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

@SuppressLint("NewApi")
public class Shop extends ActionBarActivity {
	private TextView buyLive, buyBoom, thoat, score;
	private ChibiCharacter chibi;
	private int tam ;
	private RelativeLayout rl;
	private Typeface tf;
	private MediaPlayer msShop, mapMove;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().hide();
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.setContentView(R.layout.shop);
		
		tf = Typeface.createFromAsset(getAssets(), "font/UVNHanViet.TTF");
		chibi = GamePanel.chibi1;
		tam = chibi.getTam();
//		tam = 40;
		rl = (RelativeLayout ) this.findViewById(R.id.idShop);
		rl.getLayoutParams().height = 10*tam;
		rl.getLayoutParams().width = 15*tam;
		buyLive = new TextView(this);
		buyLive.setText("Buy");
		buyLive.setX(tam*2);
		buyLive.setY((int)(tam*5.3));
		buyLive.setTextSize((int) tam / 4);
		buyLive.setTypeface(tf);
		buyLive.setTextColor(Color.WHITE);
		rl.addView(buyLive);
		int tien = chibi.getScore();
		score = new TextView(this);
		score.setX(3 * tam);
		score.setY((int) (tam / 2));
		score.setText("" + tien);
		score.setTextColor(Color.WHITE);
		score.setTypeface(tf);
		score.setTextSize((int) tam / 3);
		rl.addView(score);
		buyLive.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// TODO Auto-generated method stub
				int score1 = chibi.getScore();
				if (score1 >= 20) {
					int live = chibi.getLive();
					live += 1;
					chibi.setLive(live);
					Message msg = chibi.gamePanel.context.handle.obtainMessage();
					msg.what = 0;
					chibi.gamePanel.context.handle.sendMessage(msg);
					score1 = score1 - 20;
					chibi.setScore(score1);
					score.setText(""+score1);
					Message msg1 = chibi.gamePanel.context.handle.obtainMessage();
					msg1.what = 1;
					chibi.gamePanel.context.handle.sendMessage(msg1);
					mapMove = MediaPlayer.create(Shop.this, R.raw.mapmove);
					mapMove.setVolume(0.6f, 0.6f);
					mapMove.start();

				}
			}
		});

		buyBoom = new TextView(this);
		buyBoom.setText("Buy");
		buyBoom.setX((int)(tam*5.5));
		buyBoom.setY((int)(tam*5.3));
		buyBoom.setTextSize((int) tam / 4);
		buyBoom.setTypeface(tf);
		buyBoom.setTextColor(Color.WHITE);
		rl.addView(buyBoom);
		buyBoom.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int score1 = chibi.getScore();
				if (score1 >= 20) {
					int boom = chibi.getQuaBoom();
					boom += 1;
					chibi.setDatBom(boom);
					Message msg = chibi.gamePanel.context.handle.obtainMessage();
					msg.what = 2;
					chibi.gamePanel.context.handle.sendMessage(msg);
					score1 = score1 - 20;
					chibi.setScore(score1);
					score.setText(""+score1);
					Message msg1 = chibi.gamePanel.context.handle.obtainMessage();
					msg1.what = 1;
					chibi.gamePanel.context.handle.sendMessage(msg1);
					mapMove = MediaPlayer.create(Shop.this, R.raw.mapmove);
					mapMove.setVolume(0.6f, 0.6f);
					mapMove.start();

				}
			}
		});
		thoat= new TextView(this);
		thoat.setText("X");
		thoat.setX((int)(tam*14.2));
		thoat.setY((int) (tam*0.3));
		thoat.setTextSize((int) tam / 3);
		thoat.setTypeface(tf);
		thoat.setTextColor(Color.WHITE);
		rl.addView(thoat);
		thoat.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				chibi.gamePanel.setPause(false);
//				GamePanel.context.getChibi().gamePanel.setPause(false);
				Shop.this.finish();

			}
		});
		
		msShop = MediaPlayer.create(Shop.this, R.raw.coin);
		msShop.setVolume(0.3f, 0.3f);
		msShop.start();
	}
	
}
