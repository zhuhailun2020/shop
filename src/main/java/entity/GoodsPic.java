package entity;

import java.io.Serializable;

/**
 * 旅游线路图片实体类
 */
public class GoodsPic implements Serializable {
    private int pid; // 商品图片id
    private int gid; // 商品id
    private String bigPic; // 详情商品大图
    private String smallPic; // 详情商品小图

    /**
     * 无参构造方法
     */
    public GoodsPic() {
    }


    /**
     * 有参构造方法
     *
     * @param pid
     * @param gid
     * @param bigPic
     * @param smallPic
     */

    public GoodsPic(int pid, int gid, String bigPic, String smallPic) {
        this.pid = pid;
        this.gid = gid;
        this.bigPic = bigPic;
        this.smallPic = smallPic;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getBigPic() {
        return bigPic;
    }

    public void setBigPic(String bigPic) {
        this.bigPic = bigPic;
    }

    public String getSmallPic() {
        return smallPic;
    }

    public void setSmallPic(String smallPic) {
        this.smallPic = smallPic;
    }
    @Override
    public String toString() {
        return "GoodsPic{" +
                "pid=" + pid +
                ", gid=" + gid +
                ", bigPic='" + bigPic + '\'' +
                ", smallPic='" + smallPic + '\'' +
                '}';
    }

}
