package entity;

import java.io.Serializable;

public class Label implements Serializable {
    private Integer lid;
    private String  lname;
    public Label() {
    }

    public Label(Integer lid, String lname) {
        this.lid = lid;
        this.lname = lname;
    }

    @Override
    public String toString() {
        return "Label{" +
                "lid=" + lid +
                ", lname='" + lname + '\'' +
                '}';
    }

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
}
