package com.example.sduse;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract.Calendars;
import android.provider.Contacts.People;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.R.anim;
import android.R.integer;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.AsyncTaskLoader;
import android.content.CursorLoader;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MainActivity extends Activity {
	ArrayList<File> list;
	ArrayList<Map<String, Object>> maps;

	@SuppressLint("SdCardPath")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ListView listView = (ListView) findViewById(R.id.lv);
		list = new ArrayList<File>();
		maps = new ArrayList<Map<String, Object>>();
		// getAllFile(new File("/sdcard"));
		// initMapData(list);
		ArrayList<String> list1 = getlist(getcursor());
		getnum(list1);
		SimpleAdapter adapter = new SimpleAdapter(this, maps, R.layout.item,
				new String[] { "name", "path" }, new int[] { R.id.name,
						R.id.tittle });
		listView.setAdapter(adapter);
		TextView empty = new TextView(this);
		empty.setText("empty");
		listView.setEmptyView(empty);

	}

	private Map<String, Integer> getnum(ArrayList<String> list) {
		ArrayList<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (String l : list) {
			if (map.get(l) == null) {
				map.put(l, 1);
			} else {
				map.put(l, Integer.valueOf(map.get(l)) + 1);
			}
		}
		for (String s : map.keySet()) {
			Map<String, Object> nMap = new HashMap<String, Object>();
			nMap.put("name", s);
			nMap.put("path", map.get(s).toString());
			maps.add(nMap);
		}
		return map;
	}

	@SuppressWarnings("deprecation")
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private Cursor getcursor() {
		Uri uri;
		uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
		// Cursor cursor = managedQuery(uri, new String[] { Media.DISPLAY_NAME,
		// Media.LATITUDE, Media.LONGITUDE, Media._ID }, null, null, null);
		Cursor cursor = managedQuery(uri, null, null, null, null);
		return cursor;
	}

	private ArrayList<String> getlist(Cursor cursor1) {
		Cursor cursor = (Cursor) cursor1;
		ArrayList<String> list = new ArrayList<String>();
		if (cursor.moveToFirst()) {
			do {
				int index = cursor.getColumnIndex(Media.DATA);
				String path = cursor.getString(index);
				list.add(path.substring(0, path.lastIndexOf("/")));
			} while (cursor.moveToNext());
		}
		return list;
	}
	/*
	 * private void initMapData(ArrayList<File> list2) { // TODO Auto-generated
	 * method stub for (int i = 0; i < list2.size(); i++) { Map<String, Object>
	 * tempMap = new HashMap<String, Object>(); String path =
	 * list2.get(i).toURI().toString(); String name =
	 * path.substring(path.lastIndexOf("/") + 1, path.length());
	 * tempMap.put("name", name); tempMap.put("path", path); String s = (String)
	 * tempMap.get("name"); maps.add(tempMap);
	 * 
	 * } }
	 * 
	 * private void getAllFile(File file) { // TODO Auto-generated method stub
	 * File[] files = file.listFiles(); if (files != null) { for (File f :
	 * files) { if (f.isDirectory()) // getAllFile(f); ; else { list.add(f); } }
	 * } }
	 */
}
