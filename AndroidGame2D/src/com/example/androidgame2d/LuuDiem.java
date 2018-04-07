package com.example.androidgame2d;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import android.content.res.AssetManager;
import android.os.Environment;
import android.provider.OpenableColumns;
import android.util.Log;

public class LuuDiem {
	// private GamePanel gamePanel;
	private String luudiem = "LuuDiem";
	private File file;
	String filename = "LuuDiem.txt";
//	String filename = "luud.txt";

	public LuuDiem() {
		// this.gamePanel = gamePanel;
		file = new File(Environment.getExternalStorageDirectory(), filename);
		Log.v("duong dan", "duong dan"+file.getPath());
	}

	public void setDiem(String diem) {
		// String filename = "LuuDiem";
		// String diem ;

//		file = new File(Environment.getExternalStorageDirectory(), filename);
//		Log.v("buoc 4", "buoc 4");
		try {
			FileOutputStream fo = new FileOutputStream(file);
			fo.write(diem.getBytes());
			fo.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String loadDiem() {
//		file = new File(Environment.getExternalStorageDirectory(), filename);
		String text = "";
		try {
			int length = (int) file.length();
			byte[] bytes = new byte[length];
			FileInputStream fi = new FileInputStream(file);
			try {
				fi.read(bytes);
				text = new String(bytes);
				Log.v("Load duoc file", "Load duoc file" + text);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return text;

	}
}
