package collection.suyuan.pdwy.com.suyuancollection.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import collection.suyuan.pdwy.com.suyuancollection.R;
import collection.suyuan.pdwy.com.suyuancollection.core.BaseActivity;
import collection.suyuan.pdwy.com.suyuancollection.fragment.TaskInformationFragment;
import collection.suyuan.pdwy.com.suyuancollection.model.EventMessage;

/**
 * author : KongLW
 * e-mail : kongluweishujia@163.com
 * date   : 2019/3/110:45
 * desc   :
 */
public class TaskBasicInformationActivity extends BaseActivity{
    @BindView(R.id.ll_head_return)
    LinearLayout ll_head_return;
    @BindView(R.id.tv_head_title)
    TextView tv_head_title;
    @BindView(R.id.tv_scdabhh)
    TextView tv_scdabhh;

    @BindView(R.id.banner_task_basic)
    Banner banner_task_basic;
    private MyImageLoader mMyImageLoader;
    private ArrayList<Integer> imagePath;
    private ArrayList<String> imagePath1;
    private ArrayList<String> imageTitle;
    private Intent intent;

    @Override
    protected boolean userEventBus() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_task_basic_information;
    }

    @Override
    protected void onCreateAfter() {

        getData();
        setView();

    }

    private void getData() {

        getBannerData();
         intent=getIntent();
    }

    private void setView() {
        ll_head_return.setVisibility(View.VISIBLE);

        tv_head_title.setVisibility(View.VISIBLE);
        tv_head_title.setText("任务基本信息");
        tv_scdabhh.setText(intent.getStringExtra("TaskBasicInformation"));
        setBanner();

    }

    //获取Banner数据源
    private void getBannerData() {
        imagePath = new ArrayList<>();
        imageTitle = new ArrayList<>();
        imagePath1= new ArrayList<>();
        imagePath.add(R.mipmap.img2);
        imagePath.add(R.mipmap.img1);
        imagePath.add(R.mipmap.img2);
        imagePath1.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1533566443368&di=b056ce1f74ee9a2d4fd910866c21de39&imgtype=0&src=http://p5.pccoo.cn/news/20160720/2016072010271681587483.png");
        imagePath1.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1533566443368&di=b056ce1f74ee9a2d4fd910866c21de39&imgtype=0&src=http://p5.pccoo.cn/news/20160720/2016072010271681587483.png");
        imagePath1.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1533566443368&di=b056ce1f74ee9a2d4fd910866c21de39&imgtype=0&src=http://p5.pccoo.cn/news/20160720/2016072010271681587483.png");
//        imageTitle.add("关于确定2018-2020年中山市政策性农业保险承办机构的通知");

//        imageTitle.add("沙漠");
        imageTitle.add("");
        imageTitle.add("");
        imageTitle.add("");
    }
    //设置轮播图
    private void setBanner() {
        mMyImageLoader = new MyImageLoader();
        //设置样式，里面有很多种样式可以自己都看看效果
        banner_task_basic.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器
        banner_task_basic.setImageLoader(mMyImageLoader);
        //设置轮播的动画效果,里面有很多种特效,可以都看看效果。
        banner_task_basic.setBannerAnimation(Transformer.ZoomOutSlide);
        //轮播图片的文字
        banner_task_basic.setBannerTitles(imageTitle);

        //设置轮播间隔时间
        banner_task_basic.setDelayTime(5000);
        //设置是否为自动轮播，默认是true
        banner_task_basic.isAutoPlay(true);
        //设置指示器的位置，小点点，居中显示
        banner_task_basic.setIndicatorGravity(BannerConfig.RIGHT);
        //设置图片加载地址
        banner_task_basic.setImages(imagePath)
                //轮播图的监听
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
//                        Intent intentClick=new Intent(this,NoticeGgXqActivity.class);
//                        intentClick.putExtra("bt",imageTitle.get(position));
//                        startActivity(intentClick);

                    }
                })
                //开始调用的方法，启动轮播图。；
                .start();
    }
    @OnClick({R.id.tv_data_acquisition})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.tv_data_acquisition:
                EventBus.getDefault().post(new EventMessage(getIntent().getStringExtra("TaskBasicInformation")));
                 setResult(1);
                finishActivity( );
                break;

        }
    }

    /**
     * 图片加载类
     */
    private class MyImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load(path)
                    .into(imageView);
        }
    }
}
