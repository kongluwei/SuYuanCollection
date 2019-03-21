package collection.suyuan.pdwy.com.suyuancollection.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;


/**
 * 自定义Toast工具 可直接调用，不需要实例化
 * Author： by MR on 2017/3/15.
 */
public class ToastUtil {
	private static Handler handler = new Handler(Looper.getMainLooper());

	private static Toast toast = null;

	private static Toast toast1 = null;

	private static Object synObj = new Object();

	public static void showMessage(final Context act, final String msg) {
		showMessage(act, msg, Toast.LENGTH_SHORT);
	}

	public static void showMessage(final Context act, final int msg) {
		showMessage(act, msg, Toast.LENGTH_SHORT);
	}

	public static void showMessage(final Context act, final String msg, final int len) {
		new Thread(new Runnable() {
			public void run() {
				handler.post(new Runnable() {
					@Override
					public void run() {
						synchronized (synObj) {
							if (toast != null) {
								toast.setText(msg);
								toast.setDuration(len);
							} else {
								toast = Toast.makeText(act, msg, len);
							}
							toast.show();
						}
					}
				});
			}
		}).start();
	}

	public static void showMessage(final Context act, final int msg,
                                   final int len) {
		new Thread(new Runnable() {
			public void run() {
				handler.post(new Runnable() {
					@Override
					public void run() {
						synchronized (synObj) {
							if (toast != null) {
								toast.setText(msg);
								toast.setDuration(len);
							} else {
								toast = Toast.makeText(act, msg, len);
							}
							toast.show();
						}
					}
				});
			}
		}).start();
	}



}
