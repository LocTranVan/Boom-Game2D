package com.example.androidgame2d;

import java.util.ArrayList;

import com.example.androidgame2d.R.layout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MainActivity extends ActionBarActivity {
	View pauseButton, pauseButton2, pause1, vShop;
	ImageView control, bom, pause, heart, volumeTurn, volumeoff, volume;
	ImageView controlScoll, shop;
	private int x = 58;
	private int y = 520;
	private TextView sScore, sthoat, buyBoom, buyLive;
	private GestureDetector gestureDetector;
	private RelativeLayout rl;
	private TextView textEvt2, score, live, sbom;
	private long time;
	private float mDownX, mDownY;
	private boolean isOnClick;
	private ChibiCharacter chibi1;
	private GamePanel gamePanel;
	public int tam, dscore = 20, dlive = 3, dbom = 1;
	private boolean mute = true, ktrpause = true;
	private MediaPlayer music, datbom;
	private LuuDiem ludiem;
	private Typeface tf;
	final Handler handle = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 0) {
				setLive();
			}
			if (msg.what == 1) {
				setScore();
			}
			if (msg.what == 2) {
				setBom();
			}
			if (msg.what == 3) {
				lose();
			}
			super.handleMessage(msg);
		}
	};
	OnClickListener datBoom = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (chibi1.getLive() != 0) {
				if (chibi1.getQuaBoom() != 0) {
					chibi1.datBoom();
					if (mute == true) {
						datbom = MediaPlayer.create(MainActivity.this, R.raw.datbom);
						datbom.setVolume(0.3f, 0.3f);
						datbom.start();
					}
					int boom = chibi1.getQuaBoom();
					boom--;
					dbom--;
					sbom.setText("" + dbom);
					chibi1.setDatBoom(dbom);
				}
			}
//			ludiem.setDiem("hello");
//			Log.v("Hello", ludiem.loadDiem("LuuDiem.txt"));

		}
	};
	OnClickListener datPause = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (ktrpause) {
				ktrpause = false;
				gamePanel.setPause(true);
				LayoutInflater mylayout = (LayoutInflater) getApplicationContext().getSystemService(
						getApplicationContext().LAYOUT_INFLATER_SERVICE);
				pause1 = mylayout.inflate(R.layout.pause, null, false);
				pause1.setX(6 * tam);
				pause1.setY(2 * tam);
				rl.addView(pause1);
				TextView tv_tieptuc = (TextView) pause1.findViewById(R.id.tv_tieptuc);
				tv_tieptuc.setTypeface(tf);
				tv_tieptuc.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						gamePanel.setPause(false);
						rl.removeView(pause1);
						ktrpause = true;
					}
				});
				TextView tv_mainme = (TextView) pause1.findViewById(R.id.tv_menuchinh);
				tv_mainme.setTypeface(tf);
				tv_mainme.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method st
						Intent intent = new Intent(MainActivity.this, Start.class);
						MainActivity.this.startActivity(intent);
						// music.stop();
						ktrpause = true;
						MainActivity.this.finish();

					}
				});
				TextView tv_thoat = (TextView) pause1.findViewById(R.id.tv_thoat);
				tv_thoat.setTypeface(tf);
				tv_thoat.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						ktrpause = true;
						System.exit(0);
					}
				});
			}
		}

	};
	OnClickListener datVolume = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (!mute) {
				volume.setImageResource(R.drawable.sound_on);
				music = MediaPlayer.create(MainActivity.this, R.raw.bgmusic);
				music.setVolume(0.3f, 0.3f);
				music.start();
				mute = true;
			} else {
				volume.setImageResource(R.drawable.sound_off);
				stopMusic();
				mute = false;
			}

		}
	};
	OnClickListener datShop = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent mIntent = new Intent(MainActivity.this, Shop.class);
			MainActivity.this.startActivity(mIntent);
			gamePanel.setPause(true);
//			ludiem.setDiem("Hello");
//			ludiem.loadDiem();
//			Log.v("Hello ", "Hello "+ ludiem.loadDiem());
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().hide();
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.setContentView(R.layout.activity_main);
		rl = (RelativeLayout) this.findViewById(R.id.Rel_main);
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		int height = displaymetrics.heightPixels;
		int width = displaymetrics.widthPixels;

		tf = Typeface.createFromAsset(getAssets(), "font/UVNHanViet.TTF");
		gamePanel = new GamePanel(this, height, width);
		rl.addView(gamePanel);
		tam = gamePanel.getTam();

		LayoutInflater myinflate = (LayoutInflater) getApplicationContext().getSystemService(
				getApplicationContext().LAYOUT_INFLATER_SERVICE);
		pauseButton = myinflate.inflate(R.layout.control, null, false);
		pauseButton.setX(0);
		pauseButton.setY(tam * 7);
		rl.addView(pauseButton);
		pauseButton.getLayoutParams().height = 3 * tam;
		pauseButton.getLayoutParams().width = 3 * tam;

		textEvt2 = new TextView(this);
		textEvt2.setX(300);
		textEvt2.setY(100);
		textEvt2.setTextColor(Color.BLACK);

		ludiem = new LuuDiem();
		bom = new ImageView(this);
		bom.setImageResource(R.drawable.icon_bom);
		bom.setX(tam * 13);
		bom.setY(tam * 8);
		bom.setAlpha(140);
		rl.addView(bom);
		bom.getLayoutParams().height = tam * 2;
		bom.getLayoutParams().width = tam * 2;
		chibi1 = gamePanel.getChibi();

		score = new TextView(this);
		score.setX(0);
		score.setY(-(int) tam / 4);
		// Log.v("tag 1", "tag1");
		score.setText("" + dscore);
		// Log.v("tag 2", "tag2");/
		score.setTextColor(Color.WHITE);
		score.setTypeface(tf);
		score.setTextSize((int) tam / 2);
		rl.addView(score);

//		ludiem = new LuuDiem(gamePanel);
//		ludiem.setDiem("Hello");
//		ludiem.loadDiem("");
//		textEvt2.setText("Hello"+ ludiem.loadDiem("LuuDiem.txt"));
			
		live = new TextView(this);
		live.setX(6 * tam);
		live.setY(-(int) tam / 4);
		live.setText("" + dlive);
		live.setTextColor(Color.WHITE);
		live.setTypeface(tf);
		live.setTextSize((int) tam / 2);
		rl.addView(live);

		sbom = new TextView(this);
		sbom.setX(14 * tam);
		sbom.setY((int) (tam * 8.5));
		sbom.setTextColor(Color.WHITE);
		sbom.setTypeface(tf);
		sbom.setTextSize((int) tam / 2);
		sbom.setText("" + dbom);
		rl.addView(sbom);
		shop = new ImageView(this);
		shop.setImageResource(R.drawable.bonus);
		shop.setX(tam * 14);
		shop.setY(tam * 3);
		rl.addView(shop);
		shop.getLayoutParams().height = tam;
		shop.getLayoutParams().width = tam;
		shop.setOnClickListener(datShop);

		bom.setOnClickListener(datBoom);
		pause = new ImageView(this);
		pause.setImageResource(R.drawable.pause);
		pause.setX(tam * 14);
		pause.setY(0);
		rl.addView(pause);
		pause.getLayoutParams().height = tam;
		pause.getLayoutParams().width = tam;

		pause.setOnClickListener(datPause);

		heart = new ImageView(this);
		heart.setImageResource(R.drawable.heart);
		heart.setX(tam * 7);
		heart.setY(1);
		rl.addView(heart);
		heart.getLayoutParams().height = tam;
		heart.getLayoutParams().width = tam;

		volume = new ImageView(this);
		volume.setImageResource(R.drawable.sound_on);
		volume.setOnClickListener(datVolume);

		volume.setX(tam * 13);
		volume.setY(0);
		rl.addView(volume);
		volume.getLayoutParams().height = tam;
		volume.getLayoutParams().width = tam;

		controlScoll = new ImageView(this);
		controlScoll.setImageResource(R.drawable.control2);
		controlScoll.setX((int) (tam * 0.9));
		controlScoll.setY((int) (tam * 7.9));
		rl.addView(controlScoll);
		controlScoll.getLayoutParams().height = (int) (tam * 1.3);
		controlScoll.getLayoutParams().width = (int) (tam * 1.3);

		rl.addView(textEvt2);

		pauseButton.setOnTouchListener(oTouch);
		music = MediaPlayer.create(MainActivity.this, R.raw.bgmusic);
		music.setVolume(0.3f, 0.3f);
		music.start();

	}
	OnTouchListener oTouch = new OnTouchListener() {
		
		@Override
//		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			public boolean onTouch(View v, MotionEvent ev) {
				//
				switch (ev.getAction() & MotionEvent.ACTION_MASK) {
				case MotionEvent.ACTION_DOWN:
					mDownX = ev.getX();
					mDownY = ev.getY();
					if ((ev.getX() >= 0) && (ev.getX() <= tam * 3)) {

						if (ev.getY() < tam) {
							controlScoll.setX((int) (tam * 0.9));
							controlScoll.setY(tam * 6);
							chibi1.setMove(1);

						}
						if (ev.getY() > tam * 2) {
							controlScoll.setY((int) (tam * 9.8));
							controlScoll.setX((int) (tam * 0.9));
							chibi1.setMove(2);

						}

					}
					if ((ev.getY() < tam * 3) && (ev.getY() > 0)) {
						if (ev.getX() > tam * 2) {
							controlScoll.setY((int) (tam * 7.9));
							controlScoll.setX(tam * 3);
							chibi1.setMove(4);

						}
						if (ev.getX() < tam) {
							controlScoll.setY((int) (tam * 7.9));
							controlScoll.setX(-tam);
							chibi1.setMove(3);

						}
					}
					break;
				case MotionEvent.ACTION_CANCEL:
					break;
				case MotionEvent.ACTION_UP:
					controlScoll.setX((int) (tam * 0.9));
					controlScoll.setY((int) (tam * 7.9));
					chibi1.setMove(0);
					break;
				case MotionEvent.ACTION_MOVE:
//					if (((mDownX <= (tam * 2)) && ((tam) <= mDownX)) && ((mDownY <= tam * 2) && (mDownY >= tam))) {
						if ((ev.getX() >= 0) && (ev.getX() <= tam * 3)) {

							if (ev.getY() < tam) {
								controlScoll.setX((int) (tam * 0.9));
								controlScoll.setY(tam * 6);
								chibi1.setMove(1);

							}
							if (ev.getY() > tam * 2) {
								controlScoll.setY((int) (tam * 9.8));
								controlScoll.setX((int) (tam * 0.9));
								chibi1.setMove(2);

							}

						}
						if ((ev.getY() < tam * 3) && (ev.getY() > 0)) {
							if (ev.getX() > tam * 2) {
								controlScoll.setY((int) (tam * 7.9));
								controlScoll.setX(tam * 3);
								chibi1.setMove(4);

							}
							if (ev.getX() < tam) {
								controlScoll.setY((int) (tam * 7.9));
								controlScoll.setX(-tam);
								chibi1.setMove(3);

							}
						}

//					}
					break;
				default:
					break;
				}
				return true;
			}
//			return false;
//		}
	};
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.x
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public boolean getMute() {
		return mute;
	}

	//
	protected void setScore() {
		dscore = chibi1.getScore();
		score.setText("" + dscore);
	}

	protected void setBom() {
		dbom = chibi1.getQuaBoom();
		sbom.setText("" + dbom);
	}

	protected void onStop() {
		if (music.isPlaying()) {
			music.stop();
		}
		super.onStop();
	}

	public void stopMusic() {
		if (music.isPlaying()) {
			music.stop();
			// music.pause();
		}
	}

	public void onPlay() {
		if (!music.isPlaying()) {
			music.start();
		}
	}

	public void onDestroy() {
		super.onDestroy();

	}

	protected void setLive() {
		dlive = chibi1.getLive();
		live.setText("" + dlive);
	}

	public ChibiCharacter getChibi() {
		return chibi1;
	}

	protected void lose() {
		Intent intent = new Intent(MainActivity.this, GameOver.class);
		MainActivity.this.startActivity(intent);
		onStop();
		MainActivity.this.finish();

	}

}