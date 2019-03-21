package collection.suyuan.pdwy.com.suyuancollection.model.bean;

/**
 * author : KongLW
 * e-mail : kongluweishujia@163.com
 * date   : 2019/2/2816:03
 * desc   : 任务bean
 */
public class TaskInformationBean {
    //任务Id
    private String taskId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    //任务名称
    private String taskName;
    //任务日期
    private String taskDate;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }
}
