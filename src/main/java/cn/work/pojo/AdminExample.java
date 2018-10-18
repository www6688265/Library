package cn.work.pojo;

import java.util.ArrayList;
import java.util.List;

public class AdminExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AdminExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andAdmidIsNull() {
            addCriterion("AdmId is null");
            return (Criteria) this;
        }

        public Criteria andAdmidIsNotNull() {
            addCriterion("AdmId is not null");
            return (Criteria) this;
        }

        public Criteria andAdmidEqualTo(Integer value) {
            addCriterion("AdmId =", value, "admid");
            return (Criteria) this;
        }

        public Criteria andAdmidNotEqualTo(Integer value) {
            addCriterion("AdmId <>", value, "admid");
            return (Criteria) this;
        }

        public Criteria andAdmidGreaterThan(Integer value) {
            addCriterion("AdmId >", value, "admid");
            return (Criteria) this;
        }

        public Criteria andAdmidGreaterThanOrEqualTo(Integer value) {
            addCriterion("AdmId >=", value, "admid");
            return (Criteria) this;
        }

        public Criteria andAdmidLessThan(Integer value) {
            addCriterion("AdmId <", value, "admid");
            return (Criteria) this;
        }

        public Criteria andAdmidLessThanOrEqualTo(Integer value) {
            addCriterion("AdmId <=", value, "admid");
            return (Criteria) this;
        }

        public Criteria andAdmidIn(List<Integer> values) {
            addCriterion("AdmId in", values, "admid");
            return (Criteria) this;
        }

        public Criteria andAdmidNotIn(List<Integer> values) {
            addCriterion("AdmId not in", values, "admid");
            return (Criteria) this;
        }

        public Criteria andAdmidBetween(Integer value1, Integer value2) {
            addCriterion("AdmId between", value1, value2, "admid");
            return (Criteria) this;
        }

        public Criteria andAdmidNotBetween(Integer value1, Integer value2) {
            addCriterion("AdmId not between", value1, value2, "admid");
            return (Criteria) this;
        }

        public Criteria andIdcardIsNull() {
            addCriterion("IDcard is null");
            return (Criteria) this;
        }

        public Criteria andIdcardIsNotNull() {
            addCriterion("IDcard is not null");
            return (Criteria) this;
        }

        public Criteria andIdcardEqualTo(String value) {
            addCriterion("IDcard =", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotEqualTo(String value) {
            addCriterion("IDcard <>", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardGreaterThan(String value) {
            addCriterion("IDcard >", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardGreaterThanOrEqualTo(String value) {
            addCriterion("IDcard >=", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardLessThan(String value) {
            addCriterion("IDcard <", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardLessThanOrEqualTo(String value) {
            addCriterion("IDcard <=", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardLike(String value) {
            addCriterion("IDcard like", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotLike(String value) {
            addCriterion("IDcard not like", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardIn(List<String> values) {
            addCriterion("IDcard in", values, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotIn(List<String> values) {
            addCriterion("IDcard not in", values, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardBetween(String value1, String value2) {
            addCriterion("IDcard between", value1, value2, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotBetween(String value1, String value2) {
            addCriterion("IDcard not between", value1, value2, "idcard");
            return (Criteria) this;
        }

        public Criteria andAdmpasswordIsNull() {
            addCriterion("AdmPassword is null");
            return (Criteria) this;
        }

        public Criteria andAdmpasswordIsNotNull() {
            addCriterion("AdmPassword is not null");
            return (Criteria) this;
        }

        public Criteria andAdmpasswordEqualTo(String value) {
            addCriterion("AdmPassword =", value, "admpassword");
            return (Criteria) this;
        }

        public Criteria andAdmpasswordNotEqualTo(String value) {
            addCriterion("AdmPassword <>", value, "admpassword");
            return (Criteria) this;
        }

        public Criteria andAdmpasswordGreaterThan(String value) {
            addCriterion("AdmPassword >", value, "admpassword");
            return (Criteria) this;
        }

        public Criteria andAdmpasswordGreaterThanOrEqualTo(String value) {
            addCriterion("AdmPassword >=", value, "admpassword");
            return (Criteria) this;
        }

        public Criteria andAdmpasswordLessThan(String value) {
            addCriterion("AdmPassword <", value, "admpassword");
            return (Criteria) this;
        }

        public Criteria andAdmpasswordLessThanOrEqualTo(String value) {
            addCriterion("AdmPassword <=", value, "admpassword");
            return (Criteria) this;
        }

        public Criteria andAdmpasswordLike(String value) {
            addCriterion("AdmPassword like", value, "admpassword");
            return (Criteria) this;
        }

        public Criteria andAdmpasswordNotLike(String value) {
            addCriterion("AdmPassword not like", value, "admpassword");
            return (Criteria) this;
        }

        public Criteria andAdmpasswordIn(List<String> values) {
            addCriterion("AdmPassword in", values, "admpassword");
            return (Criteria) this;
        }

        public Criteria andAdmpasswordNotIn(List<String> values) {
            addCriterion("AdmPassword not in", values, "admpassword");
            return (Criteria) this;
        }

        public Criteria andAdmpasswordBetween(String value1, String value2) {
            addCriterion("AdmPassword between", value1, value2, "admpassword");
            return (Criteria) this;
        }

        public Criteria andAdmpasswordNotBetween(String value1, String value2) {
            addCriterion("AdmPassword not between", value1, value2, "admpassword");
            return (Criteria) this;
        }

        public Criteria andAdmrightIsNull() {
            addCriterion("AdmRight is null");
            return (Criteria) this;
        }

        public Criteria andAdmrightIsNotNull() {
            addCriterion("AdmRight is not null");
            return (Criteria) this;
        }

        public Criteria andAdmrightEqualTo(Integer value) {
            addCriterion("AdmRight =", value, "admright");
            return (Criteria) this;
        }

        public Criteria andAdmrightNotEqualTo(Integer value) {
            addCriterion("AdmRight <>", value, "admright");
            return (Criteria) this;
        }

        public Criteria andAdmrightGreaterThan(Integer value) {
            addCriterion("AdmRight >", value, "admright");
            return (Criteria) this;
        }

        public Criteria andAdmrightGreaterThanOrEqualTo(Integer value) {
            addCriterion("AdmRight >=", value, "admright");
            return (Criteria) this;
        }

        public Criteria andAdmrightLessThan(Integer value) {
            addCriterion("AdmRight <", value, "admright");
            return (Criteria) this;
        }

        public Criteria andAdmrightLessThanOrEqualTo(Integer value) {
            addCriterion("AdmRight <=", value, "admright");
            return (Criteria) this;
        }

        public Criteria andAdmrightIn(List<Integer> values) {
            addCriterion("AdmRight in", values, "admright");
            return (Criteria) this;
        }

        public Criteria andAdmrightNotIn(List<Integer> values) {
            addCriterion("AdmRight not in", values, "admright");
            return (Criteria) this;
        }

        public Criteria andAdmrightBetween(Integer value1, Integer value2) {
            addCriterion("AdmRight between", value1, value2, "admright");
            return (Criteria) this;
        }

        public Criteria andAdmrightNotBetween(Integer value1, Integer value2) {
            addCriterion("AdmRight not between", value1, value2, "admright");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}