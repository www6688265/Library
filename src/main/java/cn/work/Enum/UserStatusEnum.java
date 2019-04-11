package cn.work.Enum;

/**
 * @program: libraryOs
 * @description:
 * @author: Aaron Ke
 * @create: 2019-04-11 13:14
 **/
public enum UserStatusEnum {
    NORMAL("1", "表示用户状态正常"), MAXBORROWNUM("2", "用户已经达到最大借书数量"), NODEALTICKET("3", "用户有未处理得罚单"),
    NOTEXIST("4", "用户不存在"), HASOVERDUEBOOK("5", "有过期未还图书"), FREEZEUSER("6", "该用户被冻结"), NONEEDTORETURN("7", "无需归还图书");

    private String code;
    private String name;

    UserStatusEnum(String code, String name) {
        this.setCode(code);
        this.setName(name);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
