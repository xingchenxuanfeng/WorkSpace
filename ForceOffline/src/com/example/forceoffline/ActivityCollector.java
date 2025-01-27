package com.example.forceoffline;

import java.util.ArrayList;

import android.app.Activity;

public class ActivityCollector {
	static ArrayList<Activity> activities = new ArrayList<Activity>();

	public static void AddActivity(Activity activity) {
		activities.add(activity);
	}

	public static void RemoveActivity(Activity activity) {
		activities.remove(activity);
	}

	public static void FinishAll() {
		for (Activity activity : activities)
			activity.finish();
	}
}
