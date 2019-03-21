package collection.suyuan.pdwy.com.suyuancollection.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import collection.suyuan.pdwy.com.suyuancollection.LoginActivity;
import collection.suyuan.pdwy.com.suyuancollection.MainActivity;
import collection.suyuan.pdwy.com.suyuancollection.R;
import collection.suyuan.pdwy.com.suyuancollection.activity.FunctionalIntroductionActivity;
import collection.suyuan.pdwy.com.suyuancollection.activity.PasswordModificationActivity;
import collection.suyuan.pdwy.com.suyuancollection.activity.VersionInformationActivity;
import collection.suyuan.pdwy.com.suyuancollection.utils.SharePreferencesUtils;

/**
 * author : KongLW
 * e-mail : kongluweishujia@163.com
 * date   : 2019/3/117:06
 * desc   :
 */
public class PersonalCenterFragment extends Fragment implements View.OnClickListener{
    @BindView(R.id.ll_head_return)
    LinearLayout ll_head_return;
    @BindView(R.id.tv_head_title)
    TextView tv_head_title;
    @BindView(R.id.tv_fullName)
    TextView tv_fullName;
    @BindView(R.id.tv_identity)
    TextView tv_identity;


    @BindView(R.id.tv_banben)
    TextView tv_banben;



    TextView tv_personal_out;

    LinearLayout ll_personal_password_modification;
    LinearLayout ll_personal_functional_introduction;
    LinearLayout ll_personal_version_information;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_personal_center,null);
        onCreateAfter(view);
        return view;
    }



    protected void onCreateAfter(View view) {
        int versioncode;
        String versionname = "12.0";
        PackageManager pm = getActivity().getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo( getActivity().getPackageName(), 0);
            versioncode = packageInfo.versionCode;
            versionname = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        tv_fullName=view.findViewById(R.id.tv_fullName);
        tv_identity=view.findViewById(R.id.tv_identity);
        tv_banben=view.findViewById(R.id.tv_banben);
        tv_personal_out=view.findViewById(R.id.tv_personal_out);
        ll_personal_password_modification=view.findViewById(R.id.ll_personal_password_modification);
        ll_personal_functional_introduction=view.findViewById(R.id.ll_personal_functional_introduction);
        ll_personal_version_information=view.findViewById(R.id.ll_personal_version_information);
        tv_personal_out.setOnClickListener(this);
        ll_personal_password_modification.setOnClickListener(this);
        ll_personal_functional_introduction.setOnClickListener(this);
        ll_personal_version_information.setOnClickListener(this);
//        tv_fullName.setText(SharePreferencesUtils.getString(getString(R.string.user_fullName),""));
//        tv_identity.setText(SharePreferencesUtils.getString(getString(R.string.user_identity),""));
        if("".equals(tv_fullName.getText().toString())){
            tv_personal_out.setText("去登录");
        }

        tv_banben.setText("V"+versionname+"版");


    }
    @OnClick({R.id.tv_personal_out,R.id.ll_personal_functional_introduction,
            R.id.ll_personal_version_information,R.id.ll_personal_password_modification,
            R.id.iv_personal_head_portrait
    })
    //导航栏点击事件
    public

    void onClick(View v){
        switch (v.getId()){
            //退出登录
            case R.id.tv_personal_out:

                TextView textView= (TextView) v;
                if(textView.getText().toString().equals("去登录")){
                    SharePreferencesUtils.putString(getString(R.string.user_id), "");
                    SharePreferencesUtils.putString(getString(R.string.user_identity), "");
                    SharePreferencesUtils.putString(getString(R.string.user_fullName), "");
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    ((MainActivity) getActivity()).removeALLActivity();
                }
                else if(textView.getText().toString().equals("退出登录")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("退出到登录页面");
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            SharePreferencesUtils.putString(getString(R.string.user_id), "");
                            SharePreferencesUtils.putString(getString(R.string.user_identity), "");
                            SharePreferencesUtils.putString(getString(R.string.user_fullName), "");
                            startActivity(new Intent(getActivity(), LoginActivity.class));
                            ((MainActivity) getActivity()).removeALLActivity();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                break;
                //密码修改
            case R.id.ll_personal_password_modification:

                startActivity(new Intent( getActivity(),PasswordModificationActivity.class));

                break;

            //功能介绍
            case R.id.ll_personal_functional_introduction:
                startActivity(new Intent( getActivity(),FunctionalIntroductionActivity.class));
                break;
            //版本信息
            case R.id.ll_personal_version_information:
//                  Toast.makeText(this,"已是最新版本",Toast.LENGTH_SHORT).show();
                startActivity(new Intent( getActivity(),VersionInformationActivity.class));
                break;
            //头像
            case R.id.iv_personal_head_portrait:

                break;
        }
    }
}
