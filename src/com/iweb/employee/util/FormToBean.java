package com.iweb.employee.util;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

public class FormToBean {

    public static <T> T formToBean(Class<T> tClass, Map<String,String[]> params){
        T t  = null;
        try {
             t = tClass.newInstance();
            BeanUtils.populate(t,params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
}
