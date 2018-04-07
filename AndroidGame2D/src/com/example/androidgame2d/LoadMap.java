package com.example.androidgame2d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.content.res.AssetManager;
import android.graphics.Bitmap;

public class LoadMap{
	private GamePanel gamePanel;
	private ArrayList<Title> tilearray;
	private int tam;
	private ChibiCharacter chibicharacter;
	public LoadMap(GamePanel gamePanel, int tam, ChibiCharacter chibiCharacter){
		this.gamePanel = gamePanel;
		tilearray = new ArrayList<Title>();
		this.tam = tam;
		this.chibicharacter = chibiCharacter;
	}
	public ArrayList<Title> loadMap(String strFileName, Bitmap[] image)  {
		AssetManager am = gamePanel.getResources().getAssets();
		InputStream is = null;
		BufferedReader reader = null;
		
		try {
			is = am.open(strFileName);

			reader = new BufferedReader(new InputStreamReader(is));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<String> lines = new ArrayList<String>();
		int width = 0;
		int height = 0;

		while (true) {
			String line;
			try {
				line = reader.readLine();
				if (line == null) {
					reader.close();
					break;
				}
				if (!line.startsWith("!")) {
					lines.add(line);
					width = Math.max(width, line.length());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		height = lines.size();
//
		
		for (int j = 0; j < 10; j++) {
			String line = (String) lines.get(j);
			for (int i = 0; i < width; i++) {
//				// System.out.println(i + "is i ");
				if (i < line.length()) {
					char ch = line.charAt(i);
					Title t = new Title(i*tam, j*tam, Character.getNumericValue(ch), image,tam, chibicharacter);
					tilearray.add(t);
				}
			}
		}
		return tilearray;
	}

	
}