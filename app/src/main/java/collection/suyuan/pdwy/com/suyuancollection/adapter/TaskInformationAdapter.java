package collection.suyuan.pdwy.com.suyuancollection.adapter;



import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import collection.suyuan.pdwy.com.suyuancollection.R;
import collection.suyuan.pdwy.com.suyuancollection.core.BaseCommonAdapter;
import collection.suyuan.pdwy.com.suyuancollection.model.bean.TaskInformationBean;
import collection.suyuan.pdwy.com.suyuancollection.utils.MLog;


/**
 * author : KongLW
 * e-mail : kongluweishujia@163.com
 * date   : 2019/2/2815:56
 * desc   :
 */
public class TaskInformationAdapter extends BaseCommonAdapter {

    public TaskInformationAdapter(Context context, List list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=inflater.inflate(R.layout.item_listview_task_information,null);
        TaskInformationBean taskInformationBean= (TaskInformationBean) list.get(position);
        TextView tv_taskId=convertView.findViewById(R.id.tv_taskId);
        tv_taskId.setText(taskInformationBean.getTaskId());
        TextView tv_taskName=convertView.findViewById(R.id.tv_taskName);
        tv_taskName.setText(taskInformationBean.getTaskName());
        TextView tv_taskDate=convertView.findViewById(R.id.tv_taskDate);
        tv_taskDate.setText(taskInformationBean.getTaskDate());

        return convertView;
    }
}
