package com.example.androidgame2d;

import java.util.zip.Inflater;

import android.R.anim;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

@SuppressLint("NewApi")
public class Start extends ActionBarActivity {
	ImageView btn_choi, btn_thongtin, btn_thoat, btn_tieptuc;
	View choi;
	RelativeLayout rl;
	private LuuDiem luudiem;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().hide();
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_start);
		rl = (RelativeLayout)this.findViewById(R.id.rl_start);
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		int height = displaymetrics.heightPixels;
		int width = displaymetrics.widthPixels;
		btn_choi = (ImageView)this.findViewById(R.id.Btn_choi);
		btn_thongtin=(ImageView)this.findViewById(R.id.Btn_thongtin);
		btn_thoat =(ImageView)this.findViewById(R.id.Btn_thoat);
		btn_tieptuc = new ImageView(this);
		btn_tieptuc.setBackgroundResource(R.drawable.tieptuc_converted);
//		btn
		btn_tieptuc.setX((int)(width/2 - 125));
		btn_tieptuc.setY((int)(height/8));
//		
		luudiem = new LuuDiem();
		
		if(luudiem.loadDiem() != ""){
			rl.addView(btn_tieptuc);
			btn_tieptuc.getLayoutParams().height = 250;
			btn_tieptuc.getLayoutParams().width = 250;
			Log.v("Load diem", "Load diem"+ luudiem.loadDiem());
			btn_tieptuc.setOnClickListener(new OnClickListener() {
//				
			
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					GameStateManager.level = Integer.parseInt(luudiem.loadDiem());
					Intent myIntent = new Intent(Start.this, MainActivity.class);
					Start.this.startActivity(myIntent);
					Start.this.finish();
					
				}
			});
		}
		
//		btn_choi.setAlpha(40);
//		btn_choi.setAlpha(40);
		btn_choi.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GameStateManager.level = 0;
				String level4 = Integer.toString(0);
				luudiem.setDiem(level4);
				Intent myIntent = new Intent(Start.this, Huongdan.class);
				Start.this.startActivity(myIntent);
				Start.this.finish();
				
				
			}
		});
//		int heigth = (int)btn_choi.getX();
		int heigth  = btn_choi.getDrawable().getIntrinsicHeight();
//		Log.v("do dai", "do dai"+heigth);
		btn_thoat.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				getWindow().
				
				android.os.Process.killProcess(android.os.Process.myPid());
				System.exit(1);
			}
		});
		btn_thongtin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent(Start.this, ThongTin.class);
				Start.this.startActivity(myIntent);
//				Start.this.finish();
			}
		});
//		btn_thongtin.get
//		Inflater
//		LayoutInflater myinflate = (LayoutInflater) getApplicationContext().getSystemService(
//				getApplicationContext().LAYOUT_INFLATER_SERVICE);
//		choi = myinflate.inflate(R.layout.luudiem, null, false);
//		choi.setX((int)(width/2));
//		choi.setY(0);
//		rl.addView(choi);
//		choi.getLayoutParams().height = (int)(height/2);
//		choi.getLayoutParams().width = (int)(width/2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
