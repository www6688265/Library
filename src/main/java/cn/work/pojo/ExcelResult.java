package cn.work.pojo;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @program: libraryOs
 * @description: Excel导入结果
 * @author: Aaron Ke
 * @create: 2018-11-13 11:57
 **/
public class ExcelResult {

    private HashMap<String, Object> result;
    private HashMap<Integer, String> errorList;
    private int passRowNum = 0;
    private int sumRowNum = 0;

    public ExcelResult() {
        this.result = new HashMap<>();
        this.errorList = new HashMap<>();
    }

    public void setResult(HashMap<String, Object> result) {
        this.result = result;
    }

    public HashMap<Integer, String> getErrorList() {
        return errorList;
    }

    public void setErrorList(HashMap<Integer, String> errorList) {
        this.errorList = errorList;
    }

    public int getPassRowNum() {
        return passRowNum;
    }

    public void setPassRowNum(int passRowNum) {
        this.passRowNum = passRowNum;
    }

    public int getSumRowNum() {
        return sumRowNum;
    }

    public void setSumRowNum(int sumRowNum) {
        this.sumRowNum = sumRowNum;
    }

    public void addError(String error) {
        this.result.put("result", "error");
        this.result.put("msg", error);
    }

    public HashMap<String, Object> getResult() {
        result.put("errorList", errorList);
        result.put("passRowNum", passRowNum);
        result.put("errorRowNum", sumRowNum - passRowNum);
        result.put("sumRowNum", sumRowNum);
        return result;
    }

    public void addRowError(int row, String error) {
        if (this.errorList.get(row) == null) {
            this.errorList.put(row, "第" + row + "行有错误," + error);
        } else {
            String o_Error = this.errorList.get(row);
            o_Error += "," + error;
            this.errorList.put(row, o_Error);
        }
    }
}
