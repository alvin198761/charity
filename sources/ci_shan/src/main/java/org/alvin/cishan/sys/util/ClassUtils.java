package org.alvin.cishan.sys.util;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * 反射操作
 */
public class ClassUtils {

    public static void setFieldValueByFieldName(Object targetBean, String fieldName, Object value) {
        AccessController.doPrivileged(new PrivilegedAction<Object>() {
            @Override
            public Object run() {
                try {
                    Field itemField = targetBean.getClass().getDeclaredField(fieldName);
                    boolean itemAcc = itemField.isAccessible();
                    try {
                        itemField.setAccessible(true);
                        itemField.set(targetBean, value);
                    } finally {
                        itemField.setAccessible(itemAcc);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }

    /**
     * 从对象中获取一个值
     *
     * @param targetBean
     * @param columnName
     * @return
     */
    public static Object getFieldByName(Object targetBean, String columnName) {
        return AccessController.doPrivileged(new PrivilegedAction<Object>() {

            @Override
            public Object run() {
                try {
                    Field field = targetBean.getClass().getDeclaredField(columnName);
                    boolean acc = field.isAccessible();
                    try {
                        field.setAccessible(true);
                        return field.get(targetBean);
                    } finally {
                        field.setAccessible(acc);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }
}
