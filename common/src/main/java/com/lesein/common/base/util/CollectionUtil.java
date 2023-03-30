package com.lesein.common.base.util;

import java.util.Collection;

/**
 * @author WangJie
 * @date 2023/3/29
 */
public class CollectionUtil {

    /**
     * 判断集合是否不为空
     * @param coll
     * @return
     */
    public static boolean isNotEmpty(Collection<?> coll) {
        return !isEmpty(coll);
    }

    /**
     * 判断集合是否为空
     * @param coll
     * @return
     */
    public static boolean isEmpty(Collection<?> coll) {
        return coll == null || coll.isEmpty();
    }
}
