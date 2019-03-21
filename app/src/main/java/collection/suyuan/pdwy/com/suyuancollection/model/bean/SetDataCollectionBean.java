package collection.suyuan.pdwy.com.suyuancollection.model.bean;

import java.util.ArrayList;
import java.util.List;

import collection.suyuan.pdwy.com.suyuancollection.utils.MLog;
import collection.suyuan.pdwy.com.suyuancollection.utils.PickerScrollView;

/**
 * author : KongLW
 * e-mail : kongluweishujia@163.com
 * date   : 2019/3/810:50
 * desc   :
 */
public class SetDataCollectionBean {

    private static DataCollectionBean dataCollectionBean;
    private static List<PickerScrollView.Pickers> listString;
    public static DataCollectionBean setDataCollectionBean(){
        dataCollectionBean=new DataCollectionBean();
        listString=new ArrayList<>();
        setListString();
        return dataCollectionBean;
    }
    public static void setListString(){
        listString=new ArrayList<>();
        for (int i=0;i<10;i++)
        listString.add(new PickerScrollView.Pickers("播种00"+i,String.valueOf(i)));
        dataCollectionBean.setBozhong(listString);




        listString=new ArrayList<>();
        for (int i=0;i<10;i++)
            listString.add(new PickerScrollView.Pickers("定植00"+i,String.valueOf(i)));
        dataCollectionBean.setDingzhi(listString);





        listString=new ArrayList<>();
        for (int i=0;i<10;i++)
            listString.add(new PickerScrollView.Pickers("整地00"+i,String.valueOf(i)));
        dataCollectionBean.setZhengdi(listString);




        listString=new ArrayList<>();
        for (int i=0;i<10;i++)
            listString.add(new PickerScrollView.Pickers("浇水00"+i,String.valueOf(i)));
        dataCollectionBean.setJiaoshui(listString);



        listString=new ArrayList<>();
        for (int i=0;i<10;i++)
            listString.add(new PickerScrollView.Pickers("采收00"+i,String.valueOf(i)));
        dataCollectionBean.setCaishou(listString);



        listString=new ArrayList<>();
        for (int i=0;i<10;i++)
            listString.add(new PickerScrollView.Pickers("除草00"+i,String.valueOf(i)));
        dataCollectionBean.setChucao(listString);



        listString=new ArrayList<>();
        for (int i=0;i<10;i++)
            listString.add(new PickerScrollView.Pickers("施肥00"+i,String.valueOf(i)));
        dataCollectionBean.setShifei(listString);





        listString=new ArrayList<>();
        for (int i=0;i<10;i++)
            listString.add(new PickerScrollView.Pickers("喷药00"+i,String.valueOf(i)));
        dataCollectionBean.setPenyao(listString);



    }

}
