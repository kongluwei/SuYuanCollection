package collection.suyuan.pdwy.com.suyuancollection.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * author : KongLW
 * e-mail : kongluweishujia@163.com
 * date   : 2019/2/28-11:11
 * desc   :
 */
public class MainFragmentAdapter extends FragmentPagerAdapter{
    List<Fragment> list;
    public MainFragmentAdapter(FragmentManager supportFragmentManager, List<Fragment> list) {
        super(supportFragmentManager);
        this.list=list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
