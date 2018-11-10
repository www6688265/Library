package cn.work.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BorrowedbooksExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BorrowedbooksExample() {
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

        public Criteria andOrderidIsNull() {
            addCriterion("orderid is null");
            return (Criteria) this;
        }

        public Criteria andOrderidIsNotNull() {
            addCriterion("orderid is not null");
            return (Criteria) this;
        }

        public Criteria andOrderidEqualTo(Integer value) {
            addCriterion("orderid =", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotEqualTo(Integer value) {
            addCriterion("orderid <>", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidGreaterThan(Integer value) {
            addCriterion("orderid >", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidGreaterThanOrEqualTo(Integer value) {
            addCriterion("orderid >=", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLessThan(Integer value) {
            addCriterion("orderid <", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLessThanOrEqualTo(Integer value) {
            addCriterion("orderid <=", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidIn(List<Integer> values) {
            addCriterion("orderid in", values, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotIn(List<Integer> values) {
            addCriterion("orderid not in", values, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidBetween(Integer value1, Integer value2) {
            addCriterion("orderid between", value1, value2, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotBetween(Integer value1, Integer value2) {
            addCriterion("orderid not between", value1, value2, "orderid");
            return (Criteria) this;
        }

        public Criteria andBookidIsNull() {
            addCriterion("bookid is null");
            return (Criteria) this;
        }

        public Criteria andBookidIsNotNull() {
            addCriterion("bookid is not null");
            return (Criteria) this;
        }

        public Criteria andBookidEqualTo(Integer value) {
            addCriterion("bookid =", value, "bookid");
            return (Criteria) this;
        }

        public Criteria andBookidNotEqualTo(Integer value) {
            addCriterion("bookid <>", value, "bookid");
            return (Criteria) this;
        }

        public Criteria andBookidGreaterThan(Integer value) {
            addCriterion("bookid >", value, "bookid");
            return (Criteria) this;
        }

        public Criteria andBookidGreaterThanOrEqualTo(Integer value) {
            addCriterion("bookid >=", value, "bookid");
            return (Criteria) this;
        }

        public Criteria andBookidLessThan(Integer value) {
            addCriterion("bookid <", value, "bookid");
            return (Criteria) this;
        }

        public Criteria andBookidLessThanOrEqualTo(Integer value) {
            addCriterion("bookid <=", value, "bookid");
            return (Criteria) this;
        }

        public Criteria andBookidIn(List<Integer> values) {
            addCriterion("bookid in", values, "bookid");
            return (Criteria) this;
        }

        public Criteria andBookidNotIn(List<Integer> values) {
            addCriterion("bookid not in", values, "bookid");
            return (Criteria) this;
        }

        public Criteria andBookidBetween(Integer value1, Integer value2) {
            addCriterion("bookid between", value1, value2, "bookid");
            return (Criteria) this;
        }

        public Criteria andBookidNotBetween(Integer value1, Integer value2) {
            addCriterion("bookid not between", value1, value2, "bookid");
            return (Criteria) this;
        }

        public Criteria andReturntimeIsNull() {
            addCriterion("ReturnTime is null");
            return (Criteria) this;
        }

        public Criteria andReturntimeIsNotNull() {
            addCriterion("ReturnTime is not null");
            return (Criteria) this;
        }

        public Criteria andReturntimeEqualTo(Date value) {
            addCriterion("ReturnTime =", value, "returntime");
            return (Criteria) this;
        }

        public Criteria andReturntimeNotEqualTo(Date value) {
            addCriterion("ReturnTime <>", value, "returntime");
            return (Criteria) this;
        }

        public Criteria andReturntimeGreaterThan(Date value) {
            addCriterion("ReturnTime >", value, "returntime");
            return (Criteria) this;
        }

        public Criteria andReturntimeGreaterThanOrEqualTo(Date value) {
            addCriterion("ReturnTime >=", value, "returntime");
            return (Criteria) this;
        }

        public Criteria andReturntimeLessThan(Date value) {
            addCriterion("ReturnTime <", value, "returntime");
            return (Criteria) this;
        }

        public Criteria andReturntimeLessThanOrEqualTo(Date value) {
            addCriterion("ReturnTime <=", value, "returntime");
            return (Criteria) this;
        }

        public Criteria andReturntimeIn(List<Date> values) {
            addCriterion("ReturnTime in", values, "returntime");
            return (Criteria) this;
        }

        public Criteria andReturntimeNotIn(List<Date> values) {
            addCriterion("ReturnTime not in", values, "returntime");
            return (Criteria) this;
        }

        public Criteria andReturntimeBetween(Date value1, Date value2) {
            addCriterion("ReturnTime between", value1, value2, "returntime");
            return (Criteria) this;
        }

        public Criteria andReturntimeNotBetween(Date value1, Date value2) {
            addCriterion("ReturnTime not between", value1, value2, "returntime");
            return (Criteria) this;
        }

        public Criteria andBorrowtimeIsNull() {
            addCriterion("BorrowTime is null");
            return (Criteria) this;
        }

        public Criteria andBorrowtimeIsNotNull() {
            addCriterion("BorrowTime is not null");
            return (Criteria) this;
        }

        public Criteria andBorrowtimeEqualTo(Date value) {
            addCriterion("BorrowTime =", value, "borrowtime");
            return (Criteria) this;
        }

        public Criteria andBorrowtimeNotEqualTo(Date value) {
            addCriterion("BorrowTime <>", value, "borrowtime");
            return (Criteria) this;
        }

        public Criteria andBorrowtimeGreaterThan(Date value) {
            addCriterion("BorrowTime >", value, "borrowtime");
            return (Criteria) this;
        }

        public Criteria andBorrowtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("BorrowTime >=", value, "borrowtime");
            return (Criteria) this;
        }

        public Criteria andBorrowtimeLessThan(Date value) {
            addCriterion("BorrowTime <", value, "borrowtime");
            return (Criteria) this;
        }

        public Criteria andBorrowtimeLessThanOrEqualTo(Date value) {
            addCriterion("BorrowTime <=", value, "borrowtime");
            return (Criteria) this;
        }

        public Criteria andBorrowtimeIn(List<Date> values) {
            addCriterion("BorrowTime in", values, "borrowtime");
            return (Criteria) this;
        }

        public Criteria andBorrowtimeNotIn(List<Date> values) {
            addCriterion("BorrowTime not in", values, "borrowtime");
            return (Criteria) this;
        }

        public Criteria andBorrowtimeBetween(Date value1, Date value2) {
            addCriterion("BorrowTime between", value1, value2, "borrowtime");
            return (Criteria) this;
        }

        public Criteria andBorrowtimeNotBetween(Date value1, Date value2) {
            addCriterion("BorrowTime not between", value1, value2, "borrowtime");
            return (Criteria) this;
        }

        public Criteria andLimittimeIsNull() {
            addCriterion("LimitTime is null");
            return (Criteria) this;
        }

        public Criteria andLimittimeIsNotNull() {
            addCriterion("LimitTime is not null");
            return (Criteria) this;
        }

        public Criteria andLimittimeEqualTo(Date value) {
            addCriterion("LimitTime =", value, "limittime");
            return (Criteria) this;
        }

        public Criteria andLimittimeNotEqualTo(Date value) {
            addCriterion("LimitTime <>", value, "limittime");
            return (Criteria) this;
        }

        public Criteria andLimittimeGreaterThan(Date value) {
            addCriterion("LimitTime >", value, "limittime");
            return (Criteria) this;
        }

        public Criteria andLimittimeGreaterThanOrEqualTo(Date value) {
            addCriterion("LimitTime >=", value, "limittime");
            return (Criteria) this;
        }

        public Criteria andLimittimeLessThan(Date value) {
            addCriterion("LimitTime <", value, "limittime");
            return (Criteria) this;
        }

        public Criteria andLimittimeLessThanOrEqualTo(Date value) {
            addCriterion("LimitTime <=", value, "limittime");
            return (Criteria) this;
        }

        public Criteria andLimittimeIn(List<Date> values) {
            addCriterion("LimitTime in", values, "limittime");
            return (Criteria) this;
        }

        public Criteria andLimittimeNotIn(List<Date> values) {
            addCriterion("LimitTime not in", values, "limittime");
            return (Criteria) this;
        }

        public Criteria andLimittimeBetween(Date value1, Date value2) {
            addCriterion("LimitTime between", value1, value2, "limittime");
            return (Criteria) this;
        }

        public Criteria andLimittimeNotBetween(Date value1, Date value2) {
            addCriterion("LimitTime not between", value1, value2, "limittime");
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