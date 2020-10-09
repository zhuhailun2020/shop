package entity;

import java.util.Date;

public class FeedbackMail {
    private int eid;
    private Date time;
    private String ename;
    private String email;
    private String subject;
    private String message;

    public FeedbackMail() {
    }

    public FeedbackMail(int eid, Date time, String ename, String email, String subject, String message) {
        this.eid = eid;
        this.time = time;
        this.ename = ename;
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
