package com.example.androidgame2d;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class LoadImage {
	public LoadImage() {

	}

	public static Bitmap getBmpFromAsset(Context ctx, String strFileName) {
		Bitmap bmp = null;
		AssetManager am = ctx.getResources().getAssets();
		InputStream inputStr = null;
		try {
			inputStr = am.open(strFileName);
			bmp = BitmapFactory.decodeStream(inputStr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bmp;
		
	}
}
