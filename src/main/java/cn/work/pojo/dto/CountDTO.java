package cn.work.pojo.dto;

/**
 * @program: libraryOs
 * @description:
 * @author: Aaron Ke
 * @create: 2019-04-11 14:10
 **/
public class CountDTO {
    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "CountDTO{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
