package collection.suyuan.pdwy.com.suyuancollection;


import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import butterknife.BindView;
import butterknife.OnClick;
import collection.suyuan.pdwy.com.suyuancollection.activity.PasswordModificationActivity;
import collection.suyuan.pdwy.com.suyuancollection.core.BaseActivity;
import collection.suyuan.pdwy.com.suyuancollection.utils.MLog;
import collection.suyuan.pdwy.com.suyuancollection.utils.SharePreferencesUtils;
import rx.Observer;
import rx.Subscriber;

/**
 * 登陆LoginActivity
 * Author： by klw on 2019/2/25.
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.tv_username)
    TextView tv_username;
    @BindView(R.id.tv_passwordd)
    TextView tv_passwordd;
    @BindView(R.id.tv_version)
    TextView tv_version;


    @BindView(R.id.cb_remember)
    CheckBox cb_remember;
    @BindView(R.id.ll_login)
    LinearLayout ll_login;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login_layout;
    }

    @Override
    protected void onCreateAfter() {
        ll_login.setBackgroundDrawable(getDrawableMipmap(R.mipmap.login));

        try {
            int versioncode;
            String versionname ;
            PackageManager pm = getPackageManager();
            PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
            versioncode = packageInfo.versionCode;
            versionname = packageInfo.versionName;
            tv_version.setText("V"+versionname+"版");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        tv_username.setText(SharePreferencesUtils.getString(getString(R.string.user_name),""));
        if("".equals(SharePreferencesUtils.getString(getString(R.string.user_paw),"")))
            tv_passwordd.setText("");
        else {
            tv_username.setText(SharePreferencesUtils.getString(getString(R.string.user_name),"admin"));
            tv_passwordd.setText(SharePreferencesUtils.getString(getString(R.string.user_paw), ""));
            cb_remember.setChecked(true);
        }
        tv_username.setText("admin1");
        tv_passwordd.setText("admin");
    }

    @OnClick({R.id.tv_forget_password})
    public void forgetPassword(View v){
        startActivity(new Intent(this, PasswordModificationActivity.class));
    }
    @OnClick({R.id.tv_login})
    public void signIn(View v){
        Observer<String> observer = new Observer<String>() {

            @Override
            public void onNext(String s) {
                MLog.d("Item: " + s);
            }

            @Override
            public void onCompleted() {
                MLog.d("Completed!");
            }

            @Override
            public void onError(Throwable e) {
                MLog.d( "Error!");
            }
        };

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onNext(String s) {
                MLog.d("Item: " + s);
            }

            @Override
            public void onCompleted() {
                MLog.d( "Completed!");
            }

            @Override
            public void onError(Throwable e) {
                MLog.d( "Error!");
            }
        };
        if(tv_username.getText()==null||"".equals(tv_username.getText().toString().trim())||tv_passwordd.getText()==null||"".equals(tv_passwordd.getText().toString().trim())){
            Toast.makeText(this,"账号密码不能为空", Toast.LENGTH_SHORT).show();
        }else{

            if(signInAsynchronous()==1) {


                SharePreferencesUtils.putString(getString(R.string.user_id), tv_username.getText().toString());
                SharePreferencesUtils.putString(getString(R.string.user_name), tv_username.getText().toString());
                switch (tv_username.getText().toString()){
                    case "admin0":
                        SharePreferencesUtils.putString(getString(R.string.user_identity), "处长");
                        SharePreferencesUtils.putString(getString(R.string.user_fullName), "周海涛0");
                        break;
                    case "admin1":
                        SharePreferencesUtils.putString(getString(R.string.user_identity), "审查员");
                        SharePreferencesUtils.putString(getString(R.string.user_fullName), "周海涛1");
                        break;
                    case "admin2":
                        SharePreferencesUtils.putString(getString(R.string.user_identity), "分中心主任");
                        SharePreferencesUtils.putString(getString(R.string.user_fullName), "周海涛2");
                        break;
                    case "admin3":
                        SharePreferencesUtils.putString(getString(R.string.user_identity), "测试员");
                        SharePreferencesUtils.putString(getString(R.string.user_fullName), "周海涛3");
                        break;
                    case "admin4":
                        SharePreferencesUtils.putString(getString(R.string.user_identity), "分中心保藏员");
                        SharePreferencesUtils.putString(getString(R.string.user_fullName), "周海涛4");
                        break;
                }

                if(cb_remember.isChecked()) {
                      SharePreferencesUtils.putString(getString(R.string.user_paw), tv_passwordd.getText().toString());

                }
                else{
                    SharePreferencesUtils.putString(getString(R.string.user_paw),"");

                }
                Toast.makeText(this,"登录成功", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,MainActivity.class));
                finish();


            }
        }

    }
    int signInAsynchronous(){
        if("admin".equals(tv_passwordd.getText().toString())&&("admin0".equals(tv_username.getText().toString())||
                "admin1".equals(tv_username.getText().toString())||
                "admin2".equals(tv_username.getText().toString())||
                "admin3".equals(tv_username.getText().toString())||
                "admin4".equals(tv_username.getText().toString()))


                ){
            return 1;
        }
        else{
            Toast.makeText(this,"账号密码错误", Toast.LENGTH_SHORT).show();
            return 0;
        }

    }
}
