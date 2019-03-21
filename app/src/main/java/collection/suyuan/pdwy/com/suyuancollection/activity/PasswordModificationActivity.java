package collection.suyuan.pdwy.com.suyuancollection.activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import collection.suyuan.pdwy.com.suyuancollection.R;
import collection.suyuan.pdwy.com.suyuancollection.core.BaseActivity;

/**
 * author : KongLW
 * e-mail : kongluweishujia@163.com
 * date   : 2019/3/415:28
 * desc   :
 */
public class PasswordModificationActivity extends BaseActivity{

    @BindView(R.id.ll_head_return)
    LinearLayout ll_head_return;
    @BindView(R.id.tv_head_title)
    TextView tv_head_title;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_password_modification;
    }

    @Override
    protected void onCreateAfter() {
           inData();
          inView();
    }

    private void inData() {
    }

    private void inView() {
        ll_head_return.setVisibility(View.VISIBLE);
        tv_head_title.setVisibility(View.VISIBLE);
        tv_head_title.setText("密码设置");
    }
}
