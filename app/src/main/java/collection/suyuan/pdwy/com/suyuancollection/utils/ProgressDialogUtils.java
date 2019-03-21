package collection.suyuan.pdwy.com.suyuancollection.utils;

import android.app.Dialog;


/**
 * 通用ProgressDialog
 * Author： by MR on 2017/3/15.
 */
public class ProgressDialogUtils {



	/**
	 * @param infoDialog
	 */
	public static void Close(Dialog infoDialog) {
		if (infoDialog != null) {
			infoDialog.dismiss();
		}
		infoDialog = null;
	}

}
