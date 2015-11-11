package org.zerock.domain;

import java.util.Date;

/**
 * @author LeeSoohoon
 */
public class MessageVO {

    private Integer mid;
    private String targetid;
    private String sender;
    private String message;
    private Date opendate;
    private Date senddate;

    public Integer getMid() {
        return mid;
    }

    public void setMid(final Integer mid) {
        this.mid = mid;
    }

    public String getTargetid() {
        return targetid;
    }

    public void setTargetid(final String targetid) {
        this.targetid = targetid;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(final String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public Date getOpendate() {
        return opendate;
    }

    public void setOpendate(final Date opendate) {
        this.opendate = opendate;
    }

    public Date getSenddate() {
        return senddate;
    }

    public void setSenddate(final Date senddate) {
        this.senddate = senddate;
    }

    @Override
    public String toString() {
        return "MessageVO{" +
                "mid=" + mid +
                ", targetid='" + targetid + '\'' +
                ", sender='" + sender + '\'' +
                ", message='" + message + '\'' +
                ", opendate=" + opendate +
                ", senddate=" + senddate +
                '}';
    }
}
