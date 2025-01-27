package com.example.forceoffline;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.WindowManager;

public class fobroadcast extends BroadcastReceiver {

	@Override
	public void onReceive(final Context context, Intent arg1) {
		// TODO Auto-generated method stub
		Log.i("test", "fobroadcast receive successful");
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("下线通知");
		builder.setMessage("请您下线");
		builder.setCancelable(false);
		builder.setPositiveButton("确认下线",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						ActivityCollector.FinishAll();
						Intent intent = new Intent(context, MainActivity.class);
						intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						context.startActivity(intent);
					}
				});
		AlertDialog alertDialog = builder.create();
		alertDialog.getWindow().setType(
				WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		alertDialog.show();
	}

}
