package entity;

public class Brand {
    private Integer bid;
    private String  bname;

    public Brand() {
    }

    private String  bimage;

    public Brand(Integer bid, String bname, String bimage) {
        this.bid = bid;
        this.bname = bname;
        this.bimage = bimage;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "bid=" + bid +
                ", bname='" + bname + '\'' +
                ", bimage='" + bimage + '\'' +
                '}';
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getBimage() {
        return bimage;
    }

    public void setBimage(String bimage) {
        this.bimage = bimage;
    }
}
