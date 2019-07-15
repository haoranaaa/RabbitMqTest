package com.dhr.bean;


/**
 * @Author: lichao
 * @Description:
 * @Date: Created in 下午4:01 2018/8/24
 * @Modified By:
 */
public class AccompanyInvite {
    private String fromUid; //发起人
    private String toUid; //邀请的人
    private long timeOut; //超时时间
    private String inviteType;

    public String getFromUid() {
        return fromUid;
    }

    public void setFromUid(String fromUid) {
        this.fromUid = fromUid;
    }

    public String getToUid() {
        return toUid;
    }

    public void setToUid(String toUid) {
        this.toUid = toUid;
    }

    public long getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(long timeOut) {
        this.timeOut = timeOut;
    }

    public String getOther(String uid){
        return uid.equals(fromUid) ? toUid : fromUid;
    }

    public String getInviteType() {
        return inviteType;
    }

    public void setInviteType(String inviteType) {
        this.inviteType = inviteType;
    }
}
