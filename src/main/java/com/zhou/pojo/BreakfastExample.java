package com.zhou.pojo;

import java.util.ArrayList;
import java.util.List;

public class BreakfastExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BreakfastExample() {
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

        public Criteria andHotelIdIsNull() {
            addCriterion("hotel_id is null");
            return (Criteria) this;
        }

        public Criteria andHotelIdIsNotNull() {
            addCriterion("hotel_id is not null");
            return (Criteria) this;
        }

        public Criteria andHotelIdEqualTo(Integer value) {
            addCriterion("hotel_id =", value, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdNotEqualTo(Integer value) {
            addCriterion("hotel_id <>", value, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdGreaterThan(Integer value) {
            addCriterion("hotel_id >", value, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("hotel_id >=", value, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdLessThan(Integer value) {
            addCriterion("hotel_id <", value, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdLessThanOrEqualTo(Integer value) {
            addCriterion("hotel_id <=", value, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdIn(List<Integer> values) {
            addCriterion("hotel_id in", values, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdNotIn(List<Integer> values) {
            addCriterion("hotel_id not in", values, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdBetween(Integer value1, Integer value2) {
            addCriterion("hotel_id between", value1, value2, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdNotBetween(Integer value1, Integer value2) {
            addCriterion("hotel_id not between", value1, value2, "hotelId");
            return (Criteria) this;
        }

        public Criteria andImgUrl1IsNull() {
            addCriterion("img_url1 is null");
            return (Criteria) this;
        }

        public Criteria andImgUrl1IsNotNull() {
            addCriterion("img_url1 is not null");
            return (Criteria) this;
        }

        public Criteria andImgUrl1EqualTo(String value) {
            addCriterion("img_url1 =", value, "imgUrl1");
            return (Criteria) this;
        }

        public Criteria andImgUrl1NotEqualTo(String value) {
            addCriterion("img_url1 <>", value, "imgUrl1");
            return (Criteria) this;
        }

        public Criteria andImgUrl1GreaterThan(String value) {
            addCriterion("img_url1 >", value, "imgUrl1");
            return (Criteria) this;
        }

        public Criteria andImgUrl1GreaterThanOrEqualTo(String value) {
            addCriterion("img_url1 >=", value, "imgUrl1");
            return (Criteria) this;
        }

        public Criteria andImgUrl1LessThan(String value) {
            addCriterion("img_url1 <", value, "imgUrl1");
            return (Criteria) this;
        }

        public Criteria andImgUrl1LessThanOrEqualTo(String value) {
            addCriterion("img_url1 <=", value, "imgUrl1");
            return (Criteria) this;
        }

        public Criteria andImgUrl1Like(String value) {
            addCriterion("img_url1 like", value, "imgUrl1");
            return (Criteria) this;
        }

        public Criteria andImgUrl1NotLike(String value) {
            addCriterion("img_url1 not like", value, "imgUrl1");
            return (Criteria) this;
        }

        public Criteria andImgUrl1In(List<String> values) {
            addCriterion("img_url1 in", values, "imgUrl1");
            return (Criteria) this;
        }

        public Criteria andImgUrl1NotIn(List<String> values) {
            addCriterion("img_url1 not in", values, "imgUrl1");
            return (Criteria) this;
        }

        public Criteria andImgUrl1Between(String value1, String value2) {
            addCriterion("img_url1 between", value1, value2, "imgUrl1");
            return (Criteria) this;
        }

        public Criteria andImgUrl1NotBetween(String value1, String value2) {
            addCriterion("img_url1 not between", value1, value2, "imgUrl1");
            return (Criteria) this;
        }

        public Criteria andImgUrl2IsNull() {
            addCriterion("img_url2 is null");
            return (Criteria) this;
        }

        public Criteria andImgUrl2IsNotNull() {
            addCriterion("img_url2 is not null");
            return (Criteria) this;
        }

        public Criteria andImgUrl2EqualTo(String value) {
            addCriterion("img_url2 =", value, "imgUrl2");
            return (Criteria) this;
        }

        public Criteria andImgUrl2NotEqualTo(String value) {
            addCriterion("img_url2 <>", value, "imgUrl2");
            return (Criteria) this;
        }

        public Criteria andImgUrl2GreaterThan(String value) {
            addCriterion("img_url2 >", value, "imgUrl2");
            return (Criteria) this;
        }

        public Criteria andImgUrl2GreaterThanOrEqualTo(String value) {
            addCriterion("img_url2 >=", value, "imgUrl2");
            return (Criteria) this;
        }

        public Criteria andImgUrl2LessThan(String value) {
            addCriterion("img_url2 <", value, "imgUrl2");
            return (Criteria) this;
        }

        public Criteria andImgUrl2LessThanOrEqualTo(String value) {
            addCriterion("img_url2 <=", value, "imgUrl2");
            return (Criteria) this;
        }

        public Criteria andImgUrl2Like(String value) {
            addCriterion("img_url2 like", value, "imgUrl2");
            return (Criteria) this;
        }

        public Criteria andImgUrl2NotLike(String value) {
            addCriterion("img_url2 not like", value, "imgUrl2");
            return (Criteria) this;
        }

        public Criteria andImgUrl2In(List<String> values) {
            addCriterion("img_url2 in", values, "imgUrl2");
            return (Criteria) this;
        }

        public Criteria andImgUrl2NotIn(List<String> values) {
            addCriterion("img_url2 not in", values, "imgUrl2");
            return (Criteria) this;
        }

        public Criteria andImgUrl2Between(String value1, String value2) {
            addCriterion("img_url2 between", value1, value2, "imgUrl2");
            return (Criteria) this;
        }

        public Criteria andImgUrl2NotBetween(String value1, String value2) {
            addCriterion("img_url2 not between", value1, value2, "imgUrl2");
            return (Criteria) this;
        }

        public Criteria andImgUrl3IsNull() {
            addCriterion("img_url3 is null");
            return (Criteria) this;
        }

        public Criteria andImgUrl3IsNotNull() {
            addCriterion("img_url3 is not null");
            return (Criteria) this;
        }

        public Criteria andImgUrl3EqualTo(String value) {
            addCriterion("img_url3 =", value, "imgUrl3");
            return (Criteria) this;
        }

        public Criteria andImgUrl3NotEqualTo(String value) {
            addCriterion("img_url3 <>", value, "imgUrl3");
            return (Criteria) this;
        }

        public Criteria andImgUrl3GreaterThan(String value) {
            addCriterion("img_url3 >", value, "imgUrl3");
            return (Criteria) this;
        }

        public Criteria andImgUrl3GreaterThanOrEqualTo(String value) {
            addCriterion("img_url3 >=", value, "imgUrl3");
            return (Criteria) this;
        }

        public Criteria andImgUrl3LessThan(String value) {
            addCriterion("img_url3 <", value, "imgUrl3");
            return (Criteria) this;
        }

        public Criteria andImgUrl3LessThanOrEqualTo(String value) {
            addCriterion("img_url3 <=", value, "imgUrl3");
            return (Criteria) this;
        }

        public Criteria andImgUrl3Like(String value) {
            addCriterion("img_url3 like", value, "imgUrl3");
            return (Criteria) this;
        }

        public Criteria andImgUrl3NotLike(String value) {
            addCriterion("img_url3 not like", value, "imgUrl3");
            return (Criteria) this;
        }

        public Criteria andImgUrl3In(List<String> values) {
            addCriterion("img_url3 in", values, "imgUrl3");
            return (Criteria) this;
        }

        public Criteria andImgUrl3NotIn(List<String> values) {
            addCriterion("img_url3 not in", values, "imgUrl3");
            return (Criteria) this;
        }

        public Criteria andImgUrl3Between(String value1, String value2) {
            addCriterion("img_url3 between", value1, value2, "imgUrl3");
            return (Criteria) this;
        }

        public Criteria andImgUrl3NotBetween(String value1, String value2) {
            addCriterion("img_url3 not between", value1, value2, "imgUrl3");
            return (Criteria) this;
        }

        public Criteria andImgUrl4IsNull() {
            addCriterion("img_url4 is null");
            return (Criteria) this;
        }

        public Criteria andImgUrl4IsNotNull() {
            addCriterion("img_url4 is not null");
            return (Criteria) this;
        }

        public Criteria andImgUrl4EqualTo(String value) {
            addCriterion("img_url4 =", value, "imgUrl4");
            return (Criteria) this;
        }

        public Criteria andImgUrl4NotEqualTo(String value) {
            addCriterion("img_url4 <>", value, "imgUrl4");
            return (Criteria) this;
        }

        public Criteria andImgUrl4GreaterThan(String value) {
            addCriterion("img_url4 >", value, "imgUrl4");
            return (Criteria) this;
        }

        public Criteria andImgUrl4GreaterThanOrEqualTo(String value) {
            addCriterion("img_url4 >=", value, "imgUrl4");
            return (Criteria) this;
        }

        public Criteria andImgUrl4LessThan(String value) {
            addCriterion("img_url4 <", value, "imgUrl4");
            return (Criteria) this;
        }

        public Criteria andImgUrl4LessThanOrEqualTo(String value) {
            addCriterion("img_url4 <=", value, "imgUrl4");
            return (Criteria) this;
        }

        public Criteria andImgUrl4Like(String value) {
            addCriterion("img_url4 like", value, "imgUrl4");
            return (Criteria) this;
        }

        public Criteria andImgUrl4NotLike(String value) {
            addCriterion("img_url4 not like", value, "imgUrl4");
            return (Criteria) this;
        }

        public Criteria andImgUrl4In(List<String> values) {
            addCriterion("img_url4 in", values, "imgUrl4");
            return (Criteria) this;
        }

        public Criteria andImgUrl4NotIn(List<String> values) {
            addCriterion("img_url4 not in", values, "imgUrl4");
            return (Criteria) this;
        }

        public Criteria andImgUrl4Between(String value1, String value2) {
            addCriterion("img_url4 between", value1, value2, "imgUrl4");
            return (Criteria) this;
        }

        public Criteria andImgUrl4NotBetween(String value1, String value2) {
            addCriterion("img_url4 not between", value1, value2, "imgUrl4");
            return (Criteria) this;
        }

        public Criteria andImgUrl5IsNull() {
            addCriterion("img_url5 is null");
            return (Criteria) this;
        }

        public Criteria andImgUrl5IsNotNull() {
            addCriterion("img_url5 is not null");
            return (Criteria) this;
        }

        public Criteria andImgUrl5EqualTo(String value) {
            addCriterion("img_url5 =", value, "imgUrl5");
            return (Criteria) this;
        }

        public Criteria andImgUrl5NotEqualTo(String value) {
            addCriterion("img_url5 <>", value, "imgUrl5");
            return (Criteria) this;
        }

        public Criteria andImgUrl5GreaterThan(String value) {
            addCriterion("img_url5 >", value, "imgUrl5");
            return (Criteria) this;
        }

        public Criteria andImgUrl5GreaterThanOrEqualTo(String value) {
            addCriterion("img_url5 >=", value, "imgUrl5");
            return (Criteria) this;
        }

        public Criteria andImgUrl5LessThan(String value) {
            addCriterion("img_url5 <", value, "imgUrl5");
            return (Criteria) this;
        }

        public Criteria andImgUrl5LessThanOrEqualTo(String value) {
            addCriterion("img_url5 <=", value, "imgUrl5");
            return (Criteria) this;
        }

        public Criteria andImgUrl5Like(String value) {
            addCriterion("img_url5 like", value, "imgUrl5");
            return (Criteria) this;
        }

        public Criteria andImgUrl5NotLike(String value) {
            addCriterion("img_url5 not like", value, "imgUrl5");
            return (Criteria) this;
        }

        public Criteria andImgUrl5In(List<String> values) {
            addCriterion("img_url5 in", values, "imgUrl5");
            return (Criteria) this;
        }

        public Criteria andImgUrl5NotIn(List<String> values) {
            addCriterion("img_url5 not in", values, "imgUrl5");
            return (Criteria) this;
        }

        public Criteria andImgUrl5Between(String value1, String value2) {
            addCriterion("img_url5 between", value1, value2, "imgUrl5");
            return (Criteria) this;
        }

        public Criteria andImgUrl5NotBetween(String value1, String value2) {
            addCriterion("img_url5 not between", value1, value2, "imgUrl5");
            return (Criteria) this;
        }

        public Criteria andImgUrl6IsNull() {
            addCriterion("img_url6 is null");
            return (Criteria) this;
        }

        public Criteria andImgUrl6IsNotNull() {
            addCriterion("img_url6 is not null");
            return (Criteria) this;
        }

        public Criteria andImgUrl6EqualTo(String value) {
            addCriterion("img_url6 =", value, "imgUrl6");
            return (Criteria) this;
        }

        public Criteria andImgUrl6NotEqualTo(String value) {
            addCriterion("img_url6 <>", value, "imgUrl6");
            return (Criteria) this;
        }

        public Criteria andImgUrl6GreaterThan(String value) {
            addCriterion("img_url6 >", value, "imgUrl6");
            return (Criteria) this;
        }

        public Criteria andImgUrl6GreaterThanOrEqualTo(String value) {
            addCriterion("img_url6 >=", value, "imgUrl6");
            return (Criteria) this;
        }

        public Criteria andImgUrl6LessThan(String value) {
            addCriterion("img_url6 <", value, "imgUrl6");
            return (Criteria) this;
        }

        public Criteria andImgUrl6LessThanOrEqualTo(String value) {
            addCriterion("img_url6 <=", value, "imgUrl6");
            return (Criteria) this;
        }

        public Criteria andImgUrl6Like(String value) {
            addCriterion("img_url6 like", value, "imgUrl6");
            return (Criteria) this;
        }

        public Criteria andImgUrl6NotLike(String value) {
            addCriterion("img_url6 not like", value, "imgUrl6");
            return (Criteria) this;
        }

        public Criteria andImgUrl6In(List<String> values) {
            addCriterion("img_url6 in", values, "imgUrl6");
            return (Criteria) this;
        }

        public Criteria andImgUrl6NotIn(List<String> values) {
            addCriterion("img_url6 not in", values, "imgUrl6");
            return (Criteria) this;
        }

        public Criteria andImgUrl6Between(String value1, String value2) {
            addCriterion("img_url6 between", value1, value2, "imgUrl6");
            return (Criteria) this;
        }

        public Criteria andImgUrl6NotBetween(String value1, String value2) {
            addCriterion("img_url6 not between", value1, value2, "imgUrl6");
            return (Criteria) this;
        }

        public Criteria andImgUrl7IsNull() {
            addCriterion("img_url7 is null");
            return (Criteria) this;
        }

        public Criteria andImgUrl7IsNotNull() {
            addCriterion("img_url7 is not null");
            return (Criteria) this;
        }

        public Criteria andImgUrl7EqualTo(String value) {
            addCriterion("img_url7 =", value, "imgUrl7");
            return (Criteria) this;
        }

        public Criteria andImgUrl7NotEqualTo(String value) {
            addCriterion("img_url7 <>", value, "imgUrl7");
            return (Criteria) this;
        }

        public Criteria andImgUrl7GreaterThan(String value) {
            addCriterion("img_url7 >", value, "imgUrl7");
            return (Criteria) this;
        }

        public Criteria andImgUrl7GreaterThanOrEqualTo(String value) {
            addCriterion("img_url7 >=", value, "imgUrl7");
            return (Criteria) this;
        }

        public Criteria andImgUrl7LessThan(String value) {
            addCriterion("img_url7 <", value, "imgUrl7");
            return (Criteria) this;
        }

        public Criteria andImgUrl7LessThanOrEqualTo(String value) {
            addCriterion("img_url7 <=", value, "imgUrl7");
            return (Criteria) this;
        }

        public Criteria andImgUrl7Like(String value) {
            addCriterion("img_url7 like", value, "imgUrl7");
            return (Criteria) this;
        }

        public Criteria andImgUrl7NotLike(String value) {
            addCriterion("img_url7 not like", value, "imgUrl7");
            return (Criteria) this;
        }

        public Criteria andImgUrl7In(List<String> values) {
            addCriterion("img_url7 in", values, "imgUrl7");
            return (Criteria) this;
        }

        public Criteria andImgUrl7NotIn(List<String> values) {
            addCriterion("img_url7 not in", values, "imgUrl7");
            return (Criteria) this;
        }

        public Criteria andImgUrl7Between(String value1, String value2) {
            addCriterion("img_url7 between", value1, value2, "imgUrl7");
            return (Criteria) this;
        }

        public Criteria andImgUrl7NotBetween(String value1, String value2) {
            addCriterion("img_url7 not between", value1, value2, "imgUrl7");
            return (Criteria) this;
        }

        public Criteria andImgUrl8IsNull() {
            addCriterion("img_url8 is null");
            return (Criteria) this;
        }

        public Criteria andImgUrl8IsNotNull() {
            addCriterion("img_url8 is not null");
            return (Criteria) this;
        }

        public Criteria andImgUrl8EqualTo(String value) {
            addCriterion("img_url8 =", value, "imgUrl8");
            return (Criteria) this;
        }

        public Criteria andImgUrl8NotEqualTo(String value) {
            addCriterion("img_url8 <>", value, "imgUrl8");
            return (Criteria) this;
        }

        public Criteria andImgUrl8GreaterThan(String value) {
            addCriterion("img_url8 >", value, "imgUrl8");
            return (Criteria) this;
        }

        public Criteria andImgUrl8GreaterThanOrEqualTo(String value) {
            addCriterion("img_url8 >=", value, "imgUrl8");
            return (Criteria) this;
        }

        public Criteria andImgUrl8LessThan(String value) {
            addCriterion("img_url8 <", value, "imgUrl8");
            return (Criteria) this;
        }

        public Criteria andImgUrl8LessThanOrEqualTo(String value) {
            addCriterion("img_url8 <=", value, "imgUrl8");
            return (Criteria) this;
        }

        public Criteria andImgUrl8Like(String value) {
            addCriterion("img_url8 like", value, "imgUrl8");
            return (Criteria) this;
        }

        public Criteria andImgUrl8NotLike(String value) {
            addCriterion("img_url8 not like", value, "imgUrl8");
            return (Criteria) this;
        }

        public Criteria andImgUrl8In(List<String> values) {
            addCriterion("img_url8 in", values, "imgUrl8");
            return (Criteria) this;
        }

        public Criteria andImgUrl8NotIn(List<String> values) {
            addCriterion("img_url8 not in", values, "imgUrl8");
            return (Criteria) this;
        }

        public Criteria andImgUrl8Between(String value1, String value2) {
            addCriterion("img_url8 between", value1, value2, "imgUrl8");
            return (Criteria) this;
        }

        public Criteria andImgUrl8NotBetween(String value1, String value2) {
            addCriterion("img_url8 not between", value1, value2, "imgUrl8");
            return (Criteria) this;
        }

        public Criteria andImgUrl9IsNull() {
            addCriterion("img_url9 is null");
            return (Criteria) this;
        }

        public Criteria andImgUrl9IsNotNull() {
            addCriterion("img_url9 is not null");
            return (Criteria) this;
        }

        public Criteria andImgUrl9EqualTo(String value) {
            addCriterion("img_url9 =", value, "imgUrl9");
            return (Criteria) this;
        }

        public Criteria andImgUrl9NotEqualTo(String value) {
            addCriterion("img_url9 <>", value, "imgUrl9");
            return (Criteria) this;
        }

        public Criteria andImgUrl9GreaterThan(String value) {
            addCriterion("img_url9 >", value, "imgUrl9");
            return (Criteria) this;
        }

        public Criteria andImgUrl9GreaterThanOrEqualTo(String value) {
            addCriterion("img_url9 >=", value, "imgUrl9");
            return (Criteria) this;
        }

        public Criteria andImgUrl9LessThan(String value) {
            addCriterion("img_url9 <", value, "imgUrl9");
            return (Criteria) this;
        }

        public Criteria andImgUrl9LessThanOrEqualTo(String value) {
            addCriterion("img_url9 <=", value, "imgUrl9");
            return (Criteria) this;
        }

        public Criteria andImgUrl9Like(String value) {
            addCriterion("img_url9 like", value, "imgUrl9");
            return (Criteria) this;
        }

        public Criteria andImgUrl9NotLike(String value) {
            addCriterion("img_url9 not like", value, "imgUrl9");
            return (Criteria) this;
        }

        public Criteria andImgUrl9In(List<String> values) {
            addCriterion("img_url9 in", values, "imgUrl9");
            return (Criteria) this;
        }

        public Criteria andImgUrl9NotIn(List<String> values) {
            addCriterion("img_url9 not in", values, "imgUrl9");
            return (Criteria) this;
        }

        public Criteria andImgUrl9Between(String value1, String value2) {
            addCriterion("img_url9 between", value1, value2, "imgUrl9");
            return (Criteria) this;
        }

        public Criteria andImgUrl9NotBetween(String value1, String value2) {
            addCriterion("img_url9 not between", value1, value2, "imgUrl9");
            return (Criteria) this;
        }

        public Criteria andImgUrl10IsNull() {
            addCriterion("img_url10 is null");
            return (Criteria) this;
        }

        public Criteria andImgUrl10IsNotNull() {
            addCriterion("img_url10 is not null");
            return (Criteria) this;
        }

        public Criteria andImgUrl10EqualTo(String value) {
            addCriterion("img_url10 =", value, "imgUrl10");
            return (Criteria) this;
        }

        public Criteria andImgUrl10NotEqualTo(String value) {
            addCriterion("img_url10 <>", value, "imgUrl10");
            return (Criteria) this;
        }

        public Criteria andImgUrl10GreaterThan(String value) {
            addCriterion("img_url10 >", value, "imgUrl10");
            return (Criteria) this;
        }

        public Criteria andImgUrl10GreaterThanOrEqualTo(String value) {
            addCriterion("img_url10 >=", value, "imgUrl10");
            return (Criteria) this;
        }

        public Criteria andImgUrl10LessThan(String value) {
            addCriterion("img_url10 <", value, "imgUrl10");
            return (Criteria) this;
        }

        public Criteria andImgUrl10LessThanOrEqualTo(String value) {
            addCriterion("img_url10 <=", value, "imgUrl10");
            return (Criteria) this;
        }

        public Criteria andImgUrl10Like(String value) {
            addCriterion("img_url10 like", value, "imgUrl10");
            return (Criteria) this;
        }

        public Criteria andImgUrl10NotLike(String value) {
            addCriterion("img_url10 not like", value, "imgUrl10");
            return (Criteria) this;
        }

        public Criteria andImgUrl10In(List<String> values) {
            addCriterion("img_url10 in", values, "imgUrl10");
            return (Criteria) this;
        }

        public Criteria andImgUrl10NotIn(List<String> values) {
            addCriterion("img_url10 not in", values, "imgUrl10");
            return (Criteria) this;
        }

        public Criteria andImgUrl10Between(String value1, String value2) {
            addCriterion("img_url10 between", value1, value2, "imgUrl10");
            return (Criteria) this;
        }

        public Criteria andImgUrl10NotBetween(String value1, String value2) {
            addCriterion("img_url10 not between", value1, value2, "imgUrl10");
            return (Criteria) this;
        }

        public Criteria andImgUrl11IsNull() {
            addCriterion("img_url11 is null");
            return (Criteria) this;
        }

        public Criteria andImgUrl11IsNotNull() {
            addCriterion("img_url11 is not null");
            return (Criteria) this;
        }

        public Criteria andImgUrl11EqualTo(String value) {
            addCriterion("img_url11 =", value, "imgUrl11");
            return (Criteria) this;
        }

        public Criteria andImgUrl11NotEqualTo(String value) {
            addCriterion("img_url11 <>", value, "imgUrl11");
            return (Criteria) this;
        }

        public Criteria andImgUrl11GreaterThan(String value) {
            addCriterion("img_url11 >", value, "imgUrl11");
            return (Criteria) this;
        }

        public Criteria andImgUrl11GreaterThanOrEqualTo(String value) {
            addCriterion("img_url11 >=", value, "imgUrl11");
            return (Criteria) this;
        }

        public Criteria andImgUrl11LessThan(String value) {
            addCriterion("img_url11 <", value, "imgUrl11");
            return (Criteria) this;
        }

        public Criteria andImgUrl11LessThanOrEqualTo(String value) {
            addCriterion("img_url11 <=", value, "imgUrl11");
            return (Criteria) this;
        }

        public Criteria andImgUrl11Like(String value) {
            addCriterion("img_url11 like", value, "imgUrl11");
            return (Criteria) this;
        }

        public Criteria andImgUrl11NotLike(String value) {
            addCriterion("img_url11 not like", value, "imgUrl11");
            return (Criteria) this;
        }

        public Criteria andImgUrl11In(List<String> values) {
            addCriterion("img_url11 in", values, "imgUrl11");
            return (Criteria) this;
        }

        public Criteria andImgUrl11NotIn(List<String> values) {
            addCriterion("img_url11 not in", values, "imgUrl11");
            return (Criteria) this;
        }

        public Criteria andImgUrl11Between(String value1, String value2) {
            addCriterion("img_url11 between", value1, value2, "imgUrl11");
            return (Criteria) this;
        }

        public Criteria andImgUrl11NotBetween(String value1, String value2) {
            addCriterion("img_url11 not between", value1, value2, "imgUrl11");
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