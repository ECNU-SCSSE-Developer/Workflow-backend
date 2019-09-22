package com.scsse.workflow.util.MVCUtil;

import com.scsse.workflow.constant.PredicateType;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * @author Alfred Fu
 * Created on 2019/9/20 12:06 下午
 */
public class PredicateUtil<T> {

    private static final String TIME_PATTERN = "yyyy-MM-dd'T'hh:mm:ss z";
    private static final String FORMAT_TIME_PATTERN = "yyyy-MM-dd hh:mm:ss";

    private CriteriaBuilder criteriaBuilder;
    private Root<T> root;

    public PredicateUtil(CriteriaBuilder criteriaBuilder, Root<T> root) {
        this.criteriaBuilder = criteriaBuilder;
        this.root = root;
    }

    public static String transferTimeFromPSTToGMT(String localTimeString) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(FORMAT_TIME_PATTERN);
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            return dateFormat.format(
                    new SimpleDateFormat(TIME_PATTERN).parse(localTimeString + " GMT+08:00")
            );
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Predicate generatePredicate(int predicateType, String key, String value) {
        switch (predicateType) {
            case PredicateType.EQUAL:
                return criteriaBuilder.equal(root.get(key).as(String.class), value);
            case PredicateType.NOT_EQUAL:
                return (criteriaBuilder.notEqual(root.get(key).as(String.class), value));
            case PredicateType.LIKE:
                return criteriaBuilder.like(root.get(key).as(String.class), '%' + value + '%');
            case PredicateType.LEXI_GREATER_THAN:
                return criteriaBuilder.greaterThan(root.get(key).as(String.class), value);
            case PredicateType.LEXI_LESS_THAN:
                return criteriaBuilder.lessThan(root.get(key).as(String.class), value);
            case PredicateType.TIME_COMPARE:
                return criteriaBuilder.lessThan(root.get(key).as(String.class), transferTimeFromPSTToGMT(value));
            default:
                throw new IllegalStateException("Unexpected value: " + predicateType);
        }
    }
}
