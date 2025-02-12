package com.example.threadmessage;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.R.layout;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	String TAG = "Threadtest";
	PipedReader pr;
	PipedWriter pw;
	private TextView et;
	private Button bt;
	NewThread newThread;
	private NThread nThread;
	private Handler hand;
	Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		pr = new PipedReader();
		pw = new PipedWriter();
		try {
			pw.connect(pr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		et = (TextView) findViewById(R.id.editText1);
		bt = (Button) findViewById(R.id.button1);
		bt.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					// pw.write(et.getText().toString());
					// nThread.doWork();
					Log.i(TAG, Thread.currentThread().toString());
					Message m = handler.obtainMessage();
					m.arg1 = 10;
					m.sendToTarget();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		nThread = new NThread();
		nThread.start();
		hand = new Handler() {
			@Override
			public void handleMessage(android.os.Message msg) {
				switch (msg.arg1) {
				case 1:
					Log.i(TAG, "handlermessagetest");
					break;

				default:
					Log.i(TAG, "handlermessagetest" + msg.arg1);
					break;
				}
			};
		};
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		try {
			pr.close();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		newThread.interrupt();
		nThread.exit();
		nThread.interrupt();
	}

	public class NThread extends Thread {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Log.i(TAG, Thread.currentThread().toString());

			Looper.prepare();
			handler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					// TODO Auto-generated method stub
					Log.i(TAG, Thread.currentThread().toString());

					Log.i(TAG, msg.arg1 + "");
					Message message = hand.obtainMessage();
					message.arg1 = 1;
					 hand.sendMessage(message);
//					message.arg1 = 2;
//
//					message.sendToTarget();
				}

			};
			Looper.loop();

		}

		public void exit() {
			// TODO Auto-generated method stub
			// handler.getLooper().quit();

		}
	}
}
