package org.alvin.cishan.sys.util;

import java.util.Collection;

/**
 * 集合工具类
 */
public class ListsUtils {

    public static boolean isNullOrEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNotNullOrEmpty(Collection<?> collection) {
        return collection != null && !collection.isEmpty();
    }
}
