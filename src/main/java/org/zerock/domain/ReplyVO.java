package org.zerock.domain;

import java.util.Date;

/**
 * @author LeeSoohoon
 */
public class ReplyVO {

    private Integer rno;
    private Integer bno;
    private String replytext;
    private String replyer;

    private Date regDate;
    private Date updatedate;

    public Integer getRno() {
        return rno;
    }

    public void setRno(final Integer rno) {
        this.rno = rno;
    }

    public Integer getBno() {
        return bno;
    }

    public void setBno(final Integer bno) {
        this.bno = bno;
    }

    public String getReplytext() {
        return replytext;
    }

    public void setReplytext(final String replytext) {
        this.replytext = replytext;
    }

    public String getReplyer() {
        return replyer;
    }

    public void setReplyer(final String replyer) {
        this.replyer = replyer;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(final Date regDate) {
        this.regDate = regDate;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(final Date updatedate) {
        this.updatedate = updatedate;
    }

    @Override
    public String toString() {
        return "ReplyVO{" +
                "rno=" + rno +
                ", bno=" + bno +
                ", replytext='" + replytext + '\'' +
                ", replyer='" + replyer + '\'' +
                ", regDate=" + regDate +
                ", updatedate=" + updatedate +
                '}';
    }
}
