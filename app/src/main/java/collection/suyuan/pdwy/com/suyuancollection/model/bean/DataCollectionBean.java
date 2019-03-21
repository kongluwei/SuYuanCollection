package collection.suyuan.pdwy.com.suyuancollection.model.bean;

import java.util.ArrayList;
import java.util.List;

import collection.suyuan.pdwy.com.suyuancollection.utils.MLog;
import collection.suyuan.pdwy.com.suyuancollection.utils.PickerScrollView;

/**
 * author : KongLW
 * e-mail : kongluweishujia@163.com
 * date   : 2019/3/810:47
 * desc   :数据采集bean
 */
public class DataCollectionBean {
    /** 播种 **/
        private List<PickerScrollView.Pickers> bozhong;
    /** 定植 **/
    private List<PickerScrollView.Pickers> dingzhi;
    /** 整地 **/
    private List<PickerScrollView.Pickers> zhengdi;
    /** 浇水 **/
    private List<PickerScrollView.Pickers> jiaoshui;
    /** 采收 **/
    private List<PickerScrollView.Pickers> caishou;
    /** 除草 **/
    private List<PickerScrollView.Pickers> chucao;
    /** 施肥 **/
    private List<PickerScrollView.Pickers> shifei;
    /** 喷药 **/
    private List<PickerScrollView.Pickers> penyao;


    public List<PickerScrollView.Pickers> getBozhong() {
        return bozhong;
    }

    public void setBozhong(List<PickerScrollView.Pickers> bozhong) {
        this.bozhong = bozhong;
    }

    public List<PickerScrollView.Pickers> getDingzhi() {
        return dingzhi;
    }

    public void setDingzhi(List<PickerScrollView.Pickers> dingzhi) {
        this.dingzhi = dingzhi;
    }

    public List<PickerScrollView.Pickers> getZhengdi() {
        return zhengdi;
    }

    public void setZhengdi(List<PickerScrollView.Pickers> zhengdi) {
        this.zhengdi = zhengdi;
    }

    public List<PickerScrollView.Pickers> getJiaoshui() {
        return jiaoshui;
    }

    public void setJiaoshui(List<PickerScrollView.Pickers> jiaoshui) {
        this.jiaoshui = jiaoshui;
    }

    public List<PickerScrollView.Pickers> getCaishou() {
        return caishou;
    }

    public void setCaishou(List<PickerScrollView.Pickers> caishou) {
        this.caishou = caishou;
    }

    public List<PickerScrollView.Pickers> getChucao() {
        return chucao;
    }

    public void setChucao(List<PickerScrollView.Pickers> chucao) {
        this.chucao = chucao;
    }

    public List<PickerScrollView.Pickers> getShifei() {
        return shifei;
    }

    public void setShifei(List<PickerScrollView.Pickers> shifei) {
        this.shifei = shifei;
    }

    public List<PickerScrollView.Pickers> getPenyao() {
        return penyao;
    }

    public void setPenyao(List<PickerScrollView.Pickers> penyao) {
        this.penyao = penyao;
    }
    public List<PickerScrollView.Pickers> getListString(int position){

        List<PickerScrollView.Pickers> list =new ArrayList<>();
        switch (position){
            case 0:

                list= bozhong;

                break;
            case 1:

                list= dingzhi;
                break;
            case 2:

                list= zhengdi;
                break;
            case 3:

                list= jiaoshui;
                break;
            case 4:

                list= caishou;
                break;
            case 5:

                list= chucao;
                break;
            case 6:

                list= shifei;
                break;
            case 7:

                list= penyao;
                break;
        }

//        for(PickerScrollView.Pickers p:list)
//            MLog.e("PickerScrollView.Pickers============"+p.getShowId()+"-------"+p.getShowConetnt());
      return   list;
    }
}
