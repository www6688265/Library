package cn.work.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Error {

    private Map<String, Object> fieldErrors;
    private List<Map> errors;
    private Map<String, String> error;

    public Error() {
        this.fieldErrors = new HashMap<>();
        this.errors = new ArrayList<>();
    }

    public void addError(String name, String msg) {
        this.error = new HashMap<>();
        error.put("name", name);
        error.put("status", msg);
        errors.add(error);
        fieldErrors.put("fieldErrors", errors);
    }

    public Map<String, Object> getFieldErrors() {
        return fieldErrors;
    }

    public int getCount() {
        return errors.size();
    }
}
