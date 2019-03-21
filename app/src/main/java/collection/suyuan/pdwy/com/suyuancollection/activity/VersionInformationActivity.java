package collection.suyuan.pdwy.com.suyuancollection.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import collection.suyuan.pdwy.com.suyuancollection.R;
import collection.suyuan.pdwy.com.suyuancollection.core.BaseActivity;


/**
 * 版本信息activity
 * Author： by MR on 2018/8/15.
 */

public class VersionInformationActivity extends BaseActivity {
    @BindView(R.id.ll_head_return)
    LinearLayout ll_head_return;

    @BindView(R.id.tv_head_title)
    TextView tv_head_title;
    @BindView(R.id.tv_banben)
    TextView tv_banben;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_version_information;
    }

    @Override
    protected void onCreateAfter() {
        ll_head_return.setVisibility(View.VISIBLE);

        tv_head_title.setVisibility(View.VISIBLE);
        tv_head_title.setText("版本信息");
        int versioncode;
        String versionname = "12.0";
        PackageManager pm = getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
            versioncode = packageInfo.versionCode;
            versionname = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        tv_banben.setText("V"+versionname+"版");
    }

    @OnClick({R.id.ll_telephone,R.id.ll_version_update})
    void onClick(View v){
        switch (v.getId()){
            case R.id.ll_telephone:
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "123456789"));
//                intent.setAction(Intent.ACTION_CALL);//直接拨号
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//拨号页面
//                intent.setData(Uri.parse("tel:"+"13693108575"));//设置数据
                startActivity(intent);//开启意图

                break;
            case R.id.ll_version_update:
                Toast.makeText(this,"已是最新版本", Toast.LENGTH_SHORT).show();
                break;

        }

    }

}
