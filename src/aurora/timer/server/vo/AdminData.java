package aurora.timer.server.vo;

import java.sql.Time;

public class AdminData {
    private String announcement;
    private String dutylist;
    private Time freeTimeStart;
    private Time freeTimeEnd;

    public AdminData(){

    }

    public AdminData(String announcement, String dutylist) {
        this.announcement = announcement;
        this.dutylist = dutylist;
    }

    /**
     * 获取公告内容
     * @return 返回公告内容
     */
    public String getAnnouncement() {
        return announcement;
    }

    /**
     * 设置公告内容
     * @param announcement 公告内容
     */
    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    /**
     * 获取值日表
     * @return 返回值日表
     */
    public String getDutylist() {
        return dutylist;
    }

    /**
     * 设置值日表
     * @param dutylist 值日表
     */
    public void setDutylist(String dutylist) {
        this.dutylist = dutylist;
    }

    /**
     * 获取放挂计时开始时间
     * @return 放挂计时开始时间
     */
    public Time getFreeTimeStart() {
        return freeTimeStart;
    }

    /**
     * 设置放挂计时开始时间
     *
     */
    public void setFreeTimeStart(Time freeTimeStart) {
        this.freeTimeStart = freeTimeStart;
    }
    /**
     * 获取放挂计时结束时间
     * @return 放挂计时结束时间
     */
    public Time getFreeTimeEnd() {
        return freeTimeEnd;
    }
    /**
     * 设置放挂计时结束时间
     *
     */
    public void setFreeTimeEnd(Time freeTimeEnd) {
        this.freeTimeEnd = freeTimeEnd;
    }
}
