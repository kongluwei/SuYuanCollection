package collection.suyuan.pdwy.com.suyuancollection.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.othershe.calendarview.bean.DateBean;
import com.othershe.calendarview.listener.OnMultiChooseListener;
import com.othershe.calendarview.listener.OnPagerChangeListener;
import com.othershe.calendarview.listener.OnSingleChooseListener;
import com.othershe.calendarview.utils.CalendarUtil;
import com.othershe.calendarview.weiget.CalendarView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import collection.suyuan.pdwy.com.suyuancollection.R;
import collection.suyuan.pdwy.com.suyuancollection.adapter.StatusExpandAdapter;
import collection.suyuan.pdwy.com.suyuancollection.model.entity.OneStatusEntity;
import collection.suyuan.pdwy.com.suyuancollection.model.entity.TwoStatusEntity;
import collection.suyuan.pdwy.com.suyuancollection.utils.MLog;
import collection.suyuan.pdwy.com.suyuancollection.utils.PickerScrollView;


/**
 * author : KongLW
 * e-mail : kongluweishujia@163.com
 * date   : 2019/3/5 11:01
 * desc   :数据查看Fragment
 */
public class DataViewingFragment extends android.support.v4.app.Fragment {

  private   CalendarView calendarView;
    private TextView tv_today;
    private TextView bt_today;
    private TextView tv_dajlbh;

    private String showConetnt; // 滚动选择器选中的值

    private List<OneStatusEntity> oneList;
    private ExpandableListView expandlistView;
    private StatusExpandAdapter statusAdapter;
    private Context context;
    private View bt_scrollchoose; // 滚动选择器按钮
    private PickerScrollView pickerscrlllview; // 滚动选择器
    private List<PickerScrollView.Pickers> list; // 滚动选择器数据
    private String[] id;
    private String[] name;

    private Button bt_yes; // 确定按钮
    private LinearLayout picker_rel; // 选择器布局

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_data_viewing,null);

        inView(view);

        return view;
    }

    private void inView(View view) {
        tv_dajlbh=view.findViewById(R.id.tv_dajlbh);
        tv_dajlbh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt_scrollchoose=tv_dajlbh;
                showConetnt="李晓2019022801";
                list = new ArrayList<PickerScrollView.Pickers>();
                id = new String[]{"1", "2", "3", "4", "5", "6"};
                name = new String[]{"李晓2019022801", "李晓2019022802", "李晓2019022803", "李晓2019022804", "李晓2019022805", "李晓2019022806"};
                for (int i = 0; i < name.length; i++) {
                    list.add(new PickerScrollView.Pickers(name[i], id[i]));
                }

                pickerscrlllview.setData(list);
                if(picker_rel.getVisibility()==View.GONE) {
                    picker_rel.setVisibility(View.VISIBLE);
                    pickerscrlllview.setSelected(0);
                }
                else if(picker_rel.getVisibility()==View.VISIBLE)
                    picker_rel.setVisibility(View.GONE);
            }
        });
        setPickerScrollView(view);



          calendarView = view.findViewById(R.id.calendar);
        tv_today=view.findViewById(R.id.tv_today);
        bt_today=view.findViewById(R.id.bt_today);
        bt_today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calendarView.today();
            }
        });

       int today[]= CalendarUtil.getCurrentDate();
        tv_today.setText(today[0]+"年  "+today[1]+"月");
//日历init，年月日之间用点号隔开
        calendarView
                .setStartEndDate(today[0]+".1", today[0]+".12")
                .setInitDate(today[0]+"."+today[1])
                .setSingleDate(today[0]+"."+today[1]+"."+today[2]).
                setOnPagerChangeListener(new OnPagerChangeListener() {
            @Override
            public void onPagerChanged(int[] date) {

                MLog.e("date========",date);
            }
        });
        calendarView.setOnSingleChooseListener(new OnSingleChooseListener() {
            @Override
            public void onSingleChoose(View view, DateBean dateBean) {
                int date[]=dateBean.getSolar();
                MLog.e("date========"+date[0]+date[1]+date[2],"");


            }
        });
        calendarView.setOnPagerChangeListener(new OnPagerChangeListener () {
            @Override
            public void onPagerChanged(int[] ints) {

                tv_today.setText(ints[0]+"年  "+ints[1]+"月");
                MLog.e("date========"+ints[0]+ints[1]+ints[2]);

            }


        });
        HashMap<String, String> map = new HashMap<>();
        map.put("2019.3.3","浇水");

        calendarView.setSpecifyMap(map);
        calendarView.init();
        context = getActivity();
        expandlistView = (ExpandableListView) view.findViewById(R.id.expandlist);
        putInitData();


        statusAdapter = new StatusExpandAdapter(context, oneList);
        expandlistView.setAdapter(statusAdapter);
        expandlistView.setGroupIndicator(null); // 去掉默认带的箭头

        // 遍历所有group,将所有项设置成默认展开
        int groupCount = expandlistView.getCount();
        for (int i = 0; i < groupCount; i++) {
            expandlistView.expandGroup(i);
        }
        expandlistView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                // TODO Auto-generated method stub
                return true;
            }
        });
    }

    private void putInitData() {
        String[] strArray = new String[]{"浇水", "施肥", "喷药", "施肥", "喷药"};
        String[] str1 = new String[]{"浇水1", "浇水2", "浇水3", "浇水4"};
        String[] str2 = new String[]{"施肥1", "施肥2", "施肥3", "施肥4", "施肥5"};
        String[] str3 = new String[]{"喷药1", "喷药2", "喷药3", "喷药4"};
        String[] str4 = new String[]{"施肥1", "施肥2", "施肥3", "施肥4", "施肥5"};
        String[] str5 = new String[]{"喷药1", "喷药2", "喷药3", "喷药4"};

        String[] timeStr1 = new String[]{"2013-11-02 13:16:22", "2013-11-02 13:16:22", "2013-11-02 13:16:22", "2013-11-02 13:16:22"};
        String[] timeStr2 = new String[]{"2013-11-02 13:16:22", "2013-11-02 13:16:22", "2013-11-02 13:16:22", "2013-11-02 12:16:22", ""};
        String[] timeStr3 = new String[]{"", "", "", ""};
        String[] timeStr4 = new String[]{"2013-11-02 13:16:22", "2013-11-02 13:16:22", "2013-11-02 13:16:22", "2013-11-02 12:16:22", ""};
        String[] timeStr5 = new String[]{"", "", "", ""};

        oneList = new ArrayList<OneStatusEntity>();
        for(int i=0 ; i<strArray.length ; i++){
            OneStatusEntity one = new OneStatusEntity();
            one.setStatusName(strArray[i]);
            List<TwoStatusEntity> twoList = new ArrayList<TwoStatusEntity>();
            String[] order = str1;
            String[] time = timeStr1;
            switch (i) {
                case 0:
                    order = str1;
                    time = timeStr1;

                    break;
                case 1:
                    order = str2;
                    time = timeStr2;

                    break;
                case 2:
                    order = str3;
                    time = timeStr3;

                    break;
                case 3:
                    order = str4;
                    time = timeStr4;

                    break;
                case 4:
                    order = str5;
                    time = timeStr5;

                    break;
            }

            for(int j=0 ; j<order.length ; j++){
                TwoStatusEntity two = new TwoStatusEntity();
                two.setStatusName(order[j]);
                if(time[j].equals("")){
                    two.setCompleteTime("");
                    two.setIsfinished(false);
                }else{
                    two.setCompleteTime("");
                    two.setIsfinished(false);
                }

                twoList.add(two);
            }
            one.setTwoList(twoList);
            oneList.add(one);
        }

        MLog.e("二级状态："+oneList.get(0).getTwoList().get(0).getStatusName());

    }
    private void setPickerScrollView(View view) {
        initView(view);
        initLinstener();
        initData();
    }
    /**
     * 初始化
     */
    private void initView(View view) {
//        bt_scrollchoose = (Button) view.findViewById(R.id.bt_scrollchoose);
        picker_rel = (LinearLayout) view.findViewById(R.id.ll_pickerscrlllview);
        pickerscrlllview = (PickerScrollView) view.findViewById(R.id.pickerscrlllview);
        bt_yes = (Button) view.findViewById(R.id.bt_picker_yes);
    }

    /**
     * 设置监听事件
     */
    private void initLinstener() {
//        bt_scrollchoose.setOnClickListener(onClickListener);
        pickerscrlllview.setOnSelectListener(pickerListener);
        bt_yes.setOnClickListener(onClickListener);
    }
    private void initData() {

        list = new ArrayList<>();
        id = new String[] { "1", "2", "3", "4", "5", "6" };
        name = new String[] { "播种1", "播种2", "播种3", "播种4", "播种5", "播种6" };
        for (int i = 0; i < name.length; i++) {
            list.add(new PickerScrollView.Pickers(name[i], id[i]));
        }
        // 设置数据，默认选择第一条
        pickerscrlllview.setData(list);
        pickerscrlllview.setSelected(0);
    }

    // 滚动选择器选中事件
    PickerScrollView.onSelectListener pickerListener = new PickerScrollView.onSelectListener() {


        @Override
        public void onSelect(PickerScrollView.Pickers pickers) {
            showConetnt = pickers.getShowConetnt();

            MLog.e("滚轮选择器：=============="+showConetnt);
        }
    };

    // 点击监听事件
    View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            if (v == bt_scrollchoose) {
                picker_rel.setVisibility(View.VISIBLE);
            } else if (v == bt_yes) {
                picker_rel.setVisibility(View.GONE);
                if( bt_scrollchoose==tv_dajlbh){
                    tv_dajlbh.setText(showConetnt);
                    tv_dajlbh.setTextColor(Color.parseColor("#424242"));
                }
            }
        }
    };
}
