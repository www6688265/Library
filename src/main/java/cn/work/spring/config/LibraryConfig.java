package cn.work.spring.config;

public class LibraryConfig {
    public static final String KEY_SHA = "SHA";//加密私钥

    public static final int limitMonth = 3;//限制借数量（单位：月）
    public static final int borrowMonthPeriod = 1;//一次借书周期(单位：月)

    public static final double ticketFee = 0.5;

    public static final int limitBorrowNum = 10;//最多借书数量

    public static final String initPassword = "88888888";

    public static final String projectPath = System.getProperty("user.dir");

    public static final String projectCachePath = "/ImgTemp";
    public static final String projectUploadPath = "/ImgUpload";

}
