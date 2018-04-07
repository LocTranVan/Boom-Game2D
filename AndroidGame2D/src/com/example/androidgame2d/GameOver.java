package com.example.androidgame2d;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

@SuppressLint("NewApi")
public class GameOver extends ActionBarActivity {
	Button btn_tieptuc, btn_mainmenu, btn_thoat;
	TextView text;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gameover);
		getSupportActionBar().hide();
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		btn_tieptuc = (Button)this.findViewById(R.id.bt_tieptuc);
		btn_tieptuc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(GameOver.this,MainActivity.class);
				GameOver.this.startActivity(intent);
				GameOver.this.finish();
				
			}
		});
		btn_mainmenu = (Button)this.findViewById(R.id.bt_mainmenu);
		btn_mainmenu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(GameOver.this, Start.class);
				GameOver.this.startActivity(intent);
				GameOver.this.finish();
			}
		});
		btn_thoat = (Button)this.findViewById(R.id.bt_thoat);
		btn_thoat.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.exit(0);
				
			}
		});
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_over, menu);
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
