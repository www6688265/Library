package cn.work.pojo;

public class Profile {
    int notReturnNum;
    int overDueNum;
    double userFee;
    String username;
    String idcard;
    String sex;
    int access;
    String accessDetail;

    public String getAccessDetail() {
        return accessDetail;
    }

    public void setAccessDetail(String accessDetail) {
        this.accessDetail = accessDetail;
    }

    public double getUserFee() {
        return userFee;
    }

    public void setUserFee(double userFee) {
        this.userFee = userFee;
    }

    public int getNotReturnNum() {
        return notReturnNum;
    }

    public void setNotReturnNum(int notReturnNum) {
        this.notReturnNum = notReturnNum;
    }

    public int getOverDueNum() {
        return overDueNum;
    }

    public void setOverDueNum(int overDueNum) {
        this.overDueNum = overDueNum;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getSex() {
        return sex;
    }

    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

}
