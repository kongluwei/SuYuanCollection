package collection.suyuan.pdwy.com.suyuancollection.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import collection.suyuan.pdwy.com.suyuancollection.MainActivity;
import collection.suyuan.pdwy.com.suyuancollection.R;
import collection.suyuan.pdwy.com.suyuancollection.activity.TaskBasicInformationActivity;
import collection.suyuan.pdwy.com.suyuancollection.adapter.TaskInformationAdapter;
import collection.suyuan.pdwy.com.suyuancollection.model.EventMessage;
import collection.suyuan.pdwy.com.suyuancollection.model.bean.TaskInformationBean;
import collection.suyuan.pdwy.com.suyuancollection.utils.CustomScrollViewPager;
import collection.suyuan.pdwy.com.suyuancollection.utils.MLog;

/**
 * author : KongLW
 * e-mail : kongluweishujia@163.com
 * date   : 2019/2/27-18:00
 * desc   :任务信息Fragment
 */
public class TaskInformationFragment extends Fragment {

    Banner mBanner;
    private MyImageLoader mMyImageLoader;
    private ArrayList<Integer> imagePath;
    private ArrayList<String> imagePath1;
    private ArrayList<String> imageTitle;
    private List<TaskInformationBean> list;
    ListView lv_main;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_taskinformation,null);

        initData();
        inView(view);

        return view;
    }



    // 数据初始化
    private void initData() {
      setBannerData();
      setListViewData();

    }
    //获取listview数据源
    private void setListViewData() {
        list =new ArrayList<>();
        for (int i=1;i<7;i++) {

            TaskInformationBean taskInformationBean=new TaskInformationBean();
            //数据格式化
            if(i<10) {
                taskInformationBean.setTaskId(new DecimalFormat("00").format(i));
                taskInformationBean.setTaskName("李晓201902280"+i);
            }else {
                taskInformationBean.setTaskId(i + "");
                taskInformationBean.setTaskName("李晓20190228" + i);
            }
            taskInformationBean.setTaskDate("2019-02-28");
            list.add(taskInformationBean);
        }
    }
    //获取Banner数据源
    private void setBannerData() {
        imagePath = new ArrayList<>();
        imageTitle = new ArrayList<>();
        imagePath1= new ArrayList<>();
        imagePath.add(R.mipmap.img1);
//        imagePath.add(R.mipmap.shamo);
//        imagePath.add(R.mipmap.juhua);
//        imagePath1.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1533566443368&di=b056ce1f74ee9a2d4fd910866c21de39&imgtype=0&src=http://p5.pccoo.cn/news/20160720/2016072010271681587483.png");
//        imagePath1.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1533566443368&di=b056ce1f74ee9a2d4fd910866c21de39&imgtype=0&src=http://p5.pccoo.cn/news/20160720/2016072010271681587483.png");
//        imagePath1.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1533566443368&di=b056ce1f74ee9a2d4fd910866c21de39&imgtype=0&src=http://p5.pccoo.cn/news/20160720/2016072010271681587483.png");
//        imageTitle.add("关于确定2018-2020年中山市政策性农业保险承办机构的通知");

//        imageTitle.add("沙漠");
        imageTitle.add("");
    }

    // view 初始化
    private void inView(View view) {

        mBanner = view.findViewById(R.id.banner);
        setBanner();
        lv_main=view.findViewById(R.id.lv_main);
        setListView();

    }

    //设置listview
    private void setListView() {

        lv_main.setAdapter(new TaskInformationAdapter(getActivity(),list));
        lv_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent=new Intent(getActivity(),TaskBasicInformationActivity.class);
                    intent.putExtra("TaskBasicInformation",list.get(position).getTaskName());

                           startActivityForResult(intent,1);
            }
        });
    }



    //设置轮播图
    private void setBanner() {
        mMyImageLoader = new MyImageLoader();
        //设置样式，里面有很多种样式可以自己都看看效果
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器
        mBanner.setImageLoader(mMyImageLoader);
        //设置轮播的动画效果,里面有很多种特效,可以都看看效果。
        mBanner.setBannerAnimation(Transformer.ZoomOutSlide);
        //轮播图片的文字
        mBanner.setBannerTitles(imageTitle);

        //设置轮播间隔时间
        mBanner.setDelayTime(5000);
        //设置是否为自动轮播，默认是true
        mBanner.isAutoPlay(true);
        //设置指示器的位置，小点点，居中显示
        mBanner.setIndicatorGravity(BannerConfig.RIGHT);
        //设置图片加载地址
        mBanner.setImages(imagePath)
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
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventMessage eventMessage) {
    /*
     * Do something
     * */
        MLog.e(eventMessage.data);

    }


}
