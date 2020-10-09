package entity;

import java.io.Serializable;

public class User implements Serializable {
    //序列号
    private static final long serialVersionUID = 1315782936L;

    private int uid;
    private String uname;
    private String password;
    private String address;
    private String sex;
    private String phone;
    private String email;
    private String code;
    private String status;

    public User() {
    }

    public User(int uid, String uname, String password, String address, String sex, String phone, String email, String code, String status) {
        this.uid = uid;
        this.uname = uname;
        this.password = password;
        this.address = address;
        this.sex = sex;
        this.phone = phone;
        this.email = email;
        this.code = code;
        this.status = status;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", code='" + code + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
