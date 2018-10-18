package cn.work.pojo;

public class Admin {
    private Integer admid;

    private String idcard;

    private String admpassword;

    private Integer admright;

    public Integer getAdmid() {
        return admid;
    }

    public void setAdmid(Integer admid) {
        this.admid = admid;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getAdmpassword() {
        return admpassword;
    }

    public void setAdmpassword(String admpassword) {
        this.admpassword = admpassword == null ? null : admpassword.trim();
    }

    public Integer getAdmright() {
        return admright;
    }

    public void setAdmright(Integer admright) {
        this.admright = admright;
    }
}