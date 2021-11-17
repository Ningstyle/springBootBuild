package com.small.business.utils;

import cn.hutool.core.bean.BeanUtil;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CopyUtils {
        public CopyUtils() {
        }

        public static void copyProperties(Object source, Object target) {
            BeanUtil.copyProperties(source, target);
        }

        public static <T> T copyProperties(Object source, Class<T> clazz) {
            Object t = null;

            try {
                t = clazz.newInstance();
                BeanUtil.copyProperties(source, t);
            } catch (InstantiationException var4) {
                var4.printStackTrace();
            } catch (IllegalAccessException var5) {
                var5.printStackTrace();
            }

            return (T)t;
        }

        public static <T> List<T> listCopyProperties(List<Object> sourceList, Class<T> clazz) {
            if (sourceList != null && sourceList.size() != 0) {
                List<T> list = new ArrayList();
                BeanCopier bc = BeanCopier.create(sourceList.get(0).getClass(), clazz, false);

                try {
                    Iterator var4 = sourceList.iterator();

                    while(var4.hasNext()) {
                        Object item = var4.next();
                        T t = clazz.newInstance();
                        bc.copy(item, t, (Converter)null);
                        list.add(t);
                    }
                } catch (InstantiationException var7) {
                    var7.printStackTrace();
                } catch (IllegalAccessException var8) {
                    var8.printStackTrace();
                }

                return list;
            } else {
                return null;
            }
        }

        public static <T> void arrayCopyProperties(Object[] sourceArray, T[] targetArray, Class<T> clazz) {
            if (sourceArray != null && sourceArray.length != 0) {
                int min = sourceArray.length > targetArray.length ? targetArray.length : sourceArray.length;
                BeanCopier bc = BeanCopier.create(sourceArray[0].getClass(), clazz, false);

                try {
                    for(int i = 0; i < min; ++i) {
                        T t = clazz.newInstance();
                        bc.copy(sourceArray[i], t, (Converter)null);
                        targetArray[i] = t;
                    }
                } catch (InstantiationException var7) {
                    var7.printStackTrace();
                } catch (IllegalAccessException var8) {
                    var8.printStackTrace();
                }

            }
        }


}
