package collection.suyuan.pdwy.com.suyuancollection.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import collection.suyuan.pdwy.com.suyuancollection.LoginActivity;
import collection.suyuan.pdwy.com.suyuancollection.MainActivity;
import collection.suyuan.pdwy.com.suyuancollection.R;
import collection.suyuan.pdwy.com.suyuancollection.adapter.MyExtendableListViewAdapter;
import collection.suyuan.pdwy.com.suyuancollection.model.EventMessage;
import collection.suyuan.pdwy.com.suyuancollection.model.bean.DataCollectionBean;
import collection.suyuan.pdwy.com.suyuancollection.model.bean.SetDataCollectionBean;
import collection.suyuan.pdwy.com.suyuancollection.utils.MLog;
import collection.suyuan.pdwy.com.suyuancollection.utils.PickerScrollView;
import collection.suyuan.pdwy.com.suyuancollection.utils.SharePreferencesUtils;

/**
 * author : KongLW
 * e-mail : kongluweishujia@163.com
 * date   : 2019/3/5 10:43
 * desc   :  数据采集Fragment
 */
public class  DataAcquisitionFragment extends android.support.v4.app.Fragment {
   private GridView gv_data_acquisition; //数据菜单
    private List<Integer>listImageView;
    private List<String> listName; // 采集数据
    private SimpleAdapter adapter;
    private ExpandableListView ev_data_acquisition;
    private MyExtendableListViewAdapter myExtendableListViewAdapter;
    private TextView tv_dajlbh;
    private String showConetnt; // 滚动选择器选中的值

    private View bt_scrollchoose; // 滚动选择器按钮
    private PickerScrollView pickerscrlllview; // 滚动选择器
    private List<PickerScrollView.Pickers> list; // 滚动选择器数据
    private String[] id;
    private String[] name;

    private Button bt_yes; // 确定按钮
    private LinearLayout picker_rel; // 选择器布局
    private  DataCollectionBean dataCollectionBean;

    private LinearLayout ll_ev_data_acquisition;
    private ImageView fl_iv;
    private Map<String,List<List<String>>> map;
    List<List<String>> listShu;
    List<String> listShuju;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        View view=inflater.inflate(R.layout.fragment_data_acquisition,null);

        inData();

        inView(view);

        return view;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventMessage eventMessage) {
    /*
     * Do something
     * */

       MLog.e(eventMessage.data);
        listShu=new ArrayList<>();
        tv_dajlbh.setText(eventMessage.data);
        tv_dajlbh.setTextColor(Color.parseColor("#424242"));
        if("李晓2019022802".equals(eventMessage.data)){

            ll_ev_data_acquisition.setVisibility(View.VISIBLE);
            fl_iv.setVisibility(View.GONE);
        }else {
            ll_ev_data_acquisition.setVisibility(View.GONE);
            fl_iv.setVisibility(View.VISIBLE);
        }
    }

    private void inView(View view) {


       //设置采集数据变化的 滚轮选择器
        setPickerScrollView(view);
        //设置GridView
        setGridView(view);
        //设置ExpandableListView
setExpandableListView(view);
        //设置PickerScrollView



    }
    //设置采集数据变化的 滚轮选择器
    private void setPickerScrollView(View view) {
        initView(view);
        initLinstener();
        initData();
    }
    private void setExpandableListView(View view) {
        ll_ev_data_acquisition=view.findViewById(R.id.ll_ev_data_acquisition);
        fl_iv=view.findViewById(R.id.fl_iv);
        ev_data_acquisition=view.findViewById(R.id.ev_data_acquisition);
        ev_data_acquisition.setAdapter(myExtendableListViewAdapter);

        ev_data_acquisition.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });


                int itemType = ExpandableListView.getPackedPositionType(id);

                if ( itemType == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
                    int childPosition = ExpandableListView.getPackedPositionChild(id);
                    int groupPosition = ExpandableListView.getPackedPositionGroup(id);
                    builder.setTitle("删除此条记录");
                    AlertDialog dialog = builder.create();
                    dialog.show();
//                    Toast.makeText(getActivity(), "childPosition"+childPosition+"groupPosition"+groupPosition, Toast.LENGTH_SHORT).show();
                    //do your per-item callback here
                    return true; //true if we consumed the click, false if not. consume : 消耗

                } else if(itemType == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
                    int  groupPosition = ExpandableListView.getPackedPositionGroup(id);
                    builder.setTitle("删除此组记录");
                    AlertDialog dialog = builder.create();
                    dialog.show();
//                    Toast.makeText(getActivity(),"groupPosition"+groupPosition, Toast.LENGTH_SHORT).show();
                    //do your per-group callback here
                    return true; //true if we consumed the click, false if not

                } else {
                    // null item; we don't consume the click
                    return false;
                }



            }
        });


    }


    private void setGridView(View view) {
        gv_data_acquisition=view.findViewById(R.id.gv_data_acquisition);


        //为GridView设置适配器
        gv_data_acquisition.setAdapter(adapter);
        gv_data_acquisition.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id1) {

                bt_scrollchoose=view;




                pickerscrlllview.setData( dataCollectionBean.getListString(position));


                showConetnt=dataCollectionBean.getListString(position).get(0).getShowConetnt();
                if(picker_rel.getVisibility()==View.GONE) {
                    picker_rel.setVisibility(View.VISIBLE);

                }
                else if(picker_rel.getVisibility()==View.VISIBLE)
                    picker_rel.setVisibility(View.GONE);
                pickerscrlllview.setSelected(0);
            }
        });
    }

    private void inData() {


        getGridViewDate();

        getExpandableListViewData();
        }


    private void getGridViewDate() {
        //图片数据
        listImageView=new ArrayList<>();
        listImageView.add(R.mipmap.btn01);
        listImageView.add(R.mipmap.btn02);
        listImageView.add(R.mipmap.btn03);
        listImageView.add(R.mipmap.btn04);
        listImageView.add(R.mipmap.btn05);
        listImageView.add(R.mipmap.btn06);
        listImageView.add(R.mipmap.btn07);
        listImageView.add(R.mipmap.btn08);
        listName=new ArrayList<>();
        listName.add("播种");
        listName.add("定植");
        listName.add("整地");
        listName.add("浇水");
        listName.add("采收");
        listName.add("除草");
        listName.add("施肥");
        listName.add("喷药");
        //图片编号



        //初始化数据
        List<Map<String, Object>> data = new ArrayList<>();
        for (int i = 0; i < listImageView.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("ItemImage",listImageView.get(i));
            //如果只需要显示图片，可以不用这一行，需要同时将from和to中的相关内容删去
            map.put("ItemText", listName.get(i));
            data.add(map);
        }

        String[] from = {"ItemImage", "ItemText"};
        int[] to = {R.id.iv_item_gv, R.id.tv_item_gv};
        adapter = new SimpleAdapter(getActivity(), data, R.layout.item_gv_data_acquisition, from, to);

    }

    private void getExpandableListViewData() {
        map=new HashMap<>();



        myExtendableListViewAdapter= new MyExtendableListViewAdapter(getActivity(),map);


    }

    /**
     * 初始化
     * 档案记录编号的pickerscrlllview
     *
     */
    private void initView(View view) {


        tv_dajlbh=view.findViewById(R.id.tv_dajlbh);
        tv_dajlbh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt_scrollchoose=tv_dajlbh;

                list.removeAll(list);
                id = new String[]{"1", "2", "3", "4", "5", "6"};
                name = new String[]{"李晓2019022801", "李晓2019022802", "李晓2019022803", "李晓2019022804", "李晓2019022805", "李晓2019022806"};
                for (int i = 0; i < name.length; i++) {
                    list.add(new PickerScrollView.Pickers(name[i], id[i]));
                }
                pickerscrlllview.setData(list);
                showConetnt=list.get(0).getShowConetnt();
                if(picker_rel.getVisibility()==View.GONE) {
                    picker_rel.setVisibility(View.VISIBLE);

                }
                else if(picker_rel.getVisibility()==View.VISIBLE)
                    picker_rel.setVisibility(View.GONE);

                pickerscrlllview.setSelected(0);
            }
        });
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

    /**
     * 初始化数据
     */
    private void initData() {

        dataCollectionBean= SetDataCollectionBean.setDataCollectionBean();
        list = new ArrayList<>();
        id = new String[] { "0", "1", "2", "3", "4", "5" };
        name = new String[] { "播种1", "播种2", "播种3", "播种4", "播种5", "播种6" };

        for (int i = 0; i < name.length; i++) {
            list.add(new PickerScrollView.Pickers(name[i], id[i]));
        }
        // 设置数据，默认选择第一条
        pickerscrlllview.setData(list);
        pickerscrlllview.setSelected(0);
    }


    /**
     *   // 滚动选择器滑动完成选中事件
     *
     */
    PickerScrollView.onSelectListener pickerListener = new PickerScrollView.onSelectListener() {


        @Override
        public void onSelect(PickerScrollView.Pickers pickers) {
            showConetnt = pickers.getShowConetnt();

            MLog.e("滚轮选择器：=============="+pickers.getShowId()+"---"+showConetnt);
        }
    };



    /**
     *  //  点击监听事件
     *
     */
    View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            pickerscrlllview.setSelected(0);
           if (v == bt_yes) {
                picker_rel.setVisibility(View.GONE);

                if( bt_scrollchoose==tv_dajlbh){
                    listShu=new ArrayList<>();
                    listShu.add(listShuju);
                    tv_dajlbh.setText(showConetnt);
                    tv_dajlbh.setTextColor(Color.parseColor("#424242"));
                    if("李晓2019022802".equals(showConetnt)){

                        ll_ev_data_acquisition.setVisibility(View.VISIBLE);
                        fl_iv.setVisibility(View.GONE);
                    }else {
                        ll_ev_data_acquisition.setVisibility(View.GONE);
                        fl_iv.setVisibility(View.VISIBLE);
                    }

                }else {

                    MLog.e(showConetnt);
                    map.remove(tv_dajlbh.getText().toString());
                    listShuju=new ArrayList<>();
                    listShuju.add(showConetnt);


                    map.put(tv_dajlbh.getText().toString(),listShu);


                }



            }
        }
    };

    /**
     *
     * 解绑EventBus
     */
    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
