package collection.suyuan.pdwy.com.suyuancollection;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;
import collection.suyuan.pdwy.com.suyuancollection.adapter.MainFragmentAdapter;
import collection.suyuan.pdwy.com.suyuancollection.core.BaseActivity;
import collection.suyuan.pdwy.com.suyuancollection.fragment.DataAcquisitionFragment;
import collection.suyuan.pdwy.com.suyuancollection.fragment.DataViewingFragment;
import collection.suyuan.pdwy.com.suyuancollection.fragment.PersonalCenterFragment;
import collection.suyuan.pdwy.com.suyuancollection.fragment.TaskInformationFragment;
import collection.suyuan.pdwy.com.suyuancollection.model.EventMessage;
import collection.suyuan.pdwy.com.suyuancollection.utils.MLog;

public class MainActivity extends BaseActivity {
    private String mTitle;
    private static boolean mBackKeyPressed = false;//记录是否有首次按键
    @BindView(R.id.tv_head_title)
    TextView tv_head_title;


    @BindView(R.id.csvp_main)
    ViewPager csvp_main;
    private List<Fragment> list;

    private LinearLayout ll_p;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }






    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreateAfter() {
        tv_head_title.setVisibility(View.VISIBLE);
        tv_head_title.setText(getString(R.string.app_name));
        tv_head_title.setText("任务信息");
        inView();
    }



    private void inView() {
        list=new ArrayList<>();
        //任务信息   默认是这个
        list.add(new TaskInformationFragment());
        //数据采集
        list.add(new DataAcquisitionFragment());
        //数据查看
        list.add(new DataViewingFragment());
        //个人中心
        list.add(new PersonalCenterFragment());
        //初始化adapter
        MainFragmentAdapter adapter = new MainFragmentAdapter(getSupportFragmentManager(), list);
        //将适配器和ViewPager结合

        csvp_main.setAdapter(adapter);
        //预加载数量
        csvp_main.setOffscreenPageLimit(3);

        //设置默认值为第一个
        csvp_main.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){

                    case 0:

                        setLk((LinearLayout) findViewById(R.id.tv_main_task));
                        tv_head_title.setText("任务信息");
                        break;
                    case 1:

                        setLk((LinearLayout) findViewById(R.id.tv_main_collection));
                        tv_head_title.setText("数据采集");
                        break;
                    case 2:

                        setLk((LinearLayout) findViewById(R.id.tv_main_see));
                        tv_head_title.setText("数据查看");
                        break;
                    case 3:

                        setLk((LinearLayout) findViewById(R.id.ll_head_personalcenter));
                        tv_head_title.setText("个人中心");
                        break;

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        csvp_main.setCurrentItem(0);
        ll_p=(LinearLayout) findViewById(R.id.tv_main_task);
    }

    @OnClick({R.id.tv_main_task,R.id.tv_main_collection,R.id.tv_main_see,R.id.ll_head_personalcenter})
    public void voidOnViewClock(View v){

             switch (v.getId()){

                 case R.id.tv_main_task:

                     tv_head_title.setText("任务信息");
                     csvp_main.setCurrentItem(0);
                     break;
                 case R.id.tv_main_collection:
                     tv_head_title.setText("数据采集");
                     csvp_main.setCurrentItem(1);
                     break;
                 case R.id.tv_main_see:
                     tv_head_title.setText("数据查看");
                     csvp_main.setCurrentItem(2);
                     break;
                 case R.id.ll_head_personalcenter:
                     tv_head_title.setText("个人中心");
                     csvp_main.setCurrentItem(3);
                     break;

             }
        setLk((LinearLayout) v);
    }


    //选择器切换
    private void setLk(LinearLayout ll) {
        if (ll.equals(ll_p))
            return;
        TextView tvxz= (TextView) ll_p.getChildAt(1);
        tvxz.setTextColor(Color.parseColor("#C6C6C6"));
        TextView tvlx= (TextView) ll.getChildAt(1);
        tvlx.setTextColor(Color.parseColor("#424242"));
        TextView tvxz0= (TextView) ll_p.getChildAt(0);
        TextView tvlx0= (TextView) ll.getChildAt(0);

        switch (tvlx.getText().toString()) {


            case "任务信息":
                tvlx0.setBackgroundDrawable(getDrawableMipmap(R.mipmap.foot_icon01on));
                break;
            case "数据采集":
                tvlx0.setBackgroundDrawable(getDrawableMipmap(R.mipmap.foot_icon02on));
                break;
            case "数据查看":
                tvlx0.setBackgroundDrawable(getDrawableMipmap(R.mipmap.foot_icon03on));
                break;
            case "个人中心":
                tvlx0.setBackgroundDrawable(getDrawableMipmap(R.mipmap.foot_icon04on));
                break;

        }
        switch (tvxz.getText().toString()) {


            case "任务信息":
                tvxz0.setBackgroundDrawable(getDrawableMipmap(R.mipmap.foot_icon01));
                break;
            case "数据采集":
                tvxz0.setBackgroundDrawable(getDrawableMipmap(R.mipmap.foot_icon02));
                break;
            case "数据查看":
                tvxz0.setBackgroundDrawable(getDrawableMipmap(R.mipmap.foot_icon03));
                break;
            case "个人中心":
                tvxz0.setBackgroundDrawable(getDrawableMipmap(R.mipmap.foot_icon04));
                break;

        }
        ll_p=ll;
    }



    public void removeALLActivity() {
        super.removeALLActivity();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
           MLog.e(requestCode+"-----"+resultCode);
          if(resultCode==1) {
              tv_head_title.setText("数据采集");
              csvp_main.setCurrentItem(1);

          }

    }

    @Override
    public void onBackPressed() {
    if(!mBackKeyPressed){
Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
mBackKeyPressed = true;
new Timer().schedule(new TimerTask() {//延时两秒，如果超出则擦错第一次按键记录
@Override
public void run() {
mBackKeyPressed = false;
}
}, 2000);
}
else{//退出程序
        removeALLActivity();

}

    }

}
